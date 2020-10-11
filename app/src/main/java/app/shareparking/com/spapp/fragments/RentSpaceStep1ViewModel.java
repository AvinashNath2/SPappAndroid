package app.shareparking.com.spapp.fragments;

import android.view.View;

import androidx.lifecycle.ViewModel;

import app.shareparking.com.spapp.SingleLiveData;

public class RentSpaceStep1ViewModel extends ViewModel {

    public String pinCode, address;
    public SingleLiveData<String> clickLiveData = new SingleLiveData<>();

    public void doneLocationClick(View view) {
        clickLiveData.postValue("done");
    }
}
