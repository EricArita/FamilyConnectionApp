package com.example.familyconnectionapp.ui.myLocation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.familyconnectionapp.CircleModel;
import com.example.familyconnectionapp.FirebaseOperation;
import com.example.familyconnectionapp.MainActivity;
import com.example.familyconnectionapp.MapActivity;
import com.example.familyconnectionapp.R;
import com.example.familyconnectionapp.UserModel;
import com.example.familyconnectionapp.UserViewModel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Locale;

import static android.content.Context.LOCATION_SERVICE;

public class LocationSettingsFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    private static final long UPDATE_INTERVAL = 5000;
    private static final long FASTEST_INTERVAL = 5000;
    private static final int REQUEST_LOCATION_PERMISSION = 100;

    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Location mLastLocation;
    private boolean mIsAutoUpdateLocation;
    private boolean allowShareLocation;
    private FirebaseOperation crudFirebase;
    private FirebaseAuth mAuth;
    private UserModel currUser;

    private TextView mTvCurrentLocation;
    private Button mBtnGetLocation;
    private Switch mSwAutoUpdateLocation;
    private Switch mSwShareLocation;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_location_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        initViews();

        crudFirebase = new FirebaseOperation();
        mAuth = FirebaseAuth.getInstance();
        crudFirebase.getUserById(mAuth.getCurrentUser().getUid(), u -> currUser = u);

        requestLocationPermissions();

        if (isPlayServicesAvailable()) {
            setUpLocationClientIfNeeded();
            buildLocationRequest();
        } else {
            mTvCurrentLocation.setText("Device does not support Google Play services");
        }

        mBtnGetLocation.setOnClickListener(v -> {
            if (isGpsOn()) {
                updateUi();
                Bundle bundle = new Bundle();
                bundle.putDouble("kinhdo", mLastLocation.getLongitude());
                bundle.putDouble("vido", mLastLocation.getLatitude());

                crudFirebase.updateUserLocation(mAuth.getCurrentUser().getUid(), mLastLocation.getLatitude(), mLastLocation.getLongitude());

                Intent intent = new Intent(getActivity(), MapActivity.class);
                intent.putExtra("dulieu", bundle);
                startActivity(intent);
            } else {
                Toast.makeText(getActivity(), "GPS is OFF", Toast.LENGTH_SHORT).show();
            }
        });

        mSwAutoUpdateLocation.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    if (!isGpsOn()) {
                        Toast.makeText(getActivity(), "GPS is OFF",
                                Toast.LENGTH_SHORT).show();
                        mSwAutoUpdateLocation.setChecked(false);
                        return;
                    }

                    mIsAutoUpdateLocation = isChecked;
                    if (isChecked) {
                        startLocationUpdates();
                    } else {
                        stopLocationUpdates();
                    }
                }
        );

        mSwShareLocation.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    allowShareLocation = isChecked;

                    if (allowShareLocation) {
                        crudFirebase.setShareLocation(mAuth.getCurrentUser().getUid(), true);
                    }
                    else {
                        crudFirebase.setShareLocation(mAuth.getCurrentUser().getUid(), false);
                    }
                }
        );
    }

    private void initViews() {
        mTvCurrentLocation = getView().findViewById(R.id.tv_current_location);
        mBtnGetLocation = getView().findViewById(R.id.btn_get_location);
        mSwAutoUpdateLocation = getView().findViewById(R.id.sw_auto_update_location);
        mSwShareLocation = getView().findViewById(R.id.sw_share_location);
    }

    private void updateUi() {
        if (mLastLocation != null) {
            mTvCurrentLocation.setText(String.format(Locale.getDefault(), "%f, %f",
                    mLastLocation.getLatitude(), mLastLocation.getLongitude()));
        }
    }

    private void requestLocationPermissions() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    requestLocationPermissions();
                }
                break;
            default:
                break;
        }
    }

    private boolean isPlayServicesAvailable() {
        return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getActivity())
                == ConnectionResult.SUCCESS;
    }

    private boolean isGpsOn() {
        LocationManager manager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private void setUpLocationClientIfNeeded() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    private void buildLocationRequest() {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
    }

    protected void startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onStop() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (lastLocation != null) {
            mLastLocation = lastLocation;
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mIsAutoUpdateLocation) {
            updateUi();
            crudFirebase.updateUserLocation(currUser.userId, mLastLocation.getLatitude(), mLastLocation.getLongitude());
            ArrayList<CircleModel> joinedCircleList =  new ArrayList<>(currUser.JoinedCircleList.values());
            for(CircleModel circle : joinedCircleList){
               crudFirebase.getUserByCircleCode(circle.circleCode, user -> {
                   crudFirebase.updateCircleMemberLocation(user.userId, currUser.userId, mLastLocation.getLatitude(), mLastLocation.getLongitude());
               });
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onDestroy() {
        if (mGoogleApiClient != null
                && mGoogleApiClient.isConnected()) {
            stopLocationUpdates();
            mGoogleApiClient.disconnect();
            mGoogleApiClient = null;
        }
        Log.d(TAG, "onDestroy LocationService");
        super.onDestroy();
    }
}