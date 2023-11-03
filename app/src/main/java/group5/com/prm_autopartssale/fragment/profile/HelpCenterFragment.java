package group5.com.prm_autopartssale.fragment.profile;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import group5.com.prm_autopartssale.R;


public class HelpCenterFragment extends Fragment {

  LinearLayout ll_helpCenter1, ll_helpCenter2, ll_helpCenter3;
  CardView expandCard1, expandCard2, expandCard3;
  TextView tv_helpCenterSubtile1, tv_helpCenterSubtile2, tv_helpCenterSubtile3;

  ImageView iv_back, iv_more;


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

    iv_back = view.findViewById(R.id.iv_back);
    iv_more = view.findViewById(R.id.iv_more);
    expandCard1 = view.findViewById(R.id.expandCard1);
    tv_helpCenterSubtile1 = view.findViewById(R.id.tv_helpCenterSubtile1);
    ll_helpCenter1 = view.findViewById(R.id.ll_helpCenter1);

    expandCard2 = view.findViewById(R.id.expandCard2);
    tv_helpCenterSubtile2 = view.findViewById(R.id.tv_helpCenterSubtile2); // Corrected
    ll_helpCenter2 = view.findViewById(R.id.ll_helpCenter2);

    expandCard3 = view.findViewById(R.id.expandCard3);
    tv_helpCenterSubtile3 = view.findViewById(R.id.tv_helpCenterSubtile3); // Corrected
    ll_helpCenter3 = view.findViewById(R.id.ll_helpCenter3);



    LayoutTransition layoutTransition = new LayoutTransition();
    layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
    ll_helpCenter1.setLayoutTransition(layoutTransition);
    ll_helpCenter2.setLayoutTransition(layoutTransition);
    ll_helpCenter3.setLayoutTransition(layoutTransition);


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

    expandCard2.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (tv_helpCenterSubtile2.getVisibility() == View.GONE){
          tv_helpCenterSubtile2.setVisibility(View.VISIBLE);
        } else {
          tv_helpCenterSubtile2.setVisibility(View.GONE);
        }
      }
    });

    expandCard3.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (tv_helpCenterSubtile3.getVisibility() == View.GONE){
          tv_helpCenterSubtile3.setVisibility(View.VISIBLE);
        } else {
          tv_helpCenterSubtile3.setVisibility(View.GONE);
        }
      }
    });


    return view;
  }
}