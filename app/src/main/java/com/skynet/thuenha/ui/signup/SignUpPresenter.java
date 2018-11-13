package com.skynet.thuenha.ui.signup;

import android.net.Uri;
import android.util.Patterns;


import com.skynet.thuenha.R;
import com.skynet.thuenha.network.api.ApiUtil;
import com.skynet.thuenha.utils.AppConstant;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

public class SignUpPresenter implements SignUpContract.Presenter {
    SignUpContract.View view;
    SignUpContract.Interactor interactor;

    public SignUpPresenter(SignUpContract.View view) {
        this.view = view;
        interactor = new SignUpRemoteImpl(this);
    }

    @Override
    public void signUp(String phone,int type) {
        if (view == null) return;

        if (Patterns.PHONE.matcher(phone).matches()) {
            if (phone.length() < 10 || phone.length() > 11) {
                view.onError(view.getMyContext().getString(R.string.phone_invalid));
                return;
            }
        }

        view.showProgress();
        interactor.doSignUp( phone, type);

    }

    @Override
    public void signUpSuccess(String code) {
        if (view == null) return;
        view.hiddenProgress();
        view.signUpSuccess(code);
    }

    @Override
    public void onDestroyView() {
        view = null;

    }

    @Override
    public void onErrorApi(String message) {
        if (view != null) {
            view.hiddenProgress();
            view.onErrorApi(message);
        }
    }

    @Override
    public void onError(String message) {
        if (view != null) {
            view.hiddenProgress();
            view.onError(message);
        }
    }

    @Override
    public void onErrorAuthorization() {

    }

}
