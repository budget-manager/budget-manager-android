package edu.cnm.deepdive.budgetmanager.model;

import androidx.annotation.NonNull;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.net.URL;
import java.util.Date;

public class User {

  @Expose
  private Long id;

  @Expose
  @SerializedName("displayName")
  private String name;

  @Expose
  private Role role;

  @Expose
  private URL href;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public URL getHref() {
    return href;
  }

  public void setHref(URL href) {
    this.href = href;
  }

  @NonNull
  @Override
  public String toString() {
    return name + ": " + role;
  }

  public enum Role {
    USER, ADMINISTRATOR
  }

}
