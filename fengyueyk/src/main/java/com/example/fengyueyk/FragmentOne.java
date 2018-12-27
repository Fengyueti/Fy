package com.example.fengyueyk;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class FragmentOne extends BaseFragment{

    private TabLayout tab;
    private ViewPager viewpager;
    private String[] names=new String[]{
            "正在热映","即将上映"
    };
    @Override
    protected void initView(View view) {
        tab =view.findViewById(R.id.tab);
        viewpager =view.findViewById(R.id.viewpager);
        viewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0:
                        return new left();
                        default:
                            return new FragmentTwo();
                }
            }

            @Override
            public int getCount() {
                return names.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return names[position];
            }
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
