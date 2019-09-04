package com.eram.manager.ui.stateReception;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eram.manager.BR;
import com.eram.manager.R;
import com.eram.manager.data.model.api.OrganizationUnit;
import com.eram.manager.data.model.api.PoolReception;
import com.eram.manager.data.model.api.PoolReceptionLimit;
import com.eram.manager.data.model.api.PoolReceptionStatus;
import com.eram.manager.databinding.ActivityStateReceptionBinding;
import com.eram.manager.ui.base.BaseActivity;
import com.eram.manager.utils.AppConstants;
import com.eram.manager.utils.CommonUtils;
import com.eram.manager.utils.NumberFormatter;
import com.eram.manager.utils.fdate.DateUtil;
import com.eram.manager.utils.fdate.PersianDate;
import com.mojtaba.materialdatetimepicker.utils.PersianCalendar;

import javax.inject.Inject;

public class StateReceptionActivity extends BaseActivity<ActivityStateReceptionBinding, StateReceptionViewModel> implements AppConstants, StateReceptionNavigator {

    @Inject
    StateReceptionViewModel mStateReceptionViewModel;

    ActivityStateReceptionBinding mActivityStateReceptionBinding;
    private RecyclerView recyclerView1;
    private LinearLayoutManager layoutManager1;
    private OrganListAdapter mAdapter;
    private String organSelected = "";
    private String timeDateSelected = "1";
    private OrganizationUnit organizationUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityStateReceptionBinding = getViewDataBinding();
            mStateReceptionViewModel.setNavigator(this);
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
            mActivityStateReceptionBinding.date.setText(persianCalendar.getPersianYear() + "/" + monthh + "/" + dayy);
            callOrganizationUnit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, StateReceptionActivity.class);
    }

    @Override
    public StateReceptionViewModel getViewModel() {
        return mStateReceptionViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_state_reception;
    }

    @Override
    public void showOrganization() {
        showSortDialog();
    }

    private void callOrganizationUnit() {
        try {
            mStateReceptionViewModel.getOrganizatonUnit();
            mStateReceptionViewModel.getOrganizationUnitResponseModelMutableLiveData().observe(this, this::receivedOrganizationUnit);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(StateReceptionActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedOrganizationUnit(OrganizationUnit organizationUnit) {
        try {
            if (organizationUnit.isStatus()) {
                this.organizationUnit = organizationUnit;
                this.organizationUnit.getResult().add(organizationUnit.getResult().size(), new OrganizationUnit.Result("", "همه"));
                callGetPoolReceptionDay();
                callGetPoolReceptionStatus();
            }
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(StateReceptionActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void showSortDialog() {
        final Dialog dialogSort = new Dialog(StateReceptionActivity.this);
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
                mActivityStateReceptionBinding.organization.setText(organizationUnit.getResult().get(position).getName());
                if (timeDateSelected.equalsIgnoreCase("1")) {
                    callGetPoolReceptionDay();

                } else if (timeDateSelected.equalsIgnoreCase("2")) {
                    callGetPoolReceptionLimit();

                } else if (timeDateSelected.equalsIgnoreCase("3")) {
                    callGetPoolReceptionLimit();

                } else if (timeDateSelected.equalsIgnoreCase("4")) {
                    callGetPoolReceptionLimit();

                }
            }
        });
    }

    @Override
    public void showTimeDate() {
        final Dialog dialogTime = new Dialog(StateReceptionActivity.this);
        dialogTime.setContentView(R.layout.layout_time_date);
        TextView onvan = (TextView) dialogTime.findViewById(R.id.textView4);
        ImageView back = (ImageView) dialogTime.findViewById(R.id.back);
        RadioButton roozane = (RadioButton) dialogTime.findViewById(R.id.roozane);
        RadioButton haftegi = (RadioButton) dialogTime.findViewById(R.id.haftegi);
        RadioButton mahane = (RadioButton) dialogTime.findViewById(R.id.mahane);
        RadioButton salane = (RadioButton) dialogTime.findViewById(R.id.salane);

        if (timeDateSelected.equalsIgnoreCase("1") || timeDateSelected.equalsIgnoreCase("")) {
            roozane.setChecked(true);
            haftegi.setChecked(false);
            mahane.setChecked(false);
            salane.setChecked(false);
            mActivityStateReceptionBinding.time.setText("امروز");
        } else if (timeDateSelected.equalsIgnoreCase("2")) {
            roozane.setChecked(false);
            haftegi.setChecked(true);
            mahane.setChecked(false);
            salane.setChecked(false);
            mActivityStateReceptionBinding.time.setText("هفتگی");
        } else if (timeDateSelected.equalsIgnoreCase("3")) {
            roozane.setChecked(false);
            haftegi.setChecked(false);
            mahane.setChecked(true);
            salane.setChecked(false);
            mActivityStateReceptionBinding.time.setText("ماهانه");
        } else if (timeDateSelected.equalsIgnoreCase("4")) {
            roozane.setChecked(false);
            haftegi.setChecked(false);
            mahane.setChecked(false);
            salane.setChecked(true);
            mActivityStateReceptionBinding.time.setText("سالانه");
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
                mActivityStateReceptionBinding.time.setText("روزانه");
                callGetPoolReceptionDay();
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
                mActivityStateReceptionBinding.time.setText("هفتگی");
                callGetPoolReceptionLimit();
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
                mActivityStateReceptionBinding.time.setText("ماهانه");
                callGetPoolReceptionLimit();
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
                mActivityStateReceptionBinding.time.setText("سالانه");
                callGetPoolReceptionLimit();
                dialogTime.dismiss();
            }
        });
    }

    @Override
    public void rightClick() {
        if (timeDateSelected.equalsIgnoreCase("1")) {
            mActivityStateReceptionBinding.date.setText(DateUtil.OneDayNext(mActivityStateReceptionBinding.date.getText().toString()));
            callGetPoolReceptionDay();
        } else if (timeDateSelected.equalsIgnoreCase("2")) {
            mActivityStateReceptionBinding.date.setText(DateUtil.OneWeekNext(mActivityStateReceptionBinding.date.getText().toString()));
            callGetPoolReceptionLimit();
        } else if (timeDateSelected.equalsIgnoreCase("3")) {
            mActivityStateReceptionBinding.date.setText(DateUtil.OneMonthNext(mActivityStateReceptionBinding.date.getText().toString()));
            callGetPoolReceptionLimit();
        } else if (timeDateSelected.equalsIgnoreCase("4")) {
            mActivityStateReceptionBinding.date.setText(DateUtil.OneYearNext(mActivityStateReceptionBinding.date.getText().toString()));
            callGetPoolReceptionLimit();
        }
    }

    @Override
    public void leftClick() {
        if (timeDateSelected.equalsIgnoreCase("1")) {
            mActivityStateReceptionBinding.date.setText(DateUtil.OneDayBefor(mActivityStateReceptionBinding.date.getText().toString()));
            callGetPoolReceptionDay();

        } else if (timeDateSelected.equalsIgnoreCase("2")) {
            mActivityStateReceptionBinding.date.setText(DateUtil.OneWeekBefor(mActivityStateReceptionBinding.date.getText().toString()));
            callGetPoolReceptionLimit();

        } else if (timeDateSelected.equalsIgnoreCase("3")) {
            mActivityStateReceptionBinding.date.setText(DateUtil.OneMonthBefor(mActivityStateReceptionBinding.date.getText().toString()));
            callGetPoolReceptionLimit();

        } else if (timeDateSelected.equalsIgnoreCase("4")) {
            mActivityStateReceptionBinding.date.setText(DateUtil.OneYearBefor(mActivityStateReceptionBinding.date.getText().toString()));
            callGetPoolReceptionLimit();

        }
    }

    private void callGetPoolReceptionDay() {
        try {
            String dateMiladi = DateUtil.changeFarsiToMiladi(mActivityStateReceptionBinding.date.getText().toString());
            String dateYear = NumberFormatter.convertPriceToNumber(dateMiladi.substring(0, 4));
            String dateMonth = NumberFormatter.convertPriceToNumber(dateMiladi.substring(5, 7));
            String dateDay = NumberFormatter.convertPriceToNumber(dateMiladi.substring(8, 10));
            String date = dateYear + "/" + dateMonth + "/" + dateDay;
            mStateReceptionViewModel.callGetPoolReceptionDay(date, organSelected);
            mStateReceptionViewModel.getPoolReceptionDayResponseModelMutableLiveData().observe(this, this::receivedGetPoolReceptionDay);
        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(StateReceptionActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedGetPoolReceptionDay(PoolReception poolReception) {
        if (poolReception != null && poolReception.isStatus()) {
            mActivityStateReceptionBinding.numberPresent.setText(poolReception.getPresentMemberCount());
            mActivityStateReceptionBinding.numberAbsent.setText(poolReception.getExitedMemberCount());
            mActivityStateReceptionBinding.numberAll.setText(poolReception.getAllReceptionedMemberCount());
        }
    }

    private void callGetPoolReceptionLimit() {
        try {
            String fromdate = "", todate = "";
            if (timeDateSelected.equalsIgnoreCase("1")) {
                todate = DateUtil.OneDayNext(mActivityStateReceptionBinding.date.getText().toString());
                fromdate = mActivityStateReceptionBinding.date.getText().toString();
            }
            if (timeDateSelected.equalsIgnoreCase("2")) {
                todate = DateUtil.OneWeekNext(mActivityStateReceptionBinding.date.getText().toString());
                fromdate = mActivityStateReceptionBinding.date.getText().toString();
            }
            if (timeDateSelected.equalsIgnoreCase("3")) {
                todate = DateUtil.OneMonthNext(mActivityStateReceptionBinding.date.getText().toString());
                fromdate = mActivityStateReceptionBinding.date.getText().toString();
            }
            if (timeDateSelected.equalsIgnoreCase("4")) {
                todate = DateUtil.OneYearNext(mActivityStateReceptionBinding.date.getText().toString());
                fromdate = mActivityStateReceptionBinding.date.getText().toString();
            }

            mStateReceptionViewModel.callGetPoolReceptionLimit(fromdate, todate, organSelected);
            mStateReceptionViewModel.getPoolReceptionLimitResponseModelMutableLiveData().observe(this, this::receivedGetPoolReceptionLimit);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(StateReceptionActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedGetPoolReceptionLimit(PoolReceptionLimit poolReceptionLimit) {
        if (poolReceptionLimit != null && poolReceptionLimit.isStatus()) {
            mActivityStateReceptionBinding.numberPresent.setText("0");
            mActivityStateReceptionBinding.numberAbsent.setText(poolReceptionLimit.getAllReceptionedMemberCount());
            mActivityStateReceptionBinding.numberAll.setText(poolReceptionLimit.getAllReceptionedMemberCount());
        }
    }

    private void callGetPoolReceptionStatus() {
        try {
            mStateReceptionViewModel.callGetPoolReceptionStatus(organSelected);
            mStateReceptionViewModel.getPoolReceptionStatusResponseModelMutableLiveData().observe(this, this::receivedGetPoolReceptionStatus);

        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(StateReceptionActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void receivedGetPoolReceptionStatus(PoolReceptionStatus poolReceptionStatus) {

        mActivityStateReceptionBinding.max.setText(poolReceptionStatus.getPresentMember_Max_Count());
        mActivityStateReceptionBinding.day.setText(DateUtil.getNameDay(poolReceptionStatus.getPresentMember_Max_DateTime()));
        PersianDate persianDate = new PersianDate();
        int[] i = persianDate.toJalali(Integer.parseInt(poolReceptionStatus.getPresentMember_Max_DateTime().substring(0, 4)),
                Integer.parseInt(poolReceptionStatus.getPresentMember_Max_DateTime().substring(5, 7)),
                Integer.parseInt(poolReceptionStatus.getPresentMember_Max_DateTime().substring(8, 10)));
        mActivityStateReceptionBinding.movarekh.setText(i[0] + "/" + i[1] + "/" + i[2]);

        mActivityStateReceptionBinding.max2.setText(poolReceptionStatus.getAllReceptionedMember_Max_Count());
        mActivityStateReceptionBinding.day2.setText(DateUtil.getNameDay(poolReceptionStatus.getAllReceptionedMember_Max_DateTime()));
        int[] i2 = persianDate.toJalali(Integer.parseInt(poolReceptionStatus.getPresentMember_Max_DateTime().substring(0, 4)),
                Integer.parseInt(poolReceptionStatus.getPresentMember_Max_DateTime().substring(5, 7)),
                Integer.parseInt(poolReceptionStatus.getPresentMember_Max_DateTime().substring(8, 10)));
        mActivityStateReceptionBinding.movarekh2.setText(i2[0] + "/" + i2[1] + "/" + i2[2]);

        mActivityStateReceptionBinding.max3.setText(poolReceptionStatus.getAllReceptionedMember_Min_Count());
        mActivityStateReceptionBinding.day3.setText(DateUtil.getNameDay(poolReceptionStatus.getAllReceptionedMember_Min_DateTime()));
        int[] i3 = persianDate.toJalali(Integer.parseInt(poolReceptionStatus.getAllReceptionedMember_Min_DateTime().substring(0, 4)),
                Integer.parseInt(poolReceptionStatus.getAllReceptionedMember_Min_DateTime().substring(5, 7)),
                Integer.parseInt(poolReceptionStatus.getAllReceptionedMember_Min_DateTime().substring(8, 10)));
        mActivityStateReceptionBinding.movarekh3.setText(i3[0] + "/" + i3[1] + "/" + i3[2]);
    }
}
