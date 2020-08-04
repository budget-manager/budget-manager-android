package edu.cnm.deepdive.budgetmanager;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.budgetmanager.service.GoogleSignInService;

public class BudgetManagerApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    GoogleSignInService.setContext(this);
    Stetho.initializeWithDefaults(this);
  }

}
