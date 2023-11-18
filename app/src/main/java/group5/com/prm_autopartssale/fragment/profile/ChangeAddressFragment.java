package group5.com.prm_autopartssale.fragment.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.api.ApiService;
import group5.com.prm_autopartssale.models.Province;
import group5.com.prm_autopartssale.models.Customer;
import group5.com.prm_autopartssale.models.CustomerUpdateRequest;
import group5.com.prm_autopartssale.models.DataContainer;
import group5.com.prm_autopartssale.models.District;
import group5.com.prm_autopartssale.models.ResponseMessage;
import group5.com.prm_autopartssale.models.Ward;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass. Use the {@link ChangeAddressFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class ChangeAddressFragment extends Fragment {

  private ApiService apiService;

  Customer customer;

  Spinner provinceSpinner, districtSpinner, wardSpinner;
  EditText edtNameAdress, edtAddressDetail;
  Button btnChange;
  ImageView iv_back, iv_more;

  List<Province> provinces;
  List<District> districts;
  List<Ward> wards;
  int provinceId = 1 , districtId = 1;
  String wardCode = "1";


  public ChangeAddressFragment() {
    // Required empty public constructor
  }


  public static ChangeAddressFragment newInstance(String param1, String param2) {
    ChangeAddressFragment fragment = new ChangeAddressFragment();

    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_change_address, container, false);
    provinceSpinner = view.findViewById(R.id.citySpinner);
    districtSpinner = view.findViewById(R.id.districtSpinner);
    wardSpinner = view.findViewById(R.id.wardSpinner);
    edtNameAdress = view.findViewById(R.id.edtNameAdress);
    edtAddressDetail = view.findViewById(R.id.edtAddressDetail);
    btnChange = view.findViewById(R.id.btnChange);
    iv_back = view.findViewById(R.id.iv_back);
    iv_more = view.findViewById(R.id.iv_more);


    DataContainer dataContainer = DataContainer.getInstance();
    customer = dataContainer.getCustomer();
    edtNameAdress.setText(customer.getAddress_name());
    edtAddressDetail.setText(customer.getAddress_details());

    provinces = new ArrayList<>();
    districts = new ArrayList<>();
    wards = new ArrayList<>();

    Retrofit retrofitServer = new Retrofit.Builder()
        .baseUrl("https://swd-six.vercel.app/api/")
        .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
        .build();

    apiService = retrofitServer.create(ApiService.class);


    Call<List<Province>> provinceCall = apiService.getProvinces();

    provinceCall.enqueue(new Callback<List<Province>>() {
      @Override
      public void onResponse(Call<List<Province>> call, Response<List<Province>> response) {
        if (response.isSuccessful()) {
          provinces = response.body();
          List<String> provinceNames = new ArrayList<>();
          for (Province province : provinces) {
            provinceNames.add(province.getName());
          }

          // Populate the citySpinner with city names
          ArrayAdapter<String> provinceAdapter = new ArrayAdapter<>(getContext(),
              android.R.layout.simple_spinner_item, provinceNames);
          provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          provinceSpinner.setAdapter(provinceAdapter);
          if (customer != null) {
            provinceSpinner.setSelection(getProvincePosition(customer.getProvince_id()));

          } else {
            provinceSpinner.setSelection(0);
          }

        }
      }

      @Override
      public void onFailure(Call<List<Province>> call, Throwable t) {
        Log.d("TAG", "onFailure: " + t.getMessage());

      }
    });

    provinceSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        provinceId = provinces.get(position).getId();
        Call<List<District>> districtCall = apiService.getDistricts(provinceId);
        districtCall.enqueue(new Callback<List<District>>() {
          @Override
          public void onResponse(Call<List<District>> call, Response<List<District>> response) {
            if (response.isSuccessful()) {
              districts = response.body();
              List<String> districtNames = new ArrayList<>();
              for (District district : districts) {
                districtNames.add(district.getName());
              }

              // Populate the citySpinner with city names
              ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(getContext(),
                  android.R.layout.simple_spinner_item, districtNames);
              districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
              districtSpinner.setAdapter(districtAdapter);
              if (customer != null) {
                districtSpinner.setSelection(getDistrictPosition(customer.getDistrict_id()));

              } else {
                districtSpinner.setSelection(0);
              }

            }
          }

          @Override
          public void onFailure(Call<List<District>> call, Throwable t) {
            Log.d("TAG", "onFailure: " + t.getMessage());

          }
        });
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });

    districtSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        districtId = districts.get(position).getId();
        Call<List<Ward>> wardCall = apiService.getWards(districtId);
        wardCall.enqueue(new Callback<List<Ward>>() {
          @Override
          public void onResponse(Call<List<Ward>> call, Response<List<Ward>> response) {
            if (response.isSuccessful()) {
              wards = response.body();
              List<String> wardNames = new ArrayList<>();
              for (Ward ward : wards) {
                wardNames.add(ward.getName());
              }

              // Populate the citySpinner with city names
              ArrayAdapter<String> wardAdapter = new ArrayAdapter<>(getContext(),
                  android.R.layout.simple_spinner_item, wardNames);
              wardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
              wardSpinner.setAdapter(wardAdapter);
              if (customer != null) {
                wardSpinner.setSelection(getWardPosition(customer.getWard_code()));

              } else {
                wardSpinner.setSelection(0);
              }

            }
          }

          @Override
          public void onFailure(Call<List<Ward>> call, Throwable t) {
            Log.d("TAG", "onFailure: " + t.getMessage());

          }
        });
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });

    wardSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        wardCode = wards.get(position).getCode();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });
    btnChange.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        apiService = retrofitServer.create(ApiService.class);
        String nameAddress = edtNameAdress.getText().toString();
        String addressDetail = edtAddressDetail.getText().toString();

        if(nameAddress.isEmpty()){
          edtNameAdress.setError("Name address is required");
          edtNameAdress.requestFocus();
          return;
        }
        if(addressDetail.isEmpty()){
          edtAddressDetail.setError("Address detail is required");
          edtAddressDetail.requestFocus();
          return;
        }
        CustomerUpdateRequest updateRequest = new CustomerUpdateRequest();
        updateRequest.setAddress_name(nameAddress);
        updateRequest.setAddress_details(addressDetail);
        updateRequest.setProvince_id(provinceId);
        updateRequest.setDistrict_id(districtId);
        updateRequest.setWard_code(wardCode);
        Call<ResponseMessage> customerCall1 = apiService.updateCustomer(customer.getId(), updateRequest);
        customerCall1.enqueue(new retrofit2.Callback<ResponseMessage>() {
          @Override
          public void onResponse(Call<ResponseMessage> call, retrofit2.Response<ResponseMessage> response) {
            String message = response.body().getMessage();
            Log.d("CC", response.toString());
            if(response.isSuccessful()){
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

              Call<Customer> customerCall2 = apiService.getCustomer(customer.getId());
              customerCall2.enqueue(new retrofit2.Callback<Customer>() {
                @Override
                public void onResponse(Call<Customer> call, retrofit2.Response<Customer> response) {
                  customer = response.body();
                  DataContainer dataContainer = DataContainer.getInstance();
                  dataContainer.setCustomer(customer);
                }

                @Override
                public void onFailure(Call<Customer> call, Throwable t) {
                  Log.d("UpdateCustomerError", "Update failed1: " + t.getMessage());
                  Toast.makeText(getContext(), "Update failed", Toast.LENGTH_SHORT).show();

                }
              });
              if(requireActivity().getSupportFragmentManager().getBackStackEntryCount() > 0){
                requireActivity().getSupportFragmentManager().popBackStack();
              }
            }
            else {
              Log.d("UpdateCustomerError", "Update failed2: " + message);
              Toast.makeText(getContext(), "Update failed", Toast.LENGTH_SHORT).show();
            }
          }

          @Override
          public void onFailure(Call<ResponseMessage> call, Throwable t) {
            Log.d("UpdateCustomerError", "Update failed3: " + t.getMessage());
            Toast.makeText(getContext(), "Update failed", Toast.LENGTH_SHORT).show();

          }
        });


      }
    });

    iv_back.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if(requireActivity().getSupportFragmentManager().getBackStackEntryCount() > 0){
          requireActivity().getSupportFragmentManager().popBackStack();
        }
      }
    });

    iv_more.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        PopupMenu popupMenu = new PopupMenu(getContext(), iv_more);
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



    return view;
  }

  private int getProvincePosition(int cityCode){
    for(int i = 0; i < provinces.size(); i++){
      if(provinces.get(i).getId() == cityCode){
        return i;
      }
    }
    return 0;
  }

  private int getDistrictPosition(int districtCode){
    for(int i = 0; i < districts.size(); i++){
      if(districts.get(i).getId() == districtCode){
        return i;
      }
    }
    return 0;
  }

  private int getWardPosition(String wardCode){
    for(int i = 0; i < wards.size(); i++){
      if(wards.get(i).getCode().equals(wardCode)){
        return i;
      }
    }
    return 0;
  }
}