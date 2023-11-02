package group5.com.prm_autopartssale.fragment.profile;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.fragment.CategoryFragment;
import group5.com.prm_autopartssale.fragment.HomeFragment;
import group5.com.prm_autopartssale.fragment.NotificationFragment;
import group5.com.prm_autopartssale.fragment.OrderFragment;
import group5.com.prm_autopartssale.fragment.ProfileFragment;

/**
 * A simple {@link Fragment} subclass. Use the {@link ChangeInformationFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class ChangeInformationFragment extends Fragment {
  ImageView ivBack;

  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  private String mParam1;
  private String mParam2;

  public ChangeInformationFragment() {
    // Required empty public constructor
  }


  public static ChangeInformationFragment newInstance(String param1, String param2) {
    ChangeInformationFragment fragment = new ChangeInformationFragment();
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
    View view = inflater.inflate(R.layout.fragment_change_information, container, false);
    ivBack = view.findViewById(R.id.iv_back);
    ivBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(requireActivity().getSupportFragmentManager().getBackStackEntryCount() > 0){
          requireActivity().getSupportFragmentManager().popBackStack();
        }
      }
    });

    return view;
  }
}