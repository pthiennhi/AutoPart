package group5.com.prm_autopartssale.fragment.home;

import android.os.Bundle;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.models.ProductItem;


public class DescriptionProductDetailFragment extends Fragment {
  ProductItem productItem;


  public void setProductItem(ProductItem productItem) {
    this.productItem = productItem;
  }
  public DescriptionProductDetailFragment() {
    // Required empty public constructor
  }


  public static DescriptionProductDetailFragment newInstance(String param1, String param2) {
    DescriptionProductDetailFragment fragment = new DescriptionProductDetailFragment();

    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_description_product_detail, container, false);

    TextView textView = view.findViewById(R.id.textView);
    textView.setText(productItem.getDescription());
    return view;
  }
}