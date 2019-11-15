package com.example.basketmenejeri.Adapters;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.basketmenejeri.DataBase.DataBaseHelper;
import com.example.basketmenejeri.GamePageActivity;
import com.example.basketmenejeri.R;
import com.example.basketmenejeri.TransferPage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 3/14/2017.
 */

public class PersonListAdapter extends ArrayAdapter<Person> implements View.OnClickListener {
    boolean guncelle = false;
    private static final String TAG = "PersonListAdapter";
 Cursor c = null;
    private Context mContext;
    DataBaseHelper db;
    String personid;
    String personteamid;

    ArrayList<Person> list_urunler;
    private int mResource;
    private int lastPosition = -1;

    public PersonListAdapter(Context context, ArrayList<Person> players) {
        super(context, 0, players);
        this.mContext = context;
        this.list_urunler = players;
    }

    @Override
    public void onClick(View v) {
        ViewHolder viewHolder = new ViewHolder();
    }

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        Button teklif;
        TextView name;


        TextView age;
        TextView overall;
        TextView value;
        TextView positionn;
    }



    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    /**
     * Default constructor for the PersonListAdapter
     *
     * @param context
     * @param resource
     * @param objects
     */
    public PersonListAdapter(Context context, int resource, ArrayList<Person> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {




        String name = getItem(position).getName();

        String age = getItem(position).getAge();
        String overall = getItem(position).getOverall();
        String value = getItem(position).getValue();
        String positionn = getItem(position).getPosition();

        //Create the person object with the information
        Person person = new Person(name, age, positionn, value, overall);

        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ViewHolder holder;


        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.transfertableadapter, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.squatadaptorname);
            holder.teklif = convertView.findViewById(R.id.teklifbutton);
            holder.age = (TextView) convertView.findViewById(R.id.squatadaptorage);
            holder.overall = (TextView) convertView.findViewById(R.id.squatadaptoroverall);
            holder.value = (TextView) convertView.findViewById(R.id.squatadaptorvalue);
            holder.positionn = (TextView) convertView.findViewById(R.id.squatadaptorposition);




            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();

        }

        holder.teklif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "A", Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Bu Oyuncuyu Almak İstiyor Musunuz ?");
                builder.setMessage("15.00m");
                builder.setNegativeButton("HAYIR", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {


                    }
                });


                builder.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                db = new DataBaseHelper(getContext());
                        db = new DataBaseHelper(getContext());
                        DataBaseHelper myDbHelper = new DataBaseHelper(getContext());
                        try {
                            myDbHelper.createDataBase();
                        } catch (IOException e) {

                            throw new Error("Unable to create DataBase");
                        }
                        try {
                            myDbHelper.openDataBase();
                        } catch (SQLException sqle) {
                            throw sqle;
                        }

                        ArrayList<PersonData> peopleList = new ArrayList<>();
                        c = myDbHelper.queryNonTeam(null, null  , null,null,null,null,    null);

                        if (c.moveToFirst()) {
                            do {

                                PersonData john1 = new PersonData(c.getString(0), c.getString(1));
                                peopleList.add(john1);
                                personid=john1.getId();
                                personteamid=john1.getTeamid();
                            } while (c.moveToNext());
                            db = new DataBaseHelper(getContext());


                            boolean isUpdate = db.updateDataTransfer(personid, "100");
                            if (isUpdate == true)
                                Toast.makeText(getContext(), "Data Update", Toast.LENGTH_LONG).show();

                            else {
                                Toast.makeText(getContext(), "Data not Updated", Toast.LENGTH_LONG).show();
                            }



                        }
                    }
                });


                builder.show();
            }
        });

        holder.name.setText(person.getName());

        holder.age.setText(person.getAge());
        holder.value.setText(person.getValue());
        holder.positionn.setText(person.getPosition());
        holder.overall.setText(person.getOverall());

        return convertView ;

    }


}