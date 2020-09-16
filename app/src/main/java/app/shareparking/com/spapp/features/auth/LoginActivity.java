package app.shareparking.com.spapp.features.auth;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import app.shareparking.com.spapp.R;
import app.shareparking.com.spapp.baseComponents.BaseActivity;
import app.shareparking.com.spapp.databinding.ActivityLoginBinding;
import app.shareparking.com.spapp.features.home.HomeActivity;
import app.shareparking.com.spapp.utils.IntentUtils;
import app.shareparking.com.spapp.utils.ViewUtils;

public class LoginActivity extends BaseActivity implements AuthListener {

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setViewModel(viewModel);

        viewModel.authListener = this;
        mContext = this;

        viewModel.createAccountLiveData.observe(this, s -> {
            Intent intent = new Intent(mContext, SignUpActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public void onStarted() {
        binding.progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess() {
        binding.progressbar.setVisibility(View.GONE);
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFailed(String message) {
        ViewUtils.showShortToast(this, message);
        binding.progressbar.setVisibility(View.GONE);
    }
}