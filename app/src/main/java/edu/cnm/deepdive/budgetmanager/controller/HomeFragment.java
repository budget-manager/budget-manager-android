package edu.cnm.deepdive.budgetmanager.controller;

import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.budgetmanager.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

  public HomeFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_home, container, false);
    Button budgetButton = view.findViewById(R.id.budgets_button);
    Button createTransactionButton = view.findViewById(R.id.create_transaction_button);
    Button transactionButton = view.findViewById(R.id.transaction_button);

    budgetButton.setOnClickListener(
        (v) -> Navigation.findNavController(v).navigate(HomeFragmentDirections.homeToBudgets()));

    createTransactionButton.setOnClickListener(
        (v) -> Navigation.findNavController(v).navigate(HomeFragmentDirections.homeToCreateTransaction()));

    transactionButton.setOnClickListener(
        (v) -> Navigation.findNavController(v).navigate(HomeFragmentDirections.homeToTransaction()));
    return view;
  }

  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }
}
