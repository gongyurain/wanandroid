package com.aiandroid.api;

import com.aiandroid.bean.BannerBean;
import com.aiandroid.bean.HomeArticleBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by rain on 2018-7-7.
 */

public interface HomeApi {

    @GET(HttpCommon.HOME_ARTICLE_URL)
    Observable<HomeArticleBean> getHomeArticle(@Path("page") String page);

    @GET(HttpCommon.HOME_BANNER_URL)
    Observable<BannerBean> getBanner();
}
