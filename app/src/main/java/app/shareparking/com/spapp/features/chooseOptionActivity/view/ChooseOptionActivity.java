package app.shareparking.com.spapp.features.chooseOptionActivity.view;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import app.shareparking.com.spapp.R;
import app.shareparking.com.spapp.features.chooseOptionActivity.presenter.ChooseOptionPresenter;

public class ChooseOptionActivity extends MvpActivity<ChooseOptionView, ChooseOptionPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_choose_action);
    }

    @NonNull
    @Override
    public ChooseOptionPresenter createPresenter() {
        return new ChooseOptionPresenter();
    }

}
