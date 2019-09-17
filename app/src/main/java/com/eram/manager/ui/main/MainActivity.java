package com.eram.manager.ui.main;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.WindowManager;

import com.eram.manager.BR;
import com.eram.manager.R;
import com.eram.manager.data.DataManager;
import com.eram.manager.data.model.api.AllReport;
import com.eram.manager.databinding.ActivityMainBinding;
import com.eram.manager.ui.base.BaseActivity;
import com.eram.manager.ui.docContractors.DocContractorsActivity;
import com.eram.manager.ui.docMember.DocMemberActivity;
import com.eram.manager.ui.functionalReport.FunctionalReportActivity;
import com.eram.manager.ui.kartableManager.KartableManagerActivity;
import com.eram.manager.ui.login.LoginActivity;
import com.eram.manager.ui.reportRecived.ReportRecivedActivity;
import com.eram.manager.ui.stateReception.StateReceptionActivity;
import com.eram.manager.utils.AppConstants;
import com.eram.manager.utils.CommonUtils;
import com.google.android.material.navigation.NavigationView;
import com.mojtaba.materialdatetimepicker.utils.PersianCalendar;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements AppConstants, MainNavigator
        , NavigationView.OnNavigationItemSelectedListener {

    @Inject
    MainViewModel mMainViewModel;

    ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityMainBinding = getViewDataBinding();
            mMainViewModel.setNavigator(MainActivity.this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            mActivityMainBinding.nv.setNavigationItemSelectedListener(this);
            PersianCalendar persianCalendar = new PersianCalendar();
            String date ="امروز " +persianCalendar.getPersianWeekDayName()+" "+persianCalendar.getPersianDay() + " " + persianCalendar.getPersianMonthName() + " "+"ماه "+ persianCalendar.getPersianYear();
            mActivityMainBinding.date.setText(date);
            mActivityMainBinding.nameWelcome.setText(mMainViewModel.getDataManager().getFirstName()+" خوش آمدید");
            callAllReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public MainViewModel getViewModel() {
        return mMainViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    public void openMenu() {
        mActivityMainBinding.dl.openDrawer(Gravity.RIGHT);
    }

    @Override
    public void statePazireshClick() {
        Intent intent =  StateReceptionActivity.getStartIntent(MainActivity.this);
        startActivity(intent);
    }

    @Override
    public void reciveClick() {
        Intent intent = ReportRecivedActivity.getStartIntent(MainActivity.this);
        startActivity(intent);
    }

    @Override
    public void functionalityClick() {
        Intent intent =  FunctionalReportActivity.getStartIntent(MainActivity.this);
        startActivity(intent);
    }

    @Override
    public void incomeClick() {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        try {
            int id = item.getItemId();
            if (id == R.id.nav_conrtractors) {
                startActivity(new Intent(DocContractorsActivity.getStartIntent(MainActivity.this)));
            } else if (id == R.id.nav_kartable) {
                startActivity(new Intent(KartableManagerActivity.getStartIntent(MainActivity.this)));
            } else if (id == R.id.nav_member) {
                startActivity(new Intent(DocMemberActivity.getStartIntent(MainActivity.this)));
            } else if (id == R.id.nav_exit) {
                CommonUtils.showTwoButtonAlert(MainActivity.this, getString(R.string.exit_ok), getString(R.string.txt_menu_exit), getString(R.string.btn_cancel), new CommonUtils.IL() {
                    @Override
                    public void onSuccess() {
                        setLogOut();
                    }

                    @Override
                    public void onCancel() {
                    }
                });
            }

            mActivityMainBinding.dl.closeDrawer(Gravity.RIGHT);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void setLogOut() {
        try {
            mMainViewModel.getDataManager().setCurrentUserLoggedInMode(DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT);
            mMainViewModel.getDataManager().updateUserInfo(
                    DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
                    "",
                    "",
                    "",
                    "",
                    "");

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callAllReport() {
        try {
            mMainViewModel.getAllReport();
            mMainViewModel.getAllReportResponseModelMutableLiveData().observe(this, this::receivedData);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(MainActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedData(AllReport allReport) {
        if(allReport.isStatus()){
            mActivityMainBinding.absent.setText(allReport.getResult().getPresentMemberCount());
            mActivityMainBinding.exit.setText(allReport.getResult().getExitedMemberCount());
            mActivityMainBinding.functionToday.setText(allReport.getResult().getSumTotalAmount_Creditor());
            mActivityMainBinding.gotToday.setText(allReport.getResult().getSumTotalAmount_Receipt());
        }else{
            CommonUtils.showSingleButtonAlert(MainActivity.this, getString(R.string.text_attention), allReport.getErrmessage(), null, null);
        }
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
