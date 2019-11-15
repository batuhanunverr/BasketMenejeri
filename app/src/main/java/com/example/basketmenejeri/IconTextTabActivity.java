package com.example.basketmenejeri;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.basketmenejeri.Adapters.Clup;
import com.example.basketmenejeri.Adapters.ClupListaAdapter;
import com.example.basketmenejeri.DataBase.DataBaseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class IconTextTabActivity extends AppCompatActivity {
    TextView haftasay;
    Cursor c = null;
    Button squat;
    ImageButton Calendar;
    ImageView flag;
    ImageButton roulettebuton;
  Clup john;
    Integer flags;
    String name;
    ListView leaguetable;
    ImageButton gamebutton;
    ImageButton trasferbutton;
    TextView teammoney;
    Cursor f =null;
    Cursor g =null;
    Cursor b =null;
    Cursor a =null;
    String teamname;
    int boy;
    String username;
    TextView usersurname;
    String haftalar[]= {"1.hafta","2.hafta","3.hafta","4.hafta","5.hafta","6.hafta","7.hafta"};
    ArrayList<Clup> peopleList = new ArrayList<>();
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_text_tab);
        squat = (Button) findViewById(R.id.icontexttabsquatbutton);
        Calendar = (ImageButton) findViewById(R.id.icontexttabcalendarbutton);
        teammoney =(TextView) findViewById(R.id.teammoney);
        flag = (ImageView) findViewById(R.id.oneteamcoloriw);
        trasferbutton = (ImageButton) findViewById(R.id.trasferpagebutton);
        gamebutton = (ImageButton) findViewById(R.id.gamebutton);
        roulettebuton = (ImageButton) findViewById(R.id.roulettebutton);
        leaguetable = (ListView) findViewById(R.id.leaguetablelist);
        flags = getIntent().getExtras().getInt("clupflag");
        flags = 2131230834;
        flag.setImageResource(flags);
        username=getIntent().getExtras().getString("usernamesurname");
       boy = getIntent().getExtras().getInt("kadroboy");
        haftasay = (TextView) findViewById(R.id.haftasay);
     teamname = getIntent().getExtras().getString("clupname");
     teamname="FBahce";
        DataBaseHelper myDbHelper = new DataBaseHelper(IconTextTabActivity.this);
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
        f = myDbHelper.FBahceteam(null, null, null, null, null, null, null);
        g = myDbHelper.GSarayteam(null, null, null, null, null, null, null);
        b = myDbHelper.BJKteam(null, null, null, null, null, null, null);
        a = myDbHelper.AEfesteam(null, null, null, null, null, null, null);

        c = myDbHelper.Teams(null, null, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {


              john = new Clup(c.getString(1), c.getString(4), c.getString(5), c.getString(6));



            peopleList.add(john);




            } while (c.moveToNext());

            ClupListaAdapter adapter = new ClupListaAdapter(this, R.layout.leaguetableadaptor, peopleList);
            leaguetable.setAdapter(adapter);

        }

            if (teamname.equals("FBahce")){
                    haftasay.setText("1.Hafta");
                if (f.moveToFirst()) {

                    do {


                        Clup fb = new Clup(f.getString(1),f.getString(2),  f.getString(4), f.getString(6) );
                       teammoney.setText(fb.getClupwin() + "M TL");



                    } while (f.moveToNext());


                }


            }
        if (teamname.equals("FBahce")){

            if (f.moveToFirst()) {

                do {


                    Clup fb = new Clup(f.getString(1),f.getString(2),  f.getString(4), f.getString(6) );
                    teammoney.setText(fb.getClupwin() + "M TL");



                } while (f.moveToNext());


            }


        }
        if (teamname.equals("GSaray")){

            if (g.moveToFirst()) {

                do {


                    Clup gs = new Clup(g.getString(1),g.getString(2),  g.getString(4), g.getString(6) );
                    teammoney.setText(gs.getClupwin() + "M TL");



                } while (g.moveToNext());


            }


        }
        if (teamname.equals("Besik")){

            if (b.moveToFirst()) {

                do {


                    Clup bjk = new Clup(b.getString(1),b.getString(2),  b.getString(4), b.getString(6) );
                    teammoney.setText(bjk.getClupwin() + "M TL");



                } while (b.moveToNext());


            }


        }
        if (teamname.equals("AnadolE")){

            if (a.moveToFirst()) {

                do {


                    Clup ae = new Clup(a.getString(1),a.getString(2),  a.getString(4), a.getString(6) );
                    teammoney.setText(ae.getClupwin() + "M TL");



                } while (a.moveToNext());


            }


        }


        squat.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                Intent intocan = new Intent(IconTextTabActivity.this , SquatPage.class);
                intocan.putExtra( "clupname",teamname );
                intocan.putExtra( "clupname",teamname );
                intocan.putExtra( "clupflag",flags);
                intocan.putExtra( "usernamesurname" , username + usersurname );
                intocan.putExtra( "clupname",teamname );

                startActivity(intocan);
            }
        } );
        Calendar = (ImageButton) findViewById(R.id.icontexttabcalendarbutton);

        Calendar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                Intent intocan = new Intent(IconTextTabActivity.this , CalanderPage.class);
                int otherteam1points = getIntent().getExtras().getInt("otherteam1point");
                int hometeampoints = getIntent().getExtras().getInt("hometeampoints");
                int awayteampoints = getIntent().getExtras().getInt("awayteampoints");
                int otherteam2points = getIntent().getExtras().getInt("otherteam2point");
                intocan.putExtra("otherteam1point", otherteam1points);
                intocan.putExtra("otherteam2point",otherteam2points);
                intocan.putExtra("hometeampoints",hometeampoints);
                intocan.putExtra("awayteampoints",awayteampoints);
                startActivity(intocan);
            }
        } );
        trasferbutton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                Intent intocan = new Intent(IconTextTabActivity.this , TransferPage.class);
                startActivity(intocan);
            }
        } );
        roulettebuton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                Intent intocan = new Intent(IconTextTabActivity.this , RoulettePage.class);

                intocan.putExtra( "clupnamee",teamname );
                intocan.putExtra( "clupflagg",flags);
                intocan.putExtra( "usernamesurnamee" , username + usersurname );
                intocan.putExtra( "clupnamee",teamname );
                intocan.putExtra("kadroboyy",boy);
                startActivity(intocan);
            }
        } );
        gamebutton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                Intent intocan = new Intent(IconTextTabActivity.this , GamePageActivity.class);
                intocan.putExtra( "clupname",teamname );
                intocan.putExtra( "clupflag",flags);
                intocan.putExtra( "usernamesurname" , username + usersurname );
                intocan.putExtra( "clupname",teamname );
                intocan.putExtra("kadroboy",boy);
                startActivity(intocan);

             haftasay.setText( haftalar[i]);
              i++;

            }
        } );
    }
 /*  public void sırala(ArrayList<Clup> arrayliststring  ) {


       Clup a = new Clup(peopleList.get(0).getClupname(),peopleList.get(0).getClupwin(),peopleList.get(0).getCluplose(),peopleList.get(0).getCluppoint());
       Clup b = new Clup(peopleList.get(1).getClupname(),peopleList.get(1).getClupwin(),peopleList.get(1).getCluplose(),peopleList.get(1).getCluppoint());
       Clup c = new Clup(peopleList.get(2).getClupname(),peopleList.get(2).getClupwin(),peopleList.get(2).getCluplose(),peopleList.get(2).getCluppoint());
       Clup d = new Clup(peopleList.get(3).getClupname(),peopleList.get(3).getClupwin(),peopleList.get(3).getCluplose(),peopleList.get(3).getCluppoint());
       ArrayList<Integer> arraylist = new ArrayList<Integer>();
       arraylist.add(Integer.parseInt(a.getCluppoint()));
       arraylist.add(Integer.parseInt(b.getCluppoint()));
       arraylist.add(Integer.parseInt(c.getCluppoint()));
       arraylist.add(Integer.parseInt(d.getCluppoint()));
       Collections.sort(arraylist);
       peopleList.clear();



       for (i = 0 ; i<4 ; i++){

           if (arraylist.get(i) == Integer.parseInt(a.getCluppoint())){
               peopleList.add(a);
               peopleList.add(b);
               peopleList.add(c);
               peopleList.add(d);

           }
       }


*/

   }

