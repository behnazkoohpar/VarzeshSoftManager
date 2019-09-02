package com.eram.manager.ui.login;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.eram.manager.BR;
import com.eram.manager.R;
import com.eram.manager.data.DataManager;
import com.eram.manager.data.model.api.ServerGym;
import com.eram.manager.data.model.api.TokenResponse;
import com.eram.manager.data.model.api.base.Data;
import com.eram.manager.databinding.ActivityLoginBinding;
import com.eram.manager.ui.base.BaseActivity;
import com.eram.manager.ui.main.MainActivity;
import com.eram.manager.utils.AppBaseUrl;
import com.eram.manager.utils.AppConstants;
import com.eram.manager.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by behnaz on 11/3/17.
 */

@TargetApi(Build.VERSION_CODES.M)
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements AppConstants, LoginNavigator {

    @Inject
    LoginViewModel mLoginViewModel;

    ActivityLoginBinding mActivityLoginBinding;
    Typeface typeface;
    private String[][] serverArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityLoginBinding = getViewDataBinding();
            mLoginViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            typeface = Typeface.createFromAsset(LoginActivity.this.getAssets(), "fonts/iran_sans.ttf");
            mActivityLoginBinding.ostanLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivityLoginBinding.expandableLayoutState.toggle();
                }
            });

            mActivityLoginBinding.listState.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapter, View v, int position,
                                        long arg3) {
                    mActivityLoginBinding.expandableLayoutState.toggle();
                    mActivityLoginBinding.state.setText(serverArray[position][0]);
                    mLoginViewModel.getDataManager().setAddress(serverArray[position][2]);
                    mLoginViewModel.getDataManager().setEmail(serverArray[position][5]);
                    mLoginViewModel.getDataManager().setSupporterName(serverArray[position][11]);
                    mLoginViewModel.getDataManager().setSupporterPhone(serverArray[position][12]);
                    mLoginViewModel.getDataManager().setServerAddress(serverArray[position][10]);
                    mLoginViewModel.getDataManager().setPhoneNumber(serverArray[position][9]);
                    mLoginViewModel.getDataManager().setName(serverArray[position][0]);
                    mLoginViewModel.getDataManager().setID(serverArray[position][1]);

                    AppBaseUrl.BASE_URL = mLoginViewModel.getDataManager().getServerAddress();
                }
            });
            callServerLogin();
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

    private void callServerLogin() {
        try {
            mLoginViewModel.serverLogin();
            mLoginViewModel.getServerLoginResponseModelMutableLiveData().observe(this, this::receivedServerLoginData);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(LoginActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedServerLoginData(Data<ServerGym> listBaseResponse) {
        // display response
        List<ServerGym> serverGyms = new ArrayList<>();
        Log.d("Response", listBaseResponse.toString());
        if (listBaseResponse.getSettings().getSuccess().equalsIgnoreCase("1")) {
            for (int i = 0; i < listBaseResponse.getData().size(); i++) {
                ServerGym serverGym = new ServerGym();
                ServerGym jsonObject1 = listBaseResponse.getData().get(i);
                serverGym.setAddress(jsonObject1.getAddress());
                serverGym.setCreateDateTime(jsonObject1.getCreateDateTime());
                serverGym.setCreateShamsiDate(jsonObject1.getCreateShamsiDate());
                serverGym.setEmail(jsonObject1.getEmail());
                serverGym.setExpireShamsiDate(jsonObject1.getExpireShamsiDate());
                serverGym.setExpireDate(jsonObject1.getExpireDate());
                serverGym.setGenderType(jsonObject1.getGenderType());
                serverGym.setID(jsonObject1.getID());
                serverGym.setName(jsonObject1.getName());
                serverGym.setPhoneNumber(jsonObject1.getPhoneNumber());
                serverGym.setServerAddress(jsonObject1.getServerAddress());
                serverGym.setSupporterName(jsonObject1.getSupporterName());
                serverGym.setSupporterPhone(jsonObject1.getPhoneNumber());
                serverGyms.add(serverGym);
            }

            serverArray = new String[serverGyms.size()][13];

            final ArrayAdapter<String> arrayAdapterState = new ArrayAdapter<String>(
                    LoginActivity.this,
                    R.layout.select_list_radio);
            for (int i = 0; i < serverGyms.size(); i++) {
                serverArray[i][0] = serverGyms.get(i).getName();
                serverArray[i][1] = serverGyms.get(i).getID();
                serverArray[i][2] = serverGyms.get(i).getAddress();
                serverArray[i][3] = serverGyms.get(i).getCreateDateTime();
                serverArray[i][4] = serverGyms.get(i).getCreateShamsiDate();
                serverArray[i][5] = serverGyms.get(i).getEmail();
                serverArray[i][6] = serverGyms.get(i).getExpireDate();
                serverArray[i][7] = serverGyms.get(i).getExpireShamsiDate();
                serverArray[i][8] = serverGyms.get(i).getGenderType();
                serverArray[i][9] = serverGyms.get(i).getPhoneNumber();
                serverArray[i][10] = serverGyms.get(i).getServerAddress();
                serverArray[i][11] = serverGyms.get(i).getSupporterName();
                serverArray[i][12] = serverGyms.get(i).getSupporterPhone();
                arrayAdapterState.add(serverArray[i][0]);
            }
            mActivityLoginBinding.listState.setAdapter(arrayAdapterState);
            mActivityLoginBinding.expandableLayoutState.toggle();
        }
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

    private void callLogin() {
        try {
            mLoginViewModel.login(mActivityLoginBinding.password.getText().toString(), mActivityLoginBinding.userName.getText().toString());
            mLoginViewModel.getTokenResponseModelMutableLiveData().observe(this, this::receivedData);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(LoginActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedData(TokenResponse data) {
        if (data != null) {
            if (data.isStatus()) {
                setLoginParameter(data);
            } else {
                if (data.getErrmessage() != null)
                    CommonUtils.showSingleButtonAlert(LoginActivity.this, getString(R.string.text_attention), data.getErrmessage(), null, null);
            }
        }
    }

    public void setLoginParameter(TokenResponse tokenResponse) {
        mLoginViewModel.getDataManager().setCurrentUserLoggedInMode(DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER);
        mLoginViewModel.getDataManager().updateUserInfo(
                DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                tokenResponse.getSystemUserID(),
                mLoginViewModel.getDataManager().getUsername(),
                mLoginViewModel.getDataManager().getPassword(),
                tokenResponse.getName(),
                "");
        openMainActivity();
    }

    private boolean validateInfo() {
        if (mActivityLoginBinding.userName.getText().toString().trim().isEmpty()) {
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
        if (mActivityLoginBinding.password.getText().toString().length() < 1) {
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
