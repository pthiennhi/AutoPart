package group5.com.prm_autopartssale.fragment.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import group5.com.prm_autopartssale.R;

/**
 * A simple {@link Fragment} subclass. Use the {@link PrivacyPolicyFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class PrivacyPolicyFragment extends Fragment {


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
    return view;
  }
}