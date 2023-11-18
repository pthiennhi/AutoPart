package group5.com.prm_autopartssale.models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
  private String customer_id;
  private int payment_id;
  private double total_price;
  private String address_name;
  private String address_details;
  private int province_id;
  private int district_id;
  private String ward_code;
  private String note;
  private String required_note;
  private String content;

  private List<OrderItem> items;

  public Cart() {
  }

  public Cart(String customer_id, int payment_id, double total_price, String address_name,
      String address_details, int province_id, int district_id, String ward_code, String note,
      String required_note, String content, List<OrderItem> items) {
    this.customer_id = customer_id;
    this.payment_id = payment_id;
    this.total_price = total_price;
    this.address_name = address_name;
    this.address_details = address_details;
    this.province_id = province_id;
    this.district_id = district_id;
    this.ward_code = ward_code;
    this.note = note;
    this.required_note = required_note;
    this.content = content;
    this.items = items;
  }

  public String getCustomer_id() {
    return customer_id;
  }

  public void setCustomer_id(String customer_id) {
    this.customer_id = customer_id;
  }

  public int getPayment_id() {
    return payment_id;
  }

  public void setPayment_id(int payment_id) {
    this.payment_id = payment_id;
  }

  public double getTotal_price() {

    return total_price;
  }

  public void setTotal_price(double total_price) {
    this.total_price = total_price;
  }

  public String getAddress_name() {
    return address_name;
  }

  public void setAddress_name(String address_name) {
    this.address_name = address_name;
  }

  public String getAddress_details() {
    return address_details;
  }

  public void setAddress_details(String address_details) {
    this.address_details = address_details;
  }

  public int getProvince_id() {
    return province_id;
  }

  public void setProvince_id(int province_id) {
    this.province_id = province_id;
  }

  public int getDistrict_id() {
    return district_id;
  }

  public void setDistrict_id(int district_id) {
    this.district_id = district_id;
  }

  public String getWard_code() {
    return ward_code;
  }

  public void setWard_code(String ward_code) {
    this.ward_code = ward_code;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getRequired_note() {
    return required_note;
  }

  public void setRequired_note(String required_note) {
    this.required_note = required_note;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public List<OrderItem> getItems() {
    return items;
  }

  public void setItems(List<OrderItem> items) {
    this.items = items;
  }

  public void addItem(ProductItem productItem, int quantity) {
    if (items == null) {
      items = new ArrayList<>(); // Initialize the list if it is null
    }

    // Check if the item already exists in the cart
    int index = findItemIndexById(productItem.getId());

    if (index != -1) {
      // If the item already exists, update the quantity
      OrderItem existingItem = items.get(index);
      existingItem.setQuantity(existingItem.getQuantity() + quantity);
    } else {
      // If the item doesn't exist, add it to the cart
      OrderItem newItem = new OrderItem();
      newItem.setId(productItem.getId());
      newItem.setQuantity(quantity);
      newItem.setName(productItem.getName());
      newItem.setPrice((productItem.getPrice() - (productItem.getDiscount())*productItem.getPrice()) * quantity);
      newItem.setWeight(100);
      items.add(newItem);
    }
  }
  private int findItemIndexById(int itemId) {
    if (items != null) {
      for (int i = 0; i < items.size(); i++) {
        if (items.get(i).getId() == itemId) {
          return i; // Item found, return its index
        }
      }
    }
    return -1; // Item not found
  }
  public double calculateTotalPrice() {
    double totalPrice = 0.0;

    if (items != null) {
      for (OrderItem item : items) {
        totalPrice += item.getPrice() * item.getQuantity();
      }
    }

    return totalPrice;
  }

  public void updateItemQuantity(int itemId, int newQuantity) {
    if (items != null) {
      for (OrderItem item : items) {
        if (item.getId() == itemId) {
          item.setQuantity(newQuantity);
          // Optionally, you might want to recalculate the item price here
          // based on the new quantity.
          // item.setPrice(newPrice);
          break; // Stop iterating once the item is found and updated.
        }
      }
    }
  }
  public void clearCart() {
    if (items != null) {
      items.clear();
    }
  }
}
