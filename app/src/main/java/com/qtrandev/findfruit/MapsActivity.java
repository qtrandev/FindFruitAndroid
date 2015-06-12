package com.qtrandev.findfruit;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private double currentLat = 0;
    private double currentLon = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(25.7717896, -80.2412616), 12.0f) );
        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(25.7717896, -80.2412616))
                .title("Mango")
                .snippet("Allowed Picking: Yes")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        Marker marker1 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(25.830696,-80.2749993))
                .title("Avocado")
                .snippet("Allowed Picking: Yes")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        Marker marker2 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(25.7762188,-80.2938821))
                .title("Lychees")
                .snippet("Allowed Picking: Yes")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        Marker marker3 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(25.7626149,-80.2901055))
                .title("Mamey")
                .snippet("Allowed Picking: Yes")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        Marker marker4 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(25.8204207,-80.2619531))
                .title("Kumquat")
                .snippet("Allowed Picking: Yes")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        Marker marker5 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(25.8142396,-80.2592065))
                .title("Dragon Fruit")
                .snippet("Allowed Picking: No")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Tree");
        try {
            ParseObject object = query.getFirst();
            String type = object.getString("type");
            double lat = object.getDouble("lat");
            double lon = object.getDouble("lon");
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lon))
                    .title("CUSTOM: " + type)
                    .snippet("Allowed Picking: Yes")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        } catch (ParseException ex) {
            ex.toString();
        }

        query.getInBackground("main", new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) { //exception is null
                    String type = object.getString("type");
                    double lat = object.getDouble("lat");
                    double lon = object.getDouble("lon");
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(lat,lon))
                            .title("CUSTOM: "+type)
                            .snippet("Allowed Picking: Yes")
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                } else {
                    Toast.makeText(MapsActivity.this, "Parse query error", Toast.LENGTH_SHORT);
                }
            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker arg0) {
                if (arg0.getTitle().equals("Mango")) {
                    Intent i = new Intent(MapsActivity.this, TreeActivity.class);
                    startActivity(i);
                } else if (arg0.getTitle().equals("Add New Tree")) {
                    Intent i = new Intent(MapsActivity.this, NewTreeActivity.class);
                    i.putExtra("Lat", currentLat);
                    i.putExtra("Lon", currentLon);
                    startActivity(i);
                }
            }

        });

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick (LatLng point) {
                currentLat = point.latitude;
                currentLon = point.longitude;
                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(point)
                        .title("Add New Tree")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                marker.showInfoWindow();
            }
        });
    }
}
