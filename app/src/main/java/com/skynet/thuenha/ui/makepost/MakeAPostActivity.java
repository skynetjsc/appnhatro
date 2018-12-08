package com.skynet.thuenha.ui.makepost;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.skynet.thuenha.R;
import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.interfaces.ICallback;
import com.skynet.thuenha.interfaces.SnackBarCallBack;
import com.skynet.thuenha.models.Address;
import com.skynet.thuenha.models.DetailPost;
import com.skynet.thuenha.models.Image;
import com.skynet.thuenha.models.Service;
import com.skynet.thuenha.models.Utility;
import com.skynet.thuenha.ui.base.BaseActivity;
import com.skynet.thuenha.ui.chosseAddress.ChooseAddressFragment;
import com.skynet.thuenha.ui.detailPost.DetailPostActivity;
import com.skynet.thuenha.ui.views.DialogInput;
import com.skynet.thuenha.ui.views.DialogTwoButtonUtil;
import com.skynet.thuenha.ui.views.ProgressDialogCustom;
import com.skynet.thuenha.utils.AppConstant;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.iwf.photopicker.PhotoPicker;

public class MakeAPostActivity extends BaseActivity implements MakeAPostContract.View, View.OnFocusChangeListener,ChooseAddressFragment.CallBackChooseAddress, DialogTwoButtonUtil.DialogOneButtonClickListener, ICallback {
    MakeAPostContract.Presenter presenter;
    ProgressDialogCustom dialogLoading;
    @BindView(R.id.tvToolbar)
    TextView tvToolbar;

