package com.example.lenovo.yk.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lenovo.yk.R;

public class MainActivity extends AppCompatActivity {
private int ti=5;
private TextView time;
//handeler倒计时
private Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if(ti<=0){
            //跳转
            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
        }else{
            //设置
            time.setText(ti+"秒");
            ti--;
            handler.sendEmptyMessageDelayed(0,1000);
        }
    }
};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        time =findViewById(R.id.time);
        handler.sendEmptyMessageDelayed(0,1000);
    }
}
