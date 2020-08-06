package edu.cnm.deepdive.budgetmanager.controller;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import edu.cnm.deepdive.budgetmanager.R;
import edu.cnm.deepdive.budgetmanager.service.GoogleSignInService;
import edu.cnm.deepdive.budgetmanager.viewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {

   private GoogleSignInService signInService;
   private NavController navController;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    signInService = GoogleSignInService.getInstance();
    MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    viewModel.getThrowable().observe(this, (throwable) -> {
      if (throwable != null) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show();
      }
    });
    setUpNavigation();
  }

  private void switchToLogin() {
    NavOptions options = new NavOptions.Builder()
        .setPopUpTo(R.id.navigation, true)
        .build();
    navController.navigate(R.id.navigation_login, null, options);
  }



  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    getMenuInflater().inflate(R.menu.options, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    boolean handled = true;
    //noinspection SwitchStatementWithTooFewBranches
    switch (item.getItemId()) {
      case R.id.sign_out:
        signInService.signOut().addOnCompleteListener((ignored) -> switchToLogin());
        break;
      default:
        handled: super.onOptionsItemSelected(item);
    }
    return handled;
  }

  private void setUpNavigation() {
    navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    AppBarConfiguration config = new AppBarConfiguration.Builder(
        R.id.navigation_budget, R.id.navigation_create_transaction, R.id.navigation_home,
        R.id.navigation_login, R.id.navigation_transaction
    )
        .build();
    NavigationUI.setupActionBarWithNavController(this, navController, config);
  }
  // TODO implement when ViewModel has been created
//  private void setupViewModel() {
//    MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
//    getLifecycle().addObserver(viewModel);
//  }



}
