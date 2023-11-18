package group5.com.prm_autopartssale.adapter;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import group5.com.prm_autopartssale.fragment.home.DescriptionProductDetailFragment;
import group5.com.prm_autopartssale.fragment.home.ProductDetailFragment;
import group5.com.prm_autopartssale.fragment.home.ReviewProductDetailFragment;
import group5.com.prm_autopartssale.models.ProductItem;

public class ViewPageProductDetailAdapter extends FragmentStateAdapter {
  ProductItem productItem;
  public ViewPageProductDetailAdapter(
      @NonNull FragmentActivity fragmentActivity, ProductItem productItem) {
    super(fragmentActivity);
    this.productItem = productItem;
  }

  @NonNull
  @Override
  public Fragment createFragment(int position) {
    switch (position){
      case 0:
        DescriptionProductDetailFragment descriptionProductDetailFragment = new DescriptionProductDetailFragment();
        descriptionProductDetailFragment.setProductItem(productItem);
        return descriptionProductDetailFragment;
      case 1:
        ReviewProductDetailFragment reviewProductDetailFragment = new ReviewProductDetailFragment();
        reviewProductDetailFragment.setProductItem(productItem);
        return reviewProductDetailFragment;

      default:
        DescriptionProductDetailFragment descriptionProductDetailFragment1 = new DescriptionProductDetailFragment();
        descriptionProductDetailFragment1.setProductItem(productItem);
        return descriptionProductDetailFragment1;
    }
  }

  @Override
  public int getItemCount() {
    return 2;
  }

}
