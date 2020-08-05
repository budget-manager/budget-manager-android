package edu.cnm.deepdive.budgetmanager.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * A simple {@link Fragment} subclass. Use the {@link BudgetEditFragment#newInstance} factory method
 * to create an instance of this fragment.
 */
public class BudgetEditFragment extends DialogFragment implements TextWatcher {

  private static final String ID_KEY = "budget_id";

  private long budgetId;
  private View root;
  private EditText budgetText;
  private EditText budgetAmount;
  private AlertDialog dialog;
  private MainViewModel viewModel;
  private Budget budget;
  private NumberFormat numberFormat;
  private DateFormat dateFormat;

  // TODO declare additional fields for all the view objects in the layout. 1 for each widget in fragment_budget.


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
    numberFormat = NumberFormat.getCurrencyInstance();
    dateFormat = android.text.format.DateFormat.getDateFormat(getContext());
    root = LayoutInflater.from(getContext()).inflate(R.layout.fragment_budget_edit, null, false);
    budgetText = root.findViewById(R.id.budget_text);
    budgetAmount = root.findViewById(R.id.budget_amount_input);
    // TODO get refrences to other view objects. find view by id to match fields.
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
    if (budgetId != 0) {
      viewModel.getBudget().observe(getViewLifecycleOwner(), (budget) -> {
        this.budget = budget;
        if (budget != null) {
          this.budget = budget;
          budgetText.setText(budget.getName());
          budgetAmount.setText(numberFormat.format(budget.getBudgetedAmount() / 100D));
          // TODO set values for rest of the view objects from fields of the budget objects.
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
    // TODO add logic to disable button with other non nullable values
  }

  private void save() {
    try {
      budget.setName(budgetText.getText().toString().trim());
      String amount = budgetAmount.getText().toString().trim();
      budget.setBudgetedAmount((long) (numberFormat.parse(amount).doubleValue() * 100));
      // TODO do the same thing as above with all the other fields
      viewModel.save(budget);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

}