package com.aiandroid.utils;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.aiandroid.view.fragment.CollectionFragment;
import com.aiandroid.view.fragment.HomeFragment;
import com.aiandroid.view.fragment.ProjectFragment;
import com.aiandroid.view.fragment.SysFragment;

import java.util.HashMap;

/**
 * Created by rain on 2018-7-7.
 */

public class FragmentFactory {
    public static final int HomeFragment=0;
    public static final int SysFragment=1;
    public static final int ProjectFragment=2;
    public static final int CollectionFragment=3;
    public static HashMap<Integer, Fragment> fragments = new HashMap<Integer, Fragment>();

    public static Fragment createFragment(int pos) { // 先从集合中取, 如果没有,才创建对象, 提高性能
        Fragment fragment = fragments.get(pos);

        if (fragment == null) {
            switch (pos) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new SysFragment();
                    break;
                case 2:
                    fragment = new ProjectFragment();
                    break;
                case 3:
                    fragment = new CollectionFragment();
                    break;
                default:
                    break;
            }
            fragments.put(pos, fragment);
        }
        Log.d("gongyu",fragment+"");
        return fragment;
    }
}