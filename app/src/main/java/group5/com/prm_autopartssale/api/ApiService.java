package group5.com.prm_autopartssale.api;

import group5.com.prm_autopartssale.models.Brand;
import group5.com.prm_autopartssale.models.Cart;
import group5.com.prm_autopartssale.models.Noti;
import group5.com.prm_autopartssale.models.ProductItem;
import group5.com.prm_autopartssale.models.Province;
import group5.com.prm_autopartssale.models.Customer;
import group5.com.prm_autopartssale.models.CustomerCreateRequest;
import group5.com.prm_autopartssale.models.CustomerUpdateRequest;
import group5.com.prm_autopartssale.models.District;
import group5.com.prm_autopartssale.models.Notification;
import group5.com.prm_autopartssale.models.ResponseMessage;
import group5.com.prm_autopartssale.models.ResponseMessageCreateCustomer;
import group5.com.prm_autopartssale.models.Ward;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

//  Customer
  @GET("customers/{id}")
  Call<Customer> getCustomer(@Path("id") String id);
  @PUT("customers/{id}")
  Call<ResponseMessage> updateCustomer(@Path("id") String id, @Body CustomerUpdateRequest updateRequest);

  @POST("customers")
  Call<ResponseMessageCreateCustomer> createCustomer(@Body CustomerCreateRequest customer);

  //  City
  @GET("address/provinces")
  Call<List<Province>> getProvinces();

  @GET("address/districts")
  Call<List<District>> getDistricts(@Query("province_id") int province_id);

  @GET("address/wards")
  Call<List<Ward>> getWards(@Query("district_id") int district_id);

  //Notification
  @GET("customers/{customer_id}/notifications")
  Call<List<Notification>> getNotifications(@Path("customer_id") String customer_id);

  @GET("brands")
  Call<List<Brand>> getBrands();


  @GET("products")
  Call<List<ProductItem>> getProducts();

  @GET("products")
  Call<List<ProductItem>> getDiscountProducts(@Query("with_discount") boolean with_discount);

  @GET("products/{id}")
  Call<ProductItem> getProductById(@Path("id") int id);


  @POST("orders")
  Call<ResponseMessageCreateCustomer> createOrder(@Body Cart cart);

  @POST("customers/{id}/notifications")
  Call<ResponseMessage> createNotification(@Path("id") String id, @Body Noti notification);
}
