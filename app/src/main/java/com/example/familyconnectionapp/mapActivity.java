package com.example.familyconnectionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;



        import android.os.Bundle;
        import android.widget.Toast;

        import androidx.fragment.app.FragmentActivity;

        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.MarkerOptions;

public class  mapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    Bundle bundle;
    double kd;
    double vd;
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


            vd = bundle.getDouble("vido");
            kd = bundle.getDouble("kinhdo");

            Toast.makeText(this, ""+kd, Toast.LENGTH_SHORT).show();

            Toast.makeText(this, ""+vd, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(bundle!=null) {
            // Add a marker in Sydney, Australia, and move the camera.
            LatLng myLocal = new LatLng(kd, vd);
            mMap.addMarker(new MarkerOptions().position(myLocal).title("Marker in local"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocal));

        }else
        {
            LatLng sydney = new LatLng(-34, 151);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }


    }
}
