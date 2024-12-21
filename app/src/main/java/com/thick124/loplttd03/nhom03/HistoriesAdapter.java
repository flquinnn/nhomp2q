package com.thick124.loplttd03.nhom03;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;


import com.thick124.loplttd03.nhom03.API.Histories.Model.Histories;

import java.util.List;

public class HistoriesAdapter extends ArrayAdapter<Histories> {

    private Context context;
    private List<Histories> historiesList;

    public HistoriesAdapter(Context context, List<Histories> historiesList) {
        super(context, R.layout.item_history_calendar, historiesList);
        this.context = context;
        this.historiesList = historiesList;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_history_calendar, parent, false);
        }

        Histories history = historiesList.get(position);

        TextView titleTextView = convertView.findViewById(R.id.historyTitle);
        TextView dateTextView = convertView.findViewById(R.id.historyDate);

        titleTextView.setText(history.getTitle());
        dateTextView.setText(String.valueOf(history.getMoney()));
        int colorRed = ContextCompat.getColor(this.getContext(), R.color.spending);
        int colorBlue = ContextCompat.getColor(this.getContext(), R.color.colorAccent);
        if(!history.getTypeOfExpenditure()){
            dateTextView.setTextColor(colorRed);
        } else {
            dateTextView.setTextColor(colorBlue);
        }

        return convertView;
    }
}