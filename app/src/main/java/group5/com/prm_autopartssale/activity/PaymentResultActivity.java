package group5.com.prm_autopartssale.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class PaymentResultActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fill_profile);


  }


}