package com.aiandroid.contract;

import com.aiandroid.bean.BannerBean;
import com.aiandroid.bean.HomeArticleBean;

import rx.Observable;


/**
 * Created by rain on 2018-7-7.
 */

public interface HomeContract {
    public interface HomeView extends BaseContract.BaseView{
        public void getData();
        public void showArticle(HomeArticleBean homeArticleBean);
        public void showBanner(BannerBean bannerBean);
        public void getMoreData(String page);
        public void initView();
        public void hideProgress();
    }

    public interface HomeModel extends BaseContract.BaseModel{
        public Observable<HomeArticleBean> getHomeArticle(String page);
        public Observable<BannerBean> getBanner();
    }
    public abstract class HomePresenter extends BaseContract.BasePresenter<HomeView,HomeModel>{
        public abstract void getHomeArticle(String page);
        public abstract void getBanner();
    }
}
