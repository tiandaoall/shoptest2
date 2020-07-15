package com.example.shoptest2;

import androidx.appcompat.app.AppCompatActivity;

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

public class ModifyPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userId;
    private EditText oldPassWord;
    private EditText newPassWord;
    private EditText confirmPassWord;
    private TextView back;
    private TextView ok;
    private Context context=this;
    private AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_password);
        init();

    }
    void init(){
        userId=findViewById(R.id.userId);
        oldPassWord=findViewById(R.id.oldPassWord);
        newPassWord=findViewById(R.id.newPassWord);
        confirmPassWord=findViewById(R.id.confirmPassWord);
        back=findViewById(R.id.back);
        ok=findViewById(R.id.ok);
        confirmPassWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().equals(newPassWord.getText().toString()))
                {
                    confirmPassWord.setError("密码不一致");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        newPassWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!RegexUtils.isPassword(charSequence))
                {
                    newPassWord.setError("至少包含数字跟字母的6-18位密码");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        back.setOnClickListener(this);
        ok.setOnClickListener(this);
    }
    public void changePassword(){
        final HttpHelper instance=HttpHelper.getInstance();
        HashMap<String,Object> user=new HashMap<String,Object>();
        user.put("oldPassWord",oldPassWord.getText().toString());
        user.put("newPassWord",newPassWord.getText().toString());
        user.put("userId",userId.getText().toString());
        instance.postDataAsyn(Constant.parentUrl + "changePassword", JSON.toJSONString(user), new HttpHelper.NetCall() {
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

                                builder = new AlertDialog.Builder(context).setIcon(R.mipmap.ic_launcher).setTitle("注册成功")
                                        .setMessage("您的账号为：" + userId + "         请牢记")
                                        .setIcon(R.drawable.email)
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent=new Intent(context,LoginActivity.class);
                                                instance.setToken("");
                                                startActivity(intent);

                                            }
                                        });
                                builder.create().show();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(context,"服务器请求错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void failed(Call call, IOException e) {
                runOnUiThread(new  Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(context,"服务器请求失败",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
    public Boolean isTem(){
        if(userId.getText().toString().equals("")||oldPassWord.getText().toString().equals("")||newPassWord.getText().toString().equals("")||confirmPassWord.getText().toString().equals(""))
        {
            return false;
        }else
            return true;
    }
    public Boolean checkForm(){

        Boolean isErrol=(userId.getError()==null)&&(oldPassWord.getError()==null)&&(confirmPassWord.getError()==null)&&(newPassWord.getError()==null);
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
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                break;
            case R.id.ok:
                if(checkForm())
                {
                    changePassword();
                }
                break;

        }

    }
}
