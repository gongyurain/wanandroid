package com.aiandroid.api;

import com.aiandroid.bean.SystemBean;
import com.aiandroid.bean.TreeContentBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by rain on 2018-7-8.
 */

public interface SystemApi {
    @GET(HttpCommon.SYS_URL)
    Observable<SystemBean> getSystemTree();

    @GET(HttpCommon.Tree_Content_URL)
    Observable<TreeContentBean> getTreeContentBean(@Query("cid") String cid );
}
