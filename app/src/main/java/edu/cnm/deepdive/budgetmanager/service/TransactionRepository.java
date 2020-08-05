package edu.cnm.deepdive.budgetmanager.service;

import android.content.Context;
import edu.cnm.deepdive.budgetmanager.model.Budget;
import edu.cnm.deepdive.budgetmanager.model.Transaction;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransactionRepository {

  private static final int NETWORK_POOL_SIZE = 4;
  private static final String AUTH_HEADER_FORMAT = "Bearer %s";

  private final Context context;
  private final BudgetManagerCloudService cloudService;
  private final ExecutorService networkPool;

  public TransactionRepository(Context context) {
    this.context = context;
    cloudService = BudgetManagerCloudService.getInstance();
    networkPool = Executors.newFixedThreadPool(NETWORK_POOL_SIZE);
  }

  public Single<List<Transaction>> get(String idToken) {
    return cloudService.getAllTransactions(getHeader(idToken))
        .subscribeOn(Schedulers.from(networkPool));
  }

  public Single<Transaction> get(String idToken, long id) {
    return cloudService.getTransaction(getHeader(idToken), id)
        .subscribeOn(Schedulers.from(networkPool));
  }

  public Single<Transaction> save(String idToken, Transaction transaction) {
    Single<Transaction> task = (transaction.getId()==0)
        ? cloudService.postTransaction(getHeader(idToken), transaction)
        : cloudService.putTransaction(getHeader(idToken), transaction.getId(), transaction);
    return task
        .subscribeOn(Schedulers.from(networkPool));
  }

  public Completable remove(String token, Transaction transaction) {
    if (transaction.getId() != null) {
      return cloudService.deleteTransaction(String.format(AUTH_HEADER_FORMAT, token), transaction.getId())
          .subscribeOn(Schedulers.from(networkPool));
    } else {
      return Completable.complete();
    }
  }


  private String getHeader(String idToken) {
    return String.format(AUTH_HEADER_FORMAT, idToken);
  }


}
