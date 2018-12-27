package com.example.mvp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mvp.R;
import com.example.mvp.presenter.LoginPresenter;

public class MainActivity extends AppCompatActivity {
    private Button btn_login;
    private Button btn_resign;
    private EditText phone;
    private EditText pwd;
    private LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_login=findViewById(R.id.btn_login);
        btn_resign=findViewById(R.id.btn_resign);
        phone =findViewById(R.id.phone);
        pwd =findViewById(R.id.pwd);
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loginPresenter = new LoginPresenter(this);
            }
        });
        btn_resign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
    }
}
