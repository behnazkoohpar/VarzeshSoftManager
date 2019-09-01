package com.noor.payment.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;

import com.noor.payment.R;
import com.noor.payment.BR;
import com.noor.payment.data.DataManager;
import com.noor.payment.databinding.ActivitySplashBinding;
import com.noor.payment.ui.base.BaseActivity;
import com.noor.payment.ui.login.LoginActivity;
import com.noor.payment.ui.main.MainActivity;
import com.noor.payment.utils.AppConstants;
import com.noor.payment.utils.CommonUtils;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements  AppConstants {

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
//            stopCheck();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mSplashViewModel.getDataManager().getCurrentUserLoggedInMode()
                            == DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()) {
                        stopCheck();
                        Intent intent = LoginActivity.getStartIntent(SplashActivity.this);
                        startActivity(intent);

                    } else {
                        stopCheck();
                        openMainActivity();
                    }
                }
            }, 5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
