package com.gozdekahraman.currencyapp.middleware;

import com.gozdekahraman.currencyapp.model.response.BaseModel;
import com.gozdekahraman.currencyapp.model.response.Currency;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Bekir.Dursun on 3.10.2017.
 */
public interface ServiceRequest {

    @GET("/index.php")
    Call<BaseModel<Map<String,Currency>>> getCurrencyList(@Query("islem") String islem, @Query("device_id") String device_id, @Query("dil_kodu") String dil_kodu);
}