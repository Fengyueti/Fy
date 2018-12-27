package com.example.rk2;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

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

public class MainActivity extends AppCompatActivity {

    private PullToRefreshListView lv;
    private MAdapter mAdapter;
    private int page;
    private int pscid=39;
    private String apiurl="http://120.27.23.105/product/getProducts?";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv =findViewById(R.id.lv);
        final ListView listView = lv.getRefreshableView();
        mAdapter = new MAdapter(this);
        listView.setAdapter(mAdapter);
        lv.setMode(PullToRefreshListView.Mode.BOTH);
        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page=1;
                initData();
            }


            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                initData();
            }
        });
        page=1;
        initData();
    }

    @SuppressLint("StaticFieldLeak")
    private void initData() {
        new AsyncTask<String,Void,List<UserBean.DataBean>>(){
            @Override
            protected List<UserBean.DataBean> doInBackground(String... strings) {
                try {
                    final URL url = new URL(strings[0]);
                    final HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setReadTimeout(5000);
                    urlConnection.setRequestMethod("GET");
                    final int responseCode = urlConnection.getResponseCode();
                    if(responseCode==200){
                         InputStream inputStream = urlConnection.getInputStream();
                         String result = stram(inputStream);
                        final UserBean userBean = new Gson().fromJson(result, UserBean.class);
                        final List<UserBean.DataBean> data = userBean.getData();
                        return data;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<UserBean.DataBean> dataBeans) {
                super.onPostExecute(dataBeans);
                if(dataBeans==null){
                    lv.onRefreshComplete();
                    return;
                }
                if(page==1) {
                    mAdapter.setList(dataBeans);
                }else{
                    mAdapter.setadd(dataBeans);
                }
                lv.onRefreshComplete();
                page++;
            }
        }.execute(apiurl+"pscid="+pscid+"&page="+page);

    }
    private String stram(InputStream inputStream) throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        for(String tem=bufferedReader.readLine();tem!=null;tem=bufferedReader.readLine()){
            stringBuilder.append(tem);
        }
        return stringBuilder.toString();
    }
}
