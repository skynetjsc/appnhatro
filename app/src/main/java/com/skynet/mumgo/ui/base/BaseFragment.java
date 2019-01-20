package com.skynet.mumgo.ui.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skynet.mumgo.interfaces.FragmentCallBackTitle;
import com.skynet.mumgo.interfaces.SnackBarCallBack;
import com.skynet.mumgo.utils.AppConstant;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


/**
 * Created by Administrator on 16/12/2016.
 */

public abstract class BaseFragment extends Fragment {
    private View view;
    private FragmentCallBackTitle callBackTitle;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                onReloadDataInPage();
            }
        }
    };

    public abstract void doAction();
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    protected void onReloadDataInPage() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(initLayout(), container, false);
            initViews(view);
            initVariables();
            LocalBroadcastManager.getInstance(getContext()).registerReceiver(receiver, new IntentFilter(AppConstant.ACTION_BROADCAST_NOTIFY));

        }
        return view;
    }

    protected abstract int initLayout();

    protected abstract void initViews(View view);

    protected abstract void initVariables();


    public void replaceNewOtherFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        //fragmentManager.beginTransaction();
        //.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)

        if (getFragmentByTag(fragment.getClass().getSimpleName()) != null) {
            removeFragment(getFragmentByTag(fragment.getClass().getSimpleName()));
        }
        fragmentManager.beginTransaction().replace(getId(), fragment, fragment.getClass().getSimpleName())
                .addToBackStack(null)
                .commit();
    }

    //    public void showToast(String message, int type) {
//        if (initViewSBAnchor() == -1) return;
//        TSnackbar snackbar = TSnackbar.make(view.findViewById(initViewSBAnchor()), message, TSnackbar.LENGTH_LONG);
//        snackbar.setActionTextColor(Color.WHITE);
//        View snackbarView = snackbar.getView();
//        snackbarView.setBackgroundColor(Color.parseColor(type == POSITIVE ? "#48d360" : "#fe3824"));
//        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
//        textView.setTextColor(Color.WHITE);
//        snackbar.show();
//    }
    public void replaceToOtherFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        //fragmentManager.beginTransaction();
        //.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
        fragmentManager.beginTransaction().replace(getId(), fragment, fragment.getClass().getSimpleName())
                .addToBackStack(null)
                .commit();
    }

    public void replaceOtherFragmentWithTag(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(getId(), fragment, tag)
                .addToBackStack(null)
                .commit();
    }

    public void replaceChildFragment(Fragment fragment, int layoutID) {
        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().replace(layoutID, fragment, fragment.getClass().getName()).addToBackStack(null).commit();
    }

    public Fragment getFragmentByTag(String tag) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        return fragmentManager.findFragmentByTag(tag);
    }

    public Fragment getFragmentById(int idLayout) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        return fragmentManager.findFragmentById(idLayout);
    }

    public void removeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().remove(fragment).commit();
        fragmentManager.popBackStack();
    }


    protected void finishFragment() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(this);
        trans.commit();
        manager.popBackStack();
    }

    public void showToast(String mess, int type) {
        ((BaseActivity) getActivity()).showToast(mess, type);
    }
    public void showToast(String mess, int type, SnackBarCallBack snackBarCallBack) {
        ((BaseActivity) getActivity()).showToast(mess, type,snackBarCallBack);
    }

    protected void showDialogExpiredToken() {
        BaseActivity baseActivity = (BaseActivity) getActivity();
        baseActivity.showDialogExpired();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(receiver);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        callBackTitle = null;
    }

    public FragmentCallBackTitle getCallBackTitle() {
        return callBackTitle;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FragmentCallBackTitle)
        callBackTitle = (FragmentCallBackTitle) context;

    }
}
