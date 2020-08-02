package edu.cnm.deepdive.budgetmanager.model;

import com.google.gson.annotations.Expose;
import java.util.Date;

public class Transaction {

@Expose
  private Long id;

  @Expose
  private Budget budget;

  @Expose
  private Date date;

  @Expose
  private long amount;

  @Expose
  private String name;

  @Expose
  private String note;

  @Expose
  private Date created;

  public Long getId() {
    return id;
  }

  public Budget getBudget() {
    return budget;
  }

  public void setBudget(Budget budget) {
    this.budget = budget;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public Date getCreated() {
    return created;
  }


}