package com.example.basketmenejeri.Adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.basketmenejeri.R;

import java.util.ArrayList;

/**
 * Created by User on 3/14/2017.
 */

public class CalanderListAdapter extends ArrayAdapter<Calander_Team> {

    private static final String TAG = "PersonListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;


    private static class ViewHolder {
        TextView team1;
        TextView team2;
        TextView ws;


    }


    public CalanderListAdapter(Context context, int resource, ArrayList<Calander_Team> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String team1 = getItem(position).getTeam1();
        String ws = getItem(position).getWs();
        String team2 = getItem(position).getTeam2();


        Calander_Team person = new Calander_Team(team1,ws,team2);

        final View result;

        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.team1 = (TextView) convertView.findViewById(R.id.adaptorteam1);
            holder.team2 = (TextView) convertView.findViewById(R.id.adaptorteam2);
            holder.ws = (TextView) convertView.findViewById(R.id.adaptorwinn);


            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }




        holder.team1.setText(person.getTeam1());
        holder.ws.setText(person.getWs());
        holder.team2.setText(person.getTeam2());


        return convertView;
    }
}