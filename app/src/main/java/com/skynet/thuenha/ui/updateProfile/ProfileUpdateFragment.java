package com.skynet.thuenha.ui.updateProfile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.skynet.thuenha.R;
import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.models.Address;
import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.ui.base.BaseActivity;
import com.skynet.thuenha.ui.base.BaseFragment;
import com.skynet.thuenha.ui.chosseAddress.ChooseAddressFragment;
import com.skynet.thuenha.ui.views.ProgressDialogCustom;
import com.skynet.thuenha.utils.AppConstant;
import com.squareup.picasso.Picasso;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.iwf.photopicker.PhotoPicker;

import static android.app.Activity.RESULT_OK;

public class ProfileUpdateFragment extends BaseActivity implements ProfileContract.View, ChooseAddressFragment.CallBackChooseAddress {
    @BindView(R.id.tvToolbar)
    TextView tvToolbar;

    @BindView(R.id.imgAvt)
    CircleImageView imgAvt;
    @BindView(R.id.imgAward)
    ImageView imgAward;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.layoutProfile)
    ConstraintLayout layoutProfile;
    @BindView(R.id.edtAddress)
    TextInputEditText edtAddress;
    @BindView(R.id.inputLayoutAddress)
    TextInputLayout inputLayoutAddress;
    @BindView(R.id.edtPassword)
    TextInputEditText edtPassword;
    @BindView(R.id.edtName)
    TextInputEditText edtName;
    @BindView(R.id.inputLayoutPassword)
    TextInputLayout inputLayoutPassword;
    Unbinder unbinder;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;
    @BindView(R.id.textInputLayout)
    TextInputLayout textInputLayout;
    Unbinder unbinder1;
    private ProgressDialogCustom dialogLoading;
    private ProfileContract.Presenter presenter;
    private Profile profile;
    private Address myCity, myDistrict;

    @Override
    protected int initLayout() {
        return R.layout.fragment_update_profile;
    }


    @Override
    protected void initVariables() {
        presenter = new ProfilePresenter(this);
        dialogLoading = new ProgressDialogCustom(this);
        presenter.getInfor();
        setupAddress();

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


    @OnClick({R.id.btn_back, R.id.btnUpdate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.btnUpdate:
                presenter.update(edtName.getText().toString(), edtAddress.getText().toString(), edtPassword.getText().toString());
                break;
        }
    }

    @Override
    public void onSuccessGetInfor() {
        profile = AppController.getInstance().getmProfileUser();
        tvName.setText(profile.getName());
//        phoneTxt.setText("Chủ hàng");
        edtName.setText(profile.getName());
        edtAddress.setText(profile.getAddress());
        edtPassword.setText("******");
        edtAddress.setEnabled(false);
        tvAddress.setText(profile.getAddress());
        tvEmail.setText(profile.getPhone());
        if (profile.getAvatar() != null && !profile.getAvatar().isEmpty())
            Picasso.with(this).load(profile.getAvatar()).into(imgAvt);
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
                            .start(ProfileUpdateFragment.this, PhotoPicker.REQUEST_CODE);
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
                Picasso.with(this).load(file).into(imgAvt);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }

        }
        if (requestCode == 1001 && resultCode == RESULT_OK) {
            presenter.getInfor();
            return;
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
        showToast("Cập nhật thông tin thành công", AppConstant.POSITIVE);
    }

    @Override
    public void onSuccessSignUp() {

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
    }

    @Override
    public void onErrorAuthorization() {
        showDialogExpired();
    }


    public void setupAddress() {
        if (AppController.getInstance().getmSetting().getString(AppConstant.city) != null && !AppController.getInstance().getmSetting().getString(AppConstant.city).isEmpty())
            myCity = new Gson().fromJson(AppController.getInstance().getmSetting().getString(AppConstant.city), Address.class);
        if (AppController.getInstance().getmSetting().getString(AppConstant.district) != null && !AppController.getInstance().getmSetting().getString(AppConstant.district).isEmpty())
            myDistrict = new Gson().fromJson(AppController.getInstance().getmSetting().getString(AppConstant.district), Address.class);


    }

    @OnClick({R.id.imgAvt, R.id.inputLayoutAddress, R.id.tvChooseAddress})
    public void onView3Clicked(View view) {
        switch (view.getId()) {
            case R.id.imgAvt:
                choosePhoto();
                break;
            default:
                FragmentManager fragmentManager = getSupportFragmentManager();
                ChooseAddressFragment fragmentSearch = ChooseAddressFragment.newInstance(myCity);
                fragmentManager.beginTransaction().replace(R.id.layoutRootUpdateProfile, fragmentSearch, fragmentSearch.getClass().getSimpleName())
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }

    @Override
    public void onChooseAddress() {
        setupAddress();
        String address = "";
        if (myDistrict != null) address += myDistrict.getName() + ",";
        if (myCity != null) address += myCity.getName();
        edtAddress.setText(address);
        getSupportFragmentManager().popBackStack();
    }
}
