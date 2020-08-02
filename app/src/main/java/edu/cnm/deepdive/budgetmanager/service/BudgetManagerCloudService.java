package edu.cnm.deepdive.budgetmanager.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.budgetmanager.BuildConfig;
import edu.cnm.deepdive.budgetmanager.model.Budget;
import io.reactivex.Completable;
import io.reactivex.Single;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BudgetManagerCloudService {

  @GET("budgets")
  Single<List<Budget>> get(@Header("Authorization") String authHeader);

  @GET("budgets/{id}")
  Single<Budget> get(@Header("Authorization") String authHeader, @Path("id") long id);

  @POST("budgets")
  Single<Budget> post(@Header("Authorization") String authHeader, @Body Budget budget);

  @PUT("budgets/{id}")
  Single<Budget> put(@Header("Authorization") String authHeader, @Path("id") long id, @Body Budget budget);

  @DELETE("budgets/{id}")
  Completable delete(@Header("Authorization") String authHeader, @Path("id") long id);

// TODO Add more method declarations as necessary.

  public static BudgetManagerCloudService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  class InstanceHolder {

    private static final BudgetManagerCloudService INSTANCE;

    static {
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .create();
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(BuildConfig.DEBUG ? Level.BODY : Level.NONE);
      OkHttpClient client = new OkHttpClient.Builder()
          .addInterceptor(interceptor)
          .build();
      Retrofit retrofit = new Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .client(client)
          .baseUrl(BuildConfig.BASE_URL)
          .build();
      INSTANCE = retrofit.create(BudgetManagerCloudService.class);

    }
  }

}
