package com.example.shoptest2;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.example.shoptest2.entity.AddresslistEntity;
import com.example.shoptest2.util.Constant;
import com.example.shoptest2.util.HttpHelper;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

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
    void buy(){
        HttpHelper instance=HttpHelper.getInstance();
        AddresslistEntity address1=new AddresslistEntity();
        HashMap<String,Object> address =new HashMap<String,Object> ();
        address.put("33",33);
        instance.postDataAsyn(Constant.parentUrl + "modifyAddress", JSON.toJSONString(address), new HttpHelper.NetCall() {
            @Override
            public void success(Call call, Response response) throws IOException {

            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });


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
