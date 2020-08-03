package edu.cnm.deepdive.budgetmanager.controller;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
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
public class BudgetFragment extends Fragment {

  private MainViewModel mainViewModel;
  private RecyclerView budgetList;
  private FloatingActionButton add;
  private List<Budget> budgets;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_budget, container, false);
    budgetList = view.findViewById(R.id.budget_list);
    add = view.findViewById(R.id.add);
    add.setOnClickListener((v) -> editBudget(0));
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

//  @Override
//  public void onClick(View view, int position, Budget budget) {
//    editBudget(budget.getId());
//  }


//  private void editBudget(long budgetId) {
//    BudgetEditFragment fragment = BudgetEditFragment.newInstance(budgetId);
//    fragment.show(getChildFragmentManager(), fragment.getClass().getName());
//  }

}
