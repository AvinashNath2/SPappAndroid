package app.shareparking.com.spapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import app.shareparking.com.spapp.R;
import app.shareparking.com.spapp.baseComponents.BaseFragment;
import app.shareparking.com.spapp.databinding.FragmentRentSpaceStep1Binding;
import app.shareparking.com.spapp.features.rentSpace.DoneLocationInterface;
import app.shareparking.com.spapp.features.rentSpace.RentSpaceActivity;

public class RentSpaceStep1Fragment extends BaseFragment implements OnMapReadyCallback {

    private FragmentRentSpaceStep1Binding binding;
    private RentSpaceStep1ViewModel viewModel;

    private DoneLocationInterface doneLocationInterface;
    private GoogleMap mMap;

    public RentSpaceStep1Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            doneLocationInterface = (RentSpaceActivity) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rent_space_step1, container, false);
        viewModel = new ViewModelProvider(this).get(RentSpaceStep1ViewModel.class);
        View root = binding.getRoot();
        binding.setViewModel(viewModel);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        viewModel.clickLiveData.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                doneLocationInterface.onDoneLocation();
            }
        });

        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        setMapStyle(mMap);
        animateCamera(28.637103, 77.460411, mMap);
    }
}