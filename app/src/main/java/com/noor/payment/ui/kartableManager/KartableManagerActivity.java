package com.noor.payment.ui.kartableManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.noor.payment.BR;
import com.noor.payment.R;
import com.noor.payment.databinding.ActivityKartableManagerBinding;
import com.noor.payment.ui.base.BaseActivity;
import com.noor.payment.utils.AppConstants;

import javax.inject.Inject;

public class KartableManagerActivity extends BaseActivity<ActivityKartableManagerBinding, KartableManagerViewModel> implements AppConstants {

    @Inject
    KartableManagerViewModel mKartableManagerViewModel;
    ActivityKartableManagerBinding mActivityKartableManagerBinding;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityKartableManagerBinding = getViewDataBinding();
            mKartableManagerViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, KartableManagerActivity.class);
    }

    @Override
    public KartableManagerViewModel getViewModel() {
        return mKartableManagerViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_kartable_manager;
    }

}
