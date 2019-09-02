package com.eram.manager.ui.kartableManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.eram.manager.BR;
import com.eram.manager.R;
import com.eram.manager.databinding.ActivityKartableManagerBinding;
import com.eram.manager.ui.base.BaseActivity;
import com.eram.manager.utils.AppConstants;

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
