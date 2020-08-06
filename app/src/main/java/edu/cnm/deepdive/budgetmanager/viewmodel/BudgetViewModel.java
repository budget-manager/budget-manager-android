package edu.cnm.deepdive.budgetmanager.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import edu.cnm.deepdive.budgetmanager.model.Budget;
import edu.cnm.deepdive.budgetmanager.service.BudgetRepository;
import edu.cnm.deepdive.budgetmanager.service.GoogleSignInService;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BudgetViewModel extends AndroidViewModel {

  private final BudgetRepository budgetRepository;
//  private final TransactionRepository transactionRepository;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;
  private final MutableLiveData<Budget> budget;
  private final MutableLiveData<List<Budget>> budgets;
  private final Map<Long, Budget> budgetMap;
  private final GoogleSignInService signInService;


  public BudgetViewModel(@NonNull Application application) {
    super(application);
    budgetRepository = new BudgetRepository(application);
//    transactionRepository = new TransactionRepository(application);
    budget = new MutableLiveData<>();
    budgets = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    budgetMap = new HashMap<>();
    signInService = GoogleSignInService.getInstance();
    refreshBudgets();
  }

  public LiveData<Budget> getBudget() {
    return budget;
  }

  public LiveData<List<Budget>> getBudgets() {
    return budgets;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public void refreshBudgets() {
    refreshAndExecute((account) ->
              budgetRepository.get(account.getIdToken())
                  .subscribe(
                      (budgets) -> {
                        this.budgets.postValue(budgets);
                        for (Budget budget : budgets) {
                          budgetMap.put(budget.getId(), budget);
                        }
                      },
                      throwable::postValue
                  )
    );
  }


  public void save(Budget budget) {
    refreshAndExecute((account) ->
        budgetRepository.save(account.getIdToken(), budget)
            .subscribe(
                (budgets) -> {
                  this.budget.postValue(budgets);
                  refreshBudgets();
                },
                throwable::postValue
            )
    );
  }

  public void remove(Budget budget) {
    refreshAndExecute((account) ->
        budgetRepository.remove(account.getIdToken(), budget)
            .subscribe(
                () -> {
                  this.budget.postValue(null);
                  refreshBudgets();
                },
                throwable::postValue
            )
    );
  }

  public void setBudgetId(Long id) {
    budget.setValue(budgetMap.get(id));
  }

  private void refreshAndExecute(AuthenticatedTask task) {
    throwable.postValue(null);
    signInService.refresh()
        .addOnSuccessListener((account) -> pending.add(task.execute(account)))
        .addOnFailureListener(throwable::postValue);
  }

  public interface AuthenticatedTask {

    Disposable execute(GoogleSignInAccount account);
  }
}
