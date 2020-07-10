package com.example.shoptest2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {



    private TextView userId;
    private TextView emailorphone;
    private TextView newPassword;
    private TextView back;
    private TextView ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

        userId=findViewById(R.id.userId);
        emailorphone=findViewById(R.id.emailorphone);
        newPassword=findViewById(R.id.newPassword);
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
