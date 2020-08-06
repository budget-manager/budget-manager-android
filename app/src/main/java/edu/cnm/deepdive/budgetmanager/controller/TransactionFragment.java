package edu.cnm.deepdive.budgetmanager.controller;

import android.os.Bundle;
import android.text.Editable;
import android.view.SurfaceControl;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import edu.cnm.deepdive.budgetmanager.R;
import edu.cnm.deepdive.budgetmanager.model.Transaction;
import edu.cnm.deepdive.budgetmanager.view.TransactionAdapter;
import edu.cnm.deepdive.budgetmanager.viewmodel.TransactionViewModel;
import java.util.List;

public class TransactionFragment extends Fragment implements TransactionAdapter.OnClickListener {

  private TransactionViewModel viewModel;
  private RecyclerView transactionList;
  private List<Transaction> transactions;
  private Button budgetButton;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_transaction, container, false);
    transactionList = view.findViewById(R.id.transaction_list);
    Button budgetButton = view.findViewById(R.id.budget_threshold_percent);

    budgetButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Navigation.findNavController(view).navigate(R.id.transaction_to_budget);
      }
    });
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(getActivity())
        .get(TransactionViewModel.class);
    viewModel.getTransactions().observe(getViewLifecycleOwner(), transactions -> {
      TransactionAdapter adapter =
          new TransactionAdapter(getContext(), transactions, this);
      transactionList.setAdapter(adapter);
    });



  }

  @Override
  public void onClick(View view, int position, Transaction transaction) {

  }
}