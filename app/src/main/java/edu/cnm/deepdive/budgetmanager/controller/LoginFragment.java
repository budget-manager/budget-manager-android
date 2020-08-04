package edu.cnm.deepdive.budgetmanager.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.budgetmanager.R;
import edu.cnm.deepdive.budgetmanager.service.GoogleSignInService;


public class LoginFragment extends Fragment {

  private static final int LOGIN_REQUEST_CODE = 1000;
  private GoogleSignInService signInService;



  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    signInService = GoogleSignInService.getInstance();
    return inflater.inflate(R.layout.fragment_login, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    signInService.refresh()
        .addOnSuccessListener((account) -> {switchToMain();})
        .addOnFailureListener((throwable) -> {
          view.findViewById(R.id.sign_in).setOnClickListener((v) ->
              signInService.startSignIn(this, LOGIN_REQUEST_CODE));
        });

  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (requestCode == LOGIN_REQUEST_CODE) {
      signInService.completeSignIn(data)
          .addOnSuccessListener((account) -> {switchToMain();})
          .addOnFailureListener((throwable) ->
              Toast.makeText(getContext(), R.string.login_failure, Toast.LENGTH_LONG).show());
    } else {
      super.onActivityResult(requestCode, resultCode, data);
    }
  }

  private void switchToMain() {
    NavOptions options = new NavOptions.Builder()
        .setPopUpTo(R.id.navigation, true)
        .build();
    Navigation.findNavController(getView()).navigate(R.id.navigation_home, null, options);
  }

}
