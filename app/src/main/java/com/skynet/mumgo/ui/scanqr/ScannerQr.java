package com.skynet.mumgo.ui.scanqr;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.skynet.mumgo.R;
import com.skynet.mumgo.ui.base.BaseActivity;
import com.skynet.mumgo.ui.detailshop.DetailShopActivity;
import com.skynet.mumgo.utils.AppConstant;

import androidx.annotation.NonNull;

public class ScannerQr extends BaseActivity implements ScanContract.View {

    private ScanContract.Presenter presenter;
    private CodeScanner mCodeScanner;

    @Override
    protected int initLayout() {
        return R.layout.qrscan;
    }

    @Override
    protected void initVariables() {
        presenter = new ScanPresenter(this);
        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(ScannerQr.this, result.getText(), Toast.LENGTH_SHORT).show();
                        if(result == null || result.getText().isEmpty()) return;
                       int id =  Integer.parseInt(result.getText().replace(".jpg",""));
                        presenter.getShop(id);
                        Intent i = new Intent(ScannerQr.this, DetailShopActivity.class);
                        i.putExtra(AppConstant.MSG, id);
                        startActivity(i);
                        finish();
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }

    @Override
    public Context getMyContext() {
        return null;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hiddenProgress() {

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
}
