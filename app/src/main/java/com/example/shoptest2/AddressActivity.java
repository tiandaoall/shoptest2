package com.example.shoptest2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;

public class AddressActivity extends AppCompatActivity implements View.OnClickListener {
    CityPickerView mPicker = new CityPickerView();
    private TextView receiverAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address);
        receiverAddress = findViewById(R.id.receiverAddress);

        mPicker.init(this);
        receiverAddress.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        CityConfig cityConfig = new CityConfig.Builder().build();

        mPicker.setConfig(cityConfig);

        //监听选择点击事件及返回结果
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                //将选择的地址填入tv_address_set中
                receiverAddress.setText(province.toString().trim() + city.toString().trim() + district.toString().trim());

            }

            @Override
            public void onCancel() {
                ToastUtils.showLongToast(AddressActivity.this, "已取消");
            }
        });

        //显示


        mPicker.showCityPicker();
    }
}
