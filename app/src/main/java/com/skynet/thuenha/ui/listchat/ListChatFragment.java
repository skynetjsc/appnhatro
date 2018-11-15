package com.skynet.thuenha.ui.listchat;

import android.os.Bundle;
import android.view.View;

import com.skynet.thuenha.ui.base.BaseFragment;
import com.skynet.thuenha.R;

public class ListChatFragment extends BaseFragment {
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

    }

    @Override
    protected void initVariables() {

    }
}
