package edu.cnm.deepdive.budgetmanager.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.budgetmanager.R;
import edu.cnm.deepdive.budgetmanager.model.Budget;
import java.util.List;

public class BudgetAdapter extends RecyclerView.Adapter<BudgetAdapter.Holder> {

  private final Context context;
  private final List<Budget> budgets;
  private final OnClickListener clickListener;

  public BudgetAdapter(Context context, List<Budget> budgets, OnClickListener clickListener) {
    this.context = context;
    this.budgets = budgets;
    this.clickListener = clickListener;
  }

  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_budget, parent, false);
    return new Holder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return budgets.size();
  }

      class Holder extends RecyclerView.ViewHolder {

    private final View itemView;
    private final TextView budgetCategory;
    private final TextView thresholdPercent;

    public Holder (View itemView) {
      super(itemView);
      this.itemView = itemView;
      thresholdPercent = itemView.findViewById(R.id.threshold_percent);
      budgetCategory = itemView.findViewById(R.id.budget);
    }

    private void bind(int position) {
      Budget item = budgets.get(position);
      budgetCategory.setText(item.getName());
      thresholdPercent.setText(item.getThresholdPercent());
      itemView.setOnClickListener((v) -> clickListener.onClick(v, getAdapterPosition(), item));
    }

    public interface OnClickListener {
      void onClick(View view, int position, Budget budget);
    }
      }
  }