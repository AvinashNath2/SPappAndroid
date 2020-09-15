package app.shareparking.com.spapp.features.chooseOptionActivity;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;

import app.shareparking.com.spapp.R;
import app.shareparking.com.spapp.baseComponents.BaseActivity;

public class ChooseOptionActivity extends BaseActivity implements ChooseOptionContract.ChooseOptionContractView{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.fragment_choose_action);
    }
}
