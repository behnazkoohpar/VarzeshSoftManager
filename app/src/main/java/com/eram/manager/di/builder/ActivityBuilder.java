package com.eram.manager.di.builder;

import com.eram.manager.ui.docMember.DocMemberActivity;
import com.eram.manager.ui.docMember.DocMemberActivityModule;
import com.eram.manager.ui.functionalReport.FunctionalReportActivity;
import com.eram.manager.ui.functionalReport.FunctionalReportActivityModule;
import com.eram.manager.ui.kartableManager.KartableManagerActivity;
import com.eram.manager.ui.kartableManager.KartableManagerActivityModule;
import com.eram.manager.ui.docContractors.DocContractorsActivity;
import com.eram.manager.ui.docContractors.DocContractorsActivityModule;
import com.eram.manager.ui.login.LoginActivity;
import com.eram.manager.ui.login.LoginActivityModule;
import com.eram.manager.ui.main.MainActivity;
import com.eram.manager.ui.main.MainActivityModule;
import com.eram.manager.ui.reportRecived.ReportRecivedActivity;
import com.eram.manager.ui.reportRecived.ReportRecivedActivityModule;
import com.eram.manager.ui.splash.SplashActivity;
import com.eram.manager.ui.splash.SplashActivityModule;
import com.eram.manager.ui.stateReception.StateReceptionActivity;
import com.eram.manager.ui.stateReception.StateReceptionActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = DocContractorsActivityModule.class)
    abstract DocContractorsActivity bindDocContractorsActivity();

    @ContributesAndroidInjector(modules = KartableManagerActivityModule.class)
    abstract KartableManagerActivity bindKartableManagerActivity();

    @ContributesAndroidInjector(modules = DocMemberActivityModule.class)
    abstract DocMemberActivity bindActivationActivity();

    @ContributesAndroidInjector(modules = StateReceptionActivityModule.class)
    abstract StateReceptionActivity bindStateReceptionActivity();

    @ContributesAndroidInjector(modules = FunctionalReportActivityModule.class)
    abstract FunctionalReportActivity bindFunctionalReportActivity();

    @ContributesAndroidInjector(modules = ReportRecivedActivityModule.class)
    abstract ReportRecivedActivity bindReportRecivedActivity();

 }

