package com.eram.manager.ui.docMember;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.eram.manager.BR;
import com.eram.manager.R;
import com.eram.manager.databinding.ActivityDocMemberBinding;
import com.eram.manager.ui.base.BaseActivity;
import com.eram.manager.utils.AppConstants;

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
