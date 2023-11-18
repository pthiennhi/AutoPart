package group5.com.prm_autopartssale.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.api.ApiService;
import group5.com.prm_autopartssale.fragment.home.ProductDetailFragment;
import group5.com.prm_autopartssale.models.ProductItem;
import group5.com.prm_autopartssale.util.GetBitmapFromURL;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Retrofit;

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.ViewHolder> {

  Context context;
  List<ProductItem> list;
  private FragmentManager fragmentManager;

  ApiService apiService;

  public ProductItemAdapter(Context context, List<ProductItem> list, FragmentManager fragmentManager) {
    this.context = context;
    this.list = list;
    this.fragmentManager = fragmentManager;
  }

  @NonNull
  @Override
  public ProductItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.product_item, parent, false);
    return new ProductItemAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ProductItemAdapter.ViewHolder holder, int position) {
    ProductItem productItem = list.get(position);
    holder.tv_discount.setText("-" + productItem.getDiscount() * 100 + "%");
    holder.tv_price.setText(NumberFormat.getNumberInstance(new Locale("vi", "VN")).format(
        productItem.getPrice()) + "đ");
    holder.tv_price.setPaintFlags(holder.tv_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    holder.tv_product_name.setText(productItem.getName());
    if (productItem.getDiscount() == 0) {
      holder.tv_discount.setVisibility(View.GONE);
      holder.tv_price.setVisibility(View.GONE);
    } else {
      holder.tv_discount.setVisibility(View.VISIBLE);
      holder.tv_price.setVisibility(View.VISIBLE);
    }

    holder.tv_product_price.setText(NumberFormat.getNumberInstance(new Locale("vi", "VN")).format(
        productItem.getPrice() * (1 - productItem.getDiscount())) + "đ");

    holder.tv_product_rating.setText(productItem.getAvg_rating() + "");
    GetBitmapFromURL.loadBitmapFromURL(productItem.getThumbnail_url(),
        new GetBitmapFromURL.BitmapCallback() {
          @Override
          public void onBitmapLoaded(Bitmap bitmap) {
            holder.img_product.setImageBitmap(bitmap);
          }

          @Override
          public void onBitmapLoadFailed() {

          }
        });
  }

  @Override
  public int getItemCount() {
    return list.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    TextView tv_product_name, tv_product_price, tv_product_rating, tv_price, tv_discount;
    ImageView img_product;

    public ViewHolder(@NonNull android.view.View itemView) {
      super(itemView);

      tv_product_name = itemView.findViewById(R.id.tv_product_name);
      tv_product_price = itemView.findViewById(R.id.tv_product_price);
      tv_product_rating = itemView.findViewById(R.id.tv_product_rating);
      img_product = itemView.findViewById(R.id.iv_product_image);
      tv_price = itemView.findViewById(R.id.tv_price);
      tv_discount = itemView.findViewById(R.id.tv_discount);

      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          ProductItem productItem = list.get(getAdapterPosition());
          ProductDetailFragment productDetailFragment = new ProductDetailFragment(productItem);
          FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
          fragmentTransaction.replace(R.id.flFragment, productDetailFragment);
          fragmentTransaction.addToBackStack(null);
          fragmentTransaction.commit();
        }
      });
    }
  }
}
