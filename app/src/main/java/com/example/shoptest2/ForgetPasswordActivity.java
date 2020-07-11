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
import com.example.shoptest2.util.Constant;
import com.example.shoptest2.util.HttpHelper;
import com.example.shoptest2.util.RegexUtils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {



    private EditText userId,emailorphone,newPassword,confirmPassWord;
    private TextView back;
    private TextView ok;
    private Context context=this;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);
        init();

    }
    private void init() {
        userId=findViewById(R.id.userId);
        emailorphone=findViewById(R.id.emailorphone);
        newPassword=findViewById(R.id.newPassword);
        confirmPassWord=findViewById(R.id.confirmPassWord);
        back=findViewById(R.id.back);
        ok=findViewById(R.id.ok);
        emailorphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if((!RegexUtils.isEmail(charSequence))&&(!RegexUtils.isMobileExact(charSequence)))
                {
                    emailorphone.setError("请输入正确的手机或邮箱");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        newPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!RegexUtils.isPassword(charSequence))
                {
                    newPassword.setError("至少包含数字跟字母的6-18位密码");
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
                if(!charSequence.toString().equals(newPassword.getText().toString())) {
                    confirmPassWord.setError("密码不一致");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        back.setOnClickListener(this);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.back:
                Intent intent = new Intent(context,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.ok:
                if(checkForm()) {
                    frogerPassword();
                }
                break;

        }


    }
    public void frogerPassword(){

        HttpHelper instance = HttpHelper.getInstance();
        HashMap<String, Object> user = new HashMap<>();
        user.put("check",emailorphone.getText().toString());
        user.put("newPassWord",newPassword.getText().toString());
        user.put("userId",userId.getText().toString());
        instance.postDataAsyn(Constant.parentUrl + "forgetPassword", JSON.toJSONString(user), new HttpHelper.NetCall() {
            @Override
            public void success(Call call, final Response response) throws IOException {

                runOnUiThread( new Runnable(){
                    @Override
                    public void run() {
                        HashMap<String,Object> msg= null;
                        try {
                            msg = JSONObject.parseObject(response.body().string(), new TypeReference<HashMap<String, Object>>(){});

                            if((boolean) msg.get("flag")) {
                                builder = new AlertDialog.Builder(context).setIcon(R.mipmap.ic_launcher).setTitle("修改密码成功")
                                        .setMessage((String)msg.get("msg"))
                                        .setIcon(R.drawable.email)
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent = new Intent();
                                                Bundle bundle = new Bundle();
                                                intent.putExtra("msg",bundle);
                                                setResult(Constant.result_success,intent);
                                                finish();
                                            }
                                        });
                                builder.create().show();
                            }else
                            {
                                builder = new AlertDialog.Builder(context).setIcon(R.mipmap.ic_launcher).setTitle("失败")
                                        .setMessage((String)msg.get("msg"))
                                        .setIcon(R.drawable.email)
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                            }
                                        });
                                builder.create().show();
                            }
                        }catch (Exception e) {
                            e.printStackTrace();
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
    public boolean isTem(){
        if(userId.getText().toString().equals("")||newPassword.getText().toString().equals("")||confirmPassWord.getText().toString().equals("")||emailorphone.getText().toString().equals(""))
        {
            return false;
        }else
            return true;
    }
    public boolean checkForm(){

        Boolean isErrol=(userId.getError()==null)&&(newPassword.getError()==null)&&(confirmPassWord.getError()==null)&&(emailorphone.getError()==null);
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
