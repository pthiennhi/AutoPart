package group5.com.prm_autopartssale.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.api.ApiService;
import group5.com.prm_autopartssale.models.Customer;
import group5.com.prm_autopartssale.models.CustomerCreateRequest;
import group5.com.prm_autopartssale.models.DataContainer;
import group5.com.prm_autopartssale.models.ResponseMessageCreateCustomer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

  ApiService apiService;
  private SignInClient oneTapClient;
  private BeginSignInRequest signInRequest;
  private BeginSignInRequest signUpRequest;
  private static final int REQ_ONE_TAP = 2;  // Can be any integer unique to the Activity.
  private boolean showOneTapUI = true;

  private static final int RC_SIGN_IN = 9001;
  String id, name, email;


  LinearLayout  btn_login_with_facebook, btn_login_with_google;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
//    btn_continue_as_guest = findViewById(R.id.btn_continue_as_guest);
    btn_login_with_facebook = findViewById(R.id.btn_login_with_facebook);
    btn_login_with_google = findViewById(R.id.btn_login_with_google);

    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
    Log.d("onboarding_completed", String.valueOf(sharedPreferences.getBoolean("onboarding_completed", false)));
    if (!sharedPreferences.getBoolean("onboarding_completed", false)){
      Intent intent = new Intent(LoginActivity.this, OnboardingActivity.class);
      startActivity(intent);
    }
    SharedPreferences sharedPreferences2 = getSharedPreferences("dataLogin", MODE_PRIVATE);
    if (sharedPreferences2.getString("customer_id", "") != ""){
      Intent intent = new Intent(LoginActivity.this, MainActivity.class);
      startActivity(intent);
    }


    oneTapClient = Identity.getSignInClient(this);
    signUpRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(GoogleIdTokenRequestOptions.builder()
            .setSupported(true)
            // Your server's client ID, not your Android client ID.
            .setServerClientId(getString(R.string.web_client_id))
            // Show all accounts on the device.
            .setFilterByAuthorizedAccounts(false)
            .build())
        .build();

    ActivityResultLauncher<IntentSenderRequest> activityResultLauncher =
        registerForActivityResult(
            new StartIntentSenderForResult(),
            result -> {
              try {
                SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(
                    result.getData());
                String idToken = credential.getGoogleIdToken();
                if (idToken != null) {
                  // Got an ID token from Google. Use it to authenticate
                  // with your backend.
                  email = credential.getId();
                  name = credential.getDisplayName();
                  id = credential.getId();
                  createCustomer(id, name, email);

                }
              } catch (ApiException e) {
                // ...
                e.printStackTrace();
              }

            });

    btn_login_with_google.setOnClickListener(v -> {
      Log.d("HEHE", "OnCLick");
      oneTapClient.beginSignIn(signUpRequest)
          .addOnSuccessListener(LoginActivity.this, new OnSuccessListener<BeginSignInResult>() {
            @Override
            public void onSuccess(BeginSignInResult result) {
              Log.d("HEHE", "OnSuccess");
              try {
                IntentSenderRequest intentSenderRequest = new IntentSenderRequest.Builder(
                    result.getPendingIntent().getIntentSender())
                    .build();
                activityResultLauncher.launch(intentSenderRequest);
              } catch (Exception e) {
                Log.e("CCCC", "Couldn't start One Tap UI: " + e.getLocalizedMessage());
              }
            }
          })
          .addOnFailureListener(LoginActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
              Log.d("HEHE", "OnFFF");
              // No saved credentials found. Launch the One Tap sign-up flow, or
              // do nothing and continue presenting the signed-out UI.
              Log.d("CCCC", e.getLocalizedMessage());
            }
          });
    });
    btn_login_with_facebook.setOnClickListener(v -> {
      showCommingSoonDialog();
    });

  }





  private void showCommingSoonDialog() {
    Dialog dialog = new Dialog(this, R.style.RoundedCornersDialog);
    dialog.setContentView(R.layout.modal_comming_soon);

    // Get the current window attributes
    WindowManager.LayoutParams params = dialog.getWindow().getAttributes();

    // Set the width of the dialog (e.g., 80% of the screen width)
    DisplayMetrics displayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    int dialogWidth = (int) (displayMetrics.widthPixels * 0.8); // Adjust the percentage as needed

    // Set the width and height of the dialog
    params.width = dialogWidth;
    params.height = WindowManager.LayoutParams.WRAP_CONTENT; // You can set this to a specific height if needed

    // Apply the attributes to the dialog
    dialog.getWindow().setAttributes(params);

    Button btn_back = dialog.findViewById(R.id.btn_back);
    btn_back.setOnClickListener(v -> {
      dialog.dismiss();
    });

    dialog.show();
  }

  private void createCustomer(String id, String name, String email){
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://swd-six.vercel.app/api/")
        .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
        .build();

    apiService = retrofit.create(ApiService.class);

    CustomerCreateRequest customer = new CustomerCreateRequest(name, email, id);
    Call<ResponseMessageCreateCustomer> responseMessageCreateCustomerCall = apiService.createCustomer(customer);

    responseMessageCreateCustomerCall.enqueue(new Callback<ResponseMessageCreateCustomer>() {
      @Override
      public void onResponse(Call<ResponseMessageCreateCustomer> call,
          Response<ResponseMessageCreateCustomer> response) {
        if (response.isSuccessful()){
          ResponseMessageCreateCustomer responseMessageCreateCustomer = response.body();
          if (responseMessageCreateCustomer != null){
           switch (responseMessageCreateCustomer.getStatus()){
             case "SUCCESS":
               Intent intent = new Intent(LoginActivity.this, FillProfileActivity.class);
               SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("customer_id", id);
                editor.apply();
               startActivity(intent);

               break;
             case "ACCOUNT_EXISTED":
               setCustomer(id);

               break;
             case "FAILED":
                Toast.makeText(LoginActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
                break;

             default:
               throw new IllegalStateException("Unexpected value: " + responseMessageCreateCustomer.getMessage());
            }
          }
        }
      }

      @Override
      public void onFailure(Call<ResponseMessageCreateCustomer> call, Throwable t) {

      }
    });

  }

  private void setCustomer(String customer_id){
//    SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
//    customer_id = sharedPreferences.getString("customer_id", "");

    Log.d("customer_id", customer_id);
    apiService.getCustomer(customer_id);

    Call<Customer> customerCall = apiService.getCustomer(customer_id);

    customerCall.enqueue(new Callback<Customer>() {
      @Override
      public void onResponse(Call<Customer> call, Response<Customer> response) {
        Customer customer2 = response.body();
        DataContainer dataContainer = DataContainer.getInstance();
        dataContainer.setCustomer(customer2);
        SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("customer_id", id);
        editor.apply();
        Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent1);

      }

      @Override
      public void onFailure(Call<Customer> call, Throwable t) {
        Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();

      }
    });
  }

}