package group5.com.prm_autopartssale.models;

public class OrderItem {
  private String id;
  private double total_price;
  private String status;

  private int total_item;

  private String image_url;

  public OrderItem() {
  }

  public OrderItem(String id, double total_price, String status, int total_item,
      String image_url) {
    this.id = id;
    this.total_price = total_price;
    this.status = status;
    this.total_item = total_item;
    this.image_url = image_url;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public double getTotal_price() {
    return total_price;
  }

  public void setTotal_price(double total_price) {
    this.total_price = total_price;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getTotal_item() {
    return total_item;
  }

  public void setTotal_item(int total_item) {
    this.total_item = total_item;
  }

  public String getImage_url() {
    return image_url;
  }

  public void setImage_url(String image_url) {
    this.image_url = image_url;
  }
}
