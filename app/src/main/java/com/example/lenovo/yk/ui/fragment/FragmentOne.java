package com.example.lenovo.yk.ui.fragment;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.lenovo.yk.R;

public class FragmentOne extends BaseFragment{

    private TabLayout tab;
    private ViewPager viewpager;
    //定义数组
    private String[] names=new String[]{
            "正在热映","即将上映"
    };
    @Override
    protected void initView(View view) {
        tab =view.findViewById(R.id.tab);
        viewpager =view.findViewById(R.id.viewpager);//寻找控件
        //适配器
        viewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0:
                        return new FragmentLeft();//返回此页面
                    case 1:
                        return new FragmentRight();
                }
                return null;
            }

            @Override
            public int getCount() {
                return names.length;
            }//长度

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return names[position];
            }//下标
        });
        tab.setupWithViewPager(viewpager);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragmentone;
    }
}
