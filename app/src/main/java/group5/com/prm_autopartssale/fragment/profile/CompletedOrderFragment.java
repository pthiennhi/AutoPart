package group5.com.prm_autopartssale.fragment.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.adapter.OrderItemAdapter;
import group5.com.prm_autopartssale.constant.Constant;
import group5.com.prm_autopartssale.models.OrderItem;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass. Use the {@link CompletedOrderFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class CompletedOrderFragment extends Fragment {


  public CompletedOrderFragment() {
    // Required empty public constructor
  }

  public static CompletedOrderFragment newInstance(String param1, String param2) {
    CompletedOrderFragment fragment = new CompletedOrderFragment();

    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_completed_order, container, false);
    RecyclerView recyclerView = view.findViewById(R.id.rv_completed_order);
    List<OrderItem> listOrderItem = new ArrayList<OrderItem>();

    listOrderItem.add(new OrderItem("XBY-34", 1700862, Constant.STATUS_COMPLETED, 2 ,
        "https://cdn.shopify.com/s/files/1/1961/1987/t/56/assets/RunningBoards.jpg"));
    listOrderItem.add(new OrderItem("XBY-35", 1700862, Constant.STATUS_COMPLETED, 2 ,
        "https://cdn.shopify.com/s/files/1/1961/1987/files/SeatCovers.jpg"));

    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.setAdapter(new OrderItemAdapter(getContext(), listOrderItem));

    return view;
  }
}