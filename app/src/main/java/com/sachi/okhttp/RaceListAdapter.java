package com.sachi.okhttp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

public class RaceListAdapter extends ArrayAdapter {

    List<RaceDataClass> raceDataClasses = new ArrayList<>();
    Activity activity;

    public RaceListAdapter(@NonNull Activity activity, List<RaceDataClass> raceDataClasses) {
        super(activity, R.layout.race_list_row,raceDataClasses);

        this.activity = activity;
        this.raceDataClasses = raceDataClasses;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.race_list_row,null,false);

        TextView txtRaceName = view.findViewById(R.id.txtRaceName);
        TextView txtDate = view.findViewById(R.id.txtDate);
        TextView txtTime = view.findViewById(R.id.txtTime);

        RaceDataClass raceDataClass = (RaceDataClass) getItem(position);

        txtRaceName.setText(raceDataClass.RaceName);
        txtDate.setText(raceDataClass.Date);
        txtTime.setText(raceDataClass.time);

        if(position%2==0){
            view.setBackgroundColor(Color.GREEN);
        }


        return view;
    }
}
