package com.skynet.mumgo.ui.auth.updateProfile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.mumgo.R;
import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.interfaces.SnackBarCallBack;
import com.skynet.mumgo.models.MyPlace;
import com.skynet.mumgo.models.Place;
import com.skynet.mumgo.models.Profile;
import com.skynet.mumgo.ui.base.BaseActivity;
import com.skynet.mumgo.ui.views.ProgressDialogCustom;
import com.skynet.mumgo.utils.AppConstant;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.iwf.photopicker.PhotoPicker;

public class ActivityProfileUpdate extends BaseActivity implements ProfileContract.View {
    @BindView(R.id.tvTitleToolbar)
    TextView tvToolbar;


    @BindView(R.id.tvAddress)
    TextView edtAddress;


    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtEmail)
    EditText edtEmail;

    Unbinder unbinder;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;

    private ProgressDialogCustom dialogLoading;
    private ProfileContract.Presenter presenter;
    private Profile profile;

    @Override
    protected int initLayout() {
        return R.layout.activity_update_profile;
    }


    @Override
    protected void initVariables() {
        presenter = new ProfilePresenter(this);
        dialogLoading = new ProgressDialogCustom(this);
        presenter.getInfor();

    }

    @Override
    protected void initViews() {
        unbinder = ButterKnife.bind(this);
        tvToolbar.setText("Cập nhật thông tin");
    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroyView();
        super.onDestroy();
    }


    @OnClick({R.id.imgBackToolbar, R.id.btnCancel, R.id.btnUpdate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBackToolbar:
                AppController.getInstance().getmSetting().put("show", true);
                onBackPressed();
                break;
            case R.id.btnCancel:
                AppController.getInstance().getmSetting().put("show", true);
                onBackPressed();
                break;
            case R.id.btnUpdate:
                presenter.update(edtName.getText().toString(), edtEmail.getText().toString(), edtAddress.getText().toString());
                break;
        }
    }

    @Override
    public void onSuccessGetInfor() {
        profile = AppController.getInstance().getmProfileUser();
        edtAddress.setText(profile.getAddress());
        edtName.setText(profile.getName());
        edtEmail.setText(profile.getEmail());
        setResult(RESULT_OK);
    }

    private void choosePhoto() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PhotoPicker.builder()
                            .setPhotoCount(1)
                            .setShowCamera(true)
                            .setShowGif(true)
                            .setPreviewEnabled(false)
                            .start(ActivityProfileUpdate.this, PhotoPicker.REQUEST_CODE);
                } else {

                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });


    }

    private boolean checkPermissionGranted() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 111);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 111:
                if (grantResults.length > 2 && grantResults[0] != PackageManager.PERMISSION_GRANTED && grantResults[1] != PackageManager.PERMISSION_GRANTED) {
                    checkPermissionGranted();
                    return;
                } else {
                    choosePhoto();
                }
                return;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PhotoPicker.REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<String> photos =
                    data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            File fileImage = new File(photos.get(0));
            if (!fileImage.exists()) {
                Toast.makeText(this, "File không tồn tại.", Toast.LENGTH_SHORT).show();
                return;
            }
            CropImage.activity(Uri.fromFile(fileImage))
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                File file = new File(resultUri.getPath());
                presenter.uploadAvatar(file);
//                Picasso.with(this).load(file).into(imgAvt);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }

        }
        if (requestCode == 1001 && resultCode == RESULT_OK) {
            presenter.getInfor();
            return;
        }

        if (requestCode == 1002 && resultCode == RESULT_OK && data != null) {
            Bundle b = data.getBundleExtra(AppConstant.BUNDLE);
            if (b != null) {
                Place place = b.getParcelable(AppConstant.MSG);
                if (place != null) {
                    MyPlace myPlace = new MyPlace();
                    myPlace.setName(place.getAddress());
                    myPlace.setDescription(place.getAddress());
                    myPlace.setLat(place.getLatLng().latitude);
                    myPlace.setLng(place.getLatLng().longitude);
                    edtAddress.setText(myPlace.getDescription());
                }
            }
        }
    }


    @Override
    public void onSuccessUpdatedAvatar() {
        presenter.getInfor();
        setResult(RESULT_OK);
    }

    @Override
    public void onSuccessUpdate() {
        presenter.getInfor();
        setResult(RESULT_OK);
        showToast("Cập nhật thông tin thành công", AppConstant.POSITIVE, new SnackBarCallBack() {
            @Override
            public void onClosedSnackBar() {
                finish();
            }
        });
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
        LogUtils.e(message);
    }

    @Override
    public void onError(String message) {
        LogUtils.e(message);
        showToast(message, AppConstant.NEGATIVE);
    }

    @Override
    public void onErrorAuthorization() {
        showDialogExpired();
    }


    @OnClick({R.id.tvAddress})
    public void onView3Clicked(View view) {
        startActivityForResult(new Intent(ActivityProfileUpdate.this, SearchMapAdressActivity.class), 1002);
    }


}
