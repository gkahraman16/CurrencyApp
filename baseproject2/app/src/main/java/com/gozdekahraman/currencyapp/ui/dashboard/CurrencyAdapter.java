package com.gozdekahraman.currencyapp.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gozdekahraman.currencyapp.R;
import com.gozdekahraman.currencyapp.model.response.Currency;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> {

    private Map<String,Currency> currencyMap;
    private List<Currency> currencyList;

    public CurrencyAdapter(Map<String, Currency> currency_Map){
        currencyMap = currency_Map;
        currencyMap.remove("USDPURE");
        currencyList = new ArrayList<Currency>(currencyMap.values());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View currencyView = inflater.inflate(R.layout.currency_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(currencyView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Currency currency = currencyList.get(position);

        holder.currency_name.setText(currency.getCode());
        holder.date.setText(currency.getTarih());
        holder.purchase.setText(currency.getAlis());
        holder.sale.setText(currency.getSatis());
    }

    @Override
    public int getItemCount() {
        return currencyMap.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView currency_name;
        public TextView date;
        public ImageView img;
        public TextView purchase;
        public TextView sale;

        public ViewHolder(View itemView){
            super(itemView);

            currency_name = (TextView) itemView.findViewById(R.id.textCurrency);
            date = (TextView) itemView.findViewById(R.id.timeText);
            img = (ImageView) itemView.findViewById(R.id.imageView4);
            purchase = (TextView) itemView.findViewById(R.id.purchase);
            sale = (TextView) itemView.findViewById(R.id.sale);
        }
    }
}
