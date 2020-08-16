package com.gozdekahraman.currencyapp.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gozdekahraman.currencyapp.R;
import com.gozdekahraman.currencyapp.middleware.listener.ServiceCall;
import com.gozdekahraman.currencyapp.middleware.service.MasterService;
import com.gozdekahraman.currencyapp.model.response.BaseModel;
import com.gozdekahraman.currencyapp.model.response.Currency;
import com.gozdekahraman.currencyapp.model.response.ErrorModel;


import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardFragment extends Fragment {

    @BindView(R.id.rcviewer)
    RecyclerView rcviewer;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this,root);

        getCurrencyList();
        return root;
    }


    private void getCurrencyList() {
        MasterService.getCurrencyList(new ServiceCall<BaseModel<Map<String, Currency>>>() {
            @Override
            public void onResponse(boolean isOnline, BaseModel<Map<String, Currency>> response) {
                if (response != null && !response.getData().isEmpty()) {
                    Map<String, Currency> currencyMap = response.getData();
                    CurrencyAdapter adapter = new CurrencyAdapter(currencyMap);
                    rcviewer.setAdapter(adapter);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    rcviewer.setLayoutManager(layoutManager);
                    // button.setVisibility(View.VISIBLE);

                    //  DataUtil.post(cur);
                    // textArea.setText(definition.getAciklama());

                }
            }

            @Override
            public void onFailure(boolean isOnline, ErrorModel error) {

            }
        });
    }
}