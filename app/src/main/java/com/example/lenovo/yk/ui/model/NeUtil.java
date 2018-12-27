package com.example.lenovo.yk.ui.model;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NeUtil {
    public interface NetWorkBack<T>{
        void onsuccess(T t);
    }
    @SuppressLint("StaticFieldLeak")
    public void getRequest3(String apiurl, final Class clazz, final NetWorkBack callback){
        new AsyncTask<String,Void,Object>(){

            @Override
            protected Object doInBackground(String... strings) {
                final Object request2 = getRequest2(strings[0], clazz);
                return request2;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                callback.onsuccess(o);
            }
        }.execute(apiurl);
    }
    public <T> T getRequest2(String apiurl,Class clazz){
         String request = getRequest(apiurl);
        T t = (T) new Gson().fromJson(request, clazz);
        return t;
    }
    public String getRequest(String apiurl){
        String result="";
        try {
             URL url = new URL(apiurl);
             HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
             urlConnection.setRequestMethod("GET");
             urlConnection.setConnectTimeout(5000);
             urlConnection.setReadTimeout(5000);
             int responseCode = urlConnection.getResponseCode();
             if(responseCode==200){
                 InputStream inputStream = urlConnection.getInputStream();
                 result = stream(inputStream);
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String stream(InputStream inputStream) throws IOException {
         StringBuilder stringBuilder = new StringBuilder();
         InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
         BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
         for (String temp=bufferedReader.readLine();temp!=null;temp=bufferedReader.readLine() ){
             stringBuilder.append(temp);
         }
         return stringBuilder.toString();
    }
}
