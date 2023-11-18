package group5.com.prm_autopartssale.fragment.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.adapter.ViewPageOrderHistoryAdapter;
import group5.com.prm_autopartssale.adapter.ViewPageProductDetailAdapter;
import group5.com.prm_autopartssale.api.ApiService;
import group5.com.prm_autopartssale.fragment.OrderFragment;
import group5.com.prm_autopartssale.models.Cart;
import group5.com.prm_autopartssale.models.DataContainer;
import group5.com.prm_autopartssale.models.ProductItem;
import group5.com.prm_autopartssale.models.Review;
import group5.com.prm_autopartssale.util.GetBitmapFromURL;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass. Use the {@link ProductDetailFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class ProductDetailFragment extends Fragment {

 ProductItem productItem,productItem2;
  TabLayout tabLayout;
  ViewPager2 viewPager2;
  ViewPageProductDetailAdapter viewPageProductDetailAdapter;
  ImageView iv_back,iv_product_image;
  TextView tv_product_name, tv_quantity,tv_product_rating,tv_reviews,tv_final_price,tv_price,tv_discount;
  Button btn_add_to_cart;
  ApiService apiService;
  Cart cart;

  public ProductDetailFragment(ProductItem productItem) {
    this.productItem = productItem;
    // Required empty public constructor
  }

  public static ProductDetailFragment newInstance(ProductItem productItem) {
    ProductDetailFragment fragment = new ProductDetailFragment(productItem);

    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

    iv_back = view.findViewById(R.id.iv_back);
    tabLayout = view.findViewById(R.id.tab_layout);
    viewPager2 = view.findViewById(R.id.view_pager_product_detail);
    iv_product_image = view.findViewById(R.id.iv_product_image);
    tv_product_name = view.findViewById(R.id.tv_product_name);
    tv_quantity = view.findViewById(R.id.tv_quantity);
    tv_product_rating = view.findViewById(R.id.tv_product_rating);
    tv_reviews = view.findViewById(R.id.tv_reviews);
    tv_final_price = view.findViewById(R.id.tv_final_price);
    tv_price = view.findViewById(R.id.tv_price);
    tv_discount = view.findViewById(R.id.tv_discount);
    btn_add_to_cart = view.findViewById(R.id.btn_add_to_cart);

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://swd-six.vercel.app/api/")
        .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
        .build();

    apiService = retrofit.create(ApiService.class);
    Log.d("ProductDetailFragment", "onCreateView: " + productItem.getId());

    Call<ProductItem> productItemCall = apiService.getProductById(productItem.getId());
    productItemCall.enqueue(new retrofit2.Callback<ProductItem>() {
      @Override
      public void onResponse(Call<ProductItem> call, Response<ProductItem> response) {
        if(response.isSuccessful()){
          productItem = response.body();
          productItem2 = response.body();
          if (productItem.getReviews() != null) {
            List<Review> reviews = productItem.getReviews();
            tv_reviews.setText("("+String.valueOf(reviews.size())+ " đánh giá)");
          } else {
            tv_reviews.setText("0 đánh giá");
          }

          viewPageProductDetailAdapter = new ViewPageProductDetailAdapter(getActivity(), productItem);
          viewPager2.setAdapter(viewPageProductDetailAdapter);



          Log.d("ProductDetailFragment", "onResponse: " + response.body().toString());
        }
      }



      @Override
      public void onFailure(Call<ProductItem> call, Throwable t) {
        Log.d("ProductDetailFragment", "onFailure: " + t.getMessage());

      }
    }
    );
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

    tv_product_name.setText(productItem.getName());

    int quantity = productItem.getQuantity();
    if(quantity > 0){
      tv_quantity.setText("Còn hàng");
      tv_quantity.setTextColor(getResources().getColor(R.color.success, null));
      tv_quantity.setBackgroundTintList(getResources().getColorStateList(R.color.backgroundGreen, null));
    } else{
      tv_quantity.setText("Hết hàng");
      tv_quantity.setTextColor(getResources().getColor(R.color.error, null));
      tv_quantity.setBackgroundTintList(getResources().getColorStateList(R.color.backgroundPink, null));
      btn_add_to_cart.setEnabled(false);
    }
    tv_product_rating.setText(String.valueOf(productItem.getAvg_rating()));




    tv_final_price.setText(String.valueOf(productItem.getPrice() - (productItem.getDiscount())*productItem.getPrice()));
    tv_price.setText(String.valueOf(productItem.getPrice()));
    tv_discount.setText(String.valueOf(productItem.getDiscount()*100) + "%");


    GetBitmapFromURL.loadBitmapFromURL(productItem.getThumbnail_url(), new GetBitmapFromURL.BitmapCallback() {
      @Override
      public void onBitmapLoaded(Bitmap bitmap) {
        iv_product_image.setImageBitmap(bitmap);
      }

      @Override
      public void onBitmapLoadFailed() {

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

    DataContainer dataContainer = DataContainer.getInstance();

    cart = dataContainer.getCart();

    btn_add_to_cart.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if(productItem2 != null){
          Log.d("ProductDetailFragment", "onClick: " + productItem2.getName());
          if(cart == null){
            cart = new Cart();
          }
          cart.addItem(productItem2, 1);
          dataContainer.setCart(cart);
        }
        String cartJson = cartToJson(cart);
        saveCartToSharedPreferences(requireContext(), cartJson);
        OrderFragment orderFragment = new OrderFragment();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, orderFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

      }
    });


    return view;
  }

  private String cartToJson(Cart cart) {
    Gson gson = new Gson();
    return gson.toJson(cart);
  }

  private void saveCartToSharedPreferences(Context context, String cartJson) {
    SharedPreferences sharedPreferences = context.getSharedPreferences("cart", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString("cart", cartJson);
    editor.apply();
  }


}