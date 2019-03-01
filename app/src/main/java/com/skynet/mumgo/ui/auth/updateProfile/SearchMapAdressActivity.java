package com.skynet.mumgo.ui.auth.updateProfile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.skynet.mumgo.R;
import com.skynet.mumgo.models.MyPlace;
import com.skynet.mumgo.models.Place;
import com.skynet.mumgo.ui.location.LocationContract;
import com.skynet.mumgo.ui.location.LocationPresenter;
import com.skynet.mumgo.utils.AppConstant;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchMapAdressActivity extends FragmentActivity implements OnMapReadyCallback, LocationContract.View {

    private static final int PLACE_PICKER_REQUEST = 10;
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.address)
    TextView tvaddress;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private LatLng myLatlng;
    Place place;
    LocationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_map_adress);
        ButterKnife.bind(this);
        presenter = new LocationPresenter(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
            }
            return;
        }
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            myLatlng = new LatLng(location.getLatitude(), location.getLongitude());
                        }
                    }
                });

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                myLatlng = new LatLng(location.getLatitude(), location.getLongitude());
                            }
                        }
                    });
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapLoaded() {
                if (myLatlng != null) {
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatlng, 17));
                    mMap.setMyLocationEnabled(true);
                    mMap.getUiSettings().setMyLocationButtonEnabled(false);
                }
            }
        });

        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                presenter.getMyAddress(new LatLng(mMap.getCameraPosition().target.latitude, mMap.getCameraPosition().target.longitude));
            }
        });
    }

    @OnClick({R.id.search, R.id.mylocation, R.id.lay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search:
                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                    .build(this);
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                    // Toast.makeText(this, R.string.alert_missing_google_play, Toast.LENGTH_SHORT).show();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                    //Toast.makeText(this, R.string.alert_missing_google_play, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.mylocation:
                if (mMap != null && myLatlng != null) {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLatlng, 17));
                }
                break;
            case R.id.lay:
                Intent i = new Intent();
                Bundle b = new Bundle();
                b.putParcelable(AppConstant.MSG, this.place);
                i.putExtra(AppConstant.BUNDLE, b);
                setResult(RESULT_OK, i);
                finish();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                com.google.android.gms.location.places.Place place = PlacePicker.getPlace(data, this);
                passPlaceBack(place);
                return;
            }
        }

    }

    private void passPlaceBack(com.google.android.gms.location.places.Place place) {
        this.place = new Place();
        this.place.setLatLng(place.getLatLng());
        this.place.setName(place.getName().toString());
        this.place.setAddress(place.getAddress().toString());
        Intent i = new Intent();
        Bundle b = new Bundle();
        b.putParcelable(AppConstant.MSG, this.place);
        i.putExtra(AppConstant.BUNDLE, b);
        setResult(RESULT_OK, i);
        finish();
    }


    @Override
    public void onSuccessGetMyAddress(MyPlace response) {
        place=new Place();
        place.setAddress(response.getAddress());
        place.setLatLng(new LatLng(response.getLat(), response.getLng()));
        place.setName(response.getAddress());
        tvaddress.setText(response.getAddress());
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    public void showProgress() {
        loading.setVisibility(View.VISIBLE);

    }

    @Override
    public void hiddenProgress() {
        loading.setVisibility(View.GONE);

    }

    @Override
    public void onErrorApi(String message) {
        LogUtils.e(message);
    }

    @Override
    public void onError(String message) {
        LogUtils.e(message);

    }

    @Override
    public void onErrorAuthorization() {

    }
}