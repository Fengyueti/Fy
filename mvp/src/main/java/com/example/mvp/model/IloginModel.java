package com.example.mvp.model;

import com.example.mvp.net.RequestCallback;

import java.util.HashMap;

public interface IloginModel {
    void login(HashMap<String,String> params,RequestCallback requestCallback);
}
