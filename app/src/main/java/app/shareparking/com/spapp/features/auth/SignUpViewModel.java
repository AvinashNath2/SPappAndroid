package app.shareparking.com.spapp.features.auth;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SignUpViewModel extends ViewModel {

    public String firstName, lastName, email, password, confirmPassword;

    public MutableLiveData<String> clickLiveData = new MutableLiveData<>();

    public void onSignUpClick(View view) {

    }

    public void alreadyHaveAnAccount(View view) {
        clickLiveData.postValue("");
    }

}
