package app.shareparking.com.spapp.features.chooseOptionActivity;

import app.shareparking.com.spapp.R;
import app.shareparking.com.spapp.baseComponents.BaseActivity;

public class ChooseOptionActivity extends BaseActivity implements ChooseOptionContract.ChooseOptionContractView{

    ChooseOptionPresenter presenter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_choose_action;
    }

    @Override
    protected void getExtras() {

    }

    @Override
    protected void activityRunning() {
        presenter = new ChooseOptionPresenter(this);
    }

    @Override
    protected void showLoading() {

    }

    @Override
    protected void hideLoading() {

    }
}
