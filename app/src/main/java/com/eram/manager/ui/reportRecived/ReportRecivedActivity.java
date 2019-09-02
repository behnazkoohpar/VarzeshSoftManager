package com.eram.manager.ui.reportRecived;

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
import com.eram.manager.databinding.ActivityReportRecivedBinding;
import com.eram.manager.ui.base.BaseActivity;
import com.eram.manager.ui.stateReception.OrganListAdapter;
import com.eram.manager.utils.AppConstants;
import com.eram.manager.utils.CommonUtils;

import javax.inject.Inject;

public class ReportRecivedActivity extends BaseActivity<ActivityReportRecivedBinding, ReportRecivedViewModel> implements AppConstants, ReportRecivedNavigator {

    @Inject
    ReportRecivedViewModel mReportRecivedViewModel;

    ActivityReportRecivedBinding mActivityReportRecivedBinding;
    private RecyclerView recyclerView1;
    private LinearLayoutManager layoutManager1;
    private OrganListAdapter mAdapter;
    private OrganizationUnit.Result organSelected;
    private String timeDateSelected = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            mActivityReportRecivedBinding = getViewDataBinding();
            mReportRecivedViewModel.setNavigator(this);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
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
        callOrganizationUnit();
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
                showSortDialog(organizationUnit);
            }


        } catch (Exception e) {
            CommonUtils.showSingleButtonAlert(ReportRecivedActivity.this, getString(R.string.text_attention), getString(R.string.data_incorrect), null, null);
            e.printStackTrace();
        }
    }

    private void showSortDialog(OrganizationUnit organizationUnit) {
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
                organSelected = organizationUnit.getResult().get(position);
                mActivityReportRecivedBinding.organization.setText(organSelected.getName());
            }
        });
    }

    @Override
    public void showTimeDate() {
        final Dialog dialogTime = new Dialog(ReportRecivedActivity.this);
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
            }
        });
    }
}
