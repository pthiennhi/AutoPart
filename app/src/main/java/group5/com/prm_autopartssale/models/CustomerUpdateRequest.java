package group5.com.prm_autopartssale.models;

public class CustomerUpdateRequest {
  private String name;
  private String phone_number;
  private String address_name;
  private String address_details;
  private int province_id;
  private int district_id;
  private String ward_code;
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
}
