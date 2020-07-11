package com.example.shoptest2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
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

public class LoginActivity  extends AppCompatActivity  implements View.OnClickListener {
    private EditText userId,passWord;
    private TextView forgetPassword,login,register;

    private final Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        init();
    }
    private void init(){
        userId=findViewById(R.id.userId);
        passWord=findViewById(R.id.passWord);

        forgetPassword=findViewById(R.id.forgetPassword);
        login=findViewById(R.id.login);
        register=findViewById(R.id.register);
     //   userId.setOnClickListener(this);
      //  passWord.setOnClickListener(this);
        forgetPassword.setOnClickListener(this);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {

            case  R.id.forgetPassword:

                break;
            case R.id.login:
                final HttpHelper instance=HttpHelper.getInstance();
                UserEntity user=new UserEntity();
                user.setUserId(userId.getText().toString());
                user.setPassWord(passWord.getText().toString());
                instance.postDataAsyn(Constant.parentUrl+"login", JSON.toJSONString(user), new HttpHelper.NetCall(){


                    @Override
                    public void success(Call call, final Response response) throws IOException {

                        runOnUiThread(new  Runnable() {
                            @Override
                            public void run() {
                                try {
                                    HashMap<String,Object> msg= JSONObject.parseObject(response.body().string(), new TypeReference<HashMap<String, Object>>(){});
                                   if(!(Boolean) msg.get("flag")) {
                                       Toast.makeText(context,(String) msg.get("msg"), Toast.LENGTH_SHORT).show();
                                   }else
                                   {
                                       Intent intent=new Intent(context,MainActivity.class);
                                       instance.setToken((String)msg.get("token"));
                                       startActivity(intent);
                                   }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(context,"服务器请求错误", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void failed(Call call, final IOException e) {

                        runOnUiThread(new  Runnable() {

                            @Override
                            public void run() {
                                Toast.makeText(context,"服务器请求失败",Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                });
                break;
            case  R.id.register:
                Intent intent =new Intent(context,RegisterActivity.class);
                startActivityForResult(intent,Constant.registerCode);
                break;
        }

    }
    @Override
    protected  void  onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case Constant.registerCode:
                if(resultCode==Constant.result_success)
                {
                    Bundle bundle=data.getBundleExtra("msg");
                    userId.setText(bundle.getString("userId"));
                }
                if(resultCode==Constant.result_failed)
                {
                    Toast.makeText(context,"注册失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case Constant.forgetPasswordCode:
                if(requestCode==Constant.result_success)
                {

                }else
                {
                    Toast.makeText(context,"注册失败", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
