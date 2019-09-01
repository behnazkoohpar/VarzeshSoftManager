package com.noor.payment.ui.docMember;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.noor.payment.BR;
import com.noor.payment.R;
import com.noor.payment.databinding.ActivityDocMemberBinding;
import com.noor.payment.ui.base.BaseActivity;
import com.noor.payment.utils.AppConstants;

import javax.inject.Inject;

public class DocMemberActivity extends BaseActivity<ActivityDocMemberBinding, DocMemberViewModel> implements AppConstants {

    @Inject
    DocMemberViewModel mDocMemberViewModel;

    ActivityDocMemberBinding mActivityDocMemberBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityDocMemberBinding = getViewDataBinding();
            mDocMemberViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, DocMemberActivity.class);
    }

    @Override
    public DocMemberViewModel getViewModel() {
        return mDocMemberViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_doc_member;
    }

}
