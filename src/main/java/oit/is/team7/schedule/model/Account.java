package oit.is.team7.schedule.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "accountId")
  private int accountId;

  @Column(name = "userName")
  private String userName;

  @Column(name = "userPass")
  private String userPass;

  public int getId() {
    return accountId;
  }

  public void setId(int id) {
    this.accountId = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return userPass;
  }

  public void setPassword(String password) {
    this.userPass = password;
  }

}
