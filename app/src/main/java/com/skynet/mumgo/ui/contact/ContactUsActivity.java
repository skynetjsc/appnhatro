package com.skynet.mumgo.ui.contact;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.skynet.mumgo.R;
import com.skynet.mumgo.ui.base.BaseActivity;
import com.skynet.mumgo.ui.views.ProgressDialogCustom;
import com.skynet.mumgo.utils.AppConstant;
import com.skynet.mumgo.utils.CommomUtils;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactUsActivity extends BaseActivity implements OnMapReadyCallback, ContactUsContract.View {
    private static final int PLACE_PICKER_REQUEST = 10;
    @BindView(R.id.imageView12)
    ImageView imageView12;
    @BindView(R.id.imageView13)
    ImageView imageView13;
    @BindView(R.id.textView19)
    TextView textView19;

    @BindView(R.id.constraintLayout4)
    CardView constraintLayout4;
    @BindView(R.id.textView45)
    TextView textView45;
    @BindView(R.id.textView46)
    TextView textView46;
    @BindView(R.id.textView47)
    TextView textView47;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.editText3)
    EditText editText3;
    @BindView(R.id.editText4)
    EditText editText4;
    @BindView(R.id.editText5)
    EditText editText5;

    private GoogleMap mMap;

    private ProgressDialogCustom dialogLoading;
    private ContactUsContract.Presenter presenter;

    @Override
    protected int initLayout() {
        return R.layout.activity_contact;
    }

    @Override
    protected void initVariables() {
        dialogLoading = new ProgressDialogCustom(this);
        presenter = new ContactUsPresenter(this);
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


    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapLoaded() {
                CommomUtils.addMarker(getDrawable(R.drawable.ic_pin), mMap, new LatLng(21.05, 105.21), "Số 01, Tràng tiền, Quận Hoàn Kiếm\n" +
                        "Thành phố Hà Nội");
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(21.05, 105.21), 17));
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }


    @OnClick(R.id.imageView12)
    public void onViewClicked() {
        onBackPressed();
    }

    @Override
    public void onSucessgetFeedback() {
        showToast("Yêu cầu hỗ trợ đã được ghi nhận.", AppConstant.POSITIVE);
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    public void showProgress() {
        dialogLoading.showDialog();
    }

    @Override
    public void hiddenProgress() {
        dialogLoading.hideDialog();

    }

    @Override
    public void onErrorApi(String message) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onErrorAuthorization() {

    }

    @OnClick(R.id.btnSend)
    public void onViewSendClicked() {
        presenter.feedback(editText2.getText().toString(), editText3.getText().toString(), editText4.getText().toString(), editText5.getText().toString());
    }
}