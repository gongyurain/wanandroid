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

import com.aiandroid.adapter.HomeArticleAdapter;
import com.aiandroid.adapter.TreeContentAdapter;
import com.aiandroid.bean.TreeContentBean;
import com.aiandroid.contract.TreeContentContract;
import com.aiandroid.presenter.TreeContentPresenterImp;
import com.example.rain.aiandroid.R;

/**
 * Created by rain on 2018-7-9.
 */

public class TreeContentFragment extends Fragment implements TreeContentContract.TreeContentView{
    private String cid;
    private View view;
    private RecyclerView recyclerView;
    private TreeContentPresenterImp treeContentPresenterImp;
    private TreeContentAdapter treeContentAdapter;

    public static final String CID = "cid";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {view=inflater.inflate(R.layout.fragment_tree_content,container,false);
        Bundle bundle=getArguments();
        cid=bundle.getString(CID);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView();
        getData(cid);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showError() {

    }

    @Override
    public void getData(String cid) {
        Log.d("cid",cid);
        treeContentPresenterImp.getTreeContent(cid);
    }

    @Override
    public void showData(TreeContentBean treeContentBean) {
        treeContentAdapter = new TreeContentAdapter(treeContentBean.getData().getDatas(),getActivity());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this.getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(treeContentAdapter);
    }

    @Override
    public void initView() {
        treeContentPresenterImp=new TreeContentPresenterImp();
        treeContentPresenterImp.attachView(this);
        recyclerView=(RecyclerView)view.findViewById(R.id.rv_treecontent);

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onDestroy() {
        treeContentPresenterImp.detchView();
        super.onDestroy();
    }
}
