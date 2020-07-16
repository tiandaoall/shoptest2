package com.example.shoptest2.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shoptest2.R;
import com.example.shoptest2.entity.OrdersEntity;

import java.util.List;

public class MineAdapter extends BaseQuickAdapter<OrdersEntity, BaseViewHolder> {
    public MineAdapter(int layoutResId, @Nullable List<OrdersEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrdersEntity item) {
        helper.setText(R.id.orderId,item.getOrderId());
        helper.setText(R.id.goodsName,item.getGoodsName());

        helper.setText(R.id.buyNumber,item.getBuyNumber().toString());
        double totalprice=Integer.parseInt(item.getBuyNumber().toString())*Double.parseDouble(item.getGoodsPrices().toString());
        helper.setText(R.id.totalPrice,totalprice+" ");
        //helper.setImageResource(R.id.goodImage, Integer.parseInt(item.getGoodsImg()));
/*        if (item.getOrderState()==3) {
            helper.setImageResource(R.id.btn_buy,R.drawable.confirmreceive);
        }*/
       helper.addOnClickListener(R.id.btn_buy);
    }
}
