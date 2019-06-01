package com.aiandroid.presenter;

import com.aiandroid.bean.BannerBean;
import com.aiandroid.bean.SystemBean;
import com.aiandroid.contract.HomeContract;
import com.aiandroid.contract.SystemContract;
import com.aiandroid.model.SystemModelImp;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by rain on 2018-7-8.
 */

public class SystemPresenterImp extends SystemContract.SystemPresenter{
    @Override
    protected SystemContract.SystemModel creatModel() {
        return new SystemModelImp();
    }

    @Override
    public void getSystremTree() {
        view.showProgress();
        model.getSystremTree().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<SystemBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(SystemBean systemBean) {
                view.showData(systemBean);
            }
        });
    }
}
