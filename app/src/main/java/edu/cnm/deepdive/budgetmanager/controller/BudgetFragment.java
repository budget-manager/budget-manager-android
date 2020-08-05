package edu.cnm.deepdive.budgetmanager.controller;

import android.os.Bundle;
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
import edu.cnm.deepdive.budgetmanager.model.Budget;
import edu.cnm.deepdive.budgetmanager.R;
import edu.cnm.deepdive.budgetmanager.view.BudgetAdapter;
import edu.cnm.deepdive.budgetmanager.viewModel.MainViewModel;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BudgetFragment extends Fragment implements BudgetAdapter.OnClickListener {

  private MainViewModel mainViewModel;
  private RecyclerView budgetList;
  private List<Budget> budgets;
  private FloatingActionButton budgetFab;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_budget, container, false);
    budgetList = view.findViewById(R.id.budget_list);
    budgetFab = view.findViewById(R.id.budget_fab);
    budgetFab.setOnClickListener((v) -> editBudget(0));
    return view;
  }

  private void editBudget(int i) {
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mainViewModel = new ViewModelProvider(getActivity())
        .get(MainViewModel.class);
    mainViewModel.getBudgets().observe(getViewLifecycleOwner(), (budgets) -> {
      BudgetAdapter adapter =
          new BudgetAdapter(getContext(), budgets, this);
      budgetList.setAdapter(adapter);
    });
  }

  public void onClick(View view, int position, Budget budget) {
    editBudget(budget.getBudgetId());
  }


  private void editBudget(long budgetId) {
    BudgetEditFragment fragment = BudgetEditFragment.newInstance(budgetId);
    fragment.show(getChildFragmentManager(), fragment.getClass().getName());
  }

}
