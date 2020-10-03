package app.shareparking.com.spapp.features.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import app.shareparking.com.spapp.R;
import app.shareparking.com.spapp.baseComponents.BaseActivity;
import app.shareparking.com.spapp.databinding.ActivitySearchBinding;
import app.shareparking.com.spapp.utils.ViewUtils;

public class SearchActivity extends BaseActivity {

    private Context mContext;
    private ActivitySearchBinding binding;
    private SearchViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        binding.setViewModel(viewModel);

        setToolbarTitle("");


        mContext = this;
    }
}