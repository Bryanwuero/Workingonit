package com.tutorials.hp.mdlistviewediting.mListView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tutorials.hp.mdlistviewediting.MainActivity;
import com.tutorials.hp.mdlistviewediting.R;
import com.tutorials.hp.mdlistviewediting.mData.Activity;
import com.tutorials.hp.mdlistviewediting.mDetail.DetailActivity;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<Activity> activities;
    MainActivity activity;
    LayoutInflater inflater;

    public CustomAdapter(Context c, ArrayList<Activity> activities) {
        this.c = c;
        this.activities = activities;

        //inicio el inflater
        inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /*public CustomAdapter(ArrayList<Activity> activities, MainActivity activity) {
        this.activities = activities;
        this.activity = activity;
    }*/


    @Override
    public int getCount() {
        return activities.size();
    }

    @Override
    public Object getItem(int position) {
        return activities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.model,parent,false);
        }

        TextView nameTxt= (TextView) convertView.findViewById(R.id.nameTxt);
        TextView descTxt= (TextView) convertView.findViewById(R.id.descTxt);
        TextView startDateTxt= (TextView) convertView.findViewById(R.id.startDateTxt);
        TextView finishDateTxt= (TextView) convertView.findViewById(R.id.finishDateTxt);
        finishDateTxt.setVisibility(View.GONE);


        final String name= activities.get(position).getName();
        final String desc= activities.get(position).getDescription();
        final String start= activities.get(position).getDateStart();
        final String finish= activities.get(position).getDateFinish();


        //BIND
        nameTxt.setText(name);
        descTxt.setText(desc);
        startDateTxt.setText(start);
        finishDateTxt.setText(finish);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailActivity(name,desc,start, finish, position);
            }
        });
        return convertView;
    }

    //Abrir nueva actividad
    private void openDetailActivity(String name, String desc, String start, String finish, int pos)
    {
        Intent i=new Intent(c, DetailActivity.class);

        //PACK DATA
        i.putExtra("NAME_KEY",name);
        i.putExtra("DESC_KEY",desc);
        i.putExtra("START_KEY", start);
        i.putExtra("FINISH_KEY", finish);
        i.putExtra("POS_KEY",pos);

        //enviar y abriri actividad
        c.startActivity(i);

    }
}
















