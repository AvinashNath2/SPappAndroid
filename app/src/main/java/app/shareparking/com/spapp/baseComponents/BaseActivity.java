package app.shareparking.com.spapp.baseComponents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        getExtras();
        activityRunning();
    }

    protected abstract void getExtras();

    protected abstract void activityRunning();

    protected abstract int getLayoutResourceId();

    protected abstract void showLoading();

    protected abstract void hideLoading();

}
