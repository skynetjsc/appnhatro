package com.skynet.thuenha.ui.vnpay;



import android.net.Uri;

import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.network.api.ApiResponse;
import com.skynet.thuenha.network.api.ApiService;
import com.skynet.thuenha.network.api.ApiUtil;
import com.skynet.thuenha.network.api.CallBackBase;
import com.skynet.thuenha.ui.base.Interactor;
import com.skynet.thuenha.utils.AppConstant;

import java.util.Set;

import retrofit2.Call;
import retrofit2.Response;

public class RechargeRemoteImpl extends Interactor implements RechargeContract.Interactor {
    RechargeContract.Presenter presenter;

    public RechargeRemoteImpl(RechargeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public ApiService createService() {
        return ApiUtil.createNotTokenApi();
    }


    @Override
    public void doPayment(String amount,String note) {
        getmService().createLink(note,amount).enqueue(new CallBackBase<ApiResponse<String>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS && response.body().getData() != null) {
                        presenter.onSuccessGetPaymenConfirm(response.body().getData());
                    } else {
                        presenter.onError(response.body().getMessage());
                    }
                } else {
                    presenter.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<String>> call, Throwable t) {
                presenter.onErrorApi(t.getMessage());
            }
        });
    }

    @Override
    public void updateAmount(String url) {
        final Profile profile = AppController.getInstance().getmProfileUser();
        if (profile == null) {
            presenter.onErrorAuthorization();
            return;
        }
        //http://chapp.com.vn/cms/api/vnpay_php/vnpay_return.php?vnp_Amount=1000000&vnp_BankCode=NCB&vnp_BankTranNo=20190627095940&vnp_CardType=ATM&vnp_OrderInfo=Noi+dung+thanh+toan&vnp_PayDate=20190627100423&vnp_ResponseCode=00&vnp_TmnCode=CHAPP001&vnp_TransactionNo=13154247&vnp_TxnRef=20190627105810&vnp_SecureHashType=SHA256&vnp_SecureHash=1e984640f4c891b220c1a3636fe4b0bfe10b255eccf76c4528082857dfc37f38
        Uri uri = Uri.parse(url);
        String server = uri.getAuthority();
        String path = uri.getPath();
        String protocol = uri.getScheme();
        Set<String> args = uri.getQueryParameterNames();
        double vnp_Amount = Double.parseDouble(uri.getQueryParameter("vnp_Amount"));
        String vnp_TransactionNo = uri.getQueryParameter("vnp_TransactionNo");
        getmService().updateAccount(profile.getId(),AppConstant.TYPE_USER,vnp_Amount,vnp_TransactionNo).enqueue(new CallBackBase<ApiResponse>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS ) {
                        presenter.onSucessPaid();
                    } else {
                        presenter.onError(response.body().getMessage());
                    }
                } else {
                    presenter.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse> call, Throwable t) {
                presenter.onErrorApi(t.getMessage());

            }
        });
    }
}
