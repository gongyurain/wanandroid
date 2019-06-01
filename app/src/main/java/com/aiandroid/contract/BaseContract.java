package com.aiandroid.contract;

/**
 * Created by rain on 2018-7-7.
 */

public interface BaseContract {
    interface BaseView{
        void showError();
        void showProgress();
    }

    interface BaseModel{}

    abstract class BasePresenter <V extends BaseView,M extends BaseModel>{
        public V view;
        public M model;

        public BasePresenter(){
            this.model=creatModel();
        }
        public void attachView(V view){
            this.view=view;
        }

        public void detchView(){
            view=null;
        }

        protected abstract M creatModel();
    }
}
