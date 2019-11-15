package com.example.basketmenejeri;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.basketmenejeri.Adapters.Person;
import com.example.basketmenejeri.Adapters.PersonListAdapter;
import com.example.basketmenejeri.DataBase.DataBaseHelper;

import java.io.IOException;
import java.util.ArrayList;

public class SquatPage extends AppCompatActivity {
    int boy = 0;
    private static final String TAG = "SquatPage";
    private ListView VeriListele;


    private String[] positions = {"Tümünü Göster", "Oyun Kurucu", "Pivot", "Forvet"};
    String selectedposition;
    Button back;
    //Spinner'ları ve Adapter'lerini tanımlıyoruz.
    private Spinner spinnerPosition;
    private ArrayAdapter<String> dataAdapterForPosition;

    Cursor c = null;
    Cursor v = null;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override // Bu metod uygulama açıldığında çalıştırılan metod.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squat_page);
        spinnerPosition = (Spinner) findViewById(R.id.spinner);
        dataAdapterForPosition = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, positions);
        dataAdapterForPosition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPosition.setAdapter(dataAdapterForPosition);
        VeriListele = (ListView) findViewById(R.id.listview);
         back = (Button) findViewById(R.id.backbutton);
        selectedposition = spinnerPosition.getSelectedItem().toString();

        DataBaseHelper myDbHelper = new DataBaseHelper(SquatPage.this);
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

        String teamname = getIntent().getExtras().getString("clupname");
        String xteamname = teamname;
        ArrayList<Person> peopleList = new ArrayList<>();


        if (teamname.equals("FBahce")) {
            c = myDbHelper.queryFB(null, null, null, null, null, null, null);

            if (c.moveToFirst()) {
                do {


                    Person john = new Person(c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6) + ".00m");

                    peopleList.add(john);
                    PersonListAdapter adapter = new PersonListAdapter(this, R.layout.squat_table_adaptor, peopleList);
                    VeriListele.setAdapter(adapter);
                   boy++;

                } while (c.moveToNext());
                back.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View a) {

                        Intent i = new Intent(SquatPage.this,IconTextTabActivity.class);
                       int  flags = getIntent().getExtras().getInt("clupflag");
                        final String teamname = getIntent().getExtras().getString("clupname");
                      String  username=getIntent().getExtras().getString("usernamesurname");
                      String usersurname = null;
                        i.putExtra( "clupname",teamname );
                        i.putExtra( "clupflag",flags);
                        i.putExtra( "usernamesurname" , username + usersurname );
                        i.putExtra( "clupname",teamname );
                        i.putExtra("kadroboy",boy);
                        startActivity(i);
                    }
                } );

            }
        }


        if (teamname.equals("GSaray")) {
            v = myDbHelper.queryGS(null, null, null, null, null, null, null);

            if (v.moveToFirst()) {
                do {
                    Person johna = new Person(v.getString(2), v.getString(3), v.getString(4), v.getString(5), v.getString(6) + ".00m");

                    peopleList.add(johna);
                    PersonListAdapter adapter = new PersonListAdapter(this, R.layout.squat_table_adaptor, peopleList);
                    VeriListele.setAdapter(adapter);

                } while (v.moveToNext());
            }
        }
        if (teamname.equals("Besik")) {
            v = myDbHelper.queryBJK(null, null, null, null, null, null, null);
            if (v.moveToFirst()) {
                do {
                    Person johnb = new Person(v.getString(2), v.getString(3), v.getString(4), v.getString(5), v.getString(6) + ".00m");

                    peopleList.add(johnb);
                    PersonListAdapter adapter = new PersonListAdapter(this, R.layout.squat_table_adaptor, peopleList);
                    VeriListele.setAdapter(adapter);

                } while (v.moveToNext());
            }
        }
        if (teamname.equals("AnadolE")) {
            v = myDbHelper.queryAEfes(null, null, null, null, null, null, null);
            if (v.moveToFirst()) {
                do {
                    Person johnc = new Person(v.getString(2), v.getString(3), v.getString(4), v.getString(5), v.getString(6) + ".00m");

                    peopleList.add(johnc);
                    PersonListAdapter adapter = new PersonListAdapter(this, R.layout.squat_table_adaptor, peopleList);
                    VeriListele.setAdapter(adapter);

                } while (v.moveToNext());
            }
        }

    }
}



