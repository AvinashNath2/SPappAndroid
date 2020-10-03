package app.shareparking.com.spapp.features.home.ui.home;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;

import app.shareparking.com.spapp.R;
import app.shareparking.com.spapp.databinding.FragmentHomeBinding;
import app.shareparking.com.spapp.features.search.SearchActivity;
import app.shareparking.com.spapp.utils.ViewUtils;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    private HomeViewModel viewModel;
    private FragmentHomeBinding binding;

    private GoogleMap mMap;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        View root = binding.getRoot();
        binding.setViewModel(viewModel);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        binding.searchCard.setOnClickListener(v -> {

            animateSearchBar(true);

            new Handler().postDelayed(() -> {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }, 200);
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        setSearchBarDefault();
    }

    private void animateSearchBar(boolean animateOnTop) {
        Animation animation;
        if(animateOnTop) {
            animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_top_anim);
        } else {
            animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down_anim);
        }
        binding.searchCard.startAnimation(animation);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 0);
        binding.searchCard.setLayoutParams(params);
    }

    private void setSearchBarDefault() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(ViewUtils.pixelToDp(30), ViewUtils.pixelToDp(50), ViewUtils.pixelToDp(30), 0);
        binding.searchCard.setLayoutParams(params);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;

        setMapStyle();
        animateCamera(28.637103, 77.460411);
    }

    private void setMapStyle() {
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.map_style));
    }

    private void animateCamera(double lat, double lng) {
        LatLng coordinate = new LatLng(lat, lng);
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(coordinate, 15);
        mMap.animateCamera(location);
    }
}