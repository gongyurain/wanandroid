package com.aiandroid.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.aiandroid.view.fragment.TreeContentFragment;

import java.util.ArrayList;

/**
 * Created by rain on 2018-7-10.
 */

public class TreePageAdapter extends FragmentPagerAdapter {
    private ArrayList<TreeContentFragment> mFragments = new ArrayList<>();
    private final ArrayList<String> mTitles;

    public TreePageAdapter(FragmentManager fm, ArrayList<TreeContentFragment> mFragments, ArrayList<String> mTitles) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Log.d("getPageTitle", mTitles.get(position));
        return mTitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}
