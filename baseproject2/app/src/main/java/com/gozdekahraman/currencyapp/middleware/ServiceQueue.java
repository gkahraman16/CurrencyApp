package com.gozdekahraman.currencyapp.middleware;

import android.content.Context;
import android.widget.Toast;

import com.gozdekahraman.currencyapp.App;
import com.gozdekahraman.currencyapp.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by bekirdursun on 26.03.2018.
 */

public class ServiceQueue {

    private Context mContext;
    private HashMap<String, ArrayList<String>> ACTIVE_SERVICE_LIST;

    public ServiceQueue(Context baseContext) {
        mContext = baseContext;
        if (ACTIVE_SERVICE_LIST == null) {
            ACTIVE_SERVICE_LIST = new HashMap<>();
        }
    }

    public HashMap<String, ArrayList<String>> getActiveServiceList() {
        return ACTIVE_SERVICE_LIST;
    }

    public void setActiveServiceList(HashMap<String, ArrayList<String>> ACTIVE_SERVICE_LIST) {
        this.ACTIVE_SERVICE_LIST = ACTIVE_SERVICE_LIST;
    }

    public ArrayList<String> getList(String hash) {
        if (hash != null) {
            return ACTIVE_SERVICE_LIST.get(hash);
        }
        return null;
    }

    void addService(String hash, String mapKey) {
        //Log.e("Queue addService", "; key:" + hash + " value: " + mapKey);
        if (hash != null && mapKey != null) {
            ArrayList<String> map = ACTIVE_SERVICE_LIST.get(hash);
            if (map == null) {
                map = new ArrayList<>();
            }
            map.add(mapKey);
            ACTIVE_SERVICE_LIST.put(hash, map);
        }
    }

    void removeService(String hash, String mapKey) {
        //Log.e("Queue removeService", "; key:" + hash + " value: " + mapKey);
        if (hash != null && mapKey != null) {
            ArrayList<String> map = ACTIVE_SERVICE_LIST.get(hash);
            if (map != null) {
                map.remove(mapKey);
                ACTIVE_SERVICE_LIST.put(hash, map);
            }
        }
    }

    public boolean isSuccess() {
        boolean queueSuccess = false;
        String key = null;
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        try {
            StackTraceElement s = stackTraceElements[3];
            if (s != null && s.getClassName().contains(App.getInstance().getPackageName())) {
                key = s.getFileName();
                //Log.e("Queue isSuccess", "; key:" + key);
                queueSuccess = true;
            }
        } catch (Exception ignored) {
        }

        if (queueSuccess) {
            if (key != null) {
                ArrayList<String> map = ACTIVE_SERVICE_LIST.get(key);
                if (!(map == null || map.size() == 0)) {
                    if (mContext != null) {
                        Toast.makeText(mContext, mContext.getString(R.string.service_is_continue_warn), Toast.LENGTH_LONG).show();
                    }
                }
                return map == null || map.size() == 0;
            }
        }
        return true;
    }
}
