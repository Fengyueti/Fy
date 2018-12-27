package com.example.lenovo.yk.ui.fragment;

import android.view.View;
import android.widget.ListView;

import com.example.lenovo.yk.R;
import com.example.lenovo.yk.ui.madapter.MAdapter2;
import com.example.lenovo.yk.ui.model.NeUtil;
import com.example.lenovo.yk.ui.model.UserBean1;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

public class FragmentRight extends BaseFragment {

    private PullToRefreshListView lv;
    private MAdapter2 mAdapter2;
    private int page;
    private int count=10;
    private String apiurl=" http://172.17.8.100/movieApi/movie/v1/findReleaseMovieList?";
    @Override
    protected void initView(View view) {
        lv =view.findViewById(R.id.lvv);//找控件
        mAdapter2 = new MAdapter2(getActivity());
        lv.setAdapter(mAdapter2);
        //监听事件
        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page=1;
                initData();//调用方法
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                initData();
            }
        });
        page=1;
        initData();
    }

    @Override
    protected void initData() {
        //引用方法
        new NeUtil().getRequest3(apiurl + "page=" + page + "&count=" + count, UserBean1.class, new NeUtil.NetWorkBack() {
            @Override
            public void onsuccess(Object o) {
                UserBean1 data= (UserBean1) o;
                final List<UserBean1.ResultBean> result = data.getResult();
                if(result==null){
                    lv.onRefreshComplete();
                    return;
                }
                if(page==1){
                    //加载数据
                    mAdapter2.setList(result);
                }else{
                    mAdapter2.setadd(result);
                }
                //刷新完成
                lv.onRefreshComplete();
                page++;
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragmentright;
    }
}
