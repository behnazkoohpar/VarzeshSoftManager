package com.eram.manager.ui.reportRecived;

import androidx.lifecycle.MutableLiveData;

import com.eram.manager.api.RestManager;
import com.eram.manager.data.DataManager;
import com.eram.manager.data.model.api.DebtorAmount;
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

public class ReportRecivedViewModel extends BaseViewModel<ReportRecivedNavigator> implements AppConstants {

    public ReportRecivedViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        super(dataManager, mRestManager, mSchedulersFacade, mToastLiveData, mCompositeDisposable);
    }

    private final MutableLiveData<OrganizationUnit> organizationUnitResponseModelMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<DebtorAmount> debtorAmountTodayResponseModelMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<DebtorAmount> debtorAmountLimitResponseModelMutableLiveData = new SingleLiveData<>();
    private final MutableLiveData<SumPrice> sumPriceReceiptResponseModelMutableLiveData = new SingleLiveData<>();

    public MutableLiveData<OrganizationUnit> getOrganizationUnitResponseModelMutableLiveData() {
        return organizationUnitResponseModelMutableLiveData;
    }

    public MutableLiveData<DebtorAmount> getDebtorAmountTodayResponseModelMutableLiveData() {
        return debtorAmountTodayResponseModelMutableLiveData;
    }

    public MutableLiveData<DebtorAmount> getDebtorAmountLimitResponseModelMutableLiveData() {
        return debtorAmountLimitResponseModelMutableLiveData;
    }

    public MutableLiveData<SumPrice> getSumPriceReceiptResponseModelMutableLiveData() {
        return sumPriceReceiptResponseModelMutableLiveData;
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

    public void callGetDebtorAmountToday(String organSelected) {
        Disposable disposable = mRestManager.getDebtorAmountToday(AppBaseUrl.BASE_URL + AppBaseUrl.GET_DEBTOR_AMOUNT_TODAY,
                new PoolReceptionStatusRequestBody(organSelected))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    debtorAmountTodayResponseModelMutableLiveData.setValue(r);
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

    public void callGetDebtorAmountLimit(String fromDate, String toDate, String organSelected) {
        Disposable disposable = mRestManager.getDebtorAmountLimit(AppBaseUrl.BASE_URL + AppBaseUrl.GET_DEBTOR_AMOUNT_LIMIT,
                new PoolReceptionLimitRequestBody(fromDate, toDate, organSelected))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    debtorAmountLimitResponseModelMutableLiveData.setValue(r);
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

    public void callGetSumPriceReceipt(String fromDate, String toDate, String organSelected) {
        Disposable disposable = mRestManager.getSumPriceReciept(AppBaseUrl.BASE_URL + AppBaseUrl.GET_SUM_PRICE_RECEIOT, new PoolReceptionLimitRequestBody(fromDate, toDate, organSelected))
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    sumPriceReceiptResponseModelMutableLiveData.setValue(r);
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
