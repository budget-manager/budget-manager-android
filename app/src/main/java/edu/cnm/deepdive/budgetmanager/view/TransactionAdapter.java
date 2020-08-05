package edu.cnm.deepdive.budgetmanager.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.SurfaceControl.Transaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.budgetmanager.R;
import edu.cnm.deepdive.budgetmanager.controller.TransactionFragment;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.Holder> {

  private final Context context;
  private final List<Transaction> transactions;
  private final OnClickListener clickListener;

  public TransactionAdapter(Context context, List<Transaction> transactions,
      OnClickListener clickListener) {
    super();
    this.context = context;
    this.transactions = transactions;
    this.clickListener = clickListener;
  }


  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_transaction, parent, false);
    return new Holder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return transactions.size();
  }

  class Holder extends RecyclerView.ViewHolder {

    private final View itemView;
    private final TextView transactionName;
    private final TextView amount;

    public Holder(@NonNull View itemView) {
      super(itemView);
      this.itemView = itemView;
      amount = itemView.findViewById(R.id.transaction_amount);
      transactionName = itemView.findViewById(R.id.transaction_name);
    }

    private void bind(int position) {
      Transaction item = transactions.get(position);
//      transactionName.setName(item.getName());
//      amount.setText(item.getAmount());
      // TODO Correct red lines.
      itemView.setOnClickListener((v) -> clickListener.onClick(v, getAdapterPosition(), item));
    }
  }
    public interface OnClickListener {
      void onClick(View view, int position, Transaction transaction);
    }

}