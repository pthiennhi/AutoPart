package group5.com.prm_autopartssale.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.adapter.NotificationAdapter;
import group5.com.prm_autopartssale.api.ApiService;
import group5.com.prm_autopartssale.models.Customer;
import group5.com.prm_autopartssale.models.DataContainer;
import group5.com.prm_autopartssale.models.Notification;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass. Use the {@link NotificationFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {
  ImageView iv_back, iv_more;
  ApiService apiService;
  List<Notification> listNotification;

  Customer customer;



  public NotificationFragment() {
    // Required empty public constructor
  }


  public static NotificationFragment newInstance(String param1, String param2) {
    NotificationFragment fragment = new NotificationFragment();
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_notification, container, false);

    BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
    int selectedItemId = R.id.notification;
    bottomNavigationView.setSelectedItemId(selectedItemId);

    iv_back = view.findViewById(R.id.iv_back);
    iv_more = view.findViewById(R.id.iv_more);

    DataContainer dataContainer = DataContainer.getInstance();
    customer = dataContainer.getCustomer();

    RecyclerView recyclerView = view.findViewById(R.id.rv_notification);
    Retrofit retrofitServer = new Retrofit.Builder()
        .baseUrl("https://swd-six.vercel.app/api/")
        .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
        .build();
    apiService = retrofitServer.create(ApiService.class);

    if(customer != null){
      Call<List<Notification>> notificationCall = apiService.getNotifications(customer.getId());
      notificationCall.enqueue(new Callback<List<Notification>>() {
        @Override
        public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
          if(response.isSuccessful() && response.body() != null){
            listNotification = response.body();
            Log.d("Notification", listNotification.toString());
            NotificationAdapter notificationAdapter = new NotificationAdapter(getContext(), listNotification);
            recyclerView.setAdapter(notificationAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

          }
        }

        @Override
        public void onFailure(Call<List<Notification>> call, Throwable t) {
          Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
          Log.d("Notification", t.getMessage());
        }
      });
    } else {
      listNotification = new ArrayList<>();
      listNotification.add(new Notification(1, customer.getId(),"Chưa load được thông báo","Vui lòng mở lại home để load lại thông báo", new Timestamp(System.currentTimeMillis()).toString()));
      NotificationAdapter notificationAdapter = new NotificationAdapter(getContext(), listNotification);
      recyclerView.setAdapter(notificationAdapter);
      recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }



    iv_back.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if(requireActivity().getSupportFragmentManager().getBackStackEntryCount() > 0){
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
          if(item.getItemId() == R.id.action_exit_app){
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
}