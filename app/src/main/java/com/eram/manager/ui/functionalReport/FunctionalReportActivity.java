package com.eram.manager.ui.functionalReport;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eram.manager.BR;
import com.eram.manager.R;
import com.eram.manager.data.model.api.CreditorAmount;
import com.eram.manager.data.model.api.OrganizationUnit;
import com.eram.manager.data.model.api.SumPrice;
import com.eram.manager.databinding.ActivityFunctionalReportBinding;
import com.eram.manager.ui.base.BaseActivity;
import com.eram.manager.ui.stateReception.OrganListAdapter;
import com.eram.manager.utils.AppConstants;
import com.eram.manager.utils.CommonUtils;
import com.eram.manager.utils.NumberFormatter;
import com.eram.manager.utils.fdate.DateUtil;
import com.mojtaba.materialdatetimepicker.utils.LanguageUtils;
import com.mojtaba.materialdatetimepicker.utils.PersianCalendar;

import javax.inject.Inject;

public class FunctionalReportActivity extends BaseActivity<ActivityFunctionalReportBinding, FunctionalReportViewModel> implements AppConstants, FunctionalReportNavigator {

    @Inject
    FunctionalReportViewModel mFunctionalReportViewModel;

    ActivityFunctionalReportBinding mActivityFunctionalReportBinding;
    private RecyclerView recyclerView1;
    private LinearLayoutManager layoutManager1;
    private OrganListAdapter mAdapter;
    private String organSelected = "";
    private String timeDateSelected = "1";
    private OrganizationUnit organizationUnit;
    private String currentDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityFunctionalReportBinding = getViewDataBinding();
            mFunctionalReportViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            PersianCalendar persianCalendar = new PersianCalendar();
            int month = persianCalendar.getPersianMonth();
            int day = persianCalendar.getPersianDay();
            String monthh = String.valueOf(month);
            String dayy = String.valueOf(day);
            month = month + 1;
            if (month < 10)
                monthh = "0" + month;
            if (day < 10)
                dayy = "0" + dayy;
            currentDay = persianCalendar.getPersianYear() + "/" + monthh + "/" + dayy;
            mActivityFunctionalReportBinding.date.setText(currentDay);
            callOrganizationUnit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, FunctionalReportActivity.class);
    }

    @Override
    public FunctionalReportViewModel getViewModel() {
        return mFunctionalReportViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_functional_report;
    }

    @Override
    public void showOrganization() {
        showSortDialog();
    }

    private void callOrganizationUnit() {
        try {
            mFunctionalReportViewModel.getOrganizatonUnit();
            mFunctionalReportViewModel.getOrganizationUnitResponseModelMutableLiveData().observe(this, this::receivedOrganizationUnit);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(FunctionalReportActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedOrganizationUnit(OrganizationUnit organizationUnit) {
        try {
            if (organizationUnit.isStatus()) {
                this.organizationUnit = organizationUnit;
                this.organizationUnit.getResult().add(organizationUnit.getResult().size(), new OrganizationUnit.Result("", "همه"));
                callGetCreditorAmountToday();
                callGetSumPriceCreditorToday();
            }
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(FunctionalReportActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void showSortDialog() {
        final Dialog dialogSort = new Dialog(FunctionalReportActivity.this);
        dialogSort.setContentView(R.layout.layout_organ);

        recyclerView1 = (RecyclerView) dialogSort.findViewById(R.id.my_organ_list_recycler_view);
        layoutManager1 = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(layoutManager1);
        mAdapter = new OrganListAdapter(organizationUnit.getResult());
        recyclerView1.setAdapter(mAdapter);
        TextView onvan = (TextView) dialogSort.findViewById(R.id.textView4);
        ImageView back = (ImageView) dialogSort.findViewById(R.id.back);
        onvan.setText(R.string.txt_organ);
        dialogSort.show();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSort.dismiss();
            }
        });
        mAdapter.setOnitemclickListener(new OrganListAdapter.OnItemClickListener() {
            @Override
            public void onClick(final int position, String title, String id) {
                dialogSort.dismiss();
                dialogSort.dismiss();
                organSelected = organizationUnit.getResult().get(position).getID();
                mActivityFunctionalReportBinding.organization.setText(organizationUnit.getResult().get(position).getName());
                if (timeDateSelected.equalsIgnoreCase("1")) {
                    if (mActivityFunctionalReportBinding.date.getText().toString().equalsIgnoreCase(currentDay)) {
                        callGetCreditorAmountToday();
                        callGetSumPriceCreditorToday();
                    } else {
                        callGetCreditorAmountLimit();
                        callGetSumPriceCreditor();
                    }
                } else if (timeDateSelected.equalsIgnoreCase("2")) {
                    callGetCreditorAmountLimit();
                    callGetSumPriceCreditor();
                } else if (timeDateSelected.equalsIgnoreCase("3")) {
                    callGetCreditorAmountLimit();
                    callGetSumPriceCreditor();
                } else if (timeDateSelected.equalsIgnoreCase("4")) {
                    callGetCreditorAmountLimit();
                    callGetSumPriceCreditor();
                }
            }
        });
    }

    @Override
    public void showTimeDate() {
        final Dialog dialogTime = new Dialog(FunctionalReportActivity.this);
        dialogTime.setContentView(R.layout.layout_time_date);
        TextView onvan = (TextView) dialogTime.findViewById(R.id.textView4);
        ImageView back = (ImageView) dialogTime.findViewById(R.id.back);
        RelativeLayout roozanel = (RelativeLayout) dialogTime.findViewById(R.id.roozanel);
        RelativeLayout haftegil = (RelativeLayout) dialogTime.findViewById(R.id.haftegil);
        RelativeLayout mahanel = (RelativeLayout) dialogTime.findViewById(R.id.mahanel);
        RelativeLayout salanel = (RelativeLayout) dialogTime.findViewById(R.id.salanel);
        RadioButton roozane = (RadioButton) dialogTime.findViewById(R.id.roozane);
        RadioButton haftegi = (RadioButton) dialogTime.findViewById(R.id.haftegi);
        RadioButton mahane = (RadioButton) dialogTime.findViewById(R.id.mahane);
        RadioButton salane = (RadioButton) dialogTime.findViewById(R.id.salane);

        if (timeDateSelected.equalsIgnoreCase("1") || timeDateSelected.equalsIgnoreCase("")) {
            roozane.setChecked(true);
            haftegi.setChecked(false);
            mahane.setChecked(false);
            salane.setChecked(false);
            mActivityFunctionalReportBinding.time.setText("امروز");
        } else if (timeDateSelected.equalsIgnoreCase("2")) {
            roozane.setChecked(false);
            haftegi.setChecked(true);
            mahane.setChecked(false);
            salane.setChecked(false);
            mActivityFunctionalReportBinding.time.setText("هفتگی");
        } else if (timeDateSelected.equalsIgnoreCase("3")) {
            roozane.setChecked(false);
            haftegi.setChecked(false);
            mahane.setChecked(true);
            salane.setChecked(false);
            mActivityFunctionalReportBinding.time.setText("ماهانه");
        } else if (timeDateSelected.equalsIgnoreCase("4")) {
            roozane.setChecked(false);
            haftegi.setChecked(false);
            mahane.setChecked(false);
            salane.setChecked(true);
            mActivityFunctionalReportBinding.time.setText("سالانه");
        }

        onvan.setText(R.string.txt_time_date);
        dialogTime.show();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogTime.dismiss();
            }
        });
        roozane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                haftegi.setChecked(false);
                mahane.setChecked(false);
                salane.setChecked(false);
                //roozane
                timeDateSelected = "1";
                mActivityFunctionalReportBinding.time.setText("روزانه");
                if (mActivityFunctionalReportBinding.date.getText().toString().equalsIgnoreCase(currentDay)) {
                    callGetCreditorAmountToday();
                    callGetSumPriceCreditorToday();
                } else {
                    callGetCreditorAmountLimit();
                    callGetSumPriceCreditor();
                }
                dialogTime.dismiss();
            }
        });
        roozanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                haftegi.setChecked(false);
                mahane.setChecked(false);
                salane.setChecked(false);
                //roozane
                timeDateSelected = "1";
                mActivityFunctionalReportBinding.time.setText("روزانه");
                if (mActivityFunctionalReportBinding.date.getText().toString().equalsIgnoreCase(currentDay)) {
                    callGetCreditorAmountToday();
                    callGetSumPriceCreditorToday();
                } else {
                    callGetCreditorAmountLimit();
                    callGetSumPriceCreditor();
                }
                dialogTime.dismiss();
            }
        });
        haftegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roozane.setChecked(false);
                mahane.setChecked(false);
                salane.setChecked(false);
                //haftegi
                timeDateSelected = "2";
                mActivityFunctionalReportBinding.time.setText("هفتگی");
                callGetCreditorAmountLimit();
                callGetSumPriceCreditor();
                dialogTime.dismiss();
            }
        });
        haftegil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roozane.setChecked(false);
                mahane.setChecked(false);
                salane.setChecked(false);
                //haftegi
                timeDateSelected = "2";
                mActivityFunctionalReportBinding.time.setText("هفتگی");
                callGetCreditorAmountLimit();
                callGetSumPriceCreditor();
                dialogTime.dismiss();
            }
        });
        mahane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roozane.setChecked(false);
                haftegi.setChecked(false);
                salane.setChecked(false);
                //mahane
                timeDateSelected = "3";
                mActivityFunctionalReportBinding.time.setText("ماهانه");
                callGetCreditorAmountLimit();
                callGetSumPriceCreditor();
                dialogTime.dismiss();
            }
        });
        mahanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roozane.setChecked(false);
                haftegi.setChecked(false);
                salane.setChecked(false);
                //mahane
                timeDateSelected = "3";
                mActivityFunctionalReportBinding.time.setText("ماهانه");
                callGetCreditorAmountLimit();
                callGetSumPriceCreditor();
                dialogTime.dismiss();
            }
        });
        salane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roozane.setChecked(false);
                haftegi.setChecked(false);
                mahane.setChecked(false);
                //salane
                timeDateSelected = "4";
                mActivityFunctionalReportBinding.time.setText("سالانه");
                callGetCreditorAmountLimit();
                callGetSumPriceCreditor();
                dialogTime.dismiss();
            }
        });
        salanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roozane.setChecked(false);
                haftegi.setChecked(false);
                mahane.setChecked(false);
                //salane
                timeDateSelected = "4";
                mActivityFunctionalReportBinding.time.setText("سالانه");
                callGetCreditorAmountLimit();
                callGetSumPriceCreditor();
                dialogTime.dismiss();
            }
        });
    }


    @Override
    public void rightClick() {
        if (mActivityFunctionalReportBinding.date.getText().toString().compareToIgnoreCase(currentDay) >= 0) {
            CommonUtils.showSingleButtonAlert(this,getString(R.string.text_attention),"تاریخ درخواستی از تاریخ جاری بزرگتر است.",getString(R.string.ok),null);
            return;
        }
        if (timeDateSelected.equalsIgnoreCase("1")) {
            mActivityFunctionalReportBinding.date.setText(DateUtil.OneDayNext(mActivityFunctionalReportBinding.date.getText().toString()));
            if (mActivityFunctionalReportBinding.date.getText().toString().equalsIgnoreCase(currentDay)) {
                callGetCreditorAmountToday();
                callGetSumPriceCreditorToday();
            } else {
                callGetCreditorAmountLimit();
                callGetSumPriceCreditor();
            }
        } else if (timeDateSelected.equalsIgnoreCase("2")) {
            mActivityFunctionalReportBinding.date.setText(DateUtil.OneWeekNext(mActivityFunctionalReportBinding.date.getText().toString()));
            callGetCreditorAmountLimit();
            callGetSumPriceCreditor();
        } else if (timeDateSelected.equalsIgnoreCase("3")) {
            mActivityFunctionalReportBinding.date.setText(DateUtil.OneMonthNext(mActivityFunctionalReportBinding.date.getText().toString(), "01"));
            callGetCreditorAmountLimit();
            callGetSumPriceCreditor();
        } else if (timeDateSelected.equalsIgnoreCase("4")) {
            mActivityFunctionalReportBinding.date.setText(DateUtil.OneYearNext(mActivityFunctionalReportBinding.date.getText().toString(), "/01/01"));
            callGetCreditorAmountLimit();
            callGetSumPriceCreditor();
        }
    }

    @Override
    public void leftClick() {
        if (timeDateSelected.equalsIgnoreCase("1")) {
            mActivityFunctionalReportBinding.date.setText(DateUtil.OneDayBefor(mActivityFunctionalReportBinding.date.getText().toString()));
            if (mActivityFunctionalReportBinding.date.getText().toString().equalsIgnoreCase(currentDay)) {
                callGetCreditorAmountToday();
                callGetSumPriceCreditorToday();
            } else {
                callGetCreditorAmountLimit();
                callGetSumPriceCreditor();
            }
        } else if (timeDateSelected.equalsIgnoreCase("2")) {
            mActivityFunctionalReportBinding.date.setText(DateUtil.OneWeekBefor(mActivityFunctionalReportBinding.date.getText().toString()));
            callGetCreditorAmountLimit();
            callGetSumPriceCreditor();
        } else if (timeDateSelected.equalsIgnoreCase("3")) {
            mActivityFunctionalReportBinding.date.setText(DateUtil.OneMonthBefor(mActivityFunctionalReportBinding.date.getText().toString(), "01"));
            callGetCreditorAmountLimit();
            callGetSumPriceCreditor();
        } else if (timeDateSelected.equalsIgnoreCase("4")) {
            mActivityFunctionalReportBinding.date.setText(DateUtil.OneYearBefor(mActivityFunctionalReportBinding.date.getText().toString(), "/01/01"));
            callGetCreditorAmountLimit();
            callGetSumPriceCreditor();
        }
    }

    private void callGetCreditorAmountToday() {
        try {
            mFunctionalReportViewModel.callGetCreditorAmountToday(organSelected);
            mFunctionalReportViewModel.getCreditorAmountTodayResponseModelMutableLiveData().observe(this, this::receivedGetCreditorAmountToday);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(FunctionalReportActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedGetCreditorAmountToday(CreditorAmount creditorAmount) {
        if (creditorAmount.isStatus()) {
            LinearLayoutManager layoutManagr = new LinearLayoutManager(this);
            mActivityFunctionalReportBinding.list.setLayoutManager(layoutManagr);
            CreditorAdapter mAdapter = new CreditorAdapter(creditorAmount.getResult());
            mActivityFunctionalReportBinding.list.setAdapter(mAdapter);
        } else {
            CommonUtils.showSingleButtonAlert(FunctionalReportActivity.this, getString(R.string.text_attention), creditorAmount.getErrmessage(), null, null);
        }
    }

    private void callGetCreditorAmountLimit() {
        try {
            String fromdate = "", todate = "";
            if (timeDateSelected.equalsIgnoreCase("1")) {
                todate = DateUtil.OneDayNext(mActivityFunctionalReportBinding.date.getText().toString());
                fromdate = mActivityFunctionalReportBinding.date.getText().toString();
            }
            if (timeDateSelected.equalsIgnoreCase("2")) {
                fromdate = LanguageUtils.getLatinNumbers(DateUtil.hafteJari(mActivityFunctionalReportBinding.date.getText().toString()));
                todate = LanguageUtils.getLatinNumbers(DateUtil.AddDate(fromdate, 6));
            }
            if (timeDateSelected.equalsIgnoreCase("3")) {
                fromdate = mActivityFunctionalReportBinding.date.getText().toString().substring(0, 8) + "01";
                todate = mActivityFunctionalReportBinding.date.getText().toString().substring(0, 8) + "31";
            }
            if (timeDateSelected.equalsIgnoreCase("4")) {
                fromdate = mActivityFunctionalReportBinding.date.getText().toString().substring(0, 4) + "/01/01";
                todate = mActivityFunctionalReportBinding.date.getText().toString().substring(0, 4) + "/12/31";
            }
            mFunctionalReportViewModel.callGetCreditorAmountLimit(fromdate, todate, organSelected);
            mFunctionalReportViewModel.getCreditorAmountLimitResponseModelMutableLiveData().observe(this, this::receivedGetCreditorAmountLimit);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(FunctionalReportActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedGetCreditorAmountLimit(CreditorAmount creditorAmount) {
        if (creditorAmount.isStatus()) {
            LinearLayoutManager layoutManagr = new LinearLayoutManager(this);
            mActivityFunctionalReportBinding.list.setLayoutManager(layoutManagr);
            CreditorAdapter mAdapter = new CreditorAdapter(creditorAmount.getResult());
            mActivityFunctionalReportBinding.list.setAdapter(mAdapter);
        } else {
            CommonUtils.showSingleButtonAlert(FunctionalReportActivity.this, getString(R.string.text_attention), creditorAmount.getErrmessage(), null, null);
        }
    }

    private void callGetSumPriceCreditor() {
        try {
            String fromdate = "", todate = "";
            if (timeDateSelected.equalsIgnoreCase("1")) {
                todate = DateUtil.OneDayNext(mActivityFunctionalReportBinding.date.getText().toString());
                fromdate = mActivityFunctionalReportBinding.date.getText().toString();
            }
            if (timeDateSelected.equalsIgnoreCase("2")) {
                fromdate = LanguageUtils.getLatinNumbers(DateUtil.hafteJari(mActivityFunctionalReportBinding.date.getText().toString()));
                todate = LanguageUtils.getLatinNumbers(DateUtil.AddDate(fromdate, 6));
            }
            if (timeDateSelected.equalsIgnoreCase("3")) {
                fromdate = mActivityFunctionalReportBinding.date.getText().toString().substring(0, 8) + "01";
                todate = mActivityFunctionalReportBinding.date.getText().toString().substring(0, 8) + "31";
            }
            if (timeDateSelected.equalsIgnoreCase("4")) {
                fromdate = mActivityFunctionalReportBinding.date.getText().toString().substring(0, 4) + "/01/01";
                todate = mActivityFunctionalReportBinding.date.getText().toString().substring(0, 4) + "/12/31";
            }
            mFunctionalReportViewModel.callGetSumPriceCreditor(fromdate, todate, organSelected);
            mFunctionalReportViewModel.getSumPriceCreditorResponseModelMutableLiveData().observe(this, this::receivedSumPriceReciept);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(FunctionalReportActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedSumPriceReciept(SumPrice sumPrice) {
        if (sumPrice.isStatus())
            mActivityFunctionalReportBinding.sum.setText(NumberFormatter.format(Long.parseLong(sumPrice.getSumTotalAmount())));
    }

    private void callGetSumPriceCreditorToday() {
        try {

            mFunctionalReportViewModel.callGetSumPriceCreditorToday(organSelected);
            mFunctionalReportViewModel.getSumPriceCreditorTodayResponseModelMutableLiveData().observe(this, this::receivedSumPriceRecieptToday);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(FunctionalReportActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedSumPriceRecieptToday(SumPrice sumPrice) {
        if (sumPrice.isStatus())
            mActivityFunctionalReportBinding.sum.setText(NumberFormatter.format(Long.parseLong(sumPrice.getSumTotalAmount())));
    }

    @Override
    public void backClick() {
        finish();
    }
}
