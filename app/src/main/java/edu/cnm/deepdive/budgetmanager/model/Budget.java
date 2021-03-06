package edu.cnm.deepdive.budgetmanager.model;

import com.google.gson.annotations.Expose;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Budget implements Content {

  @Expose
    private Long id;

    @Expose
    private User user;

    @Expose
    private List<Transaction> transactions = new LinkedList<>();

    @Expose
    private String name;

    @Expose
    private long budgetedAmount;

    @Expose
    private LocalDate startDate;

    @Expose
    private LocalDate endDate;

    @Expose
    private String note;

    @Expose
    private double thresholdPercent;

    @Expose
    private boolean recurring;

    public Long getId() {
      return id;
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

    public LocalDate getStartDate() {
      return startDate;
    }

    public void setStartDate(LocalDate startDate) {
      this.startDate = startDate;
    }

    public LocalDate getEndDate() {
      return endDate;
    }

    public void setEndDate(LocalDate endDate) {
      this.endDate = endDate;
    }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
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

}
