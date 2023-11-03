package group5.com.prm_autopartssale.fragment.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
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
import group5.com.prm_autopartssale.models.Customer;
import group5.com.prm_autopartssale.models.DataContainer;

/**
 * A simple {@link Fragment} subclass. Use the {@link ChangeInformationFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class ChangeInformationFragment extends Fragment {
  ImageView ivBack, ivMore;
  EditText edtName, edtPhone;
  Button btnChange;

  Customer customer;



  public ChangeInformationFragment() {
    // Required empty public constructor
  }


  public static ChangeInformationFragment newInstance(String param1, String param2) {
    ChangeInformationFragment fragment = new ChangeInformationFragment();

    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_change_information, container, false);
    ivBack = view.findViewById(R.id.iv_back);
    ivMore = view.findViewById(R.id.iv_more);
    edtName = view.findViewById(R.id.edtName);
    edtPhone = view.findViewById(R.id.edtPhone);
    btnChange = view.findViewById(R.id.btnChange);

    DataContainer dataContainer = DataContainer.getInstance();
    customer = dataContainer.getCustomer();
    edtName.setText(customer.getName());
    edtPhone.setText(customer.getPhone_number());
    ivBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(requireActivity().getSupportFragmentManager().getBackStackEntryCount() > 0){
          requireActivity().getSupportFragmentManager().popBackStack();
        }
      }
    });
    ivMore.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
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
    btnChange.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    return view;
  }
}