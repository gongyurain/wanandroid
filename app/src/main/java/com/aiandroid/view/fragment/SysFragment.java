package com.aiandroid.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiandroid.adapter.SystemAdapter;
import com.aiandroid.bean.SystemBean;
import com.aiandroid.contract.SystemContract;
import com.aiandroid.presenter.SystemPresenterImp;
import com.aiandroid.view.TreeContentActivity;
import com.ajguan.library.EasyRefreshLayout;
import com.example.rain.aiandroid.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rain on 2018-7-7.
 */

public class SysFragment extends Fragment implements SystemContract.SystemView,SystemAdapter.OnItemClickListener{
    private SystemContract.SystemPresenter systemPresenter;
    private EasyRefreshLayout easyRefreshLayout;
    private RecyclerView recyclerView;
    private View view;
    private List<SystemBean.DataBean> dataBeans;
    private SystemBean systemBean;
    private HashMap<Integer,List<String>> strings=new HashMap<>();
    public static final String CHILED_BEANS="data";
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_sys,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView();
        getData();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showError() {

    }

    @Override
    public void getData() {
        systemPresenter.getSystremTree();
    }

    @Override
    public void showData(SystemBean systemBean) {
        this.systemBean=systemBean;
        dataBeans=systemBean.getData();
        for (int i=0;i<dataBeans.size();i++){
            List<SystemBean.DataBean.ChildrenBean> childrenBeans=dataBeans.get(i).getChildren();
            List<String> str=new ArrayList<>();
            for (SystemBean.DataBean.ChildrenBean childrenBean:
                    childrenBeans) {
                str.add(childrenBean.getName());
            }
            strings.put(i,str);
        }

        SystemAdapter systemAdapter=new SystemAdapter(dataBeans,strings,getContext(),this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this.getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(systemAdapter);
        easyRefreshLayout.refreshComplete();
    }

    @Override
    public void initView() {
        systemPresenter=new SystemPresenterImp();
        systemPresenter.attachView(this);
        easyRefreshLayout=(EasyRefreshLayout)view.findViewById(R.id.sys_asylayout);
        recyclerView=(RecyclerView)view.findViewById(R.id.rv_sys);
        easyRefreshLayout.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                easyRefreshLayout.loadMoreComplete();
            }

            @Override
            public void onRefreshing() {
                getData();
            }
        });
        easyRefreshLayout.isEnableLoadMore();

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onDestroy() {
        systemPresenter.detchView();
        super.onDestroy();
    }

    @Override
    public void onItemClick(int position) {
        ArrayList<SystemBean.DataBean.ChildrenBean> children = (ArrayList<SystemBean.DataBean.ChildrenBean>) dataBeans.get(position).getChildren();
        Intent intent=new Intent(getActivity().getApplicationContext(), TreeContentActivity.class);
        Bundle bundle=new Bundle();
        bundle.putParcelableArrayList(CHILED_BEANS,children);
        bundle.putString("title",dataBeans.get(position).getName());
        intent.putExtra("SysFragment",bundle);
        startActivity(intent);

    }
}
