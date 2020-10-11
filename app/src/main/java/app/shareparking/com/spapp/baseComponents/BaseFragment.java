package app.shareparking.com.spapp.baseComponents;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;

import app.shareparking.com.spapp.R;

public class BaseFragment extends Fragment {

    public void setMapStyle(GoogleMap mMap) {
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.map_style));
    }

    public void animateCamera(double lat, double lng, GoogleMap mMap) {
        LatLng coordinate = new LatLng(lat, lng);
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(coordinate, 15);
        mMap.animateCamera(location);
    }
}
