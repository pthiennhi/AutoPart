package group5.com.prm_autopartssale.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.viewpager2.widget.ViewPager2;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.adapter.ViewPageOnboardingAdapter;

public class OnboardingActivity extends AppCompatActivity {
  private static final String PREFS_NAME = "MyPrefsFile";
  private static final String ONBOARDING_COMPLETE = "onboarding_completed";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_onboarding);

    // Check if onboarding has been completed
    if (isOnboardingComplete()) {
      // Launch the onboarding activity
      Intent intent = new Intent(this, LoginActivity.class);
      startActivity(intent);

    } else {
      // Mark onboarding as complete
      setOnboardingComplete();
    }

    ViewPager2 viewPager2 = findViewById(R.id.onboardingViewPager2);
    View indicator1 = findViewById(R.id.onboardingIndicator1);
    View indicator2 = findViewById(R.id.onboardingIndicator2);
    viewPager2.setAdapter(new ViewPageOnboardingAdapter(this));

    viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
      @Override
      public void onPageSelected(int position) {
        super.onPageSelected(position);
        switch (position) {
          case 0:
            indicator1.setBackgroundTintList(getResources().getColorStateList(R.color.black, null));
            indicator2.setBackgroundTintList(getResources().getColorStateList(R.color.greyscale200, null));
            break;
          case 1:
            indicator2.setBackgroundTintList(getResources().getColorStateList(R.color.black, null));
            indicator1.setBackgroundTintList(getResources().getColorStateList(R.color.greyscale200, null));
            break;
        }
      }
    } );
  }

  private boolean isOnboardingComplete() {
    SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    return preferences.getBoolean(ONBOARDING_COMPLETE, false);
  }

  private void setOnboardingComplete() {
    SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = preferences.edit();
    editor.putBoolean(ONBOARDING_COMPLETE, true);
    editor.apply();
  }
}