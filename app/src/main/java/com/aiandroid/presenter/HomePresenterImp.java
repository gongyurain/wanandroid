package com.aiandroid.presenter;

import android.util.Log;

import com.aiandroid.bean.BannerBean;
import com.aiandroid.bean.HomeArticleBean;
import com.aiandroid.contract.HomeContract;
import com.aiandroid.model.HomeModelImp;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by rain on 2018-7-7.
 */

public class HomePresenterImp extends HomeContract.HomePresenter {
    private static final String TAG="homepresenter";
    @Override
    protected HomeModelImp creatModel() {
        return new HomeModelImp();
    }

    @Override
    public void getHomeArticle(String page) {
        model.getHomeArticle(page).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<HomeArticleBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,e.toString());
            }

            @Override
            public void onNext(HomeArticleBean homeArticleBean) {
                view.showArticle(homeArticleBean);
            }
        });
    }

    @Override
    public void getBanner() {
        view.showProgress();
        model.getBanner().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<BannerBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BannerBean bannerBean) {
                view.showBanner(bannerBean);
            }
    });
    }
}
