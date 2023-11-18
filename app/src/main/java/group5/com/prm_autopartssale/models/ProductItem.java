package group5.com.prm_autopartssale.models;

import java.util.List;

public class ProductItem {
  private int id;

  private String name;

  private String description;

  private double price;

  private double discount;

  private int quantity;

  private String created_at;

  private String is_active;

  private String thumbnail_url;

  private double avg_rating;

  private List<Brand> brands;

  private List<Category> categories;

  private List<Review> reviews;



  public ProductItem() {
  }

  public ProductItem(int id, String name, String description, double price, double discount,
      int quantity, String created_at, String is_active, String thumbnail_url, double avg_rating,
      List<Brand> brands, List<Category> categories, List<Review> reviews) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.discount = discount;
    this.quantity = quantity;
    this.created_at = created_at;
    this.is_active = is_active;
    this.thumbnail_url = thumbnail_url;
    this.avg_rating = avg_rating;
    this.brands = brands;
    this.categories = categories;
    this.reviews = reviews;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getCreated_at() {
    return created_at;
  }

  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  public String getIs_active() {
    return is_active;
  }

  public void setIs_active(String is_active) {
    this.is_active = is_active;
  }

  public String getThumbnail_url() {
    return thumbnail_url;
  }

  public void setThumbnail_url(String thumbnail_url) {
    this.thumbnail_url = thumbnail_url;
  }

  public double getAvg_rating() {
    return avg_rating;
  }

  public void setAvg_rating(double avg_rating) {
    this.avg_rating = avg_rating;
  }

  public List<Brand> getBrands() {
    return brands;
  }

  public void setBrands(List<Brand> brands) {
    this.brands = brands;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }
  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }
}
