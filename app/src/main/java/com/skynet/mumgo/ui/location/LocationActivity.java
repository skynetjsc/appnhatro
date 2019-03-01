package com.skynet.mumgo.ui.location;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.skynet.mumgo.R;
import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.models.MyPlace;
import com.skynet.mumgo.ui.base.BaseActivity;
import com.skynet.mumgo.ui.category.listProduct.ListProductActivity;
import com.skynet.mumgo.utils.AppConstant;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LocationActivity extends BaseActivity implements LocationContract.View {
    @BindView(R.id.textView8)
    TextView textView8;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.progressBar)
    ProgressBar loading;
    private FusedLocationProviderClient mFusedLocationClient;
    private LatLng myLatlng;
    LocationContract.Presenter presenter;
    private MyPlace myPlace;
    private boolean isDetected = false;

    @Override
    protected int initLayout() {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setExitTransition(new Explode());
        return R.layout.activity_location;
    }

    @Override
    protected void initVariables() {
        presenter = new LocationPresenter(this);
        if(AppController.getInstance().getmSetting().getString("myplace",null)!=null){
            myPlace = new Gson().fromJson(AppController.getInstance().getmSetting().getString("myplace",null),MyPlace.class);
            onSuccessGetMyAddress(myPlace);
            return;
        }
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
                            AppController.getInstance().getmSetting().put("latlng", new Gson().toJson(myLatlng));
                            getNearby(myLatlng);
                            isDetected = true;
                        }
                    }
                });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isDetected ) return;
                if (AppController.getInstance().getmSetting().getString("latlng", null) != null) {
                    LatLng latLng = new Gson().fromJson(AppController.getInstance().getmSetting().getString("latlng", null), LatLng.class);
                    if (latLng != null) {
                        myLatlng = latLng;
                        getNearby(latLng);
                    }
                }
            }
        }, 2000);
    }

    private void getNearby(LatLng myLatlng) {
        presenter.getMyAddress(myLatlng);
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
                                AppController.getInstance().getmSetting().put("latlng", new Gson().toJson(myLatlng));
                                getNearby(myLatlng);
                                isDetected = true;
                            }
                        }
                    });
        }
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tvAddress)
    public void onViewClicked() {
        Intent intent = new Intent(this, ListProductActivity.class);
        intent.putExtra(AppConstant.MSG,getIntent().getIntExtra(AppConstant.MSG,0));
        intent.putExtra("name",getIntent().getStringExtra("name"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(this, tvAddress, "nameTransition");
            startActivity(intent,options.toBundle());
        } else {
            startActivity(intent);
        }
        finish();
    }

    @Override
    public void onSuccessGetMyAddress(MyPlace response) {
        AppController.getInstance().getmSetting().put("myplace",new Gson().toJson(response));
        tvAddress.setText(response.getAddress());
        this.myPlace = response;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onViewClicked();
            }
        }, 700);
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroyView();
        super.onDestroy();
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
