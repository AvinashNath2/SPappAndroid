package app.shareparking.com.spapp.features.home;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import app.shareparking.com.spapp.R;
import app.shareparking.com.spapp.baseComponents.BaseActivity;
import app.shareparking.com.spapp.databinding.ActivityHomeBinding;
import app.shareparking.com.spapp.features.home.ui.home.HomeFragment;
import app.shareparking.com.spapp.features.home.ui.menu.MenuFragment;

public class HomeActivity extends BaseActivity {

    private ActivityHomeBinding binding;
    private HomeViewModel viewModel;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding.setViewModel(viewModel);

        mContext = this;

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}