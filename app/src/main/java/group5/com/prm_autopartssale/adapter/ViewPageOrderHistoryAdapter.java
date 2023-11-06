package group5.com.prm_autopartssale.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import group5.com.prm_autopartssale.fragment.profile.CompletedOrderFragment;
import group5.com.prm_autopartssale.fragment.profile.ProcessingOrderFragment;

public class ViewPageOrderHistoryAdapter extends FragmentStateAdapter {

  public ViewPageOrderHistoryAdapter(
      @NonNull FragmentActivity fragmentActivity) {
    super(fragmentActivity);
  }

  @NonNull
  @Override
  public Fragment createFragment(int position) {
     switch (position){
       case 0:
         return new ProcessingOrderFragment();
       case 1:
         return new CompletedOrderFragment();

       default:
         return new ProcessingOrderFragment();
     }
  }

  @Override
  public int getItemCount() {
    return 2;
  }
}
