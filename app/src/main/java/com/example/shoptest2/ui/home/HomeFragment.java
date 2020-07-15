package com.example.shoptest2.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.shoptest2.MainActivity;
import com.example.shoptest2.R;
import com.example.shoptest2.adapter.GoodsAdapter;
import com.example.shoptest2.entity.GoodsEntity;
import com.example.shoptest2.util.HttpHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private GoodsAdapter goodsAdapter;
    private RecyclerView goodsRecyclerView;
 //   private View  contentView;
    private List<GoodsEntity> list=new ArrayList<GoodsEntity>();
    @Override
     public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("TAG","onCreate");
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        //contentView=inflater.inflate(R.layout.ho,container,false);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        goodsRecyclerView=(RecyclerView) root.findViewById(R.id.goodsRecyclerView);
        initAdapter();
        return root;
    }

    private void initAdapter() {
        goodsAdapter = new GoodsAdapter(R.layout.good_item,getGoodsItem());
        goodsAdapter.openLoadAnimation();
        goodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getContext(), "click 整个布局", Toast.LENGTH_SHORT).show();
                //跳转到单个页面详细
            }
        });
        goodsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                switch (view.getId()) {
                    case R.id.btn_buy:
                        addCart(list.get(position).getGoodsId());
                        break;

                }
            }
        });
        goodsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        goodsRecyclerView.setAdapter(goodsAdapter);
    }
    private List<GoodsEntity> getGoodsItem() {
       for(int i=0;i<10;i++) {
            GoodsEntity good=new GoodsEntity();
            good.setGoodsName("good");
            list.add(good);
        }
        return list;
    }
    public void addCart(String goodId){
        HttpHelper instance= HttpHelper.getInstance();
    }

}