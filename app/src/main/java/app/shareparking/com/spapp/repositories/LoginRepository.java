package app.shareparking.com.spapp.repositories;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;

import app.shareparking.com.spapp.dtos.LoginModel;
import app.shareparking.com.spapp.networking.RestService;
import app.shareparking.com.spapp.networking.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private static final String TAG = LoginRepository.class.getSimpleName();
    private RestService restService;

    public LoginRepository() {
        restService = RetrofitInstance.getRetrofitInstance().create(RestService.class);
    }

    public LiveData<LoginModel> loginRequest(HashMap<String, Object> map) {
        final MutableLiveData<LoginModel> data = new MutableLiveData<>();
        restService.login(map).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
