package com.aiandroid.contract;

import com.aiandroid.bean.SystemBean;
import com.aiandroid.bean.TreeContentBean;

import rx.Observable;

/**
 * Created by rain on 2018-7-9.
 */

public interface TreeContentContract {
    public interface TreeContentView extends BaseContract.BaseView{
        public void getData(String cid);
        public void showData(TreeContentBean treeContentBean);
        public void initView();
        public void hideProgress();
        public void showProgress();
    }

    public interface TreeContentModel extends BaseContract.BaseModel{
        public Observable<TreeContentBean> getTreeContent(String cid);
    }
    public abstract class TreeContentPresenter extends BaseContract.BasePresenter<TreeContentView,TreeContentModel>{
        public abstract void getTreeContent(String cid);
    }
}
