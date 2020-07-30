package edu.cnm.deepdive.budgetmanager;

import android.app.Application;
import edu.cnm.deepdive.budgetmanager.service.GoogleSignInService;

public class BudgetManagerApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    GoogleSignInService.setContext(this);
  }

}
