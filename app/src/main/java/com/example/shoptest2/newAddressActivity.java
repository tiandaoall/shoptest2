package com.example.shoptest2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.shoptest2.entity.AddresslistEntity;
import com.example.shoptest2.entity.OrdersEntity;
import com.example.shoptest2.util.Constant;
import com.example.shoptest2.util.HttpHelper;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class newAddressActivity extends AppCompatActivity implements View.OnClickListener {
    CityPickerView mPicker = new CityPickerView();
    private TextView receiverAddress;
    private EditText receiverName;
    private EditText receiverPhone;
    private EditText addressDetail;
    private Button settlement;
    String address="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_address);
        receiverAddress = findViewById(R.id.receiverAddress);
        receiverName=findViewById(R.id.receiverName);
        receiverPhone=findViewById(R.id.receiverPhone);
        addressDetail=findViewById(R.id.addressDetail);
        settlement=findViewById(R.id.settlement);
        mPicker.init(this);
        receiverAddress.setOnClickListener(this);
        settlement.setOnClickListener(this);
    }


    void addAddress(String receiverName,String receiverPhone,String receiverAddress){
        HttpHelper instance=HttpHelper.getInstance();
        HashMap<String,Object> receiver=new HashMap<String,Object>();
        receiver.put("receiverName",receiverName);
        receiver.put("receiverPhone",receiverPhone);
        receiver.put("receiverAddress",receiverAddress);
        receiver.put("userId", Constant.getUserId());
        instance.postDataAsyn(Constant.parentUrl + "insertAddress", JSON.toJSONString(receiver), new HttpHelper.NetCall() {

            @Override
            public void success(Call call, final Response response) throws IOException {


                runOnUiThread( new Runnable() {

                    @Override
                    public void run() {

                        HashMap<String,Object> msg= null;
                        try {
                            msg = JSONObject.parseObject(response.body().string(), new TypeReference<HashMap<String, Object>>(){});
                            String data=(String) msg.get("data").toString();
                            Log.d("TAG", "run: data"+data);
                            List<AddresslistEntity> AddresslistEntity = JSON.parseArray(data, AddresslistEntity.class);
                           // Toast.makeText(,data, Toast.LENGTH_SHORT).show();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }



                    }
                });


            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.receiverAddress:
                CityConfig cityConfig = new CityConfig.Builder().build();
                mPicker.setConfig(cityConfig);
                //监听选择点击事件及返回结果
                mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        //将选择的地址填入tv_address_set中
                        receiverAddress.setText(province.toString().trim() + city.toString().trim() + district.toString().trim());
                        address=province.toString().trim() + city.toString().trim() + district.toString().trim();
                    }

                    @Override
                    public void onCancel() {
                        ToastUtils.showLongToast(newAddressActivity.this, "已取消");
                    }
                });
                //显示
                mPicker.showCityPicker();

                break;
            case R.id.settlement:
                String receivername=receiverName.getText().toString();
                String receiverphone=receiverPhone.getText().toString();
                String Address=addressDetail.getText().toString()+address;
                addAddress(receivername,receiverphone,Address);
                break;
        }

    }


}
