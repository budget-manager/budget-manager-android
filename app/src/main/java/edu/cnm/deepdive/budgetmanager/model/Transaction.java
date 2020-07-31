package edu.cnm.deepdive.budgetmanager.model;

import com.google.gson.annotations.Expose;
import java.util.Date;

public class Transaction {

  @Expose
  private Long id;

  @Expose
  private String user;

  @Expose
  private String name;

  @Expose
  private Long budgetedAmount;

  @Expose
  private Date startDate;

  @Expose
  private Date endDate;

  @Expose
  private double thresholdPercent;

  @Expose
  private boolean recurring;

  public Long getId() {
    return id;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
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
}