package com.noor.payment.ui.login;

import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.TextView;

import com.noor.payment.BR;
import com.noor.payment.R;
import com.noor.payment.data.DataManager;
import com.noor.payment.data.model.api.BaseResponse;
import com.noor.payment.data.model.api.TokenResponse;
import com.noor.payment.databinding.ActivityLoginBinding;
import com.noor.payment.ui.base.BaseActivity;
import com.noor.payment.ui.main.MainActivity;
import com.noor.payment.utils.AppConstants;
import com.noor.payment.utils.CommonUtils;
import com.noor.payment.utils.TextEncrypter;

import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.inject.Inject;

/**
 * Created by behnaz on 11/3/17.
 */

@TargetApi(Build.VERSION_CODES.M)
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements  AppConstants, LoginNavigator {

    @Inject
    LoginViewModel mLoginViewModel;

    ActivityLoginBinding mActivityLoginBinding;
    private String userName;
    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityLoginBinding = getViewDataBinding();
            mLoginViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            typeface = Typeface.createFromAsset(LoginActivity.this.getAssets(), "fonts/iran_sans.ttf");
            mActivityLoginBinding.userName.addTextChangedListener(new TextWatcher() {
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (count == 1)
                        if (start == 10) {
                            mActivityLoginBinding.password.requestFocus();
                        }
                }

                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }

                public void afterTextChanged(Editable s) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    public LoginViewModel getViewModel() {
        return mLoginViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }


    public void login() {
        try {
            if (validateInfo())
                callLogin();
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(LoginActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void openMainActivity() {
        startActivity(new Intent(MainActivity.getStartIntent(LoginActivity.this)));
    }

    public void setLoginParameter(TokenResponse tokenResponse) {
        mLoginViewModel.getDataManager().setCurrentUserLoggedInMode(DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER);
        mLoginViewModel.getDataManager().updateUserInfo(
                DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                tokenResponse.getUserId(),
                mLoginViewModel.getDataManager().getUsername(),
                mLoginViewModel.getDataManager().getPassword(),
                "",
                "");
        openMainActivity();
    }

    private void callLogin() {
        try {
            mLoginViewModel.getDataManager().setUsername("98".concat(String.valueOf(mActivityLoginBinding.userName.getText())
                    .substring(mActivityLoginBinding.userName.getText().length() - 10, mActivityLoginBinding.userName.getText().length())));
            mLoginViewModel.getDataManager().setNationalCode(String.valueOf(mActivityLoginBinding.userName.getText())
                    .substring(mActivityLoginBinding.userName.getText().length() - 10, mActivityLoginBinding.userName.getText().length()));
            mLoginViewModel.getDataManager().setPassword(mActivityLoginBinding.password.getText().toString());
            String passwordE = TextEncrypter.MD5String(mLoginViewModel.getDataManager().getUsername() + mActivityLoginBinding.password.getText().toString());

            mLoginViewModel.login(mActivityLoginBinding.password.getText().toString(), mLoginViewModel.getDataManager().getUsername(), REQUEST_NOOR_PAYMENT);
            mLoginViewModel.getTokenResponseModelMutableLiveData().observe(this, this::receivedData);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(LoginActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedData(BaseResponse<TokenResponse> data) {
        if (data != null) {
            if (Integer.parseInt(data.getErrorDetails().getErrorCode()) == 0) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                this.finish();
            } else {
                if (data.getErrorDetails().getErrorMessage() != null)
                    CommonUtils.showSingleButtonAlert(LoginActivity.this, getString(R.string.text_attention), data.getErrorDetails().getErrorMessage(), null, null);
            }
        }
    }

    private boolean validateInfo() {
        if (mActivityLoginBinding.userName.getText().toString().trim().isEmpty() ||
                mActivityLoginBinding.userName.getText().toString().length() < 11) {
            mActivityLoginBinding.textLayoutUserName.setError(wrapInCustomfont(getString(R.string.validation_phonenumber)));
            mActivityLoginBinding.userName.requestFocus();
            return false;
        } else {
            mActivityLoginBinding.textLayoutUserName.setErrorEnabled(false);
        }
        if (mActivityLoginBinding.password.getText().toString().trim().isEmpty()) {
            mActivityLoginBinding.textLayoutPassword.setError(wrapInCustomfont(getString(R.string.validation_password)));
            mActivityLoginBinding.password.requestFocus();
            return false;
        } else {
            mActivityLoginBinding.textLayoutPassword.setErrorEnabled(false);
        }
        if (mActivityLoginBinding.password.getText().toString().length() < 6) {
            mActivityLoginBinding.textLayoutPassword.setError(wrapInCustomfont(getString(R.string.validation_password_length)));
            mActivityLoginBinding.password.requestFocus();
            return false;
        } else {
            mActivityLoginBinding.textLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

}
