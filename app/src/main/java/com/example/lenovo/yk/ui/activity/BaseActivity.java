package com.example.lenovo.yk.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(getLayoutId());
        initView();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initView();


}
