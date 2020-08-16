package com.gozdekahraman.currencyapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.gozdekahraman.currencyapp.R;
import com.gozdekahraman.currencyapp.middleware.listener.ServiceCall;
import com.gozdekahraman.currencyapp.middleware.service.MasterService;
import com.gozdekahraman.currencyapp.model.response.BaseModel;
import com.gozdekahraman.currencyapp.model.response.Currency;
import com.gozdekahraman.currencyapp.model.response.ErrorModel;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.priceHome1)
    TextView price_usd;
    @BindView(R.id.priceHome2)
    TextView price_eur;
    @BindView(R.id.priceHome3)
    TextView price_eurusd;
    @BindView(R.id.priceHome4)
    TextView price_au;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,root);
        getCurrencyList();


        return root;
    }

    private void getCurrencyList() {
        MasterService.getCurrencyList(new ServiceCall<BaseModel<Map<String, Currency>>>() {
            @Override
            public void onResponse(boolean isOnline, BaseModel<Map<String,Currency>> response) {
                if (response != null && !response.getData().isEmpty()) {
                    Map<String,Currency> currencyMap = response.getData();
                    price_usd.setText(currencyMap.get("USDTRY").getSatis());
                    price_eur.setText(currencyMap.get("EURTRY").getSatis());
                    price_eurusd.setText(currencyMap.get("EURUSD").getSatis());
                    price_au.setText(currencyMap.get("ALTIN").getSatis());
                }
            }

            @Override
            public void onFailure(boolean isOnline, ErrorModel error) {
                 System.out.println("home fragment master service failedddd");
            }
        });
    }
}