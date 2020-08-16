package com.gozdekahraman.currencyapp.middleware.listener;

import com.gozdekahraman.currencyapp.model.response.ErrorModel;

/**
 * Created by bekirdursun on 2.02.2018.
 */

public abstract class ServiceCall<T> implements IServiceCall<T> {

    public abstract void onResponse(boolean isOnline, T response);

    public abstract void onFailure(boolean isOnline, ErrorModel error);

    @Override
    public void start() {
        // default func
    }

    public void onResponse(T response) {
        onResponse(true, response);
    }

    public void onFailure(ErrorModel error) {
        onFailure(true, error);
    }
}