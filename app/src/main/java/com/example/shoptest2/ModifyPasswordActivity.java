package com.example.shoptest2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ModifyPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView userId;
    private TextView oldPassWord;
    private TextView newPassWord;
    private TextView confirmPassWord;
    private TextView back;
    private TextView ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_password);
        userId=findViewById(R.id.userId);
        oldPassWord=findViewById(R.id.oldPassWord);
        newPassWord=findViewById(R.id.newPassWord);
        confirmPassWord=findViewById(R.id.confirmPassWord);
        back=findViewById(R.id.back);
        ok=findViewById(R.id.ok);
        back.setOnClickListener(this);
        ok.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                break;
            case R.id.ok:
                break;

        }

    }
}
