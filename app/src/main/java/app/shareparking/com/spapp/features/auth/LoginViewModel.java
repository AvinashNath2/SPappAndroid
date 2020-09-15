package app.shareparking.com.spapp.features.auth;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import app.shareparking.com.spapp.utils.StringUtils;

public class LoginViewModel extends ViewModel {

    public String email = "abc@gmail.com", password = "123456";
    public AuthListener authListener;

    public void onContinueClick(View view) {
        authListener.onStarted();
        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            authListener.onFailed("enter email and password");
            return;
        }

        //success
        authListener.onSuccess();

    }

}
