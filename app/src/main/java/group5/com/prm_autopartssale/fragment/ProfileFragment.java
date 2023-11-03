package group5.com.prm_autopartssale.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.fragment.profile.ChangeAddressFragment;
import group5.com.prm_autopartssale.fragment.profile.ChangeInformationFragment;
import group5.com.prm_autopartssale.fragment.profile.HelpCenterFragment;
import java.io.Console;

/**
 * A simple {@link Fragment} subclass. Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

  LinearLayout llChangeInformation, llChangAddress, llNoification, llOrderHistory, llPrivacyPolicy, llHelpCenter, llLogout;
  ImageView ivBack, ivMore;

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public ProfileFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of this fragment using the provided
   * parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment ProfileFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static ProfileFragment newInstance(String param1, String param2) {
    ProfileFragment fragment = new ProfileFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
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


    return rootView;
  }
}