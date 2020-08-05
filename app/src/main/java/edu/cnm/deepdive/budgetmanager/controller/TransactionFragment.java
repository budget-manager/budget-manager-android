package edu.cnm.deepdive.budgetmanager.controller;

import android.os.Bundle;
import android.text.Editable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import edu.cnm.deepdive.budgetmanager.R;
import edu.cnm.deepdive.budgetmanager.model.Transaction;
import edu.cnm.deepdive.budgetmanager.view.TransactionAdapter;
import edu.cnm.deepdive.budgetmanager.viewmodel.TransactionViewModel;

public class TransactionFragment extends Fragment {

  private TransactionViewModel viewModel;
  private RecyclerView transactionList;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_transaction, container, false);
    transactionList = view.findViewById(R.id.transaction_list);

    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(getActivity())
        .get(TransactionViewModel.class);
    viewModel.getTransactions().observe(getViewLifecycleOwner(), (transactions) -> {
      TransactionAdapter adapter =
          new TransactionAdapter(getContext(), transactions, this);
      transactionList.setAdapter(adapter);
    });

  }

}