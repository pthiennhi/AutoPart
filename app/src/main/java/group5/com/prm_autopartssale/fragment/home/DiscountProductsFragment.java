package group5.com.prm_autopartssale.fragment.home;

import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.api.ApiService;
import group5.com.prm_autopartssale.models.DataContainer;
import group5.com.prm_autopartssale.models.ProductItem;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass. Use the {@link DiscountProductsFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class DiscountProductsFragment extends Fragment {
  ApiService apiService;

  ImageView iv_back, iv_more;


  public DiscountProductsFragment() {
    // Required empty public constructor
  }


  public static DiscountProductsFragment newInstance(String param1, String param2) {
    DiscountProductsFragment fragment = new DiscountProductsFragment();

    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_discount_products, container, false);


    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://swd-six.vercel.app/api/")
        .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
        .build();

    apiService = retrofit.create(ApiService.class);
    DataContainer dataContainer = DataContainer.getInstance();
    if(dataContainer.getListProductDiscount() != null){
      List<ProductItem> listProduct = dataContainer.getListProductDiscount();
      RecyclerView recyclerView1 = view.findViewById(R.id.rv_discountproducts);
      recyclerView1.setLayoutManager(new GridLayoutManager(getContext(),2));
      recyclerView1.setAdapter(new group5.com.prm_autopartssale.adapter.ProductItemAdapter(getContext(), listProduct, getParentFragmentManager()));

    }else{
      Call<List<ProductItem>> productItemCall = apiService.getDiscountProducts(true);

      productItemCall.enqueue(new Callback<List<ProductItem>>() {
        @Override
        public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
          List<ProductItem> listProduct = response.body();
          dataContainer.setListProductDiscount(listProduct);
          RecyclerView recyclerView1 = view.findViewById(R.id.rv_discountproducts);
          recyclerView1.setLayoutManager(new GridLayoutManager(getContext(),2));
          recyclerView1.setAdapter(new group5.com.prm_autopartssale.adapter.ProductItemAdapter(getContext(), listProduct, getParentFragmentManager()));
        }

        @Override
        public void onFailure(Call<List<ProductItem>> call, Throwable t) {

        }
      });
    }


    iv_back = view.findViewById(R.id.iv_back);
    iv_more = view.findViewById(R.id.iv_more);
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