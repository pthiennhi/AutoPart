package group5.com.prm_autopartssale.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.fragment.CategoryFragment;
import group5.com.prm_autopartssale.fragment.HomeFragment;
import group5.com.prm_autopartssale.fragment.NotificationFragment;
import group5.com.prm_autopartssale.fragment.OrderFragment;
import group5.com.prm_autopartssale.fragment.ProfileFragment;


public class MainActivity extends AppCompatActivity {

  BottomNavigationView bottomNavigationView;

  HomeFragment homeFragment = new HomeFragment();

  CategoryFragment categoryFragment = new CategoryFragment();

  OrderFragment orderFragment= new OrderFragment();

  NotificationFragment notificationFragment = new NotificationFragment();

  ProfileFragment profileFragment = new ProfileFragment();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

    myRef.setValue("Hello, World!");

    bottomNavigationView = findViewById(R.id.bottomNavigationView);
    bottomNavigationView.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return handleNavigationItemSelected(item.getItemId());
      }
    });
    bottomNavigationView.setSelectedItemId(R.id.home);
  }

  private boolean handleNavigationItemSelected(int itemId) {
    if (itemId == R.id.home) {
      getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.flFragment, homeFragment )
          .commit();
      return true;
    } else if (itemId == R.id.category) {
      getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.flFragment, categoryFragment )
          .commit();
      return true;
    } else if (itemId == R.id.order) {
      getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.flFragment, orderFragment )
          .commit();
      return true;
    } else if (itemId == R.id.notification) {
      getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.flFragment, notificationFragment )
          .commit();
      return true;
    } else if (itemId == R.id.profile) {
      getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.flFragment, profileFragment )
          .commit();
      return true;
    }

    return false;
  }


}