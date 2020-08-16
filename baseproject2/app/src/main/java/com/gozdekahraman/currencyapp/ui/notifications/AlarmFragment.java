package com.gozdekahraman.currencyapp.ui.notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gozdekahraman.currencyapp.R;
import com.gozdekahraman.currencyapp.middleware.listener.ServiceCall;
import com.gozdekahraman.currencyapp.middleware.service.MasterService;
import com.gozdekahraman.currencyapp.data.Alarm;
import com.gozdekahraman.currencyapp.model.response.BaseModel;
import com.gozdekahraman.currencyapp.model.response.Currency;
import com.gozdekahraman.currencyapp.model.response.ErrorModel;
import com.gozdekahraman.currencyapp.ui.dashboard.CurrencyAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;

public class AlarmFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private ArrayList<String> currencies;
    private Map<String, Currency> currencyMap;
    private AlarmAdapter alarmAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Alarm> alarms = new ArrayList<>();
    private Map<String,List<Alarm>> alarmMap;
    private String aCurrency;


    @BindView(R.id.editAlarmName)
    EditText alarmName;
    @BindView(R.id.spinner2)
    Spinner spinnerCurrency;
    @BindView(R.id.editTextTarget)
    EditText alarmValue;
    @BindView(R.id.setAlarmButton)
    Button setAlarmButton;
    @BindView(R.id.alarmRcv2)
    RecyclerView alarmRecyclerview;

    public AlarmFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_alarm, container, false);
        ButterKnife.bind(this,root);
        spinnerCurrency.setOnItemSelectedListener(this);
        getSpinnerItems();
        saveCurrencyList();
        loadData();
        buildRCV();

        setAlarmButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
              if(alarmName.getText().toString().length() == 0 || alarmValue.getText().toString().length() == 0){
                  if(alarmName.getText().toString().length() == 0){
                      alarmName.setError("Bu alan boş bırakılamaz!");
                  }
                  if(alarmValue.getText().toString().length() == 0){
                      alarmValue.setError("Bu alan boş bırakılamaz!");
                  }
              }else{
                  addAlarm(alarmName.getText().toString(), aCurrency,alarmValue.getText().toString());
                  saveData();
                  addNotification();
              }

            }
        });

        saveCurrencyList();


        return root;
    }

    private void getSpinnerItems() {
        MasterService.getCurrencyList(new ServiceCall<BaseModel<Map<String, Currency>>>() {
            @Override
            public void onResponse(boolean isOnline, BaseModel<Map<String, Currency>> response) {
                if (response != null && !response.getData().isEmpty()) {
                    currencyMap = response.getData();
                    currencyMap.remove("USDPURE");
                    currencies = new ArrayList<String>(currencyMap.keySet());
                    ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, currencies);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCurrency.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(boolean isOnline, ErrorModel error) {

            }
        });
    }

    private void saveData(){
        SharedPreferences sp = getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(alarms);
        editor.putString("alarm list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("alarm list", null);
        Type type = new TypeToken<ArrayList<Alarm>>() {}.getType();
        alarms = gson.fromJson(json, type);
        if (alarms == null) {
            alarms = new ArrayList<>();
        }
    }

    private void saveCurrencyList(){
        MasterService.getCurrencyList(new ServiceCall<BaseModel<Map<String, Currency>>>() {
            @Override
            public void onResponse(boolean isOnline, BaseModel<Map<String, Currency>> response) {
                if (response != null && !response.getData().isEmpty()) {
                    currencyMap = response.getData();
                    currencyMap.remove("USDPURE");
                    currencies = new ArrayList<String>(currencyMap.keySet());
                    SharedPreferences shared_preferences = getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
                    SharedPreferences.Editor editor = shared_preferences.edit();
                    Gson gson = new Gson();
                    String currency = gson.toJson(currencies);
                    editor.putString("currency list", currency);
                    editor.apply();
                }
            }

            @Override
            public void onFailure(boolean isOnline, ErrorModel error) {

            }
        });
    }





    private void buildRCV(){
        alarmRecyclerview.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        alarmAdapter = new AlarmAdapter(alarms);
        alarmRecyclerview.setLayoutManager(mLayoutManager);
        alarmRecyclerview.setAdapter(alarmAdapter);
    }

    private void addAlarm(String name, String currency, String value){
        alarms.add(new Alarm(name,currency,value));
        alarmAdapter.notifyItemInserted(alarms.size());
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        aCurrency = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getActivity())
                        .setSmallIcon(R.drawable.ic_baseline_notifications_24) //set icon for notification
                        .setContentTitle("Notifications Example") //set title of notification
                        .setContentText("This is a notification message")//this is notification message
                        .setAutoCancel(true) // makes auto cancel of notification
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT); //set priority of notification


        Intent notificationIntent = new Intent(getActivity(), NotificationView.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //notification message will get at NotificationView
        notificationIntent.putExtra("message", "This is a notification message");

        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
