package com.example.basketmenejeri;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.basketmenejeri.Adapters.Person;
import com.example.basketmenejeri.Adapters.PersonListAdapter;
import com.example.basketmenejeri.DataBase.DataBaseHelper;

import java.io.IOException;
import java.util.ArrayList;

public class TransferPage extends AppCompatActivity {

    private static final String TAG = "TransferPage";
    public ListView VeriListele;
    Button teklifbutton;

DataBaseHelper myDb;
    private ArrayAdapter<String> dataAdapterForPosition;
    Cursor c = null;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override // Bu metod uygulama açıldığında çalıştırılan metod.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_page);

  myDb = new DataBaseHelper(this);

        VeriListele = (ListView) findViewById(R.id.TransferList);
        teklifbutton =(Button) findViewById(R.id.teklifbutton);

        DataBaseHelper myDbHelper = new DataBaseHelper(TransferPage.this);
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


        ArrayList<Person> peopleList = new ArrayList<>();
        c = myDbHelper.queryNonTeam(null, null  , null,null,null,null,    null);

        if (c.moveToFirst()) {
            do {

                Person john1 = new Person(  c.getString(2), c.getString(3),c.getString(4),c.getString(5)+".00m",c.getString(6));
                peopleList.add(john1);

                PersonListAdapter adapter = new PersonListAdapter(this, R.layout.transfertableadapter, peopleList);
                VeriListele.setAdapter(adapter);
            } while (c.moveToNext());


           }

        }

    }

