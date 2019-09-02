package com.eram.manager.ui.stateReception;

import androidx.lifecycle.MutableLiveData;

import com.eram.manager.api.RestManager;
import com.eram.manager.data.DataManager;
import com.eram.manager.data.model.api.OrganizationUnit;
import com.eram.manager.di.module.RxRetrofitErrorConsumer;
import com.eram.manager.ui.base.BaseViewModel;
import com.eram.manager.utils.AppBaseUrl;
import com.eram.manager.utils.AppConstants;
import com.eram.manager.utils.rx.SchedulersFacade;
import com.eram.manager.utils.rx.SingleLiveData;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class StateReceptionViewModel extends BaseViewModel<StateReceptionNavigator> implements AppConstants {
    public StateReceptionViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        super(dataManager, mRestManager, mSchedulersFacade, mToastLiveData, mCompositeDisposable);
    }

    private final MutableLiveData<OrganizationUnit> organizationUnitResponseModelMutableLiveData = new SingleLiveData<>();

    public MutableLiveData<OrganizationUnit> getOrganizationUnitResponseModelMutableLiveData() {
        return organizationUnitResponseModelMutableLiveData;
    }

    public void showOrganization() {
        getNavigator().showOrganization();
    }

    public void showTimeDate() {
        getNavigator().showTimeDate();
    }

    public void rightClick() {

    }

    public void leftClick() {

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
}
