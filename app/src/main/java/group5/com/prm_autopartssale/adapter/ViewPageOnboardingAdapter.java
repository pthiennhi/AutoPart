package group5.com.prm_autopartssale.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import group5.com.prm_autopartssale.fragment.onboarding.Onboarding1Fragment;
import group5.com.prm_autopartssale.fragment.onboarding.Onboarding2Fragment;

public class ViewPageOnboardingAdapter extends FragmentStateAdapter {

  public ViewPageOnboardingAdapter(@NonNull FragmentActivity fragmentActivity) {
    super(fragmentActivity);
  }

  @NonNull
  @Override
  public Fragment createFragment(int position) {
    switch (position) {
      case 0:
        return new Onboarding1Fragment();
      case 1:
        return new Onboarding2Fragment();
      default:
        return new Onboarding1Fragment();
    }
  }

  @Override
  public int getItemCount() {
    return 2;
  }
}
