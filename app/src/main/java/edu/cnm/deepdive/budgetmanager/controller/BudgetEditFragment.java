package edu.cnm.deepdive.budgetmanager.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.budgetmanager.R;
import edu.cnm.deepdive.budgetmanager.model.Budget;
import edu.cnm.deepdive.budgetmanager.viewModel.MainViewModel;

/**
 * A simple {@link Fragment} subclass. Use the {@link BudgetEditFragment#newInstance} factory method
 * to create an instance of this fragment.
 */
public class BudgetEditFragment extends DialogFragment {

  private static final String ID_KEY = "budget_id";

  private long budgetId;
  private View root;
  private EditText budgetText;
  private EditText budgetAmount;
  private AlertDialog dialog;
  private MainViewModel viewModel;
  private Budget budget;

  public BudgetEditFragment() {
    // Required empty public constructor
  }

  public static BudgetEditFragment newInstance(long budgetId) {
    BudgetEditFragment fragment = new BudgetEditFragment();
    Bundle args = new Bundle();
    args.putLong(ID_KEY, budgetId);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      budgetId = getArguments().getLong(ID_KEY);
    }
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    root = LayoutInflater.from(getContext()).inflate(R.layout.fragment_budget_edit, null, false);
    budgetText = root.findViewById(R.id.budget_text);
    budgetAmount = root.findViewById(R.id.budget_amount);
         budgetText.addTextChangedListener(this);
    dialog = new AlertDialog.Builder(getContext())
//        .setIcon(R.drawable.ic_message)
        .setTitle("Edit Budget")
        .setView(root)
        .setPositiveButton(android.R.string.ok, (dlg, which) -> save())
        .setNegativeButton(android.R.string.cancel, (dlg, which) -> {
        })
        .create();
    dialog.setOnShowListener((dlg) -> checkSubmitCondition());
    return dialog;
  }

  private void save() {
    budget.setName(budgetText.getText().toString().trim());
    String amount = budgetAmount.getText().toString().trim();
//    quote.setSourceId(null);
//    if (!name.isEmpty()) {
//      for (Source s : sources) {
//        if (name.equalsIgnoreCase(s.getName())) {
//          quote.setSourceId(s.getId());
//          break;
//        }
//      }
//    }
    viewModel.saveBudget(budget);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return root;
  }

  @SuppressWarnings("ConstantConditions")
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
    viewModel.getBudgets().observe(getViewLifecycleOwner(), (sources) -> {
//      this.sources = sources;
//      ArrayAdapter<Source> adapter =
//          new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, sources);
//      sourceName.setAdapter(adapter);
    });
    if (budgetId != 0) {
      viewModel.getBudget().observe(getViewLifecycleOwner(), (budget) -> {
        this.budget = budget;
        if (budget != null) {
          budgetText.setText(budget.getName());
          budgetAmount.setText(budget.getBudgetedAmount());
//              != null) ? quote.getSource().getName() : "");
        }
      });
      viewModel.setBudgetId(budgetId);
    } else {
      budget = new Budget();
    }
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {
  }

  @Override
  public void afterTextChanged(Editable s) {
    checkSubmitCondition();
  }

  private void checkSubmitCondition() {
    Button positive = dialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE);
    positive.setEnabled(!budgetText.getText().toString().trim().isEmpty());
  }

}