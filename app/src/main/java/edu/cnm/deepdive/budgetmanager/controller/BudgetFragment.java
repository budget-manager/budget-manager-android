package edu.cnm.deepdive.budgetmanager.controller;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.budgetmanager.R;
import edu.cnm.deepdive.budgetmanager.view.BudgetAdapter;
import edu.cnm.deepdive.budgetmanager.viewModel.MainViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class BudgetFragment extends Fragment implements BudgetAdapter.OnClickListener {

  private MainViewModel mainViewModel;
  private RecyclerView budgetList;

  public BudgetFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_budget, container, false);
  }
}
