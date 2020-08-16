package com.gozdekahraman.currencyapp;

import android.app.Application;

import com.gozdekahraman.currencyapp.middleware.ServiceCreator;
import com.gozdekahraman.currencyapp.middleware.ServiceQueue;
import com.gozdekahraman.currencyapp.middleware.ServiceRequest;

import java.lang.ref.WeakReference;

/**
 * Created by Bekir.Dursun on 3.10.2017.
 */

public class App extends Application {

    private static App sInstance;
    private static ServiceRequest apiService;
    private static WeakReference<ServiceQueue> serviceQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static synchronized App getInstance() {
        return sInstance;
    }

    public static synchronized ServiceRequest getApiService() {
        if (apiService == null) {
            apiService = ServiceCreator.getClient().create(ServiceRequest.class);
        }
        return apiService;
    }

    public static synchronized ServiceQueue getServiceQueue() {
        if (serviceQueue == null || serviceQueue.get() == null) {
            serviceQueue = new WeakReference<>(new ServiceQueue(getInstance().getBaseContext()));
        }
        return serviceQueue.get();
    }
}