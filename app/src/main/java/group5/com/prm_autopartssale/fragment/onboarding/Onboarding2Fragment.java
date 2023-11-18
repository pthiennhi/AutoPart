package group5.com.prm_autopartssale.fragment.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.activity.LoginActivity;

/**
 * A simple {@link Fragment} subclass. Use the {@link Onboarding2Fragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class Onboarding2Fragment extends Fragment {
  Button btn_next;

  public Onboarding2Fragment() {
    // Required empty public constructor
  }

  public static Onboarding2Fragment newInstance(String param1, String param2) {
    Onboarding2Fragment fragment = new Onboarding2Fragment();

    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
   View view = inflater.inflate(R.layout.fragment_onboarding2, container, false);
    btn_next = view.findViewById(R.id.btn_next);
    btn_next.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.d("RATMET", "onClick: ");
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
      }
    });
    return view;
  }
}