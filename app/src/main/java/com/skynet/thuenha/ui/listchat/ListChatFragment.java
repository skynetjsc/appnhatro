package com.skynet.thuenha.ui.listchat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.thuenha.R;
import com.skynet.thuenha.application.AppController;
import com.skynet.thuenha.models.ChatItem;
import com.skynet.thuenha.ui.base.BaseFragment;
import com.skynet.thuenha.ui.chatting.ChatActivity;
import com.skynet.thuenha.ui.choosewhorent.ListViewerActivity;
import com.skynet.thuenha.ui.listchat.ListChatAdapter.ChatCallBack;
import com.skynet.thuenha.ui.listviewer.ListViewerFragment;
import com.skynet.thuenha.ui.notification.NotificationActivity;
import com.skynet.thuenha.utils.AppConstant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class ListChatFragment extends BaseFragment implements ListChatContract.View, ChatCallBack, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.tvToolbar)
    TextView tvToolbar;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    @BindView(R.id.noti)
    ImageView noti;
    private ListChatContract.Presenter presenter;
    private Badge badget;

    public static ListChatFragment newInstance() {
        Bundle args = new Bundle();
        ListChatFragment fragment = new ListChatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_list_chat;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this, view);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv.setHasFixedSize(true);
        swipe.setOnRefreshListener(this);
        noti.setVisibility(View.GONE);
    }

    @Override
    protected void initVariables() {
        presenter = new ListChatPresenter(this);
        presenter.getListChat();

    }

    @Override
    public void onResume() {
        super.onResume();
        bindBadges();
    }

    private void bindBadges() {
//        if (AppController.getInstance().getmProfileUser().getNoty() != 0) {
//            if (badget == null)
//                badget = new QBadgeView(getContext())
//                        .setBadgeNumber(AppController.getInstance().getmProfileUser().getNoty())
//                        .setGravityOffset(12, 2, true)
//                        .bindTarget(noti)
//                        .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
//                            @Override
//                            public void onDragStateChanged(int dragState, Badge badge, View targetView) {
////                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState)
////                            Toast.makeText(BadgeViewActivity.this, R.string.tips_badge_removed, Toast.LENGTH_SHORT).show();
//                            }
//                        });
//            else
//                badget.setBadgeNumber(AppController.getInstance().getmProfileUser().getNoty());
//        } else if (badget != null) {
//            badget.hide(true);
//        }
    }

    @OnClick(R.id.noti)
    public void onClickNoti() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        NotificationActivity fragmentSearch = NotificationActivity.newInstance();
        fragmentManager.beginTransaction().replace(R.id.layoutRootChat, fragmentSearch, fragmentSearch.getClass().getSimpleName())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onSucessGetListChat(List<ChatItem> list) {
        rcv.setAdapter(new ListChatAdapter(list, getContext(), this));
    }

    @Override
    public void onSucessConfirmChat() {
        showToast("Xác nhận cho thuê thành công!", AppConstant.POSITIVE);
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    public void showProgress() {
        swipe.setRefreshing(true);
    }

    @Override
    public void hiddenProgress() {
        swipe.setRefreshing(false);

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
        showDialogExpiredToken();
    }

    @Override
    public void onClickItemChat(ChatItem chatItem) {
        Intent i = new Intent(getActivity(), ChatActivity.class);
        Bundle b = new Bundle();
        b.putParcelable(AppConstant.INTENT, chatItem.getShop());
        b.putParcelable("user", chatItem.getUse());
        b.putInt("idPost", chatItem.getId_post());
        b.putString("avt", AppController.getInstance().getmProfileUser().getType() == 1 ? chatItem.getShop().getAvatar() : chatItem.getUse().getAvatar());
        i.putExtra(AppConstant.BUNDLE, b);
        startActivityForResult(i, 1000);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bindBadges();
        if (requestCode == 1000 && resultCode == getActivity().RESULT_OK) onRefresh();
        if (requestCode == 100 && resultCode == getActivity().RESULT_OK && data != null) {
            presenter.confirmHired(chatItem.getId_post(),
                    AppController.getInstance().getmProfileUser().getType() == 1 ? chatItem.getShop().getId() : chatItem.getUse().getId(),
                    data.getExtras().getString(AppConstant.MSG));
        }
    }

    ChatItem chatItem;

    @Override
    public void onClickConfirm(ChatItem chatItem) {
        if (AppController.getInstance().getmProfileUser().getType() == 2) {
            this.chatItem = chatItem;
            Intent i = new Intent(getActivity(), ListViewerActivity.class);
            i.putExtra(AppConstant.MSG, chatItem.getId_post());
            startActivityForResult(i,100);
        } else
            showToast("Tài khoản của bạn không thể thực hiện chức năng này. Vui lòng đăng nhập với tư cách người cho thuê!", AppConstant.POSITIVE);

    }

    @Override
    public void onDeleteChat(ChatItem chatItem) {
        presenter.deleteChat(AppController.getInstance().getmProfileUser().getType() == 1 ? chatItem.getShop().getId() : chatItem.getUse().getId(), chatItem.getId_post());
    }

    @Override
    public void onRefresh() {
        presenter.getListChat();
    }
}