    @BindView(R.id.textView12)
    TextView textView12;
    @BindView(R.id.rcvTypeService)
    RecyclerView rcvTypeService;
    @BindView(R.id.textView13)
    TextView textView13;
    @BindView(R.id.textView14)
    TextView textView14;
    @BindView(R.id.edtTitle)
    EditText edtTitle;
    @BindView(R.id.textView17)
    TextView textView17;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.textView18)
    TextView textView18;
    @BindView(R.id.editText2)
    EditText edtPrice;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.textView19)
    TextView textView19;
    @BindView(R.id.edtArea)
    EditText edtArea;
    @BindView(R.id.textView20)
    TextView textView20;
    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.textView21)
    TextView textView21;
    @BindView(R.id.tvCity)
    TextView tvCity;
    @BindView(R.id.view5)
    View view5;
    @BindView(R.id.textView22)
    TextView textView22;
    @BindView(R.id.tvDistrict)
    TextView tvDistrict;
    @BindView(R.id.view6)
    View view6;
    @BindView(R.id.textView23)
    TextView textView23;
    @BindView(R.id.edtAddress)
    EditText edtAddress;
    @BindView(R.id.edtBed)
    EditText edtBed;
    @BindView(R.id.edtWc)
    EditText edtWc;
    @BindView(R.id.view7)
    View view7;
    @BindView(R.id.textView24)
    TextView textView24;
    @BindView(R.id.rcvUtility)
    RecyclerView rcvUtility;
    @BindView(R.id.textView25)
    TextView textView25;
    @BindView(R.id.editText3)
    EditText editText3;
    @BindView(R.id.textView26)
    TextView textView26;
    @BindView(R.id.textView27)
    TextView textView27;
    @BindView(R.id.textView28)
    TextView textView28;
    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView2;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    private Address myCity, myDistrict;
    private List<Service> listServices;
    private List<Utility> listUtilities;
    private List<Utility> listUtilityRequest = new ArrayList<>();
    private List<Image> listImage;
    private AdapterPhoto adapterPhoto;
    private double price = 0;
    private DialogTwoButtonUtil dialogConfirmPrice;
    private int idService = -1;
    private DetailPost postToEdit;

    private ICallback iCallbackDeletePhoto = new ICallback() {
        @Override
        public void onCallBack(final int pos) {
            if (pos != -1 && postToEdit != null && postToEdit.getImage_test() != null && pos < postToEdit.getImage_test().size()) {
                new DialogTwoButtonUtil(MakeAPostActivity.this, R.drawable.ic_question, "Xóa ảnh bài đăng", "Nếu như xóa ảnh sẽ không thể được khôi phục lại. Bạn có chắc chắn muốn xóa ?", new DialogTwoButtonUtil.DialogOneButtonClickListener() {
                    @Override
                    public void okClick() {
                        presenter.deletePhoto(postToEdit.getImage_test().get(pos).getId());
                        listImage.remove(pos);
                        adapterPhoto.notifyItemRemoved(pos);
                        adapterPhoto.notifyItemRangeChanged(pos, adapterPhoto.getItemCount());
                    }
                }).show();
            } else {
                listImage.remove(pos);
                adapterPhoto.notifyItemRemoved(pos);
                adapterPhoto.notifyItemRangeChanged(pos, adapterPhoto.getItemCount());
            }
        }
    };

    @Override
    protected int initLayout() {
        return R.layout.activity_make_a_post;
    }

    @Override
    protected void initVariables() {
        listImage = new ArrayList<>();
        adapterPhoto = new AdapterPhoto(listImage, this, iCallbackDeletePhoto);
        recyclerView2.setAdapter(adapterPhoto);
        if (getIntent() != null && getIntent().getBundleExtra(AppConstant.BUNDLE) != null)
            postToEdit = getIntent().getBundleExtra(AppConstant.BUNDLE).getParcelable(AppConstant.MSG);
        if (postToEdit != null) {
            tvToolbar.setText("Chỉnh sửa bài đăng");
            price = postToEdit.getPost().getPrice();
            edtPrice.setText(String.format("%,.0f", postToEdit.getPost().getPrice()));
            edtAddress.setText(postToEdit.getPost().getAddress());
            edtArea.setText(postToEdit.getPost().getArea() + "");
            edtBed.setText(postToEdit.getPost().getNumber_bed() + "");
            edtWc.setText(postToEdit.getPost().getNumber_wc() + "");
            edtTitle.setText(postToEdit.getPost().getTitle());
            editText3.setText(postToEdit.getPost().getContent());
            if (postToEdit.getImage_test() != null && !postToEdit.getImage_test().isEmpty()) {
                listImage.addAll(postToEdit.getImage_test());
                adapterPhoto.notifyDataSetChanged();
            }
        }
        presenter = new MakeAPostPresenter(this);
        dialogLoading = new ProgressDialogCustom(this);
        presenter.getService();
        presenter.getUtility();

    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        setupAddress();
        rcvTypeService.setLayoutManager(new GridLayoutManager(this, 3));
        rcvTypeService.setHasFixedSize(true);
        rcvUtility.setLayoutManager(new GridLayoutManager(this, 2));
        rcvUtility.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setHasFixedSize(true);
        tvToolbar.setText("Đăng tin");
        edtPrice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !edtPrice.getText().toString().isEmpty()) {
                    try {
                        price = Double.parseDouble(edtPrice.getText().toString());
                        edtPrice.setText(String.format("%,.0f", price));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
//        edtPrice.setOnFocusChangeListener(this);
        edtArea.setOnFocusChangeListener(this);
        edtTitle.setOnFocusChangeListener(this);
        edtWc.setOnFocusChangeListener(this);
        edtBed.setOnFocusChangeListener(this);
    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }

    @Override
    public void onSucessGetService(List<Service> list) {
        this.listServices = list;
        if (listServices.size() > 0 && getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().getInt(AppConstant.MSG, -1) != -1) {
            for (Service s : listServices) {
                if (s.getId() == getIntent().getExtras().getInt(AppConstant.MSG, -1)) {
                    s.setChecked(true);
                    presenter.getPriceServiceToChooseService(s.getId());
                    break;
                }
            }
        }
        rcvTypeService.setAdapter(new AdapterService(this.listServices, this, this));
    }

    @Override
    public void onSucessGetUtility(List<Utility> list) {
        this.listUtilities = list;
        if (postToEdit != null && postToEdit.getListUtilies() != null) {
            for (Utility utility : this.listUtilities) {
                for (Utility utilityEdt : postToEdit.getListUtilies()) {
                    if (utilityEdt.getId() == utility.getId()) {
                        utility.setChecked(true);
                        break;
                    }
                }
            }
        }
        rcvUtility.setAdapter(new AdapterUtility(this.listUtilities, this));
    }

    public void setupAddress() {
        if (AppController.getInstance().getmSetting().getString(AppConstant.city) != null && !AppController.getInstance().getmSetting().getString(AppConstant.city).isEmpty())
            myCity = new Gson().fromJson(AppController.getInstance().getmSetting().getString(AppConstant.city), Address.class);
        if (AppController.getInstance().getmSetting().getString(AppConstant.district) != null && !AppController.getInstance().getmSetting().getString(AppConstant.district).isEmpty())
            myDistrict = new Gson().fromJson(AppController.getInstance().getmSetting().getString(AppConstant.district), Address.class);
        if (myCity != null)
            tvCity.setText(myCity.getName());
        if (myDistrict != null)
            tvDistrict.setText(myDistrict.getName());
    }

    @Override
    public void onSucessSubmitPost(final int idPost) {
        showToast("Bạn đã đăng bài thành công!", AppConstant.POSITIVE, new SnackBarCallBack() {
            @Override
            public void onClosedSnackBar() {
                Intent i = new Intent(MakeAPostActivity.this, DetailPostActivity.class);
                i.putExtra(AppConstant.MSG, idPost);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onSucessEditPost(final int idPost) {
        showToast("Bạn đã cập nhật bài viết thành công!", AppConstant.POSITIVE, new SnackBarCallBack() {
            @Override
            public void onClosedSnackBar() {
                Intent i = new Intent(MakeAPostActivity.this, DetailPostActivity.class);
                i.putExtra(AppConstant.MSG, idPost);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onSucessGetPriceToChooseService(double price) {
        if (AppController.getInstance().getmProfileUser().getAccountWallet() < price) {
            showToast("Tài khoản của bạn không đủ " + price + "vnđ để đăng tin này. Vui lòng nạp thêm tiền!", AppConstant.NEGATIVE);
            String content = "";
            if (AppController.getInstance().getmProfileUser().getType() == 1) {
                content = "NGUOITHUE " + AppController.getInstance().getmProfileUser().getPhone();
            } else {
                content = "CHUNHA " + AppController.getInstance().getmProfileUser().getPhone();

            }
            new DialogInput(this, R.drawable.ic_question, "HƯỚNG DẪN NẠP TIỀN",
                    Html.fromHtml(
                            String.format(
                                    getString(R.string.ck), content)), new DialogInput.DialogOneButtonClickListener() {
                @Override
                public void okClick() {

                }
            }).show();
        } else {
//            showToast("Tài khoản của bạn không đủ " + price + "vnđ để đăng tin này. Vui lòng nạp thêm tiền!", AppConstant.NEGATIVE);
        }
    }

    @Override
    public void onSucessGetPriceService(double price) {

        dialogConfirmPrice = new DialogTwoButtonUtil(this, R.drawable.ic_question, "Xác nhận đăng tin",
                Html.fromHtml(String.format(getString(R.string.content_confirm_post), price)),
                this);
        dialogConfirmPrice.show();
    }

    @Override
    public void onSucessDeletePhoto() {

    }

    @Override
    public void onSucessAddPhoto(List<Image> photo) {
        listImage.clear();
        listImage.addAll(photo);
        adapterPhoto.notifyDataSetChanged();

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
    protected void onPause() {
        if (dialogConfirmPrice != null && dialogConfirmPrice.isShowing()) {
            dialogConfirmPrice.dismiss();
        }
        super.onPause();
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
    protected void onDestroy() {
        presenter.onDestroyView();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_back, R.id.tvCity, R.id.tvChooseCity, R.id.tvDistrict, R.id.tvChooseDistrict, R.id.btnUpload, R.id.btnsubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;

            case R.id.btnUpload:
                if (listImage.size() <= 10)
                    choosePhoto();
                else
                    showToast("Chỉ có thể cung cấp tối đa 10 ảnh!", AppConstant.NEGATIVE);
                break;
            case R.id.btnsubmit:
                if (listServices == null) {
                    showToast("Dữ liệu không đầy đủ!", AppConstant.NEGATIVE);
                    return;
                }

                for (Service service : listServices) {
                    if (service.isChecked()) {
                        idService = service.getId();
                        break;
                    }
                }
                if (idService == -1) {
                    showToast("Vui lòng chọn loại dịch vụ.", AppConstant.NEGATIVE);
                    nestedScrollView.scrollTo(0, 0);
                    return;
                }
                if (myCity == null || myDistrict == null) {
                    showToast("Vui lòng chọn địa chỉ.", AppConstant.NEGATIVE);
                    nestedScrollView.scrollTo(0, 0);
                    return;
                }


                listUtilityRequest.clear();
                if (listUtilities != null && listUtilities.size() > 0) {
                    for (Utility u : listUtilities) {
                        if (u.isChecked() || u.getNumber() > 0) {
                            listUtilityRequest.add(u);
                        }
                    }
                }
                if (postToEdit != null) {
                    presenter.edtPost(postToEdit.getPost().getId(), idService, edtTitle.getText().toString(), price + "",
                            edtArea.getText().toString(), myCity.getId(), myDistrict.getId(),
                            edtAddress.getText().toString(), listUtilityRequest, editText3.getText().toString(), edtBed.getText().toString(), edtWc.getText().toString(), listImage
                    );
                    return;
                }
//                if (listImage.size() == 0) {
//                    showToast("Hãy cung cấp ảnh căn hộ để thu hút thêm khách hàng.", AppConstant.NEGATIVE);
//                    return;
//                }
                presenter.getPriceService(idService);

                break;
            default:
                FragmentManager fragmentManager = getSupportFragmentManager();
                ChooseAddressFragment fragmentSearch = ChooseAddressFragment.newInstance(myCity);
                fragmentManager.beginTransaction().replace(R.id.layoutRootMakePost, fragmentSearch, fragmentSearch.getClass().getSimpleName())
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }

    @Override
    public void onChooseAddress() {
        getSupportFragmentManager().popBackStack();
        setupAddress();
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
//                    Matisse.from(ChoosePhotoActivity.this)
//                            .choose(MimeType.ofImage())
//                            .countable(true)
//                            .maxSelectable(1)
//                            .capture(true)
//
//                            .captureStrategy(
//                                    new CaptureStrategy(true, "com.skynetsoftware.nailsshop.provider"))
//                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
//                            .thumbnailScale(0.85f)
//                            .imageEngine(new GlideEngine())
//                            .forResult(REQUEST_CODE_CHOOSE);

                    PhotoPicker.builder()
                            .setPhotoCount(10 - listImage.size())
                            .setShowCamera(true)
                            .setShowGif(true)
                            .setPreviewEnabled(false)
                            .start(MakeAPostActivity.this, PhotoPicker.REQUEST_CODE);
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
        if (requestCode == PhotoPicker.REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                List<Image> listPickup = new ArrayList<>();
                for (String urlPath : photos) {
                    File fileImage = new File(urlPath);
                    if (!fileImage.exists()) {
                        Toast.makeText(this, "File không tồn tại.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Image image = new Image();
                    image.setId(-1);
                    image.setFile(fileImage);
                    listPickup.add(image);
                }

                listImage.addAll(listPickup);
                adapterPhoto.notifyItemInserted(adapterPhoto.getItemCount() - 1);
                recyclerView2.smoothScrollToPosition(adapterPhoto.getItemCount() - 1);
                if (postToEdit != null && postToEdit.getPost() != null) {
                    presenter.addPhoto(listPickup, postToEdit.getPost().getId());
                }
//                CropImage.activity(Uri.fromFile(fileImage))
//                        .setAspectRatio(2, 1)
//                        .setRequestedSize(800, 400, CropImageView.RequestSizeOptions.RESIZE_EXACT)
//
//                        .start(this);
            }

//            File fileImage = CommomUtils.scanFileMetisse(this, Matisse.obtainResult(data).get(0));
            // File fileImage = new File(Matisse.obtainPathResult(data).get(0));

            //  LogUtils.d("Matisse", "mSelected: " + (fileImage.exists() ? fileImage.getAbsolutePath() : "null"));

        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//            if (resultCode == RESULT_OK) {
//                Uri resultUri = result.getUri();
//                File file = new File(resultUri.getPath());
//                listImage.add(file);
//                adapterPhoto.notifyItemInserted(adapterPhoto.getItemCount() - 1);
//                recyclerView2.smoothScrollToPosition(adapterPhoto.getItemCount() - 1);
//            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                Exception error = result.getError();
//            }

        }
    }

    @Override
    public void okClick() {
        presenter.submitPost(idService, edtTitle.getText().toString(), price + "",
                edtArea.getText().toString(), myCity.getId(), myDistrict.getId(),
                edtAddress.getText().toString(), listUtilityRequest, editText3.getText().toString(), edtBed.getText().toString(), edtWc.getText().toString(), listImage
        );
    }

    @Override
    public void onCallBack(int pos) {
        presenter.getPriceServiceToChooseService(listServices.get(pos).getId());
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        ((EditText) v).selectAll();
    }
}
