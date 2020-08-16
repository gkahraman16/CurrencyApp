package com.gozdekahraman.currencyapp.ui.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gozdekahraman.currencyapp.R;
import com.gozdekahraman.currencyapp.data.Alarm;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> {

    private List<Alarm> alarmList;

    public AlarmAdapter(List<Alarm> alarmList) {
        this.alarmList = alarmList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View alarmView = inflater.inflate(R.layout.alarm_row, parent, false);
        AlarmAdapter.ViewHolder viewHolder = new AlarmAdapter.ViewHolder(alarmView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Alarm alarm = alarmList.get(position);

        holder.alarm_name.setText(alarm.getName());
        holder.alarm_currency.setText(alarm.getCurrency());
        holder.alarm_value.setText(alarm.getValue());
    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView alarm_name;
        public TextView alarm_currency;
        public TextView alarm_value;
        public Switch alarm_switch;

        public ViewHolder(View itemView) {
            super(itemView);
            alarm_name = (TextView) itemView.findViewById(R.id.alarmName);
            alarm_currency = (TextView) itemView.findViewById(R.id.alarmCurrency);
            alarm_value = (TextView) itemView.findViewById(R.id.alarmTargetValue);
            alarm_switch = (Switch) itemView.findViewById(R.id.switch1);
        }


        }
}
