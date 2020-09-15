package app.shareparking.com.spapp.features.profile;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;

import app.shareparking.com.spapp.R;
import app.shareparking.com.spapp.baseComponents.BaseActivity;

public class ProfileActivity extends BaseActivity implements ProfileContract.ProfileContractView {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
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


}
