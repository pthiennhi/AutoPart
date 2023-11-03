package group5.com.prm_autopartssale.models;

public class DataContainer {
  private static DataContainer instance;
  private Customer customer;

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
}
