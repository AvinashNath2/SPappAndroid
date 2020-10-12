package app.shareparking.com.spapp.features.search;

import android.view.View;

import androidx.lifecycle.ViewModel;

import app.shareparking.com.spapp.SingleLiveData;

public class SearchViewModel extends ViewModel {

    public SingleLiveData<String> clickLiveData = new SingleLiveData<>();

    public void onFilterClick(View view) {
        clickLiveData.postValue("filter");
    }

    public void clearFilterClick(View view) {
        clickLiveData.postValue("clear");
    }

    public void closeDrawerClick(View view) {
        clickLiveData.postValue("close_drawer");
    }

    public void applyFilterClick(View view) {
        clickLiveData.postValue("apply");
    }

}
