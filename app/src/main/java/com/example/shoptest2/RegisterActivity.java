package com.example.shoptest2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.shoptest2.entity.UserEntity;
import com.example.shoptest2.util.Constant;
import com.example.shoptest2.util.HttpHelper;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView register;
    private AlertDialog.Builder builder;
    private String userId="";
    private EditText userName,PassWord,confirmPassWord;
    private Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        register=findViewById(R.id.register);
        register.setOnClickListener(this);
    }

    private void init(){

    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.register:
                if(chekForm())
                {
                registerFunction();
                }
                break;
        }

    }
    public  void registerFunction(){
        HttpHelper instance=HttpHelper.getInstance();
        UserEntity user=new UserEntity();
        instance.postDataAsyn(Constant.parentUrl + "register", JSON.toJSONString(user), new HttpHelper.NetCall() {
            @Override
            public void success(Call call, final Response response) throws IOException {
                runOnUiThread(new  Runnable(){

                    @Override
                    public void run() {
                        try {
                            HashMap<String,Object> msg= JSONObject.parseObject(response.body().string(), new TypeReference<HashMap<String, Object>>(){});
                            userId=(String)msg.get("userId");
                            if((Boolean) msg.get("flag")) {
                                builder = new AlertDialog.Builder(context).setIcon(R.mipmap.ic_launcher).setTitle("注册成功")
                                        .setMessage("您的账号为：" + userId + "         请牢记")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent = new Intent();
                                                Bundle bundle = new Bundle();
                                                bundle.putString("userId", userId);
                                                setResult(Constant.result_success);
                                                finish();

                                            }
                                        });
                                builder.create().show();
                            }else
                            {
                                Toast.makeText(context,"注册失败", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            Toast.makeText(context,"服务器请求错误", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }

            @Override
            public void failed(Call call, IOException e) {
                runOnUiThread( new Runnable(){

                    @Override
                    public void run() {
                        Toast.makeText(context,"服务器请求错误", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    public Boolean chekForm(){

        if(true) //检验表单
        {
            return true;
        }
        else
        {
            Toast.makeText(context,"格式错误", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
