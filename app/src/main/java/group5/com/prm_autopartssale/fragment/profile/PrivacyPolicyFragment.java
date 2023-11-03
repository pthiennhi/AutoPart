package group5.com.prm_autopartssale.fragment.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import group5.com.prm_autopartssale.R;

/**
 * A simple {@link Fragment} subclass. Use the {@link PrivacyPolicyFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class PrivacyPolicyFragment extends Fragment {

  ImageView iv_back, iv_more;
  public PrivacyPolicyFragment() {
    // Required empty public constructor
  }

  public static PrivacyPolicyFragment newInstance(String param1, String param2) {
    PrivacyPolicyFragment fragment = new PrivacyPolicyFragment();

    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_privacy_policy, container, false);
    iv_back = view.findViewById(R.id.iv_back);
    iv_more = view.findViewById(R.id.iv_more);

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