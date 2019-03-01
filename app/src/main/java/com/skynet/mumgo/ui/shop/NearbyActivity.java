package com.skynet.mumgo.ui.shop;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.skynet.mumgo.R;
import com.skynet.mumgo.models.Shop;
import com.skynet.mumgo.network.api.ApiResponse;
import com.skynet.mumgo.network.api.ApiUtil;
import com.skynet.mumgo.network.api.CallBackBase;
import com.skynet.mumgo.ui.base.BaseActivity;
import com.skynet.mumgo.ui.detailshop.DetailShopActivity;
import com.skynet.mumgo.utils.AppConstant;
import com.skynet.mumgo.utils.CommomUtils;

import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

public class NearbyActivity extends BaseActivity implements OnMapReadyCallback {


    @BindView(R.id.imgHome)
    ImageView imgHome;
    @BindView(R.id.tvTitleToolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.include4)
    ConstraintLayout include4;
    HashMap<Marker, Shop> hashmap;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private LatLng myLatlng;
    private boolean isLoadMap = false;
    private boolean isFill = false;
    List<Shop> list;

    @Override
    protected int initLayout() {
        return R.layout.activity_nearby;
    }

    @Override
    protected void initVariables() {
        hashmap = new HashMap<>();
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
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
                            getNearby(myLatlng);
                        }
                    }
                });

    }

    private void getNearby(LatLng myLatlng) {
        ApiUtil.createNotTokenApi().getListShopNearby(myLatlng.latitude, myLatlng.longitude).enqueue(new CallBackBase<ApiResponse<List<Shop>>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<List<Shop>>> call, Response<ApiResponse<List<Shop>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS) {
                        list = response.body().getData();
                        if (isLoadMap) {
                            LatLngBounds.Builder builder = new LatLngBounds.Builder();
                            builder.include(myLatlng);
                            for (Shop shop : response.body().getData()) {
                                builder.include(new LatLng(shop.getLat(), shop.getLng()));
                                hashmap.put(CommomUtils.addMarker(getDrawable(R.drawable.ic_pin), mMap, new LatLng(shop.getLat(), shop.getLng()), shop.getName()), shop);
                            }
                            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 11));
                            isFill = true;
                        }
                    }
                }
            }
            @Override
            public void onRequestFailure(Call<ApiResponse<List<Shop>>> call, Throwable t) {

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
    protected int initViewSBAnchor() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapLoaded() {
                isLoadMap = false;
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatlng, 11));
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                if (!isFill && list != null) {
                    LatLngBounds.Builder builder = new LatLngBounds.Builder();
                    builder.include(myLatlng);
                    for (Shop shop : list) {
                        builder.include(new LatLng(shop.getLat(), shop.getLng()));
                        hashmap.put(CommomUtils.addMarker(getDrawable(R.drawable.ic_pin), mMap, new LatLng(shop.getLat(), shop.getLng()), shop.getName()), shop);
                    }
                    mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 11));
                    isFill = true;
                }
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent i = new Intent(NearbyActivity.this, DetailShopActivity.class);
                i.putExtra(AppConstant.MSG, hashmap.get(marker).getId()
                );
                startActivity(i);
                return false;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }


    @OnClick(R.id.imgHome)
    public void onViewClicked() {
        onBackPressed();
    }
}