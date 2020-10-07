package app.shareparking.com.spapp.features.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import app.shareparking.com.spapp.R;
import app.shareparking.com.spapp.baseComponents.BaseActivity;
import app.shareparking.com.spapp.databinding.ActivitySignUpBinding;

public class SignUpActivity extends BaseActivity implements AuthListener {

    private ActivitySignUpBinding binding;
    private SignUpViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        binding.setViewModel(viewModel);

        init();

        viewModel.alreadyAccountLiveData.observe(this, s -> {
            finish();
        });
    }

    private void init() {
        viewModel.authListener = this;
        setToolbarTitle(getString(R.string.register_with_us));
    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailed(String message) {

    }
}