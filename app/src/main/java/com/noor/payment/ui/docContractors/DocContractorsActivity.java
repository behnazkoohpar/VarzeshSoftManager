package com.noor.payment.ui.docContractors;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.noor.payment.BR;
import com.noor.payment.R;
import com.noor.payment.databinding.ActivityDocContractorsBinding;
import com.noor.payment.ui.base.BaseActivity;
import com.noor.payment.utils.AppConstants;

import javax.inject.Inject;

public class DocContractorsActivity extends BaseActivity<ActivityDocContractorsBinding, DocContractorsViewModel> implements AppConstants {

    @Inject
    DocContractorsViewModel mDocContractorsViewModel;

    ActivityDocContractorsBinding mActivityDocContractorsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityDocContractorsBinding = getViewDataBinding();
            mDocContractorsViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, DocContractorsActivity.class);
    }

    @Override
    public DocContractorsViewModel getViewModel() {
        return mDocContractorsViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_doc_contractors;
    }

}
