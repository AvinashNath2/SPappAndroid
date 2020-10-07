package app.shareparking.com.spapp.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.shareparking.com.spapp.R;
import app.shareparking.com.spapp.databinding.FragmentRentSpaceStep1Binding;

public class RentSpaceStep1Fragment extends Fragment {

    private FragmentRentSpaceStep1Binding binding;
    private RentSpaceStep1ViewModel viewModel;

    public RentSpaceStep1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rent_space_step1, container, false);
        viewModel = new ViewModelProvider(this).get(RentSpaceStep1ViewModel.class);
        View root = binding.getRoot();
        binding.setViewModel(viewModel);

        return root;
    }
}