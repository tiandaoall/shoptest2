package com.example.shoptest2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView register;
    private AlertDialog.Builder builder;
    private TextView userId;
    private TextView passWord;
    private TextView confirmPassWord;
    private TextView back;
    private String a="2900";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        register=findViewById(R.id.register);
        userId=findViewById(R.id.userId);
        passWord=findViewById(R.id.passWord);
        register.setOnClickListener(this);
        back.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        builder = new AlertDialog.Builder(this).setIcon(R.mipmap.ic_launcher).setTitle("注册成功")
                .setMessage("您的账号为："+a+"         请牢记").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ToDo: 你想做的事情
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ToDo: 你想做的事情
                       // Toast.makeText(RegisterActivity.this, "关闭按钮", Toast.LENGTH_LONG).show();
                        dialogInterface.dismiss();
                    }
                });
        builder.create().show();

    }
}
