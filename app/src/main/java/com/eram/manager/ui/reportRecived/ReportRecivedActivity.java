package com.eram.manager.ui.reportRecived;

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
import com.eram.manager.data.model.api.DebtorAmount;
import com.eram.manager.data.model.api.OrganizationUnit;
import com.eram.manager.data.model.api.SumPrice;
import com.eram.manager.databinding.ActivityReportRecivedBinding;
import com.eram.manager.ui.base.BaseActivity;
import com.eram.manager.ui.stateReception.OrganListAdapter;
import com.eram.manager.utils.AppConstants;
import com.eram.manager.utils.CommonUtils;
import com.eram.manager.utils.NumberFormatter;
import com.eram.manager.utils.fdate.DateUtil;
import com.mojtaba.materialdatetimepicker.utils.LanguageUtils;
import com.mojtaba.materialdatetimepicker.utils.PersianCalendar;

import javax.inject.Inject;

public class ReportRecivedActivity extends BaseActivity<ActivityReportRecivedBinding, ReportRecivedViewModel> implements AppConstants, ReportRecivedNavigator {

    @Inject
    ReportRecivedViewModel mReportRecivedViewModel;

    ActivityReportRecivedBinding mActivityReportRecivedBinding;
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
            mActivityReportRecivedBinding = getViewDataBinding();
            mReportRecivedViewModel.setNavigator(this);
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
            mActivityReportRecivedBinding.date.setText(currentDay);
            callOrganizationUnit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, ReportRecivedActivity.class);
    }

    @Override
    public ReportRecivedViewModel getViewModel() {
        return mReportRecivedViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_report_recived;
    }

    @Override
    public void showOrganization() {
        showSortDialog();
    }

    private void callOrganizationUnit() {
        try {
            mReportRecivedViewModel.getOrganizatonUnit();
            mReportRecivedViewModel.getOrganizationUnitResponseModelMutableLiveData().observe(this, this::receivedOrganizationUnit);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ReportRecivedActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedOrganizationUnit(OrganizationUnit organizationUnit) {
        try {
            if (organizationUnit.isStatus()) {
                this.organizationUnit = organizationUnit;
                this.organizationUnit.getResult().add(organizationUnit.getResult().size(), new OrganizationUnit.Result("", "همه"));

            }
            callGetDebtorAmountToday();

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ReportRecivedActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void showSortDialog() {
        final Dialog dialogSort = new Dialog(ReportRecivedActivity.this);
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
                organSelected = organizationUnit.getResult().get(position).getID();
                mActivityReportRecivedBinding.organization.setText(organizationUnit.getResult().get(position).getName());
                if (timeDateSelected.equalsIgnoreCase("1")) {
                    if (mActivityReportRecivedBinding.date.getText().toString().equalsIgnoreCase(currentDay))
                        callGetDebtorAmountToday();
                    else
                        callGetDebtorAmountLimit();

                } else if (timeDateSelected.equalsIgnoreCase("2")) {
                    callGetDebtorAmountLimit();

                } else if (timeDateSelected.equalsIgnoreCase("3")) {
                    callGetDebtorAmountLimit();

                } else if (timeDateSelected.equalsIgnoreCase("4")) {
                    callGetDebtorAmountLimit();

                }
            }
        });
    }

    @Override
    public void showTimeDate() {
        final Dialog dialogTime = new Dialog(ReportRecivedActivity.this);
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
            mActivityReportRecivedBinding.time.setText("امروز");
        } else if (timeDateSelected.equalsIgnoreCase("2")) {
            roozane.setChecked(false);
            haftegi.setChecked(true);
            mahane.setChecked(false);
            salane.setChecked(false);
            mActivityReportRecivedBinding.time.setText("هفتگی");
        } else if (timeDateSelected.equalsIgnoreCase("3")) {
            roozane.setChecked(false);
            haftegi.setChecked(false);
            mahane.setChecked(true);
            salane.setChecked(false);
            mActivityReportRecivedBinding.time.setText("ماهانه");
        } else if (timeDateSelected.equalsIgnoreCase("4")) {
            roozane.setChecked(false);
            haftegi.setChecked(false);
            mahane.setChecked(false);
            salane.setChecked(true);
            mActivityReportRecivedBinding.time.setText("سالانه");
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
                mActivityReportRecivedBinding.time.setText("روزانه");
                if (mActivityReportRecivedBinding.date.getText().toString().equalsIgnoreCase(currentDay))
                    callGetDebtorAmountToday();
                else
                    callGetDebtorAmountLimit();
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
                mActivityReportRecivedBinding.time.setText("روزانه");
                if (mActivityReportRecivedBinding.date.getText().toString().equalsIgnoreCase(currentDay))
                    callGetDebtorAmountToday();
                else
                    callGetDebtorAmountLimit();
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
                mActivityReportRecivedBinding.time.setText("هفتگی");
                callGetDebtorAmountLimit();
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
                mActivityReportRecivedBinding.time.setText("هفتگی");
                callGetDebtorAmountLimit();
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
                mActivityReportRecivedBinding.time.setText("ماهانه");
                callGetDebtorAmountLimit();
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
                mActivityReportRecivedBinding.time.setText("ماهانه");
                callGetDebtorAmountLimit();
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
                mActivityReportRecivedBinding.time.setText("سالانه");
                callGetDebtorAmountLimit();
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
                mActivityReportRecivedBinding.time.setText("سالانه");
                callGetDebtorAmountLimit();
                dialogTime.dismiss();
            }
        });
    }

    @Override
    public void rightClick() {
        mActivityReportRecivedBinding.next.setEnabled(false);
        if (mActivityReportRecivedBinding.date.getText().toString().compareToIgnoreCase(currentDay) >= 0) {
            CommonUtils.showSingleButtonAlert(this, getString(R.string.text_attention), "تاریخ درخواستی از تاریخ جاری بزرگتر است.", getString(R.string.ok), null);
            return;
        }
        if (timeDateSelected.equalsIgnoreCase("1")) {
            mActivityReportRecivedBinding.date.setText(DateUtil.OneDayNext(mActivityReportRecivedBinding.date.getText().toString()));
            if (mActivityReportRecivedBinding.date.getText().toString().equalsIgnoreCase(currentDay))
                callGetDebtorAmountToday();
            else
                callGetDebtorAmountLimit();
        } else if (timeDateSelected.equalsIgnoreCase("2")) {
            mActivityReportRecivedBinding.date.setText(DateUtil.OneWeekNext(mActivityReportRecivedBinding.date.getText().toString()));
            callGetDebtorAmountLimit();
        } else if (timeDateSelected.equalsIgnoreCase("3")) {
            mActivityReportRecivedBinding.date.setText(DateUtil.OneMonthNext(mActivityReportRecivedBinding.date.getText().toString(), "01"));
            callGetDebtorAmountLimit();
        } else if (timeDateSelected.equalsIgnoreCase("4")) {
            mActivityReportRecivedBinding.date.setText(DateUtil.OneYearNext(mActivityReportRecivedBinding.date.getText().toString(), "/01/01"));
            callGetDebtorAmountLimit();
        }
    }

    @Override
    public void leftClick() {
        mActivityReportRecivedBinding.before.setEnabled(false);
        if (timeDateSelected.equalsIgnoreCase("1")) {
            mActivityReportRecivedBinding.date.setText(DateUtil.OneDayBefor(mActivityReportRecivedBinding.date.getText().toString()));
            if (mActivityReportRecivedBinding.date.getText().toString().equalsIgnoreCase(currentDay))
                callGetDebtorAmountToday();
            else
                callGetDebtorAmountLimit();
        } else if (timeDateSelected.equalsIgnoreCase("2")) {
            mActivityReportRecivedBinding.date.setText(DateUtil.OneWeekBefor(mActivityReportRecivedBinding.date.getText().toString()));
            callGetDebtorAmountLimit();
        } else if (timeDateSelected.equalsIgnoreCase("3")) {
            mActivityReportRecivedBinding.date.setText(DateUtil.OneMonthBefor(mActivityReportRecivedBinding.date.getText().toString(), "01"));
            callGetDebtorAmountLimit();
        } else if (timeDateSelected.equalsIgnoreCase("4")) {
            mActivityReportRecivedBinding.date.setText(DateUtil.OneYearBefor(mActivityReportRecivedBinding.date.getText().toString(), "/01/01"));
            callGetDebtorAmountLimit();
        }
    }

    private void callGetDebtorAmountToday() {
        try {
            mReportRecivedViewModel.callGetDebtorAmountToday(organSelected);
            mReportRecivedViewModel.getDebtorAmountTodayResponseModelMutableLiveData().observe(this, this::receivedGetDebtorAmountToday);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ReportRecivedActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedGetDebtorAmountToday(DebtorAmount debtorAmount) {
        mActivityReportRecivedBinding.before.setEnabled(true);
        mActivityReportRecivedBinding.next.setEnabled(true);
        if (debtorAmount.isStatus()) {
            LinearLayoutManager layoutManagr = new LinearLayoutManager(this);
            mActivityReportRecivedBinding.list.setLayoutManager(layoutManagr);
            ReportAdapter mAdapter = new ReportAdapter(debtorAmount.getResult());
            mActivityReportRecivedBinding.list.setAdapter(mAdapter);
        } else {
            CommonUtils.showSingleButtonAlert(ReportRecivedActivity.this, getString(R.string.text_attention), debtorAmount.getErrmessage(), null, null);
        }
        callGetSumPriceReceipt();
    }

    private void callGetDebtorAmountLimit() {
        try {
            String fromdate = "", todate = "";
            if (timeDateSelected.equalsIgnoreCase("1")) {
                todate = DateUtil.OneDayNext(mActivityReportRecivedBinding.date.getText().toString());
                fromdate = mActivityReportRecivedBinding.date.getText().toString();
            }
            if (timeDateSelected.equalsIgnoreCase("2")) {
                fromdate = LanguageUtils.getLatinNumbers(DateUtil.hafteJari(mActivityReportRecivedBinding.date.getText().toString()));
                todate = LanguageUtils.getLatinNumbers(DateUtil.AddDate(fromdate, 6));
            }
            if (timeDateSelected.equalsIgnoreCase("3")) {
                fromdate = mActivityReportRecivedBinding.date.getText().toString().substring(0, 8) + "01";
                todate = mActivityReportRecivedBinding.date.getText().toString().substring(0, 8) + "31";
            }
            if (timeDateSelected.equalsIgnoreCase("4")) {
                fromdate = mActivityReportRecivedBinding.date.getText().toString().substring(0, 4) + "/01/01";
                todate = mActivityReportRecivedBinding.date.getText().toString().substring(0, 4) + "/12/31";
            }
            mReportRecivedViewModel.callGetDebtorAmountLimit(fromdate, todate, organSelected);
            mReportRecivedViewModel.getDebtorAmountLimitResponseModelMutableLiveData().observe(this, this::receivedGetDebtorAmountLimit);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ReportRecivedActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedGetDebtorAmountLimit(DebtorAmount debtorAmount) {
        mActivityReportRecivedBinding.before.setEnabled(true);
        mActivityReportRecivedBinding.next.setEnabled(true);
        if (debtorAmount.isStatus()) {
            LinearLayoutManager layoutManagr = new LinearLayoutManager(this);
            mActivityReportRecivedBinding.list.setLayoutManager(layoutManagr);
            ReportAdapter mAdapter = new ReportAdapter(debtorAmount.getResult());
            mActivityReportRecivedBinding.list.setAdapter(mAdapter);
        } else {
            CommonUtils.showSingleButtonAlert(ReportRecivedActivity.this, getString(R.string.text_attention), debtorAmount.getErrmessage(), null, null);
        }
        callGetSumPriceReceipt();
    }

    private void callGetSumPriceReceipt() {
        try {
            String fromdate = "", todate = "";
            if (timeDateSelected.equalsIgnoreCase("1")) {
                String dateMiladi = DateUtil.changeFarsiToMiladi(mActivityReportRecivedBinding.date.getText().toString());
                String dateYear = NumberFormatter.convertPriceToNumber(dateMiladi.substring(0, 4));
                String dateMonth = NumberFormatter.convertPriceToNumber(dateMiladi.substring(5, 7));
                String dateDay = NumberFormatter.convertPriceToNumber(dateMiladi.substring(8, 10));
                todate = dateYear + "/" + dateMonth + "/" + dateDay;

                fromdate = dateYear + "/" + dateMonth + "/" + dateDay;
            }
            if (timeDateSelected.equalsIgnoreCase("2")) {
                String dateMiladi = DateUtil.changeFarsiToMiladi(mActivityReportRecivedBinding.date.getText().toString());
                String dateYear = NumberFormatter.convertPriceToNumber(dateMiladi.substring(0, 4));
                String dateMonth = NumberFormatter.convertPriceToNumber(dateMiladi.substring(5, 7));
                String dateDay = NumberFormatter.convertPriceToNumber(dateMiladi.substring(8, 10));
                todate = dateYear + "/" + dateMonth + "/" + dateDay;
                dateMiladi = DateUtil.changeFarsiToMiladi(DateUtil.OneWeekBefor(mActivityReportRecivedBinding.date.getText().toString()));
                dateYear = NumberFormatter.convertPriceToNumber(dateMiladi.substring(0, 4));
                dateMonth = NumberFormatter.convertPriceToNumber(dateMiladi.substring(5, 7));
                dateDay = NumberFormatter.convertPriceToNumber(dateMiladi.substring(8, 10));
                fromdate = dateYear + "/" + dateMonth + "/" + dateDay;
            }
            if (timeDateSelected.equalsIgnoreCase("3")) {
                String dateMiladi = DateUtil.changeFarsiToMiladi(DateUtil.OneMonthBefor(mActivityReportRecivedBinding.date.getText().toString(), "31"));
                String dateYear = NumberFormatter.convertPriceToNumber(dateMiladi.substring(0, 4));
                String dateMonth = NumberFormatter.convertPriceToNumber(dateMiladi.substring(5, 7));
                String dateDay = NumberFormatter.convertPriceToNumber(dateMiladi.substring(8, 10));
                todate = dateYear + "/" + dateMonth + "/" + dateDay;
                dateMiladi = DateUtil.changeFarsiToMiladi(DateUtil.OneMonthBefor(mActivityReportRecivedBinding.date.getText().toString(), "01"));
                dateYear = NumberFormatter.convertPriceToNumber(dateMiladi.substring(0, 4));
                dateMonth = NumberFormatter.convertPriceToNumber(dateMiladi.substring(5, 7));
                dateDay = NumberFormatter.convertPriceToNumber(dateMiladi.substring(8, 10));
                fromdate = dateYear + "/" + dateMonth + "/" + dateDay;
            }
            if (timeDateSelected.equalsIgnoreCase("4")) {
                String dateMiladi = DateUtil.changeFarsiToMiladi(mActivityReportRecivedBinding.date.getText().toString());
                String dateYear = NumberFormatter.convertPriceToNumber(dateMiladi.substring(0, 4));
                String dateMonth = NumberFormatter.convertPriceToNumber(dateMiladi.substring(5, 7));
                String dateDay = NumberFormatter.convertPriceToNumber(dateMiladi.substring(8, 10));
                todate = dateYear + "/" + dateMonth + "/" + dateDay;
                dateMiladi = DateUtil.changeFarsiToMiladi(DateUtil.OneYearBefor(mActivityReportRecivedBinding.date.getText().toString(), "/01/01"));
                dateYear = NumberFormatter.convertPriceToNumber(dateMiladi.substring(0, 4));
                dateMonth = NumberFormatter.convertPriceToNumber(dateMiladi.substring(5, 7));
                dateDay = NumberFormatter.convertPriceToNumber(dateMiladi.substring(8, 10));
                fromdate = dateYear + "/" + dateMonth + "/" + dateDay;
            }

            mReportRecivedViewModel.callGetSumPriceReceipt(fromdate, todate, organSelected);
            mReportRecivedViewModel.getSumPriceReceiptResponseModelMutableLiveData().observe(this, this::receivedSumPriceReciept);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ReportRecivedActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedSumPriceReciept(SumPrice sumPrice) {
        mActivityReportRecivedBinding.before.setEnabled(true);
        mActivityReportRecivedBinding.next.setEnabled(true);
        if (sumPrice.isStatus())
            mActivityReportRecivedBinding.sum.setText(NumberFormatter.format(Long.parseLong(sumPrice.getSumTotalAmount())));
    }

    @Override
    public void backClick() {
        finish();
    }
}
