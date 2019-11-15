package com.example.basketmenejeri;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.basketmenejeri.Adapters.Clupp;
import com.example.basketmenejeri.Adapters.ClupSpinnerAdapter;
import com.example.basketmenejeri.Adapters.Country;
import com.example.basketmenejeri.Adapters.CustomSpinnerAdapter;
import com.example.basketmenejeri.DataBase.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;


public class NewCareerPage extends Activity {
    private Spinner spinner;
    private Spinner spinnerclup;
    private CustomSpinnerAdapter spinnerAdapter;
    private ClupSpinnerAdapter clupSpinnerAdapter;
    private Country selectedCountry;
    private Clupp  selectedClup;
    Button btn;
    EditText username;
    EditText usersurname;
    DataBaseHelper myDb;

    private List<Clupp> clups = new ArrayList<Clupp>();
    private List<Country> countries = new ArrayList<Country>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_career_page);
        btn =(Button) findViewById( R.id.newcareernextbtn );
        spinner = (Spinner) findViewById(R.id.newcareerspinnercountry);
        spinnerAdapter = new CustomSpinnerAdapter(this, countries );
        spinner.setAdapter(spinnerAdapter);
        spinnerclup = (Spinner) findViewById( R.id.newcareerspinnerclup );
        clupSpinnerAdapter = new ClupSpinnerAdapter(this,clups);
        spinnerclup.setAdapter( clupSpinnerAdapter );
        username = (EditText) findViewById( R.id.newcareeretname );

        usersurname =(EditText) findViewById( R.id.newcareeretsurname );



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // do something after selected item here
                Country country = countries.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        populateCountries();
        spinnerclup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Clupp clup = clups.get(position);
                selectedClup = clup;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        populateClup();

        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intocane = new Intent(NewCareerPage.this,IconTextTabActivity.class);
                intocane.putExtra( "clupflag",selectedClup.flag );
                intocane.putExtra( "usernamesurname" , username.getText() + " " + usersurname.getText() );
                intocane.putExtra( "clupname",selectedClup.name );

                startActivity(intocane);

            }
        } );
    }

    private void populateCountries() {
        Country france = new Country();
        france.name = "Fransa";
        france.flag = R.drawable.icon_fra;
        Country usa = new Country();
        usa.name = "USA";
        usa.flag = R.drawable.icons_usa;
        Country germany = new Country();
        germany.name = "Almanya";
        germany.flag = R.drawable.icon_ger;
        Country turkey = new Country();
        turkey.name = "Türkiye";
        turkey.flag = R.drawable.icon_tr;
        Country nedherland = new Country();
        nedherland.name = "Hollanda";
        nedherland.flag = R.drawable.icon_ned;
        countries.add(france);
        countries.add(usa);
        countries.add(germany);
        countries.add(turkey);
        countries.add(nedherland);
        spinnerAdapter.notifyDataSetChanged();
    }
    private void populateClup() {
        Clupp fenerbahce = new Clupp();
        fenerbahce.name = "FBahce";
        fenerbahce.flag = R.drawable.fb;
        Clupp gatalasaray = new Clupp();
        gatalasaray.name = "GSaray";
        gatalasaray.flag = R.drawable.gs;
        Clupp besiktas = new Clupp();
        besiktas.name = "Besik";
        besiktas.flag = R.drawable.bjk;
        Clupp aefes = new Clupp();
        aefes.name = "AnadolE";
        aefes.flag = R.drawable.aefes;


        clups.add(fenerbahce);
        clups.add(gatalasaray);
        clups.add(besiktas);
        clups.add(aefes);



        clupSpinnerAdapter.notifyDataSetChanged();

    }

}