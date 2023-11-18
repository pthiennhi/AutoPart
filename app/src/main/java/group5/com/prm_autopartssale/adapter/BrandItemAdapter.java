package group5.com.prm_autopartssale.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.adapter.NotificationAdapter.ViewHolder;
import group5.com.prm_autopartssale.models.Brand;
import group5.com.prm_autopartssale.models.Notification;
import group5.com.prm_autopartssale.util.GetBitmapFromURL;
import java.util.List;

public class BrandItemAdapter extends RecyclerView.Adapter<BrandItemAdapter.ViewHolder>{
  Context context;
  List<Brand> list;

  public BrandItemAdapter(Context context, List<Brand> list) {
    this.context = context;
    this.list = list;
  }
  @NonNull
  @Override
  public BrandItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.brand_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull BrandItemAdapter.ViewHolder holder, int position) {

    Brand brand = list.get(position);

    holder.tv_brand_name.setText(brand.getName());
    GetBitmapFromURL.loadBitmapFromURL(brand.getImage_url(), new GetBitmapFromURL.BitmapCallback() {
      @Override
      public void onBitmapLoaded(Bitmap bitmap) {
        holder.img_brand.setImageBitmap(bitmap);
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

  public class ViewHolder extends RecyclerView.ViewHolder{
    TextView tv_brand_name;
    ImageView img_brand;

    public ViewHolder(@NonNull android.view.View itemView) {
      super(itemView);

      tv_brand_name = itemView.findViewById(R.id.tv_brand_name);
      img_brand = itemView.findViewById(R.id.img_brand);
    }
  }
}
