package com.example.lenovo.yk.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.example.lenovo.yk.R;
import com.example.lenovo.yk.ui.activity.BaseActivity;
import com.example.lenovo.yk.ui.fragment.FragmentOne;
import com.example.lenovo.yk.ui.fragment.FragmentTwo;

public class Main2Activity extends BaseActivity {

    private ViewPager viewpager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                   viewpager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    viewpager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    viewpager.setCurrentItem(2);
                    return true;
                case R.id.navigation_notification:
                    viewpager.setCurrentItem(3);
                    return true;
                case R.id.navigation_notificatio:
                    viewpager.setCurrentItem(4);
                    return true;
            }
            return false;
        }
    };
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);//寻找控件
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0:
                        return new FragmentTwo();//返回此页面
                    case 1:
                        return new FragmentOne();
                    case 2:
                        return new FragmentTwo();
                    case 3:
                        return new FragmentTwo();
                    case 4:
                        return new FragmentTwo();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 5;
            }//个数
        });
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        navigation.setSelectedItemId(R.id.navigation_home);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_dashboard);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.navigation_notifications);
                        break;
                    case 3:
                        navigation.setSelectedItemId(R.id.navigation_notification);
                        break;
                    case 4:
                        navigation.setSelectedItemId(R.id.navigation_notificatio);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

}
