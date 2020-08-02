package edu.cnm.deepdive.budgetmanager.service;

import android.content.Context;
import edu.cnm.deepdive.budgetmanager.model.Budget;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BudgetRepository {

  private static final int NETWORK_POOL_SIZE = 4;
  private static final String AUTH_HEADER_FORMAT = "Bearer %s";

  private final Context context;
  private final BudgetManagerCloudService cloudService;
  private final ExecutorService networkPool;

  public BudgetRepository(Context context) {
    this.context = context;
    cloudService = BudgetManagerCloudService.getInstance();
    networkPool = Executors.newFixedThreadPool(NETWORK_POOL_SIZE);
  }

  public Single<List<Budget>> get(String idToken) {
    return cloudService.get(getHeader(idToken))
        .subscribeOn(Schedulers.from(networkPool));
  }

  public Single<Budget> get(String idToken, long id) {
    return cloudService.get(getHeader(idToken), id)
        .subscribeOn(Schedulers.from(networkPool));
  }

  public Single<Budget> save(String idToken, Budget budget) {
    Single<Budget> task = (budget.getId()==0)
        ? cloudService.post(getHeader(idToken), budget)
        : cloudService.put(getHeader(idToken), budget.getId(), budget);
    return task
        .subscribeOn(Schedulers.from(networkPool));
  }


  private String getHeader(String idToken) {
    return String.format(AUTH_HEADER_FORMAT, idToken);
  }

// TODO write methods that talk to a retrofit based service to communicate with back end.
}
