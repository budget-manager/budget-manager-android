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
import edu.cnm.deepdive.budgetmanager.viewmodel.TransactionViewModel;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateTransactionFragment extends Fragment {

  private long transactionId;
  private View view;
  private EditText name;
  private EditText budgetCategory;
  private EditText amount;
  private EditText date;
  private EditText note;
  private TransactionViewModel viewModel;
  private Transaction transaction;
  private NumberFormat numberFormat;



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
        Navigation.findNavController(view).navigate(R.id.create_transaction_to_transaction);
      }
    });
    return view;
  }


  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {


  }

  private void save() {
    try {
      transaction.setName(name.getText().toString().trim());
      String price = amount.getText().toString().trim();
      transaction.setAmount((long) (numberFormat.parse(String.valueOf(amount)).doubleValue() * 100));
//      transaction.setDate(LocalDateTime.ofInstant(date.toInstant(),
//          date.getTimeZone().toZoneId()).toLocalDate());
//      budget.setEndDate(LocalDateTime.ofInstant(end.toInstant(),
//          end.getTimeZone().toZoneId()).toLocalDate());
//      budget.setNote(note.getText().toString().trim());
      viewModel.save(transaction);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  public interface OnFragmentInteractionListenerHome {
    // TODO update argument type and name if there is time.
    void onFragmentInteractionHome(Uri uri);
  }

}
