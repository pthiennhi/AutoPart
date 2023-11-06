package group5.com.prm_autopartssale.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderDetailItemAdapter extends RecyclerView.Adapter<OrderDetailItemAdapter.ViewHolder>{


  @NonNull
  @Override
  public OrderDetailItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType) {
    return null;
  }

  @Override
  public void onBindViewHolder(@NonNull OrderDetailItemAdapter.ViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return 0;
  }

  public class ViewHolder extends RecyclerView.ViewHolder{
    public ViewHolder(@NonNull View itemView) {
      super(itemView);
    }
  }
}
