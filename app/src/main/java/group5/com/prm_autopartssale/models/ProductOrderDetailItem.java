package group5.com.prm_autopartssale.models;

public class ProductOrderDetailItem {
  private int id;
  private int product_id;
  private int order_id;
  private int quantity;
  private double total_price;
  private String product_name;
  private String product_image_url;


  public ProductOrderDetailItem(int id, int product_id, int order_id, int quantity,
      double total_price, String product_name, String product_image_url) {
    this.id = id;
    this.product_id = product_id;
    this.order_id = order_id;
    this.quantity = quantity;
    this.total_price = total_price;
    this.product_name = product_name;
    this.product_image_url = product_image_url;
  }


  public ProductOrderDetailItem() {
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

  public int getOrder_id() {
    return order_id;
  }

  public void setOrder_id(int order_id) {
    this.order_id = order_id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getTotal_price() {
    return total_price;
  }

  public void setTotal_price(double total_price) {
    this.total_price = total_price;
  }

  public String getProduct_name() {
    return product_name;
  }

  public void setProduct_name(String product_name) {
    this.product_name = product_name;
  }

  public String getProduct_image_url() {
    return product_image_url;
  }

  public void setProduct_image_url(String product_image_url) {
    this.product_image_url = product_image_url;
  }
}
