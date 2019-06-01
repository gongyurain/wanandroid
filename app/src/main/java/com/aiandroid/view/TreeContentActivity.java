package com.aiandroid.view;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.aiandroid.adapter.TreePageAdapter;
import com.aiandroid.bean.SystemBean;
import com.aiandroid.view.fragment.SysFragment;
import com.aiandroid.view.fragment.TreeContentFragment;
import com.example.rain.aiandroid.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;

/**
 * Created by rain on 2018-7-9.
 */

public class TreeContentActivity extends AppCompatActivity{
    private CoordinatorTabLayout coordinatorTabLayout;
    private ViewPager viewPager;
    private List<SystemBean.DataBean.ChildrenBean> childrenBeans;
    private String title;
    private ArrayList<String> titles;
    private ArrayList<TreeContentFragment> treeContentFragments;
    private int[]  images;
    private String[] mTitles;
    private int[] mImageArray= new int[]{
            R.mipmap.bg_android,
            R.mipmap.bg_ios,
            R.mipmap.bg_js,
            R.mipmap.bg_other};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_content);
        initView();
        initData();

    }

    private void initData() {
        Intent intent = getIntent();
        Bundle extras = intent.getBundleExtra("SysFragment");
        childrenBeans= extras.getParcelableArrayList(SysFragment.CHILED_BEANS);
        title=extras.getString("title");

        initFragments();
        initImage();
        initViewPager();
        coordinatorTabLayout.setTitle(title);
        coordinatorTabLayout.setTranslucentStatusBar(this);
        coordinatorTabLayout.setBackEnable(true);
        coordinatorTabLayout.setImageArray(images);
        coordinatorTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        coordinatorTabLayout.setupWithViewPager(viewPager);
    }

    private void initImage() {
        Random random=new Random();
        images=new int[childrenBeans.size()+1];
        for (int i=0;i<childrenBeans.size();i++){
            images[i]=mImageArray[random.nextInt(4)];
        }
    }

    private void initViewPager() {
        viewPager.setOffscreenPageLimit(4);
        Log.d("title",titles.toString());
        viewPager.setAdapter(new TreePageAdapter(getSupportFragmentManager(), treeContentFragments, titles));
    }

    private void initFragments() {
        treeContentFragments=new ArrayList<>();
        titles=new ArrayList<>();
        for (SystemBean.DataBean.ChildrenBean childrenBean:
                childrenBeans
             ) {
            TreeContentFragment treeContentFragment = new TreeContentFragment();
            Bundle bundle = new Bundle();
            Log.d("gongyu",childrenBean.getId()+"");
            bundle.putString(TreeContentFragment.CID, childrenBean.getId()+"");
            treeContentFragment.setArguments(bundle);
            treeContentFragments.add(treeContentFragment);
            titles.add(childrenBean.getName());

        }
    }


    public void initView() {
        coordinatorTabLayout=(CoordinatorTabLayout)findViewById(R.id.coordinatortablayout);
        viewPager=(ViewPager)findViewById(R.id.vp_treeconntent);
    }

}
