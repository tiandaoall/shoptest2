package com.example.shoptest2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WriteOrderActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView receiverAddress;
    private TextView receiverName;
    private TextView phoneNumber;
    private TextView orderPrice;
    private Button  back;
    private Button settlement;
    private ImageView selectAddress;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_order);
        receiverAddress=findViewById(R.id.receiverAddress);
        receiverName=findViewById(R.id.receiverName);
        phoneNumber=findViewById(R.id.phoneNumber);
        orderPrice=findViewById(R.id.orderPrice);
        back=findViewById(R.id.back);
        settlement=findViewById(R.id.settlement);
        back.setOnClickListener(this);
        settlement.setOnClickListener(this);


    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                break;
            case R.id.settlement:
                break;

        }

    }
}
