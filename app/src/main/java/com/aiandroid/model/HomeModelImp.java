package com.aiandroid.model;

import android.util.Log;

import com.aiandroid.api.ApiManager;
import com.aiandroid.bean.BannerBean;
import com.aiandroid.bean.HomeArticleBean;
import com.aiandroid.contract.HomeContract;

import rx.Observable;

/**
 * Created by rain on 2018-7-7.
 */

public class HomeModelImp implements HomeContract.HomeModel {
    private static final String TAG = "homemodel";

    @Override
    public Observable<HomeArticleBean> getHomeArticle(String page) {
        Observable<HomeArticleBean> observable = ApiManager.getInstance().getHomeApi().getHomeArticle(page);
        if (observable != null) {
            Log.d(TAG, observable.toString());
        }
        return observable;
    }

    @Override
    public Observable<BannerBean> getBanner() {
        Observable<BannerBean> observable = ApiManager.getInstance().getHomeApi().getBanner();
        if (observable != null) {
            Log.d(TAG, observable.toString());
        }
        return observable;
    }
}
