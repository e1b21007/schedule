package oit.is.team7.schedule.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

public class Users {
  private int userid;
  private String userName;
  private String userPass;

  public int getUserid() {
    return this.userid;
  }

  public void setUserid(int id) {
    this.userid = id;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String name) {
    this.userName = name;
  }

  public String getPassword() {
    return userPass;
  }

  public void setPassword(String userPass) {
    this.userPass = userPass;
  }
}
