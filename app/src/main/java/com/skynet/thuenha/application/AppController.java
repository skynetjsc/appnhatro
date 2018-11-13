package com.skynet.thuenha.application;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.google.gson.Gson;
import com.skynet.thuenha.R;
import com.skynet.thuenha.models.Banner;
import com.skynet.thuenha.models.Profile;
import com.skynet.thuenha.utils.AppConstant;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by DuongKK on 11/30/2017.
 */

public class AppController extends MultiDexApplication {
    private static AppController instance;
    private Profile mProfileUser;
    private List<Banner> listBanner;
    private SPUtils mSetting;
    private int typeSort = -1;
    private boolean flagInTrip = true;
    private boolean toogleInternet;
    private boolean isReview;
    private boolean isStartOverQuiz;
    public static Context context;

    // ---

    private Timer mActivityTransitionTimer;
    private TimerTask mActivityTransitionTimerTask;
    public boolean wasInBackground;
    private final long MAX_ACTIVITY_TRANSITION_TIME_MS = 2000;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public List<Banner> getListBanner() {
        return listBanner;
    }

    public void setListBanner(List<Banner> listBanner) {
        this.listBanner = listBanner;
    }

    public synchronized static AppController getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        Fabric.with(this, new Crashlytics());
//        final Fabric fabric = new Fabric.Builder(this)
//                .kits(new Crashlytics())
//                .debuggable(true)
//                .build();
//        Fabric.with(fabric);
        instance = this;
        Utils.init(this);
        mSetting = new SPUtils(AppConstant.KEY_SETTING);
        setmProfileUser(new Gson().fromJson(mSetting.getString(AppConstant.KEY_PROFILE), Profile.class));
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/OpenSans-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        context = getApplicationContext();
//        Permission[] permissions = new Permission[] {
//                Permission.EMAIL,
//                Permission.PUBLISH_ACTION
//        };
//        SimpleFacebookConfiguration configuration = new SimpleFacebookConfiguration.Builder()
//                .setAppId(getString(R.string.facebook_app_id))
//                .setNamespace("nailsmap")
//                .setPermissions(permissions)
//                .build();
//        SimpleFacebook.setConfiguration(configuration);
    }

    public boolean isFlagInTrip() {
        return flagInTrip;
    }

    public void setFlagInTrip(boolean flagInTrip) {
        this.flagInTrip = flagInTrip;
    }

    public SPUtils getmSetting() {
        return mSetting;
    }

    public static void setInstance(AppController instance) {
        AppController.instance = instance;
    }

    public boolean isToogleInternet() {
        return toogleInternet;
    }

    public boolean isReview() {
        return isReview;
    }

    public void setReview(boolean review) {
        isReview = review;
    }

    public boolean isStartOverQuiz() {
        return isStartOverQuiz;
    }

    public void setStartOverQuiz(boolean startOverQuiz) {
        isStartOverQuiz = startOverQuiz;
    }

    public void setToogleInternet(boolean toogleInternet) {
        this.toogleInternet = toogleInternet;
    }


    public void setmSetting(SPUtils mSetting) {
        this.mSetting = mSetting;
    }

    public int getTypeSort() {
        return typeSort;
    }

    public void setTypeSort(int typeSort) {
        this.typeSort = typeSort;
    }



    public Profile getmProfileUser() {
        return mProfileUser;
    }


    public void setmProfileUser(Profile mProfileUser) {
        this.mProfileUser = mProfileUser;
        if (mProfileUser != null) {
            mSetting.put(AppConstant.KEY_PROFILE, new Gson().toJson(mProfileUser));
            mSetting.put(AppConstant.KEY_TOKEN,mProfileUser.getToken());
        }else {
            mSetting.put(AppConstant.KEY_PROFILE, "");
            mSetting.put(AppConstant.KEY_TOKEN,"");

        }

    }


    public void startActivityTransitionTimer() {
        this.mActivityTransitionTimer = new Timer();
        this.mActivityTransitionTimerTask = new TimerTask() {
            public void run() {
                AppController.this.wasInBackground = true;
            }
        };

        this.mActivityTransitionTimer.schedule(mActivityTransitionTimerTask,
                MAX_ACTIVITY_TRANSITION_TIME_MS);
    }

    public void stopActivityTransitionTimer() {
        if (this.mActivityTransitionTimerTask != null) {
            this.mActivityTransitionTimerTask.cancel();
        }

        if (this.mActivityTransitionTimer != null) {
            this.mActivityTransitionTimer.cancel();
        }

        this.wasInBackground = false;
    }
}
