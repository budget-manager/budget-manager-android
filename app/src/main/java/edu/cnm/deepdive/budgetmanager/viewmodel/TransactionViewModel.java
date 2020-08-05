package edu.cnm.deepdive.budgetmanager.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import edu.cnm.deepdive.budgetmanager.model.Budget;
import edu.cnm.deepdive.budgetmanager.model.Transaction;
import edu.cnm.deepdive.budgetmanager.service.BudgetRepository;
import edu.cnm.deepdive.budgetmanager.service.GoogleSignInService;
import edu.cnm.deepdive.budgetmanager.service.TransactionRepository;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionViewModel extends AndroidViewModel {

  private final TransactionRepository transactionRepository;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;
  private final MutableLiveData<Transaction> transaction;
  private final MutableLiveData<List<Transaction>> transactions;
  private final Map<Long, Transaction> transactionMap;
  private final GoogleSignInService signInService;


  public TransactionViewModel(@NonNull Application application) {
    super(application);
    transactionRepository = new TransactionRepository(application);
    transaction = new MutableLiveData<>();
    transactions = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    transactionMap = new HashMap<>();
    signInService = GoogleSignInService.getInstance();
  }

  public MutableLiveData<Transaction> getTransaction() {
    return transaction;
  }

  public MutableLiveData<List<Transaction>> getTransactions() {
    return transactions;
  }


  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public void refreshTransactions() {
    refreshAndExecute((account) ->
              transactionRepository.get(account.getIdToken())
                  .subscribe(
                      (transactions) -> {
                        this.transactions.postValue(transactions);
                        for (Transaction transaction : transactions) {
                          transactionMap.put(transaction.getId(), transaction);
                        }
                      },
                      throwable::postValue
                  )
    );
  }


  public void save(Transaction transaction) {
    refreshAndExecute((account) ->
        transactionRepository.save(account.getIdToken(), transaction)
            .subscribe(
                (budgets) -> {
                  this.transaction.postValue(transaction);
                  refreshTransactions();
                },
                throwable::postValue
            )
    );
  }

  public void remove(Transaction transaction) {
    refreshAndExecute((account) ->
        transactionRepository.remove(account.getIdToken(), transaction)
            .subscribe(
                () -> {
                  this.transaction.postValue(null);
                  refreshTransactions();
                },
                throwable::postValue
            )
    );
  }

  public void setId(Long id) {
    transaction.setValue(transactionMap.get(id));
  }

  private void refreshAndExecute(AuthenticatedTask task) {
    throwable.setValue(null);
    signInService.refresh()
        .addOnSuccessListener((account) -> pending.add(task.execute(account)))
        .addOnFailureListener(throwable::postValue);
  }

  public interface AuthenticatedTask {

    Disposable execute(GoogleSignInAccount account);
  }
}
