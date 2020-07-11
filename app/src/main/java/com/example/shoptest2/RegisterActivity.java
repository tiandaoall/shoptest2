package com.example.shoptest2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.shoptest2.entity.UserEntity;
import com.example.shoptest2.util.Constant;
import com.example.shoptest2.util.HttpHelper;
import com.example.shoptest2.util.RegexUtils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView register;
    private TextView back;
    private AlertDialog.Builder builder;
    private String userId="";
    private EditText userName,passWord,confirmPassWord,email,phone;
    private Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        init();
    }

    private void init(){
        userName=findViewById(R.id.userName);
        passWord=findViewById(R.id.passWord);
        confirmPassWord=findViewById(R.id.confirmPassWord);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        register=findViewById(R.id.register);
        register.setOnClickListener(this);
        back=findViewById(R.id.back);
        back.setOnClickListener(this);
        passWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!RegexUtils.isPassword(charSequence))
                {
                    passWord.setError("至少包含数字跟字母的6-18位密码");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        confirmPassWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().equals(passWord.getText().toString()))
                {
                    confirmPassWord.setError("密码不一致");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!RegexUtils.isEmail(charSequence))
                {
                    email.setError("邮箱格式不正确");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(!RegexUtils.isMobileExact(charSequence))
                    {
                        phone.setError("手机不正确");
                    }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.register:
                if(checkForm())
                {
                 registerFunction();
                }
                break;
            case R.id.back:
                Intent intent = new Intent(context,LoginActivity.class);
                startActivity(intent);

                break;
        }

    }
    public  void registerFunction(){

        HttpHelper instance=HttpHelper.getInstance();
        UserEntity user=new UserEntity();
        user.setUserName(userName.getText().toString());
        user.setPassWord(passWord.getText().toString());
        user.setEmail(email.getText().toString());
        user.setPhoneNumber(phone.getText().toString());
        instance.postDataAsyn(Constant.parentUrl + "register", JSON.toJSONString(user), new HttpHelper.NetCall() {
            @Override
            public void success(Call call, final Response response) throws IOException {
                runOnUiThread(new  Runnable(){

                    @Override
                    public void run() {
                        try {

                                 HashMap<String,Object> msg= JSONObject.parseObject(response.body().string(), new TypeReference<HashMap<String, Object>>(){});
                                userId=(String)msg.get("userId");
                                if((boolean) msg.get("flag")) {
                                    builder = new AlertDialog.Builder(context).setIcon(R.mipmap.ic_launcher).setTitle("注册成功")
                                            .setMessage("您的账号为：" + userId + "         请牢记")
                                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    Intent intent = new Intent();
                                                    Bundle bundle = new Bundle();
                                                    bundle.putString("userId", userId);
                                                    intent.putExtra("msg",bundle);
                                                    setResult(Constant.result_success,intent);
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
    public Boolean isTem(){
        if(userName.getText().toString().equals("")||passWord.getText().toString().equals("")||confirmPassWord.getText().toString().equals("")||email.getText().toString().equals("")||phone.getText().toString().equals(""))
        {
            return false;
        }else
            return true;
    }
    public Boolean checkForm(){

        Boolean isErrol=(userName.getError()==null)&&(passWord.getError()==null)&&(confirmPassWord.getError()==null)&&(email.getError()==null)&&(phone.getError()==null);
        if(isTem()&&isErrol) //检验表单
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
