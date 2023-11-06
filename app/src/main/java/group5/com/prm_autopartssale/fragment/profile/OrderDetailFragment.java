package group5.com.prm_autopartssale.fragment.profile;

import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.api.ApiService;
import java.util.List;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass. Use the {@link OrderDetailFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class OrderDetailFragment extends Fragment {
  ImageView iv_back, iv_more;

  ApiService apiService;
  String orderId;
  public OrderDetailFragment() {
    // Required empty public constructor
  }
  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public static OrderDetailFragment newInstance(String param1, String param2) {
    OrderDetailFragment fragment = new OrderDetailFragment();

    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_order_detail, container, false);

    iv_back = view.findViewById(R.id.iv_back);
    iv_more = view.findViewById(R.id.iv_more);
    RecyclerView rv_order_detail = view.findViewById(R.id.rv_order_detail);

    Retrofit retrofitServer = new Retrofit.Builder()
        .baseUrl("https://swd-six.vercel.app/api/")
        .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
        .build();
    apiService = retrofitServer.create(ApiService.class);




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