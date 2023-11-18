package group5.com.prm_autopartssale.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.api.ApiService;
import group5.com.prm_autopartssale.fragment.CategoryFragment;
import group5.com.prm_autopartssale.fragment.HomeFragment;
import group5.com.prm_autopartssale.fragment.NotificationFragment;
import group5.com.prm_autopartssale.fragment.OrderFragment;
import group5.com.prm_autopartssale.fragment.ProfileFragment;
import group5.com.prm_autopartssale.models.Customer;
import group5.com.prm_autopartssale.models.DataContainer;
import retrofit2.Call;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

  ApiService apiService;
  BottomNavigationView bottomNavigationView;

  HomeFragment homeFragment = new HomeFragment();

  CategoryFragment categoryFragment = new CategoryFragment();

  OrderFragment orderFragment = new OrderFragment();

  NotificationFragment notificationFragment = new NotificationFragment();

  ProfileFragment profileFragment = new ProfileFragment();

  private int selectedItemId = R.id.home;

  Customer customer;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
    String customer_id = sharedPreferences.getString("customer_id", "");

    DataContainer dataContainer = DataContainer.getInstance();
    if (dataContainer.getCustomer() != null) {
      customer = dataContainer.getCustomer();
    } else {
      customer = new Customer();
      customer.setId(customer_id);
    }

    Log.d("customer", customer.getId());

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://swd-six.vercel.app/api/")
        .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
        .build();

    apiService = retrofit.create(ApiService.class);
    
    Call<Customer> customerCall = apiService.getCustomer(customer.getId());


    customerCall.enqueue(new retrofit2.Callback<Customer>() {
      @Override
      public void onResponse(Call<Customer> call, retrofit2.Response<Customer> response) {
        customer = response.body();
        DataContainer dataContainer = DataContainer.getInstance();
        dataContainer.setCustomer(customer);
      }

      @Override
      public void onFailure(Call<Customer> call, Throwable t) {
        Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();

      }
    });

    bottomNavigationView = findViewById(R.id.bottomNavigationView);
    bottomNavigationView.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return handleNavigationItemSelected(item.getItemId());
      }
    });
    bottomNavigationView.setSelectedItemId(R.id.home);
    replaceFragment(R.id.flFragment, homeFragment);
  }

  private boolean handleNavigationItemSelected(int itemId) {

    if (itemId != selectedItemId) {
      // Only proceed if the selected item is different
      selectedItemId = itemId;
      replaceFragment(R.id.flFragment, getFragmentForItemId(itemId));
      return true;
    }
    return false;

  }

  private Fragment getFragmentForItemId(int itemId) {

    if (itemId == R.id.home) {
      return homeFragment;

    } else if (itemId == R.id.order) {
      return orderFragment;
    } else if (itemId == R.id.notification) {
      return notificationFragment;
    } else if (itemId == R.id.profile) {
      return profileFragment;
    }
    return null;

  }

  private void replaceFragment(int id, Fragment fragment) {
    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    fragmentTransaction.replace(id, fragment);
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
    selectedItemId = getNavItemForFragment(fragment);
    setSelectedNavigationItem(selectedItemId);
  }

  private void setSelectedNavigationItem(int itemId) {
    bottomNavigationView.setSelectedItemId(itemId);
  }

  private int getNavItemForFragment(Fragment fragment) {
    if (fragment instanceof HomeFragment) {
      return R.id.home;

    } else if (fragment instanceof OrderFragment) {
      return R.id.order;
    } else if (fragment instanceof NotificationFragment) {
      return R.id.notification;
    } else if (fragment instanceof ProfileFragment) {
      return R.id.profile;
    }
    return R.id.home; // Default item
  }


}