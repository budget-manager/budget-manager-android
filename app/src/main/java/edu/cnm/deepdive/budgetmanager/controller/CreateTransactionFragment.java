package edu.cnm.deepdive.budgetmanager.controller;

import android.net.Uri;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.budgetmanager.R;
import edu.cnm.deepdive.budgetmanager.model.Transaction;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateTransactionFragment extends Fragment {

  private long transactionId;
  private View view;
  private EditText transactionName;
  private EditText budgetCategory;
  private EditText transactionAmount;
  private EditText transactionDate;
  private EditText transactionNote;
  private BudgetViewModel viewModel;
  private Transaction transaction;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_create_transaction, container, false);
    Button cancelButton = view.findViewById(R.id.cancel_button);
    Button saveButton = view.findViewById(R.id.save_button);

    cancelButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Navigation.findNavController(view).navigate(R.id.create_transaction_to_home);
      }
    });

    saveButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Navigation.findNavController(view).navigate(R.id.create_transaction_to_budget);
      }
    });
    return view;
  }


  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {


  }

  public interface OnFragmentInteractionListenerHome {
    // TODO update argument type and name if there is time.
    void onFragmentInteractionHome(Uri uri);
  }

}
