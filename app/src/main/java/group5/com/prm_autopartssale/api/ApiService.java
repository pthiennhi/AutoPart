package group5.com.prm_autopartssale.api;

import group5.com.prm_autopartssale.models.City;
import group5.com.prm_autopartssale.models.Customer;
import group5.com.prm_autopartssale.models.CustomerUpdateRequest;
import group5.com.prm_autopartssale.models.District;
import group5.com.prm_autopartssale.models.Notification;
import group5.com.prm_autopartssale.models.ResponseMessage;
import group5.com.prm_autopartssale.models.Ward;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

//  Customer
  @GET("customers/{id}")
  Call<Customer> getCustomer(@Path("id") String id);
  @PUT("customers/{id}")
  Call<ResponseMessage> updateCustomer(@Path("id") String id, @Body CustomerUpdateRequest updateRequest);

  //  City
  @GET("p/")
  Call<List<City>> getCities();

  @GET("p/{code}")
  Call<City> getCity(@Path("code") int code, @Query("depth") int depth);

  @GET("d/{code}")
  Call<District> getDistrict(@Path("code") int code, @Query("depth") int depth);

  //Notification
  @GET("customers/{customer_id}/notifications")
  Call<List<Notification>> getNotifications(@Path("customer_id") String customer_id);


}
