package app.shareparking.com.spapp.features.rentSpace;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;

import app.shareparking.com.spapp.R;
import app.shareparking.com.spapp.baseComponents.BaseActivity;

public class RentSpaceActivity extends BaseActivity implements RentSpaceContract.RentSpaceView{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.rent_parking_space_activity);
    }

}
