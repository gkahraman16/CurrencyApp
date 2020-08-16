package com.gozdekahraman.currencyapp.middleware.service;

import com.gozdekahraman.currencyapp.App;
import com.gozdekahraman.currencyapp.middleware.ServiceManager;
import com.gozdekahraman.currencyapp.middleware.listener.ServiceCall;
import com.gozdekahraman.currencyapp.model.response.BaseModel;
import com.gozdekahraman.currencyapp.model.response.Currency;
import com.gozdekahraman.currencyapp.model.response.ErrorModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;


public class MasterService {

    public static synchronized void getCurrencyList(ServiceCall<BaseModel<Map<String,Currency>>> serviceCall) {
        serviceCall.start();
        getCurrencyListCall(serviceCall);
    }

    private static synchronized void getCurrencyListCall(final ServiceCall<BaseModel<Map<String,Currency>>> serviceCall) {
        String islem = "cur__prices";
        String device_id = "DE45E516-E49C-40A3-A726-6BD7887AF251";
        String dil_kodu = "tr";
        final Call<BaseModel<Map<String,Currency>>> call = App.getApiService().getCurrencyList(islem, device_id, dil_kodu);
        new ServiceManager<BaseModel<Map<String,Currency>>>().ServiceCall(call, new ServiceCall<BaseModel<Map<String,Currency>>>() {
            @Override
            public void onResponse(boolean isOnline, BaseModel<Map<String,Currency>> response) {
                serviceCall.onResponse(true, response);
            }

            @Override
            public void onFailure(boolean isOnline, ErrorModel error) {
                serviceCall.onFailure(true, error);
            }
        });
    }
}