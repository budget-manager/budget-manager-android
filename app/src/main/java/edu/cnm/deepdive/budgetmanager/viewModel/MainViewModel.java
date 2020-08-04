package edu.cnm.deepdive.budgetmanager.viewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.budgetmanager.model.Budget;
import edu.cnm.deepdive.budgetmanager.service.BudgetRepository;
import edu.cnm.deepdive.budgetmanager.service.GoogleSignInService;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import java.sql.SQLTransactionRollbackException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MainViewModel extends AndroidViewModel {

  private final BudgetRepository budgetRepository;
//  private final TransactionRepository transactionRepository;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;
  private final MutableLiveData<Budget> budget;
  private final MutableLiveData<List<Budget>> budgets;
  private final Map<Long, Budget> budgetMap;


  public MainViewModel(@NonNull Application application) {
    super(application);
    budgetRepository = new BudgetRepository(application);
//    transactionRepository = new TransactionRepository(application);
    budget = new MutableLiveData<>();
    budgets = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    budgetMap = new HashMap<>();
  }

//  public LiveData<BudgetWithBudgetAmount> getBudget() {
//    return budgetRepository.getAll();
//  }

  public MutableLiveData<Budget> getBudget() {
    return budget;
  }

  public MutableLiveData<List<Budget>> getBudgets() {
    return budgets;
  }


  // TODO setup for MainActivity
//  public LiveData<Throwable> getThrowable() {
//    return throwable;
//  }

  public void refreshBudgets() {
    throwable.postValue(null);
    GoogleSignInService.getInstance().refresh()
        .addOnSuccessListener((account) -> {
          pending.add(
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
        })
        .addOnFailureListener(throwable::postValue);
  }


  public void save(Budget budget) {
    throwable.setValue(null);
    GoogleSignInService.getInstance().refresh()
        .addOnSuccessListener((account) -> {
          pending.add(
              budgetRepository.save(account.getIdToken(), budget)
                  .subscribe(
                      () -> {
                        this.budget.postValue(null);
                        refreshBudget();
                      },
                      throwable::postValue
                  )
          );
        })
        .addOnFailureListener(throwable::postValue);
  }

  public void remove(Budget budget) {
    throwable.setValue(null);
    GoogleSignInService.getInstance().refresh()
        .addOnSuccessListener((account) -> {
          pending.add(
              budgetRepository.remove(account.getIdToken(), budget)
                  .subscribe(
                      () -> {
                        this.budget.postValue(null);
                        refreshBudgets();
                      },
                      throwable::postValue
                  )
          );
        })
        .addOnFailureListener(throwable::postValue);
  }


  public void setBudgetId(UUID id) {
    budget.setValue(budgetMap.get(id));
  }


}
