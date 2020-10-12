package app.shareparking.com.spapp.features.profile;

import android.view.View;

import androidx.lifecycle.ViewModel;

import app.shareparking.com.spapp.SingleLiveData;

public class ProfileViewModel extends ViewModel {

    public SingleLiveData<String> clickLveData = new SingleLiveData<>();

    public void backClick(View view) {
        clickLveData.postValue("back");
    }
}
