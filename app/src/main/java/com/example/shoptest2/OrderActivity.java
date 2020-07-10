package com.example.shoptest2;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView goodsImg;
    private CheckBox select;
    private TextView orderId;
    private TextView goodsName;
    private TextView buyNumber;
    private TextView totalPrice;
    private TextView gotobuy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        orderId=findViewById(R.id.orderId);
        goodsName=findViewById(R.id.goodsName);
        buyNumber=findViewById(R.id.buyNumber);
        totalPrice=findViewById(R.id.totalPrice);
        gotobuy=findViewById(R.id.gotobuy);
        select=findViewById(R.id.select);
        goodsImg=findViewById(R.id.goodsImg);
        gotobuy.setOnClickListener(this);



    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.gotobuy:
                break;
            case R.id.select:
                break;

        }


    }
}
