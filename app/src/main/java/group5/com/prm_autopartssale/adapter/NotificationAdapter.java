package group5.com.prm_autopartssale.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.models.Notification;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{
  Context context;
  List<Notification> list;

  public NotificationAdapter(Context context, List<Notification> list) {
    this.context = context;
    this.list = list;
  }

  @NonNull
  @Override
  public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.notification_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {
    Notification notification = list.get(position);
    holder.txtTitle.setText(notification.getTitle());
    holder.txtContent.setText(notification.getContent());
    holder.txtDate.setText(notification.getCreated_at().toString());
  }

  @Override
  public int getItemCount() {
    return list.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder{
    TextView txtTitle, txtContent, txtDate;
    public ViewHolder(@NonNull android.view.View itemView) {
      super(itemView);

      txtTitle = itemView.findViewById(R.id.tv_title);
      txtContent = itemView.findViewById(R.id.tv_content);
      txtDate = itemView.findViewById(R.id.tv_date);

    }
  }
}
