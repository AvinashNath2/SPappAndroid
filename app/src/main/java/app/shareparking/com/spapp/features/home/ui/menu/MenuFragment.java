package app.shareparking.com.spapp.features.home.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import app.shareparking.com.spapp.R;
import app.shareparking.com.spapp.databinding.FragmentMenuBinding;

public class MenuFragment extends Fragment {

    private MenuViewModel viewModel;
    private FragmentMenuBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false);
        View root = binding.getRoot();
        binding.setViewModel(viewModel);

        viewModel.liveData.observe(getActivity(), s -> {
            switch (s) {
                case "profile": {
                    Toast.makeText(getActivity(), "profile", Toast.LENGTH_SHORT).show();
                    break;
                } case "rent": {
                    Toast.makeText(getActivity(), "rent", Toast.LENGTH_SHORT).show();
                    break;
                }
                case "logout": {
                    Toast.makeText(getActivity(), "logout", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        });

        return root;
    }
}