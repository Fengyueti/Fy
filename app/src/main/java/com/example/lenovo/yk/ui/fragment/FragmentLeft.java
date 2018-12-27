package com.example.lenovo.yk.ui.fragment;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;

import com.example.lenovo.yk.R;
import com.example.lenovo.yk.ui.madapter.MAdapter;
import com.example.lenovo.yk.ui.model.NeUtil;
import com.example.lenovo.yk.ui.model.UserBean;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.xml.transform.Result;

public class FragmentLeft extends BaseFragment {

    private PullToRefreshListView lv;
    private MAdapter mAdapter;
    private int page;
    private int count=10;
    private String apiurl="http://172.17.8.100/movieApi/movie/v1/findHotMovieList?";
    @Override
    protected void initView(View view) {
        lv =view.findViewById(R.id.lv);//寻找控件
       //适配器
        mAdapter = new MAdapter(getActivity());
        lv.setAdapter(mAdapter);
        lv.setMode(PullToRefreshListView.Mode.BOTH);
        //监听事件
        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page=1;
                initData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page=1;
                initData();
            }
        });
        page=1;
        initData();
    }
//数据处理
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void initData() {
        //寻找方法
        new NeUtil().getRequest3(apiurl + "page=" + page+"&count="+count, UserBean.class, new NeUtil.NetWorkBack() {
            @Override
            public void onsuccess(Object o) {
                UserBean data= (UserBean) o;
                final List<UserBean.ResultBean> result = data.getResult();
                if(result==null){
                    //完成
                    lv.onRefreshComplete();
                    return;
                }
                if(page==1){
                    mAdapter.setList(result);
                }else{
                    //保留最初数据，并加载新数据
                    mAdapter.setadd(result);
                }
                lv.onRefreshComplete();
                page++;
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragmentleft;
    }
}
