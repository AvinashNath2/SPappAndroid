package app.shareparking.com.spapp.features.home.ui.menu;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import app.shareparking.com.spapp.SingleLiveData;

public class MenuViewModel extends ViewModel {

    public SingleLiveData<String> liveData = new SingleLiveData<>();

    public void onProfileClick(View view) {
        liveData.postValue("profile");
    }

    public void onRentOutClick(View view) {
        liveData.postValue("rent");
    }

    public void onLogoutClick(View view) {
        liveData.postValue("logout");
    }
}