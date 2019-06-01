package com.aiandroid.contract;

import com.aiandroid.bean.BannerBean;
import com.aiandroid.bean.HomeArticleBean;
import com.aiandroid.bean.SystemBean;

import rx.Observable;

/**
 * Created by rain on 2018-7-8.
 */

public interface SystemContract {
    public interface SystemView extends BaseContract.BaseView{
        public void getData();
        public void showData(SystemBean systemBean);
        public void initView();
        public void hideProgress();
        public void showProgress();
    }

    public interface SystemModel extends BaseContract.BaseModel{
        public Observable<SystemBean> getSystremTree();
    }
    public abstract class SystemPresenter extends BaseContract.BasePresenter<SystemContract.SystemView,SystemContract.SystemModel>{
        public abstract void getSystremTree();
    }
}
