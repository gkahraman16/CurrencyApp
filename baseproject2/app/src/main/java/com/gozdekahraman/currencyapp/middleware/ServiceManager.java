package com.gozdekahraman.currencyapp.middleware;

import com.gozdekahraman.currencyapp.App;
import com.gozdekahraman.currencyapp.middleware.listener.ServiceCall;
import com.gozdekahraman.currencyapp.model.response.ErrorModel;
import com.gozdekahraman.currencyapp.util.ErrorUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bekirdursun on 3.02.2018.
 */

public class ServiceManager<T> {

    private String key = null, value = null;
    private boolean queueSuccess;

    public void ServiceCall(Call<T> call, final ServiceCall<T> serviceCall) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        try {
            StackTraceElement s = stackTraceElements[5];
            if (s != null && s.getClassName().contains(App.getInstance().getPackageName())) {
                key = s.getFileName();
                value = s.getMethodName();
                App.getServiceQueue().addService(key, value);
                queueSuccess = true;
            } else {
                queueSuccess = false;
            }
        } catch (Exception ignored) {
            queueSuccess = false;
        }

        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (queueSuccess) {
                    App.getServiceQueue().removeService(key, value);
                }
                if (response.isSuccessful()) {
                    serviceCall.onResponse(true, response.body());
                } else {
                    ErrorModel error = new ErrorModel();
                    try {
                        error = ErrorUtils.parseError(response);
                    } catch (Exception e) {
                        error.setMessage(e.getMessage());
                    }
                    serviceCall.onFailure(true, error);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable throwable) {
                if (queueSuccess) {
                    App.getServiceQueue().removeService(key, value);
                }
                ErrorModel error = new ErrorModel(throwable.getMessage());
                serviceCall.onFailure(true, error);
            }
        });
    }
}