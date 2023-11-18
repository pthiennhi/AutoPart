package group5.com.prm_autopartssale.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.adapter.NotificationAdapter.ViewHolder;
import group5.com.prm_autopartssale.models.Notification;
import group5.com.prm_autopartssale.models.Review;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder>{
  Context context;
  List<Review> list;

  public ReviewAdapter(Context context, List<Review> list) {
    this.context = context;
    this.list = list;
  }
  @NonNull
  @Override
  public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.review_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder holder, int position) {
    Review review = list.get(position);
    holder.tv_name.setText(review.getCustomer_id());
    holder.tv_product_rating.setText(review.getRating()+"");
    holder.tv_comment.setText(review.getComment());
    holder.tv_created_at.setText(review.getCreated_at());
  }

  @Override
  public int getItemCount() {
    return list.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder{
    TextView tv_name,tv_product_rating,tv_comment,tv_created_at;

    public ViewHolder(@NonNull android.view.View itemView) {
      super(itemView);
      tv_name = itemView.findViewById(R.id.tv_name);
      tv_product_rating = itemView.findViewById(R.id.tv_product_rating);
      tv_comment = itemView.findViewById(R.id.tv_comment);
      tv_created_at = itemView.findViewById(R.id.tv_created_at);
    }
  }
}
