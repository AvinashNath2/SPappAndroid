package app.shareparking.com.spapp.features.search;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import app.shareparking.com.spapp.R;
import app.shareparking.com.spapp.baseComponents.BaseActivity;
import app.shareparking.com.spapp.databinding.ActivitySearchBinding;

public class SearchActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Context mContext;
    private ActivitySearchBinding binding;
    private SearchViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        binding.setViewModel(viewModel);

        init();

        viewModel.clickLiveData.observe(this, s -> {
            switch (s) {
                case "close_drawer": {
                    binding.drawerLayout.closeDrawer(GravityCompat.END);
                    break;
                }
                case "clear": {
                    binding.drawerLayout.closeDrawer(GravityCompat.END);
                    break;
                }
                case "apply": {
                    binding.drawerLayout.closeDrawer(GravityCompat.END);
                    break;
                }
                case "filter": {
                    binding.drawerLayout.openDrawer(GravityCompat.END);
                    break;
                }
            }
        });
    }

    private void init() {
        setToolbarTitle("");

        mContext = this;
    }

    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}