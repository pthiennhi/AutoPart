package group5.com.prm_autopartssale.fragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.activity.MyApplication;
import group5.com.prm_autopartssale.adapter.OrderItemAdapter;
import group5.com.prm_autopartssale.api.ApiService;
import group5.com.prm_autopartssale.fragment.home.DiscountProductsFragment;
import group5.com.prm_autopartssale.models.Cart;
import group5.com.prm_autopartssale.models.Customer;
import group5.com.prm_autopartssale.models.DataContainer;
import group5.com.prm_autopartssale.models.Noti;
import group5.com.prm_autopartssale.models.OrderItem;
import group5.com.prm_autopartssale.models.ResponseMessage;
import group5.com.prm_autopartssale.models.ResponseMessageCreateCustomer;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass. Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {

  ImageView iv_back, iv_more;
  TextView tv_tv_address_name, tv_address_detail, tv_name, tv_phone, tv_total_price, tv_shipping_fee, tv_tax, tv_total;
  LinearLayout ll_order;

  Customer receiver;
  Cart cart;
  ApiService apiService;

  public OrderFragment() {
    // Required empty public constructor
  }


  public static OrderFragment newInstance(String param1, String param2) {
    OrderFragment fragment = new OrderFragment();

    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_order, container, false);

    BottomNavigationView bottomNavigationView = getActivity().findViewById(
        R.id.bottomNavigationView);
    int selectedItemId = R.id.order;
    bottomNavigationView.setSelectedItemId(selectedItemId);

    iv_back = view.findViewById(R.id.iv_back);
    iv_more = view.findViewById(R.id.iv_more);
    tv_tv_address_name = view.findViewById(R.id.tv_address_name);
    tv_address_detail = view.findViewById(R.id.tv_address_detail);
    tv_name = view.findViewById(R.id.tv_name);
    tv_phone = view.findViewById(R.id.tv_phone);
    ll_order = view.findViewById(R.id.ll_order);
    RecyclerView rv_order = view.findViewById(R.id.rv_order);
    tv_total_price = view.findViewById(R.id.tv_total_price);
    tv_shipping_fee = view.findViewById(R.id.tv_shipping_fee);
    tv_tax = view.findViewById(R.id.tv_tax);
    tv_total = view.findViewById(R.id.tv_total);
    ll_order = view.findViewById(R.id.ll_order);

    ll_order.setClickable(true);
    ll_order.setFocusable(true);
    ll_order.setBackgroundTintList(getResources().getColorStateList(R.color.black, null));

    tv_shipping_fee.setText(NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(20000));
    tv_tax.setText(NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(0));

    DataContainer dataContainer = DataContainer.getInstance();
    receiver = dataContainer.getCustomer();
    if (receiver != null) {
      tv_tv_address_name.setText(receiver.getAddress_name());
      tv_address_detail.setText(receiver.getAddress_details());
      tv_name.setText(receiver.getName());
      tv_phone.setText("+84" + receiver.getPhone_number());
    } else {
      tv_tv_address_name.setText("Chưa có địa chỉ");
      tv_address_detail.setText("Chưa có địa chỉ");
      tv_name.setText("Chưa có địa chỉ");
      tv_phone.setText("Chưa có địa chỉ");
    }

    cart = dataContainer.getCart();
    if (cart != null) {
      ll_order.setClickable(true);
      ll_order.setFocusable(true);
      ll_order.setBackgroundTintList(getResources().getColorStateList(R.color.black, null));
      List<OrderItem> list = cart.getItems();
      Log.d("OrderFragment", "onCreateView: " + list.get(0).getName());
      if (!list.isEmpty()) {
        Log.d("OrderFragment", "onCreateView: " + list.get(0).getName());

        OrderItemAdapter orderItemAdapter = new OrderItemAdapter(getContext(), list);
        rv_order.setAdapter(orderItemAdapter);
        rv_order.setLayoutManager(new LinearLayoutManager(getContext()));

        // ... rest of your existing code ...
      } else {
        // Handle the case when the list is empty
        // For example, display a message or perform some other action
        Log.d("OrderFragment", "onCreateView: The list is empty");
      }



      double totalPrice = cart.calculateTotalPrice();
//      orderItemAdapter.updateData(cart.getItems());
      tv_total_price.setText(
          NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(totalPrice));
      tv_total.setText(
          NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(totalPrice + 20000));

      ll_order.setClickable(true);
      ll_order.setFocusable(true);
      Retrofit retrofit = new Retrofit.Builder()
          .baseUrl("https://swd-six.vercel.app/api/")
          .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
          .build();
      apiService = retrofit.create(ApiService.class);

      cart.setCustomer_id(receiver.getId());
      Log.d("OrderFragment", "customer_id: " + cart.getCustomer_id());

      cart.setPayment_id(3);
      Log.d("OrderFragment", "payment_id: " + cart.getPayment_id());
      cart.setTotal_price(totalPrice + 20000);
      Log.d("OrderFragment", "total_price: " + cart.getTotal_price());
      cart.setAddress_name(receiver.getAddress_name());
      Log.d("OrderFragment", "address_name: " + cart.getAddress_name());
      cart.setAddress_details(receiver.getAddress_details());
      Log.d("OrderFragment", "address_details: " + cart.getAddress_details());
      cart.setProvince_id(receiver.getProvince_id());
      Log.d("OrderFragment", "province_id: " + cart.getProvince_id());
      cart.setDistrict_id(receiver.getDistrict_id());
      Log.d("OrderFragment", "district_id: " + cart.getDistrict_id());
      cart.setWard_code(receiver.getWard_code());
      Log.d("OrderFragment", "ward_code: " + cart.getWard_code());
      cart.setNote("Không có ghi chú");
      cart.setRequired_note("CHOXEMHANGKHONGTHU");
      cart.setContent("hahah");
      Log.d("OrderFragment", "onCreateViewq22: " + cart.getItems().get(0).getName());

      ll_order.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          Log.d("OrderFragment", "onClick: ll_order clicked");
          Call<ResponseMessageCreateCustomer> createOrder = apiService.createOrder(cart);
          Log.d("OrderFragment", "onClick: ahhaa");

          createOrder.enqueue(new Callback<ResponseMessageCreateCustomer>() {
            @Override
            public void onResponse(Call<ResponseMessageCreateCustomer> call,
                Response<ResponseMessageCreateCustomer> response) {
              if (response.isSuccessful()) {
                Log.d("OrderFragment", "onResponse: " + response.body().getMessage());
                sendNotification();
                if (cart != null) {
                  cart.clearCart();
                }

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast, null);
                ImageView toastImage = (ImageView) layout.findViewById(R.id.toast_image);
                TextView toastText = (TextView) layout.findViewById(R.id.toast_text);
                toastImage.setImageResource(R.drawable.ic_check_circle);
                toastText.setText("Đặt hàng thành công");
                Toast toast = new Toast(requireActivity().getApplicationContext());
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
                HomeFragment homeFragment = new HomeFragment();
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.flFragment, homeFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();




              } else {
                Log.d("OrderFragment", "onResponsehhaaa: " + response.message());
                if (response.errorBody() != null) {
                  try {
                    String errorBody = response.errorBody().string();
                    Log.d("OrderFragment", "onResponse: Error Body " + errorBody);
                  } catch (IOException e) {
                    e.printStackTrace();
                  }
                } else {
                  Log.d("OrderFragment", "onResponse: Error Body is null");
                }

              }
            }

            @Override
            public void onFailure(Call<ResponseMessageCreateCustomer> call, Throwable t) {

            }
          });

        }
      });
    } else {
      ll_order.setClickable(false);
      ll_order.setBackgroundTintList(getResources().getColorStateList(R.color.greyscale600, null));
    }

    iv_back.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (requireActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
          requireActivity().getSupportFragmentManager().popBackStack();
        }
      }
    });

    iv_more.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        PopupMenu popupMenu = new PopupMenu(getContext(), iv_more);
        popupMenu.getMenuInflater().inflate(R.menu.header_menu, popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(item -> {
          if (item.getItemId() == R.id.action_exit_app) {
            if (getActivity() != null) {
              getActivity().finish();
            }
          }
          return false;
        });

      }
    });

    return view;
  }

  private void sendNotification() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://swd-six.vercel.app/api/")
        .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
        .build();
    apiService = retrofit.create(ApiService.class);
    Noti noti = new Noti(
        "Đặt hàng thành công",
        "Đơn hàng của bạn đã được đặt thành công"
    );
    DataContainer dataContainer = DataContainer.getInstance();
    String id = dataContainer.getCustomer().getId();
    Call<ResponseMessage> notiCall = apiService.createNotification(id, noti);

    notiCall.enqueue(new Callback<ResponseMessage>() {
      @Override
      public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
        if (response.isSuccessful()) {
          Log.d("OrderFragment", "onResponse: " + response.body().getMessage());
          Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_noti);

          Notification notification = new Notification.Builder(getContext(), MyApplication.CHANNEL_ID)
              .setContentTitle("Đặt hàng thành công")
              .setContentText("Đơn hàng của bạn đã được đặt thành công")
              .setSmallIcon(R.drawable.ic_noti)
              .setLargeIcon(bitmap)
              .build();
          NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(
              getContext().NOTIFICATION_SERVICE);
          if (notificationManager != null) {
            notificationManager.notify(getNotificationId(), notification);
          }
          LayoutInflater inflater = getLayoutInflater();
          View layout = inflater.inflate(R.layout.custom_toast, null);
          ImageView toastImage = (ImageView) layout.findViewById(R.id.toast_image);
          TextView toastText = (TextView) layout.findViewById(R.id.toast_text);
          toastImage.setImageResource(R.drawable.ic_check_circle);
          toastText.setText("Đặt hàng thành công");
          Toast toast = new Toast(requireActivity().getApplicationContext());
          toast.setGravity(Gravity.TOP, 0, 0);
          toast.setDuration(Toast.LENGTH_LONG);
          toast.setView(layout);
          toast.show();

          NotificationFragment notificationFragment = new NotificationFragment();
          FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
          fragmentTransaction.replace(R.id.flFragment, notificationFragment);
          fragmentTransaction.addToBackStack(null);
          fragmentTransaction.commit();

        } else {
          Log.d("OrderFragment", "onResponsefail: " + response.message());
        }
      }

      @Override
      public void onFailure(Call<ResponseMessage> call, Throwable t) {

      }
    });



  }

  private int getNotificationId() {
    return (int) System.currentTimeMillis();
  }
}