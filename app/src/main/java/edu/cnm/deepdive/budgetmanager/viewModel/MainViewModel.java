package edu.cnm.deepdive.budgetmanager.viewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.budgetmanager.model.Budget;
import edu.cnm.deepdive.budgetmanager.service.BudgetRepository;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import java.sql.SQLTransactionRollbackException;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

  private final BudgetRepository budgetRepository;
//  private final TransactionRepository transactionRepository;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;
  private final MutableLiveData<Budget> budget;

  public MainViewModel(@NonNull Application application) {
    super(application);
    budgetRepository = new BudgetRepository(application);
//    transactionRepository = new TransactionRepository(application);
    budget = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
  }

//  public LiveData<BudgetWithBudgetAmount> getBudget() {
//    return budgetRepository.getAll();
//  }

  public LiveData<Budget> getBudget() {
    return budget;
  }

  public LiveData<List<Budget>> getBudgets() {
    return getBudgets();
  }

  // TODO setup for MainActivity
//  public LiveData<Throwable> getThrowable() {
//    return throwable;
//  }


}
