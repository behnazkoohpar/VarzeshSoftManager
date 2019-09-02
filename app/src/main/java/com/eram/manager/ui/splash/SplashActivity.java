package com.eram.manager.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;

import com.eram.manager.BR;
import com.eram.manager.R;
import com.eram.manager.data.DataManager;
import com.eram.manager.databinding.ActivitySplashBinding;
import com.eram.manager.ui.base.BaseActivity;
import com.eram.manager.ui.login.LoginActivity;
import com.eram.manager.ui.main.MainActivity;
import com.eram.manager.utils.AppBaseUrl;
import com.eram.manager.utils.AppConstants;
import com.eram.manager.utils.CommonUtils;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements AppConstants {

    private static final long INTERVAL_LOGIN = 5000;
    private Handler mHandler;
    final Handler handler = new Handler();
    ActivitySplashBinding mActivitySplashBinding;

    @Inject
    SplashViewModel mSplashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivitySplashBinding = getViewDataBinding();
            mHandler = new Handler();
            Intent intent = getIntent();

//       if (!hasPermission(Manifest.permission.ACCESS_FINE_LOCATION) ||
//                !hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
//            requestPermissionsSafely(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
//                            Manifest.permission.ACCESS_COARSE_LOCATION},
//                    PERMISSION_CODE_LOCATION);
//        }
            startCheck();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startCheck() {
        callMethods.run();
    }

    private void stopCheck() {
        mHandler.removeCallbacks(callMethods);
    }

    Runnable callMethods = new Runnable() {
        @Override
        public void run() {
            try {
                if (checkConnectivity(SplashActivity.this)) {
//                    if (mSplashViewModel.getDataManager().getCurrentUserLoggedInMode() == 1)
//                        sendRegistrationToServer();
//                    else
                    stopCheck();
                    decideNextActivity();
                } else {
                    CommonUtils.showSingleButtonAlert(SplashActivity.this, getString(R.string.text_attention), getString(R.string.lost_internet), null, null);
                }
            } finally {
                mHandler.postDelayed(callMethods, INTERVAL_LOGIN);
            }
        }
    };

    private void sendRegistrationToServer() {

    }


    public boolean checkConnectivity(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public SplashViewModel getViewModel() {
        return mSplashViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void decideNextActivity() {
        try {
            stopCheck();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mSplashViewModel.getDataManager().getCurrentUserLoggedInMode()
                            == DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()) {
                        stopCheck();
                        openLoginActivity();
                    } else {
                        stopCheck();
                        if (!mSplashViewModel.getDataManager().getServerAddress().isEmpty()) {
                            AppBaseUrl.BASE_URL = mSplashViewModel.getDataManager().getServerAddress();
                            openMainActivity();
                        } else
                            openLoginActivity();
                    }
                }
            }, 5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openLoginActivity() {
        Intent intent = LoginActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
    }


    public void openMainActivity() {
        startActivity(new Intent(MainActivity.getStartIntent(SplashActivity.this)));
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        try {
////            if (requestCode == PERMISSION_CODE_LOCATION) {
//            for (int i = 0; i < permissions.length; i++) {
//                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
////                    CommonUtils.showSingleButtonAlert(this, StringLanguage.no_permission_title, StringLanguage.get_permission_failed + "\n\n" + permissions[i], StringLanguage.btn_alert, new CommonUtils.IL() {
////                        @Override
////                        public void onSuccess() {
////                            AppLogger.d("PERMISSION DENIED");
////                            finish();
////                        }
////
////                        @Override
////                        public void onCancel() {
////                        }
////                    });
//
//                } else {
//                    invokeVersion();
////                    decideNextActivity();
//                }
//            }
////            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
