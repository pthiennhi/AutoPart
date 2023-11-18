package group5.com.prm_autopartssale.fragment.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.adapter.ReviewAdapter;
import group5.com.prm_autopartssale.models.ProductItem;
import group5.com.prm_autopartssale.models.Review;
import java.util.List;

/**
 * A simple {@link Fragment} subclass. Use the {@link ReviewProductDetailFragment#newInstance}
 * factory method to create an instance of this fragment.
 */
public class ReviewProductDetailFragment extends Fragment {

  ProductItem productItem;


  public void setProductItem(ProductItem productItem) {
    this.productItem = productItem;
  }


  public ReviewProductDetailFragment() {
    // Required empty public constructor
  }


  public static ReviewProductDetailFragment newInstance(String param1, String param2) {
    ReviewProductDetailFragment fragment = new ReviewProductDetailFragment();

    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_review_product_detail, container, false);
    RecyclerView rv_review = view.findViewById(R.id.rv_review);

    List<Review> list = productItem.getReviews();
    if (list != null) {
      Log.d("listhaha", list.size() + "");
      ReviewAdapter reviewAdapter = new ReviewAdapter(getContext(), list);
      rv_review.setAdapter(reviewAdapter);
      rv_review.setLayoutManager(new LinearLayoutManager(getContext()));
    } else {
      Log.d("listhaha", "List is null");
    }

    return view;
  }
}