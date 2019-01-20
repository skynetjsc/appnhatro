package com.skynet.mumgo.ui.profile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mindorks.paracamera.Camera;
import com.skynet.mumgo.R;
import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.models.Image;
import com.skynet.mumgo.models.Profile;
import com.skynet.mumgo.ui.base.BaseActivity;
import com.skynet.mumgo.ui.views.ProgressDialogCustom;
import com.skynet.mumgo.ui.views.ViewpagerNotSwipe;
import com.skynet.mumgo.utils.AppConstant;
import com.squareup.picasso.Picasso;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.iwf.photopicker.PhotoPicker;

public class ProfileActivity extends BaseActivity implements UploadContract.View, ChoosePhotoBottomSheet.ChoosePhotoOptionCallback {

    @BindView(R.id.imgMore)
    ImageView imgMore;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewpagerNotSwipe viewpager;
    @BindView(R.id.bottomNavigationViewEx)
    BottomNavigationViewEx bnve;
    @BindView(R.id.imgCover)
    ImageView imgCover;
    @BindView(R.id.imgAvatar)
    CircleImageView imgAvatar;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    private UploadContract.Presenter presenter;
    private ProgressDialogCustom dialogLoading;
    private int typeUpload;
    Camera camera;

    private ChoosePhotoBottomSheet choosePhotoBottomSheet;

    @Override
    protected int initLayout() {
        return R.layout.activity_profile;
    }

    @Override
    protected void initVariables() {
        dialogLoading = new ProgressDialogCustom(this);
        choosePhotoBottomSheet = new ChoosePhotoBottomSheet(this, this);
        presenter = new UploadPresenter(this);
        Profile profile = AppController.getInstance().getmProfileUser();
        if (profile != null) {
            tvName.setText(profile.getName());
            if (profile.getAvatar() != null && !profile.getAvatar().isEmpty()) {
                Picasso.with(this).load(profile.getAvatar()).fit().centerCrop().into(imgAvatar);
            }
            if (profile.getCover() != null && !profile.getCover().isEmpty()) {
                Picasso.with(this).load(profile.getCover()).fit().centerCrop().into(imgCover);
            }
        }
// Build the camera
        camera = new Camera.Builder()
                .resetToCorrectOrientation(true)// it will rotate the camera bitmap to the correct orientation from meta data
                .setTakePhotoRequestCode(1)
                .setDirectory("pics")
                .setName("ali_" + System.currentTimeMillis())
                .setImageFormat(Camera.IMAGE_JPEG)
                .setCompression(75)
                .setImageHeight(1000)// it will try to achieve this height as close as possible maintaining the aspect ratio;
                .build(this);
    }

    @Override
    protected void initViews() {
        bnve.enableAnimation(false);
        bnve.enableShiftingMode(false);
        bnve.setTextVisibility(false);
        bnve.enableItemShiftingMode(false);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/SF-UI-Text-Medium.otf");
        bnve.setTypeface(custom_font);

        bnve.setupWithViewPager(viewpager);
        viewpager.setPagingEnabled(true);
        viewpager.setCurrentItem(1);
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


    @OnClick({R.id.imgBack, R.id.imgMore})
    public void onView2Clicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                onBackPressed();
                break;
            case R.id.imgMore:
                break;
        }
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
                            .start(ProfileActivity.this, PhotoPicker.REQUEST_CODE);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == RESULT_OK) {

            return;
        }
        if (requestCode == PhotoPicker.REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                for (String urlPath : photos) {
                    File fileImage = new File(urlPath);
                    if (!fileImage.exists()) {
                        Toast.makeText(this, "File không tồn tại.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    CropImage.activity(Uri.fromFile(fileImage))
                            .setAspectRatio(4, 3)
//                            .setRequestedSize(800, 800, CropImageView.RequestSizeOptions.RESIZE_EXACT)
                            .start(this);
                    break;
                }

//                listImage.addAll(listPickup);
//                adapterPhoto.notifyItemInserted(adapterPhoto.getItemCount() - 1);
//                recyclerView2.smoothScrollToPosition(adapterPhoto.getItemCount() - 1);
//                if (postToEdit != null && postToEdit.getPost() != null) {
//                    presenter.addPhoto(listPickup, postToEdit.getPost().getId());
//                }

            }

//            File fileImage = CommomUtils.scanFileMetisse(this, Matisse.obtainResult(data).get(0));
            // File fileImage = new File(Matisse.obtainPathResult(data).get(0));

            //  LogUtils.d("Matisse", "mSelected: " + (fileImage.exists() ? fileImage.getAbsolutePath() : "null"));

        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                File file = new File(resultUri.getPath());
                if (typeUpload == 1) {
                    Picasso.with(this).load(file).fit().centerCrop().into(imgAvatar);
                    presenter.upload(file, typeUpload);

                } else if (typeUpload == 2) {
                    Picasso.with(this).load(file).fit().centerCrop().into(imgCover);
                    presenter.upload(file, typeUpload);

                } else {
                    LogUtils.e(file.getAbsoluteFile());
                    List<Image> list = new ArrayList<>();
                    Image image = new Image();
                    image.setFile(file);
                    image.setId(-1);
                    list.add(image);


                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }

        }

        if (requestCode == Camera.REQUEST_TAKE_PHOTO) {
            String path = camera.getCameraBitmapPath();
            File file = new File(path);
            LogUtils.e(path);

            if (file.exists()) {
                List<Image> list = new ArrayList<>();
                Image image = new Image();
                image.setFile(file);
                image.setId(-1);
                list.add(image);


            } else {
                Toast.makeText(this.getApplicationContext(), "Picture not taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick({R.id.imgCover, R.id.imgAvatar, R.id.imgAddNewMessage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgAddNewMessage:
                choosePhotoBottomSheet.show();
                break;
            case R.id.imgCover:
                typeUpload = 2;
                choosePhoto();
                break;
            case R.id.imgAvatar:
                typeUpload = 1;
                choosePhoto();

                break;
        }
    }

    @Override
    public void onSucessUploadAvat() {
        setResult(RESULT_OK);
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

    @Override
    public void onClickCapturePhoto() {
        typeUpload = 3;
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    try {
                        camera.takePicture();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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

    @Override
    public void onClickGalleryPhoto() {
        typeUpload = 3;
        choosePhoto();

    }
}
