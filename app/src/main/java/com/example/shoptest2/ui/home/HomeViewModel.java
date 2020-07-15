package com.example.shoptest2.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private RecyclerView recyclerView;
    public HomeViewModel() {
        mText = new MutableLiveData<>();
       // mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}