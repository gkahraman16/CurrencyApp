package com.gozdekahraman.currencyapp.middleware.listener;

import com.gozdekahraman.currencyapp.model.response.ErrorModel;

/**
 * Created by bekirdursun on 2.02.2018.
 */

public interface IServiceCall<T> {
    void start();

    void onResponse(T response);

    void onFailure(ErrorModel error);
}