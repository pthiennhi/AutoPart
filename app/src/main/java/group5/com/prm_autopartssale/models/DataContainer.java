package group5.com.prm_autopartssale.models;

import java.util.List;

public class DataContainer {
  private static DataContainer instance;
  private Customer customer;

  private List<Brand> listBrand;

  private List<ProductItem> listProduct;

  private List<ProductItem> listProductDiscount;

  private List<Notification> listNotification;

  private Cart cart;



  private DataContainer() {
  }

  public static DataContainer getInstance() {
    if (instance == null) {
      instance = new DataContainer();
    }
    return instance;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public List<Brand> getListBrand() {
    return listBrand;
  }

  public void setListBrand(List<Brand> listBrand) {
    this.listBrand = listBrand;
  }

  public List<ProductItem> getListProduct() {
    return listProduct;
  }

  public void setListProduct(List<ProductItem> listProduct) {
    this.listProduct = listProduct;
  }

  public List<ProductItem> getListProductDiscount() {
    return listProductDiscount;
  }

  public void setListProductDiscount(
      List<ProductItem> listProductDiscount) {
    this.listProductDiscount = listProductDiscount;
  }

  public List<Notification> getListNotification() {
    return listNotification;
  }

  public void setListNotification(
      List<Notification> listNotification) {
    this.listNotification = listNotification;
  }

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }
}
