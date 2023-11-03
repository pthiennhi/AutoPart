package group5.com.prm_autopartssale.fragment.profile;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import group5.com.prm_autopartssale.R;


public class HelpCenterFragment extends Fragment {

  LinearLayout ll_helpCenter1, ll_helpCenter2, ll_helpCenter3;
  CardView expandCard1, expandCard2, expandCard3;
  TextView tv_helpCenterSubtile1, tv_helpCenterSubtile2, tv_helpCenterSubtile3;


  public HelpCenterFragment() {
    // Required empty public constructor
  }


  public static HelpCenterFragment newInstance(String param1, String param2) {
    HelpCenterFragment fragment = new HelpCenterFragment();

    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_help_center, container, false);

    expandCard1 = view.findViewById(R.id.expandCard1);
    tv_helpCenterSubtile1 = view.findViewById(R.id.tv_helpCenterSubtile1);
    ll_helpCenter1 = view.findViewById(R.id.ll_helpCenter1);

    LayoutTransition layoutTransition = new LayoutTransition();
    layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
    ll_helpCenter1.setLayoutTransition(layoutTransition);

    expandCard1.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (tv_helpCenterSubtile1.getVisibility() == View.GONE) {
          tv_helpCenterSubtile1.setVisibility(View.VISIBLE);
        } else {
          tv_helpCenterSubtile1.setVisibility(View.GONE);

        }
      }
    });


    return view;
  }
}