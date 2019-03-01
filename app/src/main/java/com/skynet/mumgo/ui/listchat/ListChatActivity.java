package com.skynet.mumgo.ui.listchat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;
import com.skynet.mumgo.R;
import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.models.ChatItem;
import com.skynet.mumgo.ui.base.BaseActivity;
import com.skynet.mumgo.ui.chatting.ChatActivity;
import com.skynet.mumgo.ui.listchat.ListChatAdapter.ChatCallBack;
import com.skynet.mumgo.ui.views.SimpleSectionedRecyclerViewAdapter;
import com.skynet.mumgo.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import q.rorbin.badgeview.Badge;

import static com.blankj.utilcode.util.Utils.getContext;

public class ListChatActivity extends BaseActivity implements ListChatContract.View, ChatCallBack, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;

    private ListChatContract.Presenter presenter;
    private Badge badget;


    @Override
    protected int initLayout() {
        return R.layout.fragment_list_chat;
    }


    @Override
    protected void initVariables() {
        presenter = new ListChatPresenter(this);
        presenter.getListChat();

    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv.setHasFixedSize(true);
        swipe.setOnRefreshListener(this);

    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }

    @OnClick(R.id.imgBtn_back_toolbar)
    public void onClick() {
        onBackPressed();
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


    @Override
    public void onSucessGetListChat(List<ChatItem> list) {
        List<SimpleSectionedRecyclerViewAdapter.Section> sections =
                new ArrayList<SimpleSectionedRecyclerViewAdapter.Section>();

        //Sections
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(0, "Tin nhắn mới"));
//        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(3, "Gần đây"));
        //Add your adapter to the sectionAdapter
        SimpleSectionedRecyclerViewAdapter.Section[] dummy = new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];
        SimpleSectionedRecyclerViewAdapter mSectionedAdapter = new
                SimpleSectionedRecyclerViewAdapter(getContext(), R.layout.section, R.id.section_text, new ListChatAdapter(list, getContext(), this));
        mSectionedAdapter.setSections(sections.toArray(dummy));

        //Apply this adapter to the RecyclerView
        rcv.setAdapter(mSectionedAdapter);

    }

    @Override
    public void onSucessConfirmChat() {

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
        showDialogExpired();
    }

    @Override
    public void onClickItemChat(ChatItem chatItem) {
        Intent i = new Intent(this, ChatActivity.class);
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
        if (requestCode == 1000 && resultCode == RESULT_OK) onRefresh();
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            presenter.confirmHired(chatItem.getId_post(),
                    AppController.getInstance().getmProfileUser().getType() == 1 ? chatItem.getShop().getId()+"" : chatItem.getUse().getId(),
                    data.getExtras().getString(AppConstant.MSG));
        }
    }

    ChatItem chatItem;

    @Override
    public void onClickConfirm(ChatItem chatItem) {
//        if (AppController.getInstance().getmProfileUser().getType() == 2) {
//            this.chatItem = chatItem;
//            Intent i = new Intent(this, ListViewerActivity.class);
//            i.putExtra(AppConstant.MSG, chatItem.getId_post());
//            startActivityForResult(i,100);
//        } else
//            showToast("Tài khoản của bạn không thể thực hiện chức năng này. Vui lòng đăng nhập với tư cách người cho thuê!", AppConstant.POSITIVE);

    }

    @Override
    public void onDeleteChat(ChatItem chatItem) {
        presenter.deleteChat(AppController.getInstance().getmProfileUser().getType() == 1 ? chatItem.getShop().getId()+"" : chatItem.getUse().getId(), chatItem.getId_post());
    }

    @Override
    public void onRefresh() {
        presenter.getListChat();
    }
}
