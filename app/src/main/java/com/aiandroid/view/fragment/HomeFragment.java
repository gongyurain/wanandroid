package com.aiandroid.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiandroid.adapter.HomeArticleAdapter;
import com.aiandroid.bean.BannerBean;
import com.aiandroid.bean.HomeArticleBean;
import com.aiandroid.contract.HomeContract;
import com.aiandroid.presenter.HomePresenterImp;
import com.aiandroid.utils.GlideImageLoader;
import com.aiandroid.view.ContentActivity;
import com.aiandroid.view.MainActivity;
import com.ajguan.library.EasyRefreshLayout;
import com.allure.lmrecycleadapter.loadmore.LMRecycleView;
import com.example.rain.aiandroid.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.yyydjk.library.BannerLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rain on 2018-7-7.
 */

public class HomeFragment extends Fragment implements HomeContract.HomeView {
    private static final String TAG="HomeFragment";
    private HomeContract.HomePresenter homePresenter;
    private Banner banner;
    private EasyRefreshLayout easyRefreshLayout;
    private RecyclerView recyclerView;
    private ArrayList<String> bannerUrls = new ArrayList<>();
    private View view;
    private int page = 0;
    private boolean isFirst=false;
    private List<HomeArticleBean.DataBean> articleDataBeans;
    HomeArticleAdapter homeArticleAdapter;
    List<HomeArticleBean.DataBean.DatasBean> datasBeans =new ArrayList<>();
    List<BannerBean.DataBean> bannerBeans=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView();
        Log.d(TAG,"getdata");
        getData();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onDestroy() {
        homePresenter.detchView();
        super.onDestroy();
    }

    @Override
    public void getData() {
        homePresenter.getBanner();
        homePresenter.getHomeArticle(page + "");
    }

    @Override
    public void showArticle(HomeArticleBean homeArticleBean) {
        if(page==0){
            datasBeans.clear();
            datasBeans.addAll(homeArticleBean.getData().getDatas());
            homeArticleAdapter = new HomeArticleAdapter(datasBeans,getActivity());
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this.getActivity(),LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(homeArticleAdapter);
        }else {
            datasBeans.addAll(homeArticleBean.getData().getDatas());
            homeArticleAdapter.moreData(datasBeans);
            easyRefreshLayout.loadMoreComplete();
        }
        easyRefreshLayout.refreshComplete();
    }

    @Override
    public void showBanner(BannerBean bannerBean) {
        if(!isFirst){
            bannerBeans=bannerBean.getData();
            List<BannerBean.DataBean> data = bannerBean.getData();
            for (BannerBean.DataBean dataBean :
                    data) {
                bannerUrls.add(dataBean.getImagePath());
            }
            isFirst=true;
        }

        banner.setImages(bannerUrls).setImageLoader(new GlideImageLoader()).setBannerAnimation(Transformer.Default)
                .setDelayTime(3000).isAutoPlay(true).setIndicatorGravity(BannerConfig.CENTER).start();

    }

    @Override
    public void getMoreData(String page) {
        homePresenter.getHomeArticle(page);
    }

    @Override
    public void initView() {
        homePresenter = new HomePresenterImp();
        homePresenter.attachView(this);
        banner = (Banner) view.findViewById(R.id.banner);
        easyRefreshLayout = (EasyRefreshLayout) view.findViewById(R.id.easylayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_article);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent=new Intent(HomeFragment.this.getActivity(), ContentActivity.class);
                intent.putExtra("url",bannerBeans.get(position).getUrl());
                startActivity(intent);
            }
        });
        easyRefreshLayout.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {

                page=page+1;
                Log.d(TAG,"ONloadMore"+page);
                getMoreData(page+"");
            }

            @Override
            public void onRefreshing() {
                page=0;
                isFirst=false;
                homePresenter.getHomeArticle(page+"");
                homePresenter.getBanner();
                easyRefreshLayout.refreshComplete();
            }
        });
    }

    @Override
    public void hideProgress() {

    }
}
