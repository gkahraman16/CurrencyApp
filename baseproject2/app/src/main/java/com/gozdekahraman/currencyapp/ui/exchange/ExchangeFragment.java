package com.gozdekahraman.currencyapp.ui.exchange;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.gozdekahraman.currencyapp.R;
import com.gozdekahraman.currencyapp.middleware.listener.ServiceCall;
import com.gozdekahraman.currencyapp.middleware.service.MasterService;
import com.gozdekahraman.currencyapp.model.response.BaseModel;
import com.gozdekahraman.currencyapp.model.response.Currency;
import com.gozdekahraman.currencyapp.model.response.ErrorModel;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExchangeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private ExchangeViewModel mViewModel;

    private Map<String, Currency> currencyMap;
    private Double currentValue;
    private Double targetValue;



    @BindView(R.id.spinnerCurrent)
    Spinner spinnerCurrencyCurrent;
    @BindView(R.id.spinnerTarget)
    Spinner spinnerCurrencyTarget;
    @BindView(R.id.editTextNumber)
    EditText amountText;
    @BindView(R.id.convertButton)
    Button convertButton;
    @BindView(R.id.result)
    TextView result;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_exchange, container, false);
        ButterKnife.bind(this,root);
        loadMap();

        spinnerCurrencyCurrent.setOnItemSelectedListener(this);
        spinnerCurrencyTarget.setOnItemSelectedListener(this);
        String[] currencies = {"TRY","USD","EUR","GBP","CHF","AUD","DKK","CAD","JPY","SEK","NOK","SAR"};
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter ad = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, currencies);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCurrencyCurrent.setAdapter(adapter);
        spinnerCurrencyTarget.setAdapter(ad);



       convertButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                if( amountText.getText().length() == 0){
                    result.setText("0.0");
                }else {
                    String amount = amountText.getText().toString();
                    Double amountValue = Double.parseDouble(amount);
                    Double resultValue = amountValue * currentValue / targetValue;
                    String resultStr = resultValue.toString();
                    if(resultStr.indexOf(".") > 0 && resultStr.length() - resultStr.indexOf(".") > 4){
                        int index = resultStr.indexOf(".");
                        resultStr = resultStr.substring(0, index + 4);
                    }
                    result.setText(resultStr);
                }
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ExchangeViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(parent.getId() == R.id.spinnerCurrent){
            //for currencyCurrent spinner
            String itemCurrent = parent.getItemAtPosition(position).toString();
            amountText.setHint(itemCurrent);
            if(itemCurrent.equals("TRY")){
                currentValue = 1.0;
            }else{
                String currentStr = currencyMap.get(itemCurrent + "TRY").getAlis();
                currentValue = Double.parseDouble(currentStr);
            }
        }else if(parent.getId() == R.id.spinnerTarget){
            //for currencyTarget spinner
            String itemTarget = parent.getItemAtPosition(position).toString();
            if(itemTarget.equals("TRY")){
                targetValue = 1.0;
            }else{
                String targetStr = currencyMap.get(itemTarget + "TRY").getAlis();
                targetValue = Double.parseDouble(targetStr);
            }
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void loadMap() {
        MasterService.getCurrencyList(new ServiceCall<BaseModel<Map<String, Currency>>>() {
            @Override
            public void onResponse(boolean isOnline, BaseModel<Map<String, Currency>> response) {
                if (response != null && !response.getData().isEmpty()) {
                    currencyMap = response.getData();

                }
            }

            @Override
            public void onFailure(boolean isOnline, ErrorModel error) {

            }
        });
    }


}