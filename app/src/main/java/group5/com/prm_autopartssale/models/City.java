package group5.com.prm_autopartssale.models;

import java.util.List;

public class City {
  private String name;
  private int code;

  List<District> districts;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public List<District> getDistricts() {
    return districts;
  }

  public void setDistricts(List<District> districts) {
    this.districts = districts;
  }
}
