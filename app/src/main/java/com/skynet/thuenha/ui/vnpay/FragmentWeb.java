package com.skynet.thuenha.ui.vnpay;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.thuenha.R;
import com.skynet.thuenha.ui.base.BaseFragment;
import com.skynet.thuenha.utils.AppConstant;


import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentWeb extends BaseFragment {
    @BindView(R.id.web)
    WebView web;
    Unbinder unbinder;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private String url;
    OnCallbackWebView listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnCallbackWebView) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public static FragmentWeb newInstance(String url) {


        Bundle args = new Bundle();
        args.putString(AppConstant.MSG, url);
        FragmentWeb fragment = new FragmentWeb();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_web;
    }


    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initVariables() {
        this.url = getArguments().getString(AppConstant.MSG);
        progressBar.setVisibility(View.VISIBLE);
        web.loadUrl(url);
//        web.setWebChromeClient(new MyCustomChromeClient(this));
//        web.setWebViewClient(new MyCustomWebViewClient(this));
        web.clearCache(true);
        web.clearHistory();
        web.getSettings().setJavaScriptEnabled(true);
//        web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        web.setWebViewClient(new PPWebViewClient());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class PPWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            LogUtils.e("onPageFinished " + url);
            if(progressBar!= null)
            progressBar.setVisibility(View.INVISIBLE);
//            if (progress != null && progress.isShowing()) {
//                progress.dismiss();
//                progress = null;
//            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(progressBar!= null)
                progressBar.setVisibility(View.VISIBLE);
            LogUtils.e("shouldOverrideUrlLoading " + url);
//            http://chapp.com.vn/cms/api/vnpay_php/vnpay_return.php?vnp_Amount=1000000&vnp_BankCode=NCB&vnp_BankTranNo=20190627095940&vnp_CardType=ATM&vnp_OrderInfo=Noi+dung+thanh+toan&vnp_PayDate=20190627100423&vnp_ResponseCode=00&vnp_TmnCode=CHAPP001&vnp_TransactionNo=13154247&vnp_TxnRef=20190627105810&vnp_SecureHashType=SHA256&vnp_SecureHash=1e984640f4c891b220c1a3636fe4b0bfe10b255eccf76c4528082857dfc37f38
            if (url.contains("chapp.com.vn/cms/api/vnpay_php/vnpay_return.php") && listener != null) {
                Uri uri = Uri.parse(url);
                String server = uri.getAuthority();
                String path = uri.getPath();
                String protocol = uri.getScheme();
                Set<String> args = uri.getQueryParameterNames();
                String code = uri.getQueryParameter("vnp_ResponseCode");
//                if(code.equals("00")) {
                    listener.onURLCallback(url);
//                    finishFragment();
//                }

            }
            return super.shouldOverrideUrlLoading(view, url);
        }


    }

    public interface OnCallbackWebView {
        void onURLCallback(String url);
    }
}
