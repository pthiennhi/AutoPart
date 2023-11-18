package group5.com.prm_autopartssale.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import group5.com.prm_autopartssale.R;
import group5.com.prm_autopartssale.api.ApiService;
import group5.com.prm_autopartssale.models.Customer;
import group5.com.prm_autopartssale.models.CustomerUpdateRequest;
import group5.com.prm_autopartssale.models.DataContainer;
import group5.com.prm_autopartssale.models.District;
import group5.com.prm_autopartssale.models.Province;
import group5.com.prm_autopartssale.models.ResponseMessage;
import group5.com.prm_autopartssale.models.Ward;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FillProfileActivity extends AppCompatActivity {

  EditText edtName, edtPhone;
  Spinner provinceSpinner, districtSpinner, wardSpinner;
  EditText edtNameAdress, edtAddressDetail;

  Button btnChange;
  List<Province> provinces;
  List<District> districts;
  List<Ward> wards;

  Customer customer;
  String customer_id;
  int provinceId = 1, districtId = 1;
  String wardCode = "1";

  ApiService apiService;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fill_profile);

    SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
    customer_id = sharedPreferences.getString("customer_id", "");


    edtName = findViewById(R.id.edtName);
    edtPhone = findViewById(R.id.edtPhone);
    provinceSpinner = findViewById(R.id.citySpinner);
    districtSpinner = findViewById(R.id.districtSpinner);
    wardSpinner = findViewById(R.id.wardSpinner);
    edtNameAdress = findViewById(R.id.edtNameAdress);
    edtAddressDetail = findViewById(R.id.edtAddressDetail);
    btnChange = findViewById(R.id.btnChange);

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
          ArrayAdapter<String> provinceAdapter = new ArrayAdapter<>(getApplicationContext(),
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
              ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(getApplicationContext(),
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
              ArrayAdapter<String> wardAdapter = new ArrayAdapter<>(getApplicationContext(),
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

    btnChange.setOnClickListener(v -> {
      String customerName = edtName.getText().toString();
      String customerPhone = edtPhone.getText().toString();
      String customerAddressName = edtNameAdress.getText().toString();
      String customerAddressDetails = edtAddressDetail.getText().toString();

      if (customerName.isEmpty()) {
        edtName.setError("Name is required");
        edtName.requestFocus();
        return;
      }
      if (customerPhone.isEmpty()) {
        edtPhone.setError("Phone is required");
        edtPhone.requestFocus();
        return;
      }
      if (customerPhone.length() < 10 || customerPhone.length() > 11) {
        edtPhone.setError("Phone must be 10 or 11 digits");
        edtPhone.requestFocus();
        return;
      }
      if (customerAddressName.isEmpty()) {
        edtNameAdress.setError("Name is required");
        edtNameAdress.requestFocus();
        return;
      }
      if (customerAddressDetails.isEmpty()) {
        edtAddressDetail.setError("Address is required");
        edtAddressDetail.requestFocus();
        return;
      }
      if (provinceId == 0) {
        return;
      }
      if (districtId == 0) {
        return;
      }
      if (wardCode == null) {
        return;
      }
      CustomerUpdateRequest updateRequest = new CustomerUpdateRequest();
      updateRequest.setName(customerName);
      updateRequest.setPhone_number(customerPhone);
      updateRequest.setAddress_name(customerAddressName);
      updateRequest.setAddress_details(customerAddressDetails);
      updateRequest.setProvince_id(provinceId);
      updateRequest.setDistrict_id(districtId);
      updateRequest.setWard_code(wardCode);
      Log.d("customer_id", customer_id);
      Log.d("updateRequest", updateRequest.getName());
      Log.d("updateRequest", updateRequest.getPhone_number());
      Log.d("updateRequest", updateRequest.getAddress_name());
      Log.d("updateRequest", updateRequest.getAddress_details());
      Log.d("updateRequest", updateRequest.getProvince_id() + "");
      Log.d("updateRequest", updateRequest.getDistrict_id() + "");
      Log.d("updateRequest", updateRequest.getWard_code() + "");

      Call<ResponseMessage> customerCall = apiService
          .updateCustomer(customer_id, updateRequest);
      customerCall.enqueue(new retrofit2.Callback<ResponseMessage>() {
        @Override
        public void onResponse(Call<ResponseMessage> call, retrofit2.Response<ResponseMessage> response) {
          String message = response.body().getMessage();
          Log.d("CC", response.toString());
          if(response.isSuccessful()){
            Log.d("UpdateCustomer", "Update success: toi day " + message);
            Call<Customer> customerCall2 = apiService.getCustomer(customer_id);
            customerCall2.enqueue(new retrofit2.Callback<Customer>() {
              @Override
              public void onResponse(Call<Customer> call, retrofit2.Response<Customer> response) {
                customer = response.body();
                DataContainer dataContainer = DataContainer.getInstance();
                dataContainer.setCustomer(customer);
                Log.d("UpdateCustomer", "Update success: toi do " + message);

                Intent intent = new Intent(FillProfileActivity.this, MainActivity.class);
                startActivity(intent);
              }

              @Override
              public void onFailure(Call<Customer> call, Throwable t) {
                Log.d("UpdateCustomerError", "Update failed1: " + t.getMessage());
                Toast.makeText(getApplicationContext(), "Update failed1", Toast.LENGTH_SHORT).show();

              }
            });

          }
          else {
            Log.d("UpdateCustomerError", "Update failed2: " + message);
            Toast.makeText(getApplicationContext(), "Update failed2", Toast.LENGTH_SHORT).show();
          }
        }

        @Override
        public void onFailure(Call<ResponseMessage> call, Throwable t) {
          Log.d("UpdateCustomerError", "Update failed3: " + t.getMessage());
          Toast.makeText(getApplication(), "Update failed3", Toast.LENGTH_SHORT).show();

        }
      });

    });
  }

  private int getProvincePosition(int cityCode) {
    for (int i = 0; i < provinces.size(); i++) {
      if (provinces.get(i).getId() == cityCode) {
        return i;
      }
    }
    return 0;
  }

  private int getDistrictPosition(int districtCode) {
    for (int i = 0; i < districts.size(); i++) {
      if (districts.get(i).getId() == districtCode) {
        return i;
      }
    }
    return 0;
  }

  private int getWardPosition(String wardCode) {
    for (int i = 0; i < wards.size(); i++) {
      if (wards.get(i).getCode().equals(wardCode)) {
        return i;
      }
    }
    return 0;
  }
}