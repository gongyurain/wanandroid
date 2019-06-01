package com.aiandroid.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rain on 2018-7-7.
 */

public class ApiManager {
    private static ApiManager apiManager;

    private ApiManager() {
    }

    public static ApiManager getInstance() {
        if (apiManager == null) {
            synchronized (ApiManager.class) {
                if (apiManager == null) {
                    apiManager = new ApiManager();
                }
            }
        }
        return apiManager;
    }

    public HomeApi getHomeApi() {
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).baseUrl(HttpCommon.HTTP_BASER_URL).build();
        HomeApi homeApi=retrofit.create(HomeApi.class);
        return homeApi;
    }

    public SystemApi getSystemApi(){
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).baseUrl(HttpCommon.HTTP_BASER_URL).build();
        SystemApi systemApi=retrofit.create(SystemApi.class);
        return systemApi;
    }
}
