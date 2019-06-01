package com.aiandroid.presenter;

import android.util.Log;

import com.aiandroid.bean.TreeContentBean;
import com.aiandroid.contract.SystemContract;
import com.aiandroid.contract.TreeContentContract;
import com.aiandroid.model.TreeContentModelImp;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by rain on 2018-7-9.
 */

public class TreeContentPresenterImp extends TreeContentContract.TreeContentPresenter{
    @Override
    protected TreeContentContract.TreeContentModel creatModel() {
        return new TreeContentModelImp();
    }

    @Override
    public void getTreeContent(String cid) {
        Log.d("getTreeContent",cid);
        Observable<TreeContentBean> observable=model.getTreeContent(cid);
        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<TreeContentBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(TreeContentBean treeContentBean) {
                view.showData(treeContentBean);
            }
        });
    }
}
