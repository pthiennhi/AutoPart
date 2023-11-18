package group5.com.prm_autopartssale.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.activity.LoginActivity;
import group5.com.prm_autopartssale.fragment.profile.ChangeAddressFragment;
import group5.com.prm_autopartssale.fragment.profile.ChangeInformationFragment;
import group5.com.prm_autopartssale.fragment.profile.HelpCenterFragment;
import group5.com.prm_autopartssale.fragment.profile.OrderHistoryFragment;
import group5.com.prm_autopartssale.fragment.profile.PrivacyPolicyFragment;
import group5.com.prm_autopartssale.models.Customer;
import group5.com.prm_autopartssale.models.DataContainer;

/**
 * A simple {@link Fragment} subclass. Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

  LinearLayout llChangeInformation, llChangAddress, llNoification, llOrderHistory, llPrivacyPolicy, llHelpCenter, llLogout;
  ImageView ivBack, ivMore;

  TextView tvName, tvPhoneNumber;

  SignInClient oneTapClient;
  private static final int REQ_ONE_TAP = 99;  // Can be any integer unique to the Activity.
  private boolean showOneTapUI = true;
  public ProfileFragment() {
    // Required empty public constructor
  }


  public static ProfileFragment newInstance(String param1, String param2) {
    ProfileFragment fragment = new ProfileFragment();

    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

    llChangeInformation = rootView.findViewById(R.id.ll_change_information);
    llChangAddress = rootView.findViewById(R.id.ll_change_address);
    llNoification = rootView.findViewById(R.id.ll_notification);
    llOrderHistory = rootView.findViewById(R.id.ll_order_history);
    llPrivacyPolicy = rootView.findViewById(R.id.ll_privacy_policy);
    llHelpCenter = rootView.findViewById(R.id.ll_help_center);
    llLogout = rootView.findViewById(R.id.ll_logout);
    ivMore = rootView.findViewById(R.id.iv_more);
    ivBack = rootView.findViewById(R.id.iv_back);

    DataContainer dataContainer = DataContainer.getInstance();
    Customer customer = dataContainer.getCustomer();

    try {
      tvName = rootView.findViewById(R.id.tv_name);
      tvPhoneNumber = rootView.findViewById(R.id.tv_phone_number);

      tvName.setText(customer.getName());
      tvPhoneNumber.setText(customer.getPhone_number());
    } catch (Exception e) {
      e.printStackTrace();
    }

    BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationView);
    int selectedItemId = R.id.profile;

    bottomNavigationView.setSelectedItemId(selectedItemId);


    ivBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        requireActivity().getSupportFragmentManager().popBackStack();

      }
    });

    ivMore.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Log.d("TAG", "onClick: ");
        PopupMenu popupMenu = new PopupMenu(getContext(), ivMore);
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
    llChangeInformation.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        ChangeInformationFragment changeInformationFragment = new ChangeInformationFragment();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, changeInformationFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
      }
    });

    llChangAddress.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        ChangeAddressFragment changeAddressFragment = new ChangeAddressFragment();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, changeAddressFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
      }
    });

    llHelpCenter.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        HelpCenterFragment helpCenterFragment = new HelpCenterFragment();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, helpCenterFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
      }
    });

    llPrivacyPolicy.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        PrivacyPolicyFragment privacyPolicyFragment = new PrivacyPolicyFragment();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, privacyPolicyFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
      }
    });

    llLogout.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        showBottomSheet();
      }
    });

    llNoification.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        NotificationFragment notificationFragment = new NotificationFragment();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, notificationFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
      }
    });

    llOrderHistory.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        OrderHistoryFragment orderHistoryFragment = new OrderHistoryFragment();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, orderHistoryFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
      }
    });


    return rootView;
  }
  private void showBottomSheet() {
    final Dialog dialog = new Dialog(getContext());
    dialog.setContentView(R.layout.bottom_sheet_layout);

    Button btnCancel = dialog.findViewById(R.id.btnCancel);
    Button btnLogout = dialog.findViewById(R.id.btnLogout);

    btnCancel.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        dialog.dismiss();
      }
    });

    btnLogout.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
//        oneTapClient.signOut().
        Log.d("ccc", "bomay");
        SharedPreferences preferences = requireActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().commit();
        oneTapClient.signOut();
      }
    });

    dialog.show();
    dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    dialog.getWindow().getAttributes().windowAnimations= R.style.DialogAnimation;
    dialog.getWindow().setGravity(Gravity.BOTTOM);
  }
}