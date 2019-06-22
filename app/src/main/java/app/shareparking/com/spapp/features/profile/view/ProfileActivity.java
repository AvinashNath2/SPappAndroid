package app.shareparking.com.spapp.features.profile.view;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import app.shareparking.com.spapp.R;
import app.shareparking.com.spapp.features.profile.presenter.ProfilePresenter;

public class ProfileActivity extends MvpActivity<ProfileView, ProfilePresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pofile);

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

    @NonNull
    @Override
    public ProfilePresenter createPresenter() {
        return new ProfilePresenter();
    }

}
