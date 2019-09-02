package com.eram.manager.ui.main;

import androidx.lifecycle.MutableLiveData;

import com.eram.manager.api.RestManager;
import com.eram.manager.data.DataManager;
import com.eram.manager.data.model.api.AllReport;
import com.eram.manager.di.module.RxRetrofitErrorConsumer;
import com.eram.manager.ui.base.BaseViewModel;
import com.eram.manager.utils.AppBaseUrl;
import com.eram.manager.utils.AppConstants;
import com.eram.manager.utils.rx.SchedulersFacade;
import com.eram.manager.utils.rx.SingleLiveData;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class MainViewModel extends BaseViewModel<MainNavigator> implements AppConstants {

    public MainViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        super(dataManager, mRestManager, mSchedulersFacade, mToastLiveData, mCompositeDisposable);
    }
    private final MutableLiveData<AllReport> allReportResponseModelMutableLiveData = new SingleLiveData<>();

    public MutableLiveData<AllReport> getAllReportResponseModelMutableLiveData() {
        return allReportResponseModelMutableLiveData;
    }

    public void statePazireshClick(){
getNavigator().statePazireshClick();
    }
    public void reciveClick(){
        getNavigator().reciveClick();
    }
    public void functionalityClick(){
        getNavigator().functionalityClick();
    }
    public void incomeClick(){
        getNavigator().incomeClick();
    }
    public void openMenu() {
        getNavigator().openMenu();
    }

    public void getAllReport() {
        Disposable disposable = mRestManager.allReport(AppBaseUrl.BASE_URL+AppBaseUrl.GET_ALL_REPORT)
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.ui())
                .subscribe(r -> {
                    allReportResponseModelMutableLiveData.setValue(r);
                    Timber.i("data AllReport : " + r.toString());
                    Timber.d("result response : " + r.isStatus());
                }, new RxRetrofitErrorConsumer() {
                    @Override
                    public void handleError(Throwable throwable, int id) {
                        mToastLiveData.postValue(id);
                        Timber.e("error in AllReport view model response : " + throwable.getMessage());
                    }
                });

        mCompositeDisposable.add(disposable);
    }
}
