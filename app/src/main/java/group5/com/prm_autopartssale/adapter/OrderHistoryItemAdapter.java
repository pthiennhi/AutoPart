package group5.com.prm_autopartssale.adapter;

import android.content.Context;
import android.graphics.Bitmap;
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
import group5.com.prm_autopartssale.util.GetBitmapFromURL;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;



public class OrderHistoryItemAdapter extends
    RecyclerView.Adapter<OrderHistoryItemAdapter.ViewHolder> {


  Context context;
  List<OrderHistoryItem> list;


  public OrderHistoryItemAdapter(Context context, List<OrderHistoryItem> list) {
    this.context = context;
    this.list = list;
  }

  @NonNull
  @Override
  public OrderHistoryItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.order_history_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull OrderHistoryItemAdapter.ViewHolder holder,
      int position) {
    OrderHistoryItem orderHistoryItem = list.get(position);
    holder.tv_title.setText("Đơn hàng mã " + orderHistoryItem.getId());
    holder.tv_status.setText(orderHistoryItem.getStatus());
    switch (orderHistoryItem.getStatus()){
      case Constant.STATUS_CONFIRMED:
        holder.tv_status.setTextColor(context.getResources().getColor(R.color.blue, null));
        holder.tv_status.setBackgroundTintList(context.getResources().getColorStateList(R.color.backgroundBlue, null));
        break;
      case Constant.STATUS_PAID:
        holder.tv_status.setTextColor(context.getResources().getColor(R.color.purple, null));
        holder.tv_status.setBackgroundTintList(context.getResources().getColorStateList(R.color.backgroundPurple, null));
        break;
      case Constant.STATUS_ISSHIPPING:
        holder.tv_status.setTextColor(context.getResources().getColor(R.color.orange, null));
        holder.tv_status.setBackgroundTintList(context.getResources().getColorStateList(R.color.backgroundOrange, null));
        break;
      case Constant.STATUS_CANCELED:
        holder.tv_status.setTextColor(context.getResources().getColor(R.color.error, null));
        holder.tv_status.setBackgroundTintList(context.getResources().getColorStateList(R.color.backgroundPink, null));
        break;

      case Constant.STATUS_COMPLETED:
        holder.tv_status.setTextColor(context.getResources().getColor(R.color.success, null));
        holder.tv_status.setBackgroundTintList(context.getResources().getColorStateList(R.color.backgroundGreen, null));
        break;
      default:
        holder.tv_status.setTextColor(context.getResources().getColor(R.color.info, null));
        holder.tv_status.setBackgroundTintList(context.getResources().getColorStateList(R.color.backgroundBlue, null));
        break;
    }



    holder.tv_total_price.setText(NumberFormat.getNumberInstance(new Locale("vi", "VN")).format(
        orderHistoryItem.getTotal_price()) + "đ");
    holder.tv_total_item.setText(orderHistoryItem.getTotal_item() + " sp");
    GetBitmapFromURL.loadBitmapFromURL(orderHistoryItem.getImage_url(), new GetBitmapFromURL.BitmapCallback() {
      @Override
      public void onBitmapLoaded(Bitmap bitmap) {
        holder.iv_thumbnail_item.setImageBitmap(bitmap);
      }

      @Override
      public void onBitmapLoadFailed() {

      }
    });

    holder.btn_detail.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        OrderDetailFragment orderDetailFragment = new OrderDetailFragment();
        orderDetailFragment.setOrderId(orderHistoryItem.getId());
        FragmentTransaction fragmentTransaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, orderDetailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

      }
    });

  }

  @Override
  public int getItemCount() {
    return list.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    TextView tv_title, tv_status, tv_total_price, tv_total_item;
    ImageView iv_thumbnail_item;
    Button btn_detail;

    public ViewHolder(@NonNull android.view.View itemView) {
      super(itemView);

      tv_title = itemView.findViewById(R.id.tv_title);
      tv_status = itemView.findViewById(R.id.tv_status);
      tv_total_price = itemView.findViewById(R.id.tv_total_price);
      tv_total_item = itemView.findViewById(R.id.tv_total_item);
      btn_detail = itemView.findViewById(R.id.btn_detail);
      iv_thumbnail_item = itemView.findViewById(R.id.iv_thumbnail_item);


    }
  }

  private static final String TAG = "OrderItemAdapter";
}
