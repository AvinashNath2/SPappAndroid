package app.shareparking.com.spapp.features.auth;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import app.shareparking.com.spapp.R;
import app.shareparking.com.spapp.baseComponents.BaseActivity;
import app.shareparking.com.spapp.databinding.ActivityLoginBinding;
import app.shareparking.com.spapp.dtos.LoginModel;
import app.shareparking.com.spapp.features.home.HomeActivity;
import app.shareparking.com.spapp.utils.IntentUtils;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;
    private Activity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setViewModel(viewModel);

        mContext = this;
        getLoginData();

        viewModel.clickLiveData.observe(this, s -> {
            switch (s) {
                case "create": {
                    IntentUtils.startIntent(mContext, SignUpActivity.class);
                    break;
                }
                case "fields empty": {
                    Toast.makeText(mContext, "Please Enter fields", Toast.LENGTH_SHORT).show();
                    binding.progressbar.setVisibility(View.GONE);
                    break;
                }
                case "login_started": {
                    binding.progressbar.setVisibility(View.VISIBLE);
                    break;
                }
                case "login": {
                    IntentUtils.startIntent(mContext, HomeActivity.class);
                    break;
                }
            }
        });

    }

    private void getLoginData() {
        viewModel.loginLiveData.observe(this, loginModel -> {
            if(loginModel != null) {

            } else {
                binding.progressbar.setVisibility(View.GONE);
            }
        });
    }


}