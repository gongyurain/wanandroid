package com.aiandroid.model;

import android.util.Log;

import com.aiandroid.api.ApiManager;
import com.aiandroid.bean.SystemBean;
import com.aiandroid.contract.SystemContract;

import rx.Observable;

/**
 * Created by rain on 2018-7-8.
 */

public class SystemModelImp implements SystemContract.SystemModel{
    private static final String TAG = "SystemModel";
    @Override
    public Observable<SystemBean> getSystremTree() {
        Observable<SystemBean> observable = ApiManager.getInstance().getSystemApi().getSystemTree();
        if (observable!=null){
            Log.d(TAG, observable.toString());
        }
        return observable;
    }
}
