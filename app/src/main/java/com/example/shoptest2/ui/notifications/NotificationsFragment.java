package com.example.shoptest2.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.shoptest2.LoginActivity;
import com.example.shoptest2.R;
import com.example.shoptest2.adapter.MineAdapter;
import com.example.shoptest2.entity.OrdersEntity;
import com.example.shoptest2.util.Constant;
import com.example.shoptest2.util.HttpHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

import static android.content.ContentValues.TAG;
import static com.example.shoptest2.util.Constant.getUserName;

public class NotificationsFragment extends Fragment implements View.OnClickListener{

    private NotificationsViewModel notificationsViewModel;

    private ImageView waitPay;//待付款
    private ImageView paid;//待发货
    private ImageView delivered;//待收货
    private ImageView received;//已收货
    private ImageView newAddress;
    private MineAdapter mineAdapter;
    private TextView userName;
    private TextView a;//待付款字体
    private TextView b;//待发货字体
    private TextView c;//待收货字体
    private TextView d;//已收货字体
    private RecyclerView orderRecyclerView;
    private CheckBox select;
    private List<OrdersEntity> list=new ArrayList<OrdersEntity>();
  //  private final Context context=this;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("TAG","onCreate");
        super.onCreate(savedInstanceState);

    }



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mine, container, false);
        orderRecyclerView=(RecyclerView) root.findViewById(R.id.orderRecyclerView);
        waitPay=root.findViewById(R.id.waitPay);
        paid=root.findViewById(R.id.paid);
        delivered=root.findViewById(R.id.delivered);
        received=root.findViewById(R.id.received);
        userName=root.findViewById(R.id.userName);
        select=root.findViewById(R.id.select);
        newAddress=root.findViewById(R.id.newAddress);
        a=root.findViewById(R.id.a);//待付款字体
        b=root.findViewById(R.id.b);//待发货字体
        c=root.findViewById(R.id.c);//待收货字体
        d=root.findViewById(R.id.d);//已收货字体
        String username=Constant.getUserName();
        userName.setText(username);

        waitPay.setOnClickListener(this);
        paid.setOnClickListener(this);
        delivered.setOnClickListener(this);
        received.setOnClickListener(this);
        newAddress.setOnClickListener(this);




        return root;
    }

    private void initAdapter(List<OrdersEntity> orderEntity){
        mineAdapter=new MineAdapter(R.layout.order_item,orderEntity);
        mineAdapter.openLoadAnimation();
        mineAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getContext(), "click 整个布局", Toast.LENGTH_SHORT).show();

                //跳转到单个页面详细
            }
        });
        mineAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {

            @Override
            public void onItemChildClick(final BaseQuickAdapter adapter, View view,  final int position) {
                if(view.getId()==R.id.btn_buy){
                    new Runnable(){

                        @Override
                        public void run() {
                            deleteOrder(list.get(position).getOrderId(),4);
                            list.remove(position);
                            adapter.setNewData(list);
                        }
                    }.run();

                }else{

                }


            }
        });

        orderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        orderRecyclerView.setAdapter(mineAdapter);
    }


    void deleteOrder(String orderId,int orderState){

        HttpHelper instance=HttpHelper.getInstance();
        HashMap<String,Object> order=new HashMap<String,Object>();
        OrdersEntity ordersEntity=new OrdersEntity();
        ordersEntity.setOrderId(orderId);
         ordersEntity.setOrderState(orderState);
        instance.postDataAsyn(Constant.parentUrl + "modifyOrder", JSON.toJSONString(ordersEntity), new HttpHelper.NetCall() {

            @Override
            public void success(Call call, final Response response) throws IOException {

                getActivity().runOnUiThread( new Runnable() {

                    @Override
                    public void run() {

                        HashMap<String,Object> msg= null;
                        try {

                           //msg = JSONObject.parseObject(response.body().string(), new TypeReference<HashMap<String, Object>>(){});
                           // String data=(String) msg.get("msg").toString();
                           // Log.d("TAG", "run: data"+data);
                            Toast.makeText(getContext(),response.body().string()+"LL", Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(),e.toString(), Toast.LENGTH_SHORT).show();
                        }



                    }
                });
            }

            @Override
            public void failed(Call call, IOException e) {
                getActivity().runOnUiThread( new Runnable(){

                    @Override
                    public void run() {
                        Toast.makeText(getContext(),"服务器请求错误", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
    void selectOrder(int state){
        final HashMap<String,Object> order=new HashMap<String,Object>();
        order.put("orderState",state);
        order.put("userId",Constant.getUserId());
        new Runnable() {
            @Override
            public void run() {
                HttpHelper instance=HttpHelper.getInstance();

                instance.postDataAsyn(Constant.parentUrl + "queryOrderList", JSON.toJSONString(order), new HttpHelper.NetCall() {
                    @Override
                    public void success(Call call,final Response response) throws IOException {

                        getActivity().runOnUiThread( new Runnable() {

                            @Override
                            public void run() {

                                HashMap<String,Object> msg= null;
                                try {
                                    msg = JSONObject.parseObject(response.body().string(), new TypeReference<HashMap<String, Object>>(){});
                                    String data=(String) msg.get("data").toString();
                                    Log.d("TAG", "run: data"+data);
                                    List<OrdersEntity> orderEntity = JSON.parseArray(data, OrdersEntity.class);
                                    list=orderEntity;
                                    initAdapter(orderEntity);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }



                            }
                        });

                    }

                    @Override
                    public void failed(Call call, IOException e) {
                        getActivity().runOnUiThread( new Runnable(){

                            @Override
                            public void run() {
                                Toast.makeText(getContext(),"服务器请求错误", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }.run();

    }
    void selectIcon(int state){
        switch (state){
            case 1:
                waitPay.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.waitpay1));
                paid.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.paid));
                delivered.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.delivered));
                received.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.received));
                a.setTextColor(Color.parseColor("#00DDB6"));
                b.setTextColor(Color.parseColor("#888888"));
                c.setTextColor(Color.parseColor("#888888"));
                d.setTextColor(Color.parseColor("#888888"));
                break;
            case 2:

                waitPay.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.waitpay));
                paid.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.paid1));
                delivered.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.delivered));
                received.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.received));
                a.setTextColor(Color.parseColor("#888888"));
                b.setTextColor(Color.parseColor("#00DDB6"));
                c.setTextColor(Color.parseColor("#888888"));
                d.setTextColor(Color.parseColor("#888888"));
                break;
            case 3:
                waitPay.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.waitpay));
                paid.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.paid));
                delivered.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.delivered1));
                received.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.received));
                a.setTextColor(Color.parseColor("#888888"));
                b.setTextColor(Color.parseColor("#888888"));
                c.setTextColor(Color.parseColor("#00DDB6"));
                d.setTextColor(Color.parseColor("#888888"));
                break;
            case 4:
                waitPay.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.waitpay));
                paid.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.paid));
                delivered.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.delivered));
                received.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.received1));
                a.setTextColor(Color.parseColor("#888888"));
                b.setTextColor(Color.parseColor("#888888"));
                c.setTextColor(Color.parseColor("#888888"));
                d.setTextColor(Color.parseColor("#00DDB6"));
                break;
        }

    }
    void selectOrderPaid(){

        new Runnable() {
            @Override
            public void run() {
                HttpHelper instance=HttpHelper.getInstance();
                HashMap<String,Object> order=new HashMap<String,Object>();
                order.put("orderState",2);
                order.put("userId",Constant.getUserId());
                instance.postDataAsyn(Constant.parentUrl + "queryOrderList", JSON.toJSONString(order), new HttpHelper.NetCall() {
                    @Override
                    public void success(Call call,final Response response) throws IOException {

                        getActivity().runOnUiThread( new Runnable() {

                            @Override
                            public void run() {

                                HashMap<String,Object> msg= null;
                                try {
                                    msg = JSONObject.parseObject(response.body().string(), new TypeReference<HashMap<String, Object>>(){});
                                    String data=(String) msg.get("data").toString();
                                    Log.d("TAG", "run: data"+data);
                                    List<OrdersEntity> orderEntity = JSON.parseArray(data, OrdersEntity.class);
                                    list=orderEntity;
                                    initAdapter(orderEntity);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }



                            }
                        });

                    }

                    @Override
                    public void failed(Call call, IOException e) {
                        getActivity().runOnUiThread( new Runnable(){

                            @Override
                            public void run() {
                                Toast.makeText(getContext(),"服务器请求错误", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }.run();

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.waitPay:

                selectIcon(1);
          new Thread() {
                @Override
                public void run() {
                    selectOrder(1);

                }
            }.start();

                break;
            case R.id.paid:
                selectIcon(2);
                new Runnable() {
                    @Override
                    public void run() {

                        selectOrderPaid();

                    }
                }.run();
                break;
            case R.id.delivered:
                selectIcon(3);

               new Runnable() {
                    @Override
                    public void run() {
                        selectOrder(3);
                    }
                }.run();
                break;
            case R.id.received:
                selectIcon(4);
                new Runnable() {
                    @Override
                    public void run() {
                        selectOrder(4);

                    }
                }.run();

                break;
            case R.id.newAddress:

                break;
        }

    }
}