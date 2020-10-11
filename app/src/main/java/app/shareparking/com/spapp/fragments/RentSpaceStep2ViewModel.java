package app.shareparking.com.spapp.fragments;

import android.view.View;

import androidx.lifecycle.ViewModel;

import app.shareparking.com.spapp.SingleLiveData;

public class RentSpaceStep2ViewModel extends ViewModel {

    SingleLiveData<String> clickLiveData = new SingleLiveData<>();

    public void onBackClick(View view) {
        clickLiveData.postValue("back");
    }
}