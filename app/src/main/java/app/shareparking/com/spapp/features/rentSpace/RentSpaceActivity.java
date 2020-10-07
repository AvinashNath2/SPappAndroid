package app.shareparking.com.spapp.features.rentSpace;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import app.shareparking.com.spapp.R;
import app.shareparking.com.spapp.baseComponents.BaseActivity;
import app.shareparking.com.spapp.databinding.ActivityRentParkingSpaceBinding;
import app.shareparking.com.spapp.fragments.RentSpaceStep1Fragment;
import app.shareparking.com.spapp.fragments.RentSpaceStep2Fragment;

public class RentSpaceActivity extends BaseActivity {

    private RentSpaceViewModel viewModel;
    private ActivityRentParkingSpaceBinding binding;

    private RentSpaceStep1Fragment rentSpaceStep1Fragment;
    private RentSpaceStep2Fragment rentSpaceStep2Fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_rent_parking_space);
        viewModel = new ViewModelProvider(this).get(RentSpaceViewModel.class);
        binding.setViewModel(viewModel);

        init();
        addFragment();

    }

    private void init() {
        setToolbarTitle(getString(R.string.rent_out_space));
        rentSpaceStep1Fragment = new RentSpaceStep1Fragment();
        rentSpaceStep2Fragment = new RentSpaceStep2Fragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    private void addFragment() {
        fragmentTransaction.add(R.id.container,  rentSpaceStep1Fragment);
        fragmentTransaction.commit();
    }

    private void switchFragment(Fragment fragment) {
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void switchStepSelection() {

    }
}
