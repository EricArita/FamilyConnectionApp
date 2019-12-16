package com.example.familyconnectionapp;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    Bundle bundle;
    public static Double lng;
    public static Double lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        bundle=new Bundle();
        Intent intent = getIntent();
        bundle = intent.getBundleExtra("dulieu");
        if(bundle!=null) {
            lat = bundle.getDouble("vido");
            lng = bundle.getDouble("kinhdo");

            Toast.makeText(this, "" + lng, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "" + lat, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(bundle!=null) {
            // Add a marker in Sydney, Australia, and move the camera.
            LatLng myLocal = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(myLocal).title("Marker in your location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocal));

        }else
        {
            LatLng sydney = new LatLng(-34, 151);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
    }
}
