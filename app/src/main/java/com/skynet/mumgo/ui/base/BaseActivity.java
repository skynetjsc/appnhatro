package com.skynet.mumgo.ui.base;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.androidadvance.topsnackbar.TSnackbar;
import com.blankj.utilcode.util.NetworkUtils;
import com.skynet.mumgo.R;
import com.skynet.mumgo.application.AppController;
import com.skynet.mumgo.interfaces.SnackBarCallBack;
import com.skynet.mumgo.models.Profile;
import com.skynet.mumgo.network.socket.SocketClient;
import com.skynet.mumgo.ui.main.MainActivity;
import com.skynet.mumgo.ui.splash.SplashActivity;
import com.skynet.mumgo.ui.views.DialogOneButtonUtil;
import com.skynet.mumgo.utils.AppConstant;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


/**
 * Created by thaopt on 12/1/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected static final String TAG = BaseActivity.class.getName();
    public static boolean isAppWentToBg = false;

    public static boolean isWindowFocused = false;

    public static boolean isMenuOpened = false;

    public static boolean isBackPressed = false;

    protected abstract int initLayout();

    protected abstract void initVariables();

    protected abstract void initViews();

    protected abstract int initViewSBAnchor();

    protected SocketClient mSocket;
    private boolean mBounded;
    protected SnackBarCallBack snackBarCallBack;

    public SocketClient getmSocket() {
        return mSocket;
    }

//    public void setmSocket(SocketClient mSocket) {
//        this.mSocket = mSocket;
//    }

    private MaterialDialog dialogError;
    AlertDialog dialogNetwork;

    public String getTag() {
        return null;
    }

    public void tranToTab(Fragment fragmentSearch, int idResource) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(idResource, fragmentSearch, fragmentSearch.getClass().getSimpleName())
                .addToBackStack(null)
                .commit();
    }

    BroadcastReceiver receiverConnectionNetwork = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (!NetworkUtils.isConnected()) {
                if (!AppController.getInstance().isToogleInternet()) {
                    startActivity(new Intent(BaseActivity.this, ActivityNetwork.class));
                }
            } else {
                AppController.getInstance().setToogleInternet(false);
                onNetworkTurnOn();
                if (getmSocket() != null) getmSocket().initSocket();
            }
        }
    };
    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BaseActivity.this.mBounded = true;
            SocketClient.LocalBinder mLocalBinder = (SocketClient.LocalBinder) service;
            BaseActivity.this.mSocket = mLocalBinder.getServerInstance();
            onSocketConnected();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            BaseActivity.this.mBounded = false;
            BaseActivity.this.mSocket = null;
        }
    };

    DialogOneButtonUtil dialogOneButtonUtil;

    public void onNetworkTurnOn() {

    }

    public void onSocketConnected() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(initLayout());
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
//        } else {
//
//            StatusBarUtil.setLightMode(this);
//
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//            getWindow()
//                    .getDecorView()
//                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//           getWindow()
//                    .setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
        ButterKnife.bind(this);

        initViews();
        initVariables();
        if (findViewById(R.id.scrollview) != null) {
            findViewById(R.id.scrollview).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event != null && event.getAction() == MotionEvent.ACTION_MOVE) {
                        InputMethodManager imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
                        boolean isKeyboardUp = imm.isAcceptingText();
                        if (isKeyboardUp) {
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    }
                    return false;
                }
            });
        }
//        dialogError = new MaterialDialog.Builder(this).title(R.string.error)
//                .content(getString(R.string.unknow_error))
//                .positiveText(R.string.dismis)
//                .positiveColor(Color.GRAY)
//                .build();
//        IntentFilter intentFilter = new IntentFilter(GPSService.ACTION_LOCATION_UPDATE);
//        registerReceiver(receiverGPS, intentFilter);

        Intent mIntent = new Intent(this, SocketClient.class);
        startService(mIntent);
        bindService(mIntent, this.mConnection, BIND_AUTO_CREATE);
    }

    public Profile getProfile() {
        Profile profile = AppController.getInstance().getmProfileUser();
        if (profile == null) {
            showDialogExpired();
        }
        return profile;
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(this.receiverConnectionNetwork);
        ((AppController) this.getApplication()).startActivityTransitionTimer();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

//        if (this instanceof MainActivity) {
//
//        } else {
//            isBackPressed = true;
//        }
//
//        Log.d(TAG,
//                "onBackPressed " + isBackPressed + ""
//                        + this.getLocalClassName());
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        isWindowFocused = hasFocus;

        if (isBackPressed && !hasFocus) {
            isBackPressed = false;
            isWindowFocused = true;
        }

        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onUserLeaveHint() {
        Log.d("onUserLeaveHint", "Home button pressed");
        super.onUserLeaveHint();

    }

    private void applicationWillEnterForeground() {
        if (isAppWentToBg) {
            isAppWentToBg = false;
//            Toast.makeText(getApplicationContext(), "App is in foreground",
//                    Toast.LENGTH_SHORT).show();
//            if(getmSocket()!=null && (!(this instanceof LearningActivity))){
//                getmSocket().onStartAudio();
//            }
        }
    }

    public void applicationdidenterbackground() {
        if (!isWindowFocused) {
            isAppWentToBg = true;
//            Toast.makeText(getApplicationContext(),
//                    "App is Going to Background", Toast.LENGTH_SHORT).show();

//            if(getmSocket()!=null){
//                getmSocket().onStopAudio();
//            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop ");
        applicationdidenterbackground();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart isAppWentToBg " + isAppWentToBg);

        applicationWillEnterForeground();
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter i = new IntentFilter();
        i.addAction("ts_connection");
        IntentFilter iConnection = new IntentFilter();
        iConnection.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(this.receiverConnectionNetwork, iConnection);
        AppController myApp = (AppController) this.getApplication();

        if (myApp.wasInBackground) {
            //Do specific c rom-background code

        } else {

        }

        myApp.stopActivityTransitionTimer();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialogError != null && dialogError.isShowing()) dialogError.dismiss();
        if (this.mBounded) {
            unbindService(this.mConnection);
            this.mBounded = false;
        }
    }

    public void showDialogExpired() {
        if (dialogOneButtonUtil == null) {
            dialogOneButtonUtil = new DialogOneButtonUtil(this);
            dialogOneButtonUtil.setText(getString(R.string.expired_token));
            dialogOneButtonUtil.setType(2);
            dialogOneButtonUtil.setCancelable(false);

            dialogOneButtonUtil.setDialogOneButtonClick(new DialogOneButtonUtil.DialogOneButtonClickListener() {
                @Override
                public void okClick() {
                    Intent intent = new Intent(BaseActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    AppController.getInstance().setmProfileUser(null);
                    AppController.getInstance().getmSetting().remove(AppConstant.KEY_PROFILE);
                    AppController.getInstance().getmSetting().remove(AppConstant.KEY_TOKEN);
                    AppController.getInstance().getmSetting().remove(AppConstant.KEY_ID);
                    AppController.getInstance().getmSetting().remove("show");
                    AppController.getInstance().setmProfileUser(null);
                    startActivity(intent);
                    finishAffinity();
                }
            });
        }
        if (!dialogOneButtonUtil.isShowing())
            dialogOneButtonUtil.show();
    }

    public void showDialogError(String error, String titleButton, int colorButton, int resource) {
        if (dialogOneButtonUtil != null) {
            if (dialogOneButtonUtil.isShowing()) dialogOneButtonUtil.dismiss();
            dialogOneButtonUtil = null;
        }
        dialogOneButtonUtil = new DialogOneButtonUtil(this, resource, colorButton, error, titleButton, null);

        if (!dialogOneButtonUtil.isShowing())
            dialogOneButtonUtil.show();
    }

//    public void showDialogError(String error) {
//        if (dialogOneButtonUtil != null) {
//            if (dialogOneButtonUtil.isShowing()) dialogOneButtonUtil.dismiss();
//            dialogOneButtonUtil = null;
//        }
//        dialogOneButtonUtil = new DialogOneButtonUtil(this, R.drawable.ic_error, R.drawable.bg_button_red_dialog, error,getString(R.string.cancel) , null);
//
//        if (!dialogOneButtonUtil.isShowing())
//            dialogOneButtonUtil.show();
//    }

    public void showDialogError(String error, String titleButton, int colorButton, int resource, DialogOneButtonUtil.DialogOneButtonClickListener listener) {
        if (dialogOneButtonUtil != null) {
            if (dialogOneButtonUtil.isShowing()) dialogOneButtonUtil.dismiss();
            dialogOneButtonUtil = null;
        }
        dialogOneButtonUtil = new DialogOneButtonUtil(this, resource, colorButton, error, titleButton, listener);
        dialogOneButtonUtil.setTextHtml(error);
        if (!dialogOneButtonUtil.isShowing())
            dialogOneButtonUtil.show();
    }

    public void logOut() {
        Intent intent = new Intent(BaseActivity.this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        AppController.getInstance().setmProfileUser(null);
        AppController.getInstance().getmSetting().remove(AppConstant.KEY_PROFILE);
        AppController.getInstance().getmSetting().remove(AppConstant.KEY_TOKEN);
        AppController.getInstance().getmSetting().remove(AppConstant.KEY_ID);
        AppController.getInstance().getmSetting().remove("show");
        AppController.getInstance().setmProfileUser(null);
        startActivity(intent);
        finishAffinity();
    }

    public void clearTaskAndGo(Context packageContext, Class<?> cls) {
        Intent intent = new Intent(BaseActivity.this, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finishAffinity();
    }

    public void showToast(String message, int type) {
        TSnackbar snackbar = TSnackbar.make(findViewById(initViewSBAnchor() == 0 ? android.R.id.content : initViewSBAnchor()), message, TSnackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.WHITE);

        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.parseColor(type == AppConstant.POSITIVE ? "#2db25d" : "#fe3824"));
        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
        snackbar.setCallback(new TSnackbar.Callback() {
            @Override
            public void onDismissed(TSnackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                if (snackBarCallBack != null) snackBarCallBack.onClosedSnackBar();
            }
        });
    }

    public void showToast(String message, int type, final SnackBarCallBack snackBarCallBack) {
        TSnackbar snackbar = TSnackbar.make(findViewById(initViewSBAnchor() == 0 ? android.R.id.content : initViewSBAnchor()), message, TSnackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.WHITE);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.parseColor(type == AppConstant.POSITIVE ? "#2db25d" : "#fe3824"));
        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
        snackbar.setCallback(new TSnackbar.Callback() {
            @Override
            public void onDismissed(TSnackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                if (snackBarCallBack != null) snackBarCallBack.onClosedSnackBar();
            }
        });
    }

    public void setSnackBarCallBack(SnackBarCallBack snackBarCallBack) {
        this.snackBarCallBack = snackBarCallBack;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}
