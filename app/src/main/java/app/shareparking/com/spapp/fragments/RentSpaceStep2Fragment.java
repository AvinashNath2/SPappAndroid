package app.shareparking.com.spapp.fragments;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.shareparking.com.spapp.R;
import app.shareparking.com.spapp.databinding.FragmentRentSpaceStep2Binding;

public class RentSpaceStep2Fragment extends Fragment {

    private FragmentRentSpaceStep2Binding binding;
    private RentSpaceStep2ViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rent_space_step2, container, false);
        viewModel = new ViewModelProvider(this).get(RentSpaceStep2ViewModel.class);
        binding.setViewModel(viewModel);
        View root = binding.getRoot();

        return root;
    }

}