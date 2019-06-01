package com.aiandroid.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.aiandroid.utils.BottomNavigationViewHelper;
import com.aiandroid.utils.FragmentFactory;
import com.example.rain.aiandroid.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        frameLayout = (FrameLayout) findViewById(R.id.fl_content);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        switchFragment(FragmentFactory.createFragment(0));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                switchFragment(FragmentFactory.createFragment(0));
                return true;
            case R.id.navigation_sys:
                switchFragment(FragmentFactory.createFragment(1));
                return true;
            case R.id.navigation_project:
                switchFragment(FragmentFactory.createFragment(2));
                return true;
            case R.id.navigation_collection:
                switchFragment(FragmentFactory.createFragment(3));
                return true;
        }
        return false;
    }

    public void switchFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_content, fragment);
        transaction.commit();
    }
}
