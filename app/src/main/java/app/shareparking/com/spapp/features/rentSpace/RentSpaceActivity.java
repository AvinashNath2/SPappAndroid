package app.shareparking.com.spapp.features.rentSpace;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
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

public class RentSpaceActivity extends BaseActivity implements DoneLocationInterface {

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
    }

    private void addFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container,  rentSpaceStep1Fragment);
        fragmentTransaction.commit();
    }

    private void switchFragment(Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onDoneLocation() {
        switchStep2Selection();
    }

    @Override
    public void onPressBackToLocation() {
        onBackPressed();
    }

    public void switchStep2Selection() {
        switchFragment(rentSpaceStep2Fragment);

        binding.step1Layout.setBackground(null);
        binding.step1Text.setTextColor(getResources().getColor(R.color.material_light_black));

        binding.step2Layout.setBackground(ResourcesCompat.getDrawable(getResources()
                , R.drawable.bg_round_corners_rectangle, null));
        binding.step2Text.setTextColor(getResources().getColor(R.color.material_light_white));
    }

    public void switchStep1Selection() {

        binding.step2Layout.setBackground(null);
        binding.step2Text.setTextColor(getResources().getColor(R.color.material_light_black));

        binding.step1Layout.setBackground(ResourcesCompat.getDrawable(getResources()
                , R.drawable.bg_round_corners_rectangle, null));
        binding.step1Text.setTextColor(getResources().getColor(R.color.material_light_white));
    }

    @Override
    public void onBackPressed() {
        switchStep1Selection();
        super.onBackPressed();
    }
}
