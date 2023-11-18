package group5.com.prm_autopartssale.fragment.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import group5.com.prm_autopartssale.R;

/**
 * A simple {@link Fragment} subclass. Use the {@link InformationShopFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class InformationShopFragment extends Fragment {

  ImageView iv_back, iv_more;
  MapView mapView;
  GoogleMap googleMap;
  LinearLayout ll_contact_shop;

  ImageView iv_facebook, iv_instagram, iv_twitter;

  public InformationShopFragment() {
    // Required empty public constructor
  }


  public static InformationShopFragment newInstance(String param1, String param2) {
    InformationShopFragment fragment = new InformationShopFragment();

    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_information_shop, container, false);

    iv_back = view.findViewById(R.id.iv_back);
    iv_more = view.findViewById(R.id.iv_more);

    ll_contact_shop = view.findViewById(R.id.ll_contact_shop);

    iv_facebook = view.findViewById(R.id.iv_facebook);
    iv_instagram = view.findViewById(R.id.iv_instagram);
    iv_twitter = view.findViewById(R.id.iv_twitter);

    iv_facebook.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        openFacbook();
      }
    });

    iv_instagram.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        openInstagram();
      }
    });

    iv_twitter.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        openTwitter();
      }
    });

    ll_contact_shop.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
          // Permission is already granted, you can make the call.
          Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0909028788"));
          startActivity(intentCall);
        } else {
          // Permission is not granted, request it.
          ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
        }
      }
    });

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

    mapView = view.findViewById(R.id.mapView);
    mapView.onCreate(savedInstanceState);

    mapView.getMapAsync(new OnMapReadyCallback() {
      @Override
      public void onMapReady(GoogleMap map) {
        googleMap = map;
        // You can customize the map here
        // For example, add a marker to a specific location:
        LatLng location = new LatLng(10.876306081181912, 106.80123158111331); // San Francisco
        googleMap.addMarker(new MarkerOptions().position(location).title("Car Accessories Shop"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12)); // Zoom level 12
      }
    });

    return view;

  }

  @Override
  public void onResume() {
    super.onResume();
    mapView.onResume();
  }

  @Override
  public void onPause() {
    super.onPause();
    mapView.onPause();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    mapView.onDestroy();
  }

  @Override
  public void onLowMemory() {
    super.onLowMemory();
    mapView.onLowMemory();
  }
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    if (requestCode == 1) {
      if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        // Permission granted, you can now make the call.
        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0909028788"));
        startActivity(intentCall);
      } else {
        // Permission denied, handle this case (e.g., show a message to the user).
      }
    }
  }

  private void openFacbook(){
    try {
      // Check if the Facebook app is installed
      requireContext().getPackageManager().getPackageInfo("com.facebook.katana", 0);

      // Open the Facebook app
      Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"));
      startActivity(intent);
    } catch (Exception e) {
      // If the Facebook app is not installed, open the Facebook website in a browser
      Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"));
      startActivity(intent);
    }
  }

  private void openInstagram(){
    try {
      // Check if the Instagram app is installed
      requireContext().getPackageManager().getPackageInfo("com.instagram.android", 0);

      // Open the Instagram app
      Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/"));
      startActivity(intent);
    } catch (Exception e) {
      // If the Instagram app is not installed, open the Instagram website in a browser
      Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/"));
      startActivity(intent);
    }
  }

  private void openTwitter(){
    try {
      // Check if the Twitter app is installed
      requireContext().getPackageManager().getPackageInfo("com.twitter.android", 0);

      // Open the Twitter app
      Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/"));
      startActivity(intent);
    } catch (Exception e) {
      // If the Twitter app is not installed, open the Twitter website in a browser
      Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/"));
      startActivity(intent);
    }
  }
}