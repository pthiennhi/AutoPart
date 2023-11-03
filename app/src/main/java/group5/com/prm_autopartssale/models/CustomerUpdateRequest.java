package group5.com.prm_autopartssale.models;

public class CustomerUpdateRequest {
  private String name;
  private String phone_number;
  private String address_name;
  private String address_details;
  private int city_code;
  private int district_code;
  private int ward_code;
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone_number() {
    return phone_number;
  }

  public void setPhone_number(String phone_number) {
    this.phone_number = phone_number;
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

  public int getCity_code() {
    return city_code;
  }

  public void setCity_code(int city_code) {
    this.city_code = city_code;
  }

  public int getDistrict_code() {
    return district_code;
  }

  public void setDistrict_code(int district_code) {
    this.district_code = district_code;
  }

  public int getWard_code() {
    return ward_code;
  }

  public void setWard_code(int ward_code) {
    this.ward_code = ward_code;
  }
}
