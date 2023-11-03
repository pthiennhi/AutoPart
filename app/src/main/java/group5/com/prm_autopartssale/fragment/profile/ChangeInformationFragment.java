package group5.com.prm_autopartssale.fragment.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.api.ApiService;
import group5.com.prm_autopartssale.fragment.CategoryFragment;
import group5.com.prm_autopartssale.fragment.HomeFragment;
import group5.com.prm_autopartssale.fragment.NotificationFragment;
import group5.com.prm_autopartssale.fragment.OrderFragment;
import group5.com.prm_autopartssale.fragment.ProfileFragment;
import group5.com.prm_autopartssale.models.Customer;
import group5.com.prm_autopartssale.models.CustomerUpdateRequest;
import group5.com.prm_autopartssale.models.DataContainer;
import group5.com.prm_autopartssale.models.ResponseMessage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass. Use the {@link ChangeInformationFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class ChangeInformationFragment extends Fragment {
  private ApiService apiService;
  ImageView ivBack, ivMore;
  EditText edtName, edtPhone;
  Button btnChange;

  Customer customer;



  public ChangeInformationFragment() {
    // Required empty public constructor
  }


  public static ChangeInformationFragment newInstance(String param1, String param2) {
    ChangeInformationFragment fragment = new ChangeInformationFragment();

    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_change_information, container, false);
    ivBack = view.findViewById(R.id.iv_back);
    ivMore = view.findViewById(R.id.iv_more);
    edtName = view.findViewById(R.id.edtName);
    edtPhone = view.findViewById(R.id.edtPhone);
    btnChange = view.findViewById(R.id.btnChange);

    DataContainer dataContainer = DataContainer.getInstance();
    customer = dataContainer.getCustomer();
    edtName.setText(customer.getName());
    edtPhone.setText(customer.getPhone_number());
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://swd-six.vercel.app/api/")
        .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
        .build();

    apiService = retrofit.create(ApiService.class);



    ivBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(requireActivity().getSupportFragmentManager().getBackStackEntryCount() > 0){
          requireActivity().getSupportFragmentManager().popBackStack();
        }
      }
    });
    ivMore.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        PopupMenu popupMenu = new PopupMenu(getContext(), ivMore);
        popupMenu.getMenuInflater().inflate(R.menu.header_menu, popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(item -> {
          if(item.getItemId() == R.id.action_exit_app){
            if (getActivity() != null) {
              getActivity().finish();
            }
          }
          return false;
        });

      }
    });
    btnChange.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        String name = edtName.getText().toString();
        String phone = edtPhone.getText().toString();
        if(name.isEmpty()){
          edtName.setError("Name is required");
          edtName.requestFocus();
          return;
        }
        if(phone.isEmpty()){
          edtPhone.setError("Phone is required");
          edtPhone.requestFocus();
          return;
        }
        if (phone.length() < 10 || phone.length() > 11) {
          edtPhone.setError("Phone must be 10 or 11 digits");
          edtPhone.requestFocus();
          return;
        }
        CustomerUpdateRequest updateRequest = new CustomerUpdateRequest();
        updateRequest.setName(name);
        updateRequest.setPhone_number(phone);
        Call<ResponseMessage> customerCall = apiService.updateCustomer(customer.getId(), updateRequest);
        customerCall.enqueue(new Callback<ResponseMessage>() {
          @Override
          public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
            String message = response.body().getMessage();
            if(response.isSuccessful()){
              Log.d("Update Successfully", "onResponse: " + message);
              LayoutInflater inflater = getLayoutInflater();
              View layout = inflater.inflate(R.layout.custom_toast, null);
              ImageView toastImage = (ImageView) layout.findViewById(R.id.toast_image);
              TextView toastText = (TextView) layout.findViewById(R.id.toast_text);
              toastImage.setImageResource(R.drawable.ic_check_circle);
              toastText.setText(message);
              Toast toast = new Toast(requireActivity().getApplicationContext());
              toast.setGravity(Gravity.TOP, 0, 0);
              toast.setDuration(Toast.LENGTH_LONG);
              toast.setView(layout);
              toast.show();

              Call<Customer> customerCall = apiService.getCustomer(customer.getId());
              customerCall.enqueue(new Callback<Customer>() {
                @Override
                public void onResponse(Call<Customer> call, Response<Customer> response) {
                  customer = response.body();
                  DataContainer dataContainer = DataContainer.getInstance();
                  dataContainer.setCustomer(customer);
                }

                @Override
                public void onFailure(Call<Customer> call, Throwable t) {
                  Toast.makeText(getContext(), "Update failed", Toast.LENGTH_SHORT).show();

                }
              });
              if(requireActivity().getSupportFragmentManager().getBackStackEntryCount() > 0){
                requireActivity().getSupportFragmentManager().popBackStack();
              }
            }else{
              Toast.makeText(getContext(), "Update failed", Toast.LENGTH_SHORT).show();

            }

          }

          @Override
          public void onFailure(Call<ResponseMessage> call, Throwable t) {
            Toast.makeText(getContext(), "Update failed", Toast.LENGTH_SHORT).show();

          }
        });

      }
    });

    return view;
  }



}

