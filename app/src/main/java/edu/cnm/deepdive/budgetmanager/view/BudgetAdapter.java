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
import edu.cnm.deepdive.budgetmanager.view.BudgetAdapter.Holder;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

public class BudgetAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final List<Budget> budgets;
  private final OnClickListener clickListener;
  private final NumberFormat numberFormat;
  private final DateTimeFormatter dateFormatter;

  public BudgetAdapter(Context context, List<Budget> budgets, OnClickListener clickListener) {
    this.context = context;
    this.budgets = budgets;
    this.clickListener = clickListener;
    numberFormat = NumberFormat.getCurrencyInstance();
    dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
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

    private final TextView name;
    private final TextView budgetedAmount;
    private final TextView startDate;
    private final TextView endDate;


    public Holder(View itemView) {
      super(itemView);
      name = itemView.findViewById(R.id.name);
      budgetedAmount = itemView.findViewById(R.id.budgeted_amount);
      startDate = itemView.findViewById(R.id.start_date);
      endDate = itemView.findViewById(R.id.end_date);

    }

    private void bind(int position) {
      Budget item = budgets.get(position);
      name.setText(item.getName());
      budgetedAmount.setText(numberFormat.format(item.getBudgetedAmount() / 100D));
      startDate.setText(dateFormatter.format(item.getStartDate()));
      endDate.setText(dateFormatter.format(item.getEndDate()));
      itemView.setOnClickListener((v) -> clickListener.onClick(v, getAdapterPosition(), item));
    }

  }

  public interface OnClickListener {

    void onClick(View view, int position, Budget budget);
  }
}