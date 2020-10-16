package app.shareparking.com.spapp.features.home.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import app.shareparking.com.spapp.R;
import app.shareparking.com.spapp.baseComponents.BaseFragment;
import app.shareparking.com.spapp.databinding.FragmentHomeBinding;
import app.shareparking.com.spapp.features.search.SearchActivity;
import app.shareparking.com.spapp.utils.IntentUtils;
import app.shareparking.com.spapp.utils.MapUtils;
import app.shareparking.com.spapp.utils.ViewUtils;

public class HomeFragment extends BaseFragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

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
                IntentUtils.startIntentWithoutAnimation(getActivity(), SearchActivity.class);
            }, 200);
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        //animateSearchBar(false);
        new Handler().postDelayed(this::setSearchBarDefault, 0);
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

        MapUtils.setMapStyle(mMap, getActivity());
        MapUtils.animateCamera(28.637103, 77.460411, mMap);

        mMap.setOnMarkerClickListener(this);

        MapUtils.addMarker(mMap, new LatLng(28.661587, 77.408785), null, 0, getActivity());
        MapUtils.addMarker(mMap, new LatLng(28.642743, 77.406145), null, 1, getActivity());
        MapUtils.addMarker(mMap, new LatLng(28.652524, 77.429947), null, 2, getActivity());
        MapUtils.addMarker(mMap, new LatLng(28.655180, 77.396926), null, 3, getActivity());
        MapUtils.addMarker(mMap, new LatLng(28.652846, 77.412015), null, 4, getActivity());
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(getActivity(), marker.getTag()+"", Toast.LENGTH_SHORT).show();
        return false;
    }
}