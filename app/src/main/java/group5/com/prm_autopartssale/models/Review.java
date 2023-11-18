package group5.com.prm_autopartssale.models;

public class Review {
  private int id;
  private int product_id;
  private String customer_id;
  private int rating;
  private String comment;
  private String created_at;

  private String customer_name;



  public Review() {
  }

  public Review(int id, int product_id, String customer_id, int rating, String comment,
      String created_at, String customer_name) {
    this.id = id;
    this.product_id = product_id;
    this.customer_id = customer_id;
    this.rating = rating;
    this.comment = comment;
    this.created_at = created_at;
    this.customer_name = customer_name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getProduct_id() {
    return product_id;
  }

  public void setProduct_id(int product_id) {
    this.product_id = product_id;
  }

  public String getCustomer_id() {
    return customer_id;
  }

  public void setCustomer_id(String customer_id) {
    this.customer_id = customer_id;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getCreated_at() {
    return created_at;
  }

  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  public String getCustomer_name() {
    return customer_name;
  }

  public void setCustomer_name(String customer_name) {
    this.customer_name = customer_name;
  }
}
