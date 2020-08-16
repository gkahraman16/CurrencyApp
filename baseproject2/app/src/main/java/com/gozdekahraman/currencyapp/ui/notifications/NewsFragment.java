package com.gozdekahraman.currencyapp.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.gozdekahraman.currencyapp.R;

import butterknife.BindView;

public class NewsFragment extends Fragment {

    @BindView(R.id.alarmRcv2)
    RecyclerView rcv;


    public NewsFragment () {

     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_news, container, false);
    }
}
