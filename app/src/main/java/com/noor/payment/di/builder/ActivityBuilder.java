package com.noor.payment.di.builder;

import com.noor.payment.ui.docMember.DocMemberActivity;
import com.noor.payment.ui.docMember.DocMemberActivityModule;
import com.noor.payment.ui.kartableManager.KartableManagerActivity;
import com.noor.payment.ui.kartableManager.KartableManagerActivityModule;
import com.noor.payment.ui.docContractors.DocContractorsActivity;
import com.noor.payment.ui.docContractors.DocContractorsActivityModule;
import com.noor.payment.ui.login.LoginActivity;
import com.noor.payment.ui.login.LoginActivityModule;
import com.noor.payment.ui.main.MainActivity;
import com.noor.payment.ui.main.MainActivityModule;
import com.noor.payment.ui.splash.SplashActivity;
import com.noor.payment.ui.splash.SplashActivityModule;

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
    abstract DocContractorsActivity bindForgotPasswordActivity();

    @ContributesAndroidInjector(modules = KartableManagerActivityModule.class)
    abstract KartableManagerActivity bindKartableManagerActivity();

    @ContributesAndroidInjector(modules = DocMemberActivityModule.class)
    abstract DocMemberActivity bindActivationActivity();

 }

