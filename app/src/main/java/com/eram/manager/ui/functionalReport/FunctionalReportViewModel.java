package com.eram.manager.ui.functionalReport;

import androidx.lifecycle.MutableLiveData;

import com.eram.manager.api.RestManager;
import com.eram.manager.data.DataManager;
import com.eram.manager.data.model.api.CreditorAmount;
import com.eram.manager.data.model.api.OrganizationUnit;
import com.eram.manager.data.model.api.SumPrice;
import com.eram.manager.di.module.RxRetrofitErrorConsumer;
import com.eram.manager.ui.base.BaseViewModel;
import com.eram.manager.ui.stateReception.PoolReceptionLimitRequestBody;
import com.eram.manager.ui.stateReception.PoolReceptionStatusRequestBody;
import com.eram.manager.utils.AppBaseUrl;
import com.eram.manager.utils.AppConstants;
import com.eram.manager.utils.rx.SchedulersFacade;
import com.eram.manager.utils.rx.SingleLiveData;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class FunctionalReportViewModel extends BaseViewModel<FunctionalReportNavigator> implements AppConstants {
    public FunctionalReportViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        super(dataManager, mRestManager, mSchedulersFacade, mToastLiveData, mCompositeDisposable);
    }

    private final MutableLiveData<OrganizationUnit> organizationUnitResponseModelMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<CreditorAmount> creditorAmountTodayResponseModelMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<CreditorAmount> creditorAmountLimitResponseModelMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<SumPrice> sumPriceCreditorResponseModelMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<SumPrice> sumPriceCreditorTodayResponseModelMutableLiveData = new SingleLiveData<>();

    public MutableLiveData<OrganizationUnit> getOrganizationUnitResponseModelMutableLiveData() {
        return organizationUnitResponseModelMutableLiveData;
    }

    public MutableLiveData<CreditorAmount> getCreditorAmountTodayResponseModelMutableLiveData() {
        return creditorAmountTodayResponseModelMutableLiveData;
    }

    public MutableLiveData<CreditorAmount> getCreditorAmountLimitResponseModelMutableLiveData() {
        return creditorAmountLimitResponseModelMutableLiveData;
    }

    public MutableLiveData<SumPrice> getSumPriceCreditorResponseModelMutableLiveData() {
        return sumPriceCreditorResponseModelMutableLiveData;
    }

    public MutableLiveData<SumPrice> getSumPriceCreditorTodayResponseModelMutableLiveData() {
        return sumPriceCreditorTodayResponseModelMutableLiveData;
    }


    public void showOrganization() {
        getNavigator().showOrganization();
    }

    public void showTimeDate() {
        getNavigator().showTimeDate();
    }

    public void rightClick() {
        getNavigator().rightClick();
    }

    public void leftClick() {
        getNavigator().leftClick();
    }
    public void backClick() {
        getNavigator().backClick();
    }
    public void getOrganizatonUnit() {
        Disposable disposable = mRestManager.getOrganizatonUnit(AppBaseUrl.BASE_URL + AppBaseUrl.GET_ORGANIZATION_UNIT)
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    organizationUnitResponseModelMutableLiveData.setValue(r);
                    Timber.i("data getOrganizatonUnit : " + r.toString());
                    Timber.d("result response : " + r.isStatus());
                }, new RxRetrofitErrorConsumer() {
                    @Override
                    public void handleError(Throwable throwable, int id) {
                        mToastLiveData.postValue(id);
                        Timber.e("error in getOrganizatonUnit view model response : " + throwable.getMessage());
                    }
                });

        mCompositeDisposable.add(disposable);
    }

    public void callGetCreditorAmountToday(String organSelected) {
        Disposable disposable = mRestManager.getCreditorAmountToday(AppBaseUrl.BASE_URL + AppBaseUrl.GET_CREDITOR_AMOUNT_TODAY,
                new PoolReceptionStatusRequestBody(organSelected))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    creditorAmountTodayResponseModelMutableLiveData.setValue(r);
                    Timber.i("data getOrganizatonUnit : " + r.toString());
                    Timber.d("result response : " + r.isStatus());
                }, new RxRetrofitErrorConsumer() {
                    @Override
                    public void handleError(Throwable throwable, int id) {
                        mToastLiveData.postValue(id);
                        Timber.e("error in getOrganizatonUnit view model response : " + throwable.getMessage());
                    }
                });

        mCompositeDisposable.add(disposable);
    }

    public void callGetCreditorAmountLimit(String fromDate, String toDate, String organSelected) {
        Disposable disposable = mRestManager.getCreditorAmountLimit(AppBaseUrl.BASE_URL + AppBaseUrl.GET_CREDITOR_AMOUNT_LIMIT,
                new PoolReceptionLimitRequestBody(fromDate, toDate, organSelected))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    creditorAmountLimitResponseModelMutableLiveData.setValue(r);
                    Timber.i("data getOrganizatonUnit : " + r.toString());
                    Timber.d("result response : " + r.isStatus());
                }, new RxRetrofitErrorConsumer() {
                    @Override
                    public void handleError(Throwable throwable, int id) {
                        mToastLiveData.postValue(id);
                        Timber.e("error in getOrganizatonUnit view model response : " + throwable.getMessage());
                    }
                });

        mCompositeDisposable.add(disposable);
    }

    public void callGetSumPriceCreditor(String fromDate, String toDate, String organSelected) {
        Disposable disposable = mRestManager.getSumPriceCreditor(AppBaseUrl.BASE_URL + AppBaseUrl.GET_SUM_PRICE_CREDITOR, new PoolReceptionLimitRequestBody(fromDate, toDate, organSelected))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    sumPriceCreditorResponseModelMutableLiveData.setValue(r);
                    Timber.i("data getOrganizatonUnit : " + r.toString());
                    Timber.d("result response : " + r.isStatus());
                }, new RxRetrofitErrorConsumer() {
                    @Override
                    public void handleError(Throwable throwable, int id) {
                        mToastLiveData.postValue(id);
                        Timber.e("error in getOrganizatonUnit view model response : " + throwable.getMessage());
                    }
                });

        mCompositeDisposable.add(disposable);
    }

    public void callGetSumPriceCreditorToday(String organSelected) {
        Disposable disposable = mRestManager.getSumPriceCreditorToday(AppBaseUrl.BASE_URL + AppBaseUrl.GET_SUM_PRICE_CREDITOR_TODAY, new PoolReceptionStatusRequestBody(organSelected))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    sumPriceCreditorTodayResponseModelMutableLiveData.setValue(r);
                    Timber.i("data getOrganizatonUnit : " + r.toString());
                    Timber.d("result response : " + r.isStatus());
                }, new RxRetrofitErrorConsumer() {
                    @Override
                    public void handleError(Throwable throwable, int id) {
                        mToastLiveData.postValue(id);
                        Timber.e("error in getOrganizatonUnit view model response : " + throwable.getMessage());
                    }
                });

        mCompositeDisposable.add(disposable);
    }
}
