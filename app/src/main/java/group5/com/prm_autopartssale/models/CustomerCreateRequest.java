package group5.com.prm_autopartssale.models;

public class CustomerCreateRequest {
  private String name;
  private String email;
  private String id;

  public CustomerCreateRequest() {
  }

  public CustomerCreateRequest(String name, String email, String id) {
    this.name = name;
    this.email = email;
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
