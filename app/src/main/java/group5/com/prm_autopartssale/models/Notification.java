package group5.com.prm_autopartssale.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Notification {
  private int id;
  private String customer_id;
  private String title;
  private String content;
  private String created_at;

  public Notification() {
  }


  public Notification(int id, String customer_id, String title, String content, String created_at) {
    this.id = id;
    this.customer_id = customer_id;
    this.title = title;
    this.content = content;
    this.created_at = created_at;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCustomer_id() {
    return customer_id;
  }

  public void setCustomer_id(String customer_id) {
    this.customer_id = customer_id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getCreated_at() {
    return created_at;
  }

  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }
}
