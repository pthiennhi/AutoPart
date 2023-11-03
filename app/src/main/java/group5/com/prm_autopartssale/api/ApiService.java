package group5.com.prm_autopartssale.api;

import group5.com.prm_autopartssale.models.City;
import group5.com.prm_autopartssale.models.Customer;
import group5.com.prm_autopartssale.models.District;
import group5.com.prm_autopartssale.models.Ward;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

//  Customer
  @GET("customer/{id}")
  Call<Customer> getCustomer(@Path("id") String id);
  @PUT("customer/{id}")
  Call<Customer> updateCustomer(@Path("id") String id, @Query("name") String name, @Query("phone") String phone, @Query("address") String address, @Query("city") int city, @Query("district") int district, @Query("ward") int ward);

  //  City
  @GET("p/")
  Call<List<City>> getCities();

  @GET("p/{code}")
  Call<City> getCity(@Path("code") int code, @Query("depth") int depth);

  @GET("d/{code}")
  Call<District> getDistrict(@Path("code") int code, @Query("depth") int depth);


}
