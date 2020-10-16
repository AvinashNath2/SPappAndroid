package app.shareparking.com.spapp.features.auth;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;

import app.shareparking.com.spapp.dtos.LoginModel;
import app.shareparking.com.spapp.repositories.LoginRepository;
import app.shareparking.com.spapp.utils.StringUtils;

public class LoginViewModel extends ViewModel {

    public String email = "Avinash@gmail.com", password = "1232323232";
    public MutableLiveData<String> clickLiveData = new MutableLiveData<>();
    private LoginRepository repository;
    public LiveData<LoginModel> loginLiveData = new MutableLiveData<>();

    public void onLoginClick(View view) {
        clickLiveData.postValue("login_started");
        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            clickLiveData.postValue("fields empty");
            return;
        }
        clickLiveData.postValue("login");

        /*HashMap<String, Object> map = new HashMap<>();
        map.put("emailId", email);
        map.put("password", password);

        repository = new LoginRepository();
        loginLiveData = repository.loginRequest(map);*/

    }

    public void createAccount(View view) {
        clickLiveData.postValue("create");
    }

}
