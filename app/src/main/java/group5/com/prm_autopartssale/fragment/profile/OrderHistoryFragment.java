package group5.com.prm_autopartssale.fragment.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.adapter.ViewPageOrderHistoryAdapter;

/**
 * A simple {@link Fragment} subclass. Use the {@link OrderHistoryFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class OrderHistoryFragment extends Fragment {

  TabLayout tabLayout;
  ViewPager2 viewPager2;
  ViewPageOrderHistoryAdapter viewPageOrderHistoryAdapter;

  ImageView iv_back, iv_more;

  public OrderHistoryFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of this fragment using the provided
   * parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment OrderHistoryFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static OrderHistoryFragment newInstance(String param1, String param2) {
    OrderHistoryFragment fragment = new OrderHistoryFragment();

    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_order_history, container, false);
    tabLayout = view.findViewById(R.id.tab_layout);
    viewPager2 = view.findViewById(R.id.view_pager_order_history);
    iv_back = view.findViewById(R.id.iv_back);
    iv_more = view.findViewById(R.id.iv_more);
    viewPageOrderHistoryAdapter = new ViewPageOrderHistoryAdapter(getActivity());
    viewPager2.setAdapter(viewPageOrderHistoryAdapter);

    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        viewPager2.setCurrentItem(tab.getPosition());

      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {

      }
    });

    viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
      @Override
      public void onPageSelected(int position) {
        super.onPageSelected(position);
        tabLayout.getTabAt(position).select();
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

    return view;
  }
}