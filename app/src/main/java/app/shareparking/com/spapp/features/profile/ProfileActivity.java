package app.shareparking.com.spapp.features.profile;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import app.shareparking.com.spapp.R;
import app.shareparking.com.spapp.baseComponents.BaseActivity;
import app.shareparking.com.spapp.databinding.ActivityProfileBinding;

public class ProfileActivity extends BaseActivity  {

    private ProfileViewModel viewModel;
    private ActivityProfileBinding binding;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding.setViewModel(viewModel);


    }
}
