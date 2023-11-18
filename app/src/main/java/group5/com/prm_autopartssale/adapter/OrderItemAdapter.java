package group5.com.prm_autopartssale.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.constant.Constant;
import group5.com.prm_autopartssale.fragment.profile.OrderDetailFragment;
import group5.com.prm_autopartssale.models.OrderHistoryItem;
import group5.com.prm_autopartssale.models.OrderItem;
import group5.com.prm_autopartssale.util.GetBitmapFromURL;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


public class OrderItemAdapter extends
    RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {


  Context context;
  List<OrderItem> list;




  public OrderItemAdapter(Context context, List<OrderItem> list) {
    this.context = context;
    this.list = list;
  }

  @NonNull
  @Override
  public OrderItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.order_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull OrderItemAdapter.ViewHolder holder,
      int position) {
    OrderItem orderItem = list.get(position);
    Log.d("OrderItemAdapter", "onBindViewHolder: " + orderItem.getName());

    holder.tv_product_name.setText(orderItem.getName());
    holder.tv_price.setText(NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(orderItem.getPrice()));
    holder.tv_total_product_item.setText(orderItem.getQuantity() + "");
    holder.tv_minus_product_item.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (orderItem.getQuantity() > 1) {
          orderItem.setQuantity(orderItem.getQuantity() - 1);
          holder.tv_total_product_item.setText(orderItem.getQuantity() + "");

        }
      }
    });
    holder.tv_plus_product_item.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        orderItem.setQuantity(orderItem.getQuantity() + 1);
        holder.tv_total_product_item.setText(orderItem.getQuantity() + "");

      }
    });

    GetBitmapFromURL.loadBitmapFromURL("https://caautoparts.com/cdn/shop/products/RS-JBR-003-YE_750x_crop_center.jpg",
        new GetBitmapFromURL.BitmapCallback() {
          @Override
          public void onBitmapLoaded(Bitmap bitmap) {
            holder.iv_thumbnail_item.setImageBitmap(bitmap);
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

    ImageView iv_thumbnail_item;
    TextView tv_product_name,tv_price, tv_total_product_item, tv_minus_product_item, tv_plus_product_item;


    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      iv_thumbnail_item = itemView.findViewById(R.id.iv_thumbnail_item);
      tv_product_name = itemView.findViewById(R.id.tv_product_name);
      tv_price = itemView.findViewById(R.id.tv_price);
      tv_total_product_item = itemView.findViewById(R.id.tv_total_product_item);
      tv_minus_product_item = itemView.findViewById(R.id.tv_minus_product_item);
      tv_plus_product_item = itemView.findViewById(R.id.tv_plus_product_item);



    }
  }


  private static final String TAG = "OrderItemAdapter";
}
