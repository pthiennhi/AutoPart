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
import group5.com.prm_autopartssale.models.City;
import group5.com.prm_autopartssale.models.Customer;
import group5.com.prm_autopartssale.models.CustomerUpdateRequest;
import group5.com.prm_autopartssale.models.DataContainer;
import group5.com.prm_autopartssale.models.District;
import group5.com.prm_autopartssale.models.ResponseMessage;
import group5.com.prm_autopartssale.models.Ward;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass. Use the {@link ChangeAddressFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class ChangeAddressFragment extends Fragment {

  private ApiService apiService;

  Customer customer;

  Spinner citySpinner, districtSpinner, wardSpinner;
  EditText edtNameAdress, edtAddressDetail;
  Button btnChange;
  ImageView iv_back, iv_more;

  List<City> cities;
  List<District> districts;
  List<Ward> wards;
  int cityCode = 1 , districtCode = 1, wardCode = 1;


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
    citySpinner = view.findViewById(R.id.citySpinner);
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

    cities = new ArrayList<>();
    districts = new ArrayList<>();
    wards = new ArrayList<>();

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://provinces.open-api.vn/api/")
        .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
        .build();

    Retrofit retrofitServer = new Retrofit.Builder()
        .baseUrl("https://swd-six.vercel.app/api/")
        .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
        .build();

    apiService = retrofit.create(ApiService.class);


    Call<List<City>> call = apiService.getCities();

    call.enqueue(new retrofit2.Callback<List<City>>() {
      @Override
      public void onResponse(Call<List<City>> call, retrofit2.Response<List<City>> response) {
        if (response.isSuccessful() && response.body() != null) {
          cities = response.body();

          // Create a list of city names from the API response
          List<String> cityNames = new ArrayList<>();
          for (City city : cities) {
            cityNames.add(city.getName());
          }

          // Populate the citySpinner with city names
          ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(getContext(),
              android.R.layout.simple_spinner_item, cityNames);
          cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          citySpinner.setAdapter(cityAdapter);
          citySpinner.setSelection(getCityPosition(customer.getCity_code()));


        }

      }

      @Override
      public void onFailure(Call<List<City>> call, Throwable t) {
        System.out.println("Error");
      }
    });





    citySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (!cities.isEmpty() && position >= 0 && position < cities.size()) {
          cityCode = cities.get(position).getCode();
          Call<City> cityCall = apiService.getCity(cityCode, 2);
          cityCall.enqueue(new retrofit2.Callback<City>() {
            @Override
            public void onResponse(Call<City> call,
                retrofit2.Response<City> response) {
              if (response.isSuccessful() && response.body() != null) {
                districts = response.body().getDistricts();

                // Create a list of district names from the API response
                List<String> districtNames = new ArrayList<>();
                for (District district : districts) {
                  districtNames.add(district.getName());
                }

                // Populate the districtSpinner with district names
                ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, districtNames);
                districtAdapter.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item);
                districtSpinner.setAdapter(districtAdapter);
                districtSpinner.setSelection(getDistrictPosition(customer.getDistrict_code()));
              } else {
                Log.d("DistrictData", "Error");
              }
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
              t.printStackTrace();
            }
          });
        }


      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });

    districtSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(!districts.isEmpty() && position >= 0 && position < districts.size()){
          districtCode = districts.get(position).getCode();
          Call<District> districtCall = apiService.getDistrict(districtCode, 2);
          districtCall.enqueue(new retrofit2.Callback<District>() {
            @Override
            public void onResponse(Call<District> call, retrofit2.Response<District> response) {
              if (response.isSuccessful() && response.body() != null) {
                wards = response.body().getWards();

                // Create a list of ward names from the API response
                List<String> wardNames = new ArrayList<>();
                for (Ward ward : wards) {
                  wardNames.add(ward.getName());
                }

                // Populate the wardSpinner with ward names
                ArrayAdapter<String> wardAdapter = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, wardNames);
                wardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                wardSpinner.setAdapter(wardAdapter);
                wardSpinner.setSelection(getWardPosition(customer.getWard_code()));
              }
            }

            @Override
            public void onFailure(Call<District> call, Throwable t) {
              System.out.println("Error");
            }
          });
        }

      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });

    wardSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(!wards.isEmpty() && position >= 0 && position < wards.size()){
          wardCode = wards.get(position).getCode();
        }
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
        updateRequest.setCity_code(cityCode);
        updateRequest.setDistrict_code(districtCode);
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

  private int getCityPosition(int cityCode){
    for(int i = 0; i < cities.size(); i++){
      if(cities.get(i).getCode() == cityCode){
        return i;
      }
    }
    return 0;
  }

  private int getDistrictPosition(int districtCode){
    for(int i = 0; i < districts.size(); i++){
      if(districts.get(i).getCode() == districtCode){
        return i;
      }
    }
    return 0;
  }

  private int getWardPosition(int wardCode){
    for(int i = 0; i < wards.size(); i++){
      if(wards.get(i).getCode() == wardCode){
        return i;
      }
    }
    return 0;
  }
}