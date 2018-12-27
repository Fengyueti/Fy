package com.example.rk1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    private EditText phone;
    private EditText pwd;
    private Button resign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        phone =findViewById(R.id.ed_phone);
        pwd =findViewById(R.id.ed_pwd);
        resign =findViewById(R.id.resign);
        resign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                String phone1 = phone.getText().toString();
                String pwd1 = pwd.getText().toString();
                intent.putExtra("phone1",phone1);
                intent.putExtra("pwd1",pwd1);
                startActivity(intent);
            }
        });
    }
}
