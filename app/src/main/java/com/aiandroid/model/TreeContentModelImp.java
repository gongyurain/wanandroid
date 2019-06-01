package com.aiandroid.model;

import com.aiandroid.api.ApiManager;
import com.aiandroid.bean.TreeContentBean;
import com.aiandroid.contract.TreeContentContract;

import rx.Observable;

/**
 * Created by rain on 2018-7-9.
 */

public class TreeContentModelImp implements TreeContentContract.TreeContentModel{
    @Override
    public Observable<TreeContentBean> getTreeContent(String cid) {
        return ApiManager.getInstance().getSystemApi().getTreeContentBean(cid);
    }
}
