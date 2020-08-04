package edu.cnm.deepdive.budgetmanager.model;

import com.google.gson.annotations.Expose;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Budget implements Content {

  @Expose
    private Long budgetId;

    @Expose
    private User user;

    @Expose
    private List<Transaction> transactions = new LinkedList<>();

    @Expose
    private String name;

    @Expose
    private long budgetedAmount;

    @Expose
    private Date startDate;

    @Expose
    private Date endDate;

    @Expose
    private double thresholdPercent;

    @Expose
    private boolean recurring;

    public Long getBudgetId() {
      return budgetId;
    }

    public User getUser() {
      return user;
    }

    public void setUser(User user) {
      this.user = user;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }


    public long getBudgetedAmount() {
      return budgetedAmount;
    }


    public void setBudgetedAmount(long budgetedAmount) {
      this.budgetedAmount = budgetedAmount;
    }

    public Date getStartDate() {
      return startDate;
    }

    public void setStartDate(Date startDate) {
      this.startDate = startDate;
    }

    public Date getEndDate() {
      return endDate;
    }

    public void setEndDate(Date endDate) {
      this.endDate = endDate;
    }

    public double getThresholdPercent() {
      return thresholdPercent;
    }

    public void setThresholdPercent(double thresholdPercent) {
      this.thresholdPercent = thresholdPercent;


    }

    public boolean isRecurring() { return recurring; }

    public void setRecurring(boolean recurring) {
      this.recurring = recurring;
    }

//    public boolean isEditableBy(User user) {
//      return user != null
//          && contributor != null
//          && user.getId().equals(contributor.getId());
//    }
}
