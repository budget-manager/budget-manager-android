package edu.cnm.deepdive.budgetmanager.controller;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.cnm.deepdive.budgetmanager.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionFragment extends Fragment {

  public TransactionFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.transaction_fragment, container, false);
  }
}
