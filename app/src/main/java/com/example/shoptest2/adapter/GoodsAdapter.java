package com.example.shoptest2.adapter;

import android.app.SearchManager;
import android.graphics.Bitmap;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shoptest2.R;
import com.example.shoptest2.entity.GoodsEntity;

import java.util.List;

import okhttp3.internal.http.RequestLine;

public class GoodsAdapter extends BaseQuickAdapter<GoodsEntity, BaseViewHolder> {


    public GoodsAdapter(int layoutResId, @Nullable List<GoodsEntity> data) {
        super(layoutResId, data);
    }

    public GoodsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsEntity item) {

            helper.setText(R.id.goodsName,item.getGoodsName());

        helper.setText(R.id.goodsSummary,item.getGoodsName());
        helper.addOnClickListener(R.id.btn_buy);
       // helper.addOnClickListener(R.id.goodItem);

    }
}
