package app.shareparking.com.spapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import app.shareparking.com.spapp.R;

public class MapUtils {

    public static void setMapStyle(GoogleMap mMap, Context context) {
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style));
    }

    public static void animateCamera(double lat, double lng, GoogleMap mMap) {
        LatLng coordinate = new LatLng(lat, lng);
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(coordinate, 15);
        mMap.animateCamera(location);
    }

    public static void addMarker(GoogleMap map, LatLng latLng, String title, Object tag, Context context) {
        MarkerOptions options = new MarkerOptions();
        options.position(latLng);
        options.icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons(context)));
        if (title != null) {
            options.title(title);
        }

        map.addMarker(options).setTag(tag);
    }

    public static Bitmap resizeMapIcons(Context context){
        Bitmap imageBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_marker);
        return Bitmap.createScaledBitmap(imageBitmap, ViewUtils.pixelToDp(30), ViewUtils.pixelToDp(30), false);
    }
}
