package com.example.familyconnectionapp;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    Bundle bundle;
    public static double lng;
    public static double lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        bundle=new Bundle();
        Intent intent = getIntent();
        bundle = intent.getBundleExtra("dulieu");

        if(bundle!=null) {

            lat = bundle.getDouble("vido");
            lng = bundle.getDouble("kinhdo");
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(bundle!=null) {
            // Add a marker in your device's location
            LatLng yourLocation = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(yourLocation).title("Marker in your location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(yourLocation));

        }else
        {
            LatLng sydney = new LatLng(-34, 151);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }

    }
}
