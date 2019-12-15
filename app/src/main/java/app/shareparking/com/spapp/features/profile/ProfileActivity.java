package app.shareparking.com.spapp.features.profile;

import android.os.Bundle;
import app.shareparking.com.spapp.R;
import app.shareparking.com.spapp.baseComponents.BaseActivity;

public class ProfileActivity extends BaseActivity implements ProfileContract.ProfileContractView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pofile);

    }

    @Override
    protected void getExtras() {

    }

    @Override
    protected void activityRunning() {

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_pofile;

        //        RestService service = RetrofitInstance.getRetrofitInstance().create(RestService.class);
//        ;
//
//        Observable<List<TempClass>> observable = service.lsitOfComments()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//
//        observable.doOnTerminate(() -> {
//
//        }).subscribe(response -> {
//                    Toast.makeText(ProfileActivity.this, "Ayaa", Toast.LENGTH_SHORT).show();
//                },
//                throwable -> {
//                    Toast.makeText(ProfileActivity.this, "Some Error Occurred", Toast.LENGTH_SHORT).show();
//                });
    }

    @Override
    protected void showLoading() {

    }

    @Override
    protected void hideLoading() {

    }

}
