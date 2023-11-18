package group5.com.prm_autopartssale.fragment;

import android.content.Intent;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.activity.MainActivity;
import group5.com.prm_autopartssale.adapter.BrandItemAdapter;
import group5.com.prm_autopartssale.api.ApiService;
import group5.com.prm_autopartssale.fragment.home.DiscountProductsFragment;
import group5.com.prm_autopartssale.fragment.home.InformationShopFragment;
import group5.com.prm_autopartssale.models.Brand;
import group5.com.prm_autopartssale.models.Customer;
import group5.com.prm_autopartssale.models.DataContainer;
import group5.com.prm_autopartssale.models.ProductItem;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Text;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass. Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
  ApiService apiService;
  Customer customer;


  public HomeFragment() {
    // Required empty public constructor
  }

  public static HomeFragment newInstance(String param1, String param2) {
    HomeFragment fragment = new HomeFragment();

    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_home, container, false);

    BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationView);
    int selectedItemId = R.id.home;
    bottomNavigationView.setSelectedItemId(selectedItemId);

    ImageSlider imageSlider = view.findViewById(R.id.image_slider);

    ArrayList<SlideModel> slideModels = new ArrayList<>();

    slideModels.add(new SlideModel(R.drawable.banner1, ScaleTypes.FIT));
    slideModels.add(new SlideModel(R.drawable.banner2, ScaleTypes.FIT));
    imageSlider.setImageList(slideModels);
    imageSlider.setMinimumWidth(333);
    imageSlider.setMinimumHeight(200);

    RecyclerView recyclerView = view.findViewById(R.id.rv_brands);

    List<Brand> listBrand = new ArrayList<Brand>();

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://swd-six.vercel.app/api/")
        .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
        .build();

    DataContainer dataContainer = DataContainer.getInstance();
    if(dataContainer.getListBrand() != null) {
      listBrand.addAll(dataContainer.getListBrand().subList(0, 8));
      recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
      recyclerView.setAdapter(new BrandItemAdapter(getContext(), listBrand));
    } else {
      apiService = retrofit.create(ApiService.class);
      Call<List<Brand>> brandCall = apiService.getBrands();
      brandCall.enqueue(new Callback<List<Brand>>() {
        @Override
        public void onResponse(Call<List<Brand>> call, Response<List<Brand>> response) {
          listBrand.addAll(response.body().subList(0, 8));
          dataContainer.setListBrand(listBrand);
          recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
          recyclerView.setAdapter(new BrandItemAdapter(getContext(), listBrand));
        }

        @Override
        public void onFailure(Call<List<Brand>> call, Throwable t) {

        }
      });
    }
    RecyclerView recyclerView1 = view.findViewById(R.id.rv_products);
    if(dataContainer.getListProduct() != null) {
      List<ProductItem> listProduct = dataContainer.getListProduct();
      recyclerView1.setLayoutManager(new GridLayoutManager(getContext(),2));
      recyclerView1.setAdapter(new group5.com.prm_autopartssale.adapter.ProductItemAdapter(getContext(), listProduct, getParentFragmentManager()));
      Log.d("listProduct", listProduct.size() + "");
    } else {
      apiService = retrofit.create(ApiService.class);
      Call<List<ProductItem>> productItemCall = apiService.getProducts();

      productItemCall.enqueue(new Callback<List<ProductItem>>() {
        @Override
        public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
          List<ProductItem> listProduct = response.body();
          dataContainer.setListProduct(listProduct);
          recyclerView1.setLayoutManager(new GridLayoutManager(getContext(),2));
          recyclerView1.setAdapter(new group5.com.prm_autopartssale.adapter.ProductItemAdapter(getContext(), listProduct, getParentFragmentManager()));
        }

        @Override
        public void onFailure(Call<List<ProductItem>> call, Throwable t) {

        }
      });
    }





    TextView tvSeeAll = view.findViewById(R.id.tv_viewall_discount);
    TextView tv_viewall_top_product = view.findViewById(R.id.tv_viewall_top_product);
    tv_viewall_top_product.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        DiscountProductsFragment discountProductsFragment = new DiscountProductsFragment();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, discountProductsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
      }
    });

    tvSeeAll.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        DiscountProductsFragment discountProductsFragment = new DiscountProductsFragment();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, discountProductsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
      }
    });



    TextView tv_name = view.findViewById(R.id.tv_name);
    customer = dataContainer.getCustomer();
    if(customer != null) {
      tv_name.setText(customer.getName());
    }

    ImageView iv_information = view.findViewById(R.id.iv_information);
    iv_information.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        InformationShopFragment informationShopFragment = new InformationShopFragment();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, informationShopFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
      }
    });

    return view;
  }


}