package com.example.basketmenejeri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.basketmenejeri.Adapters.CalanderListAdapter;
import com.example.basketmenejeri.Adapters.Calander_Team;
import com.example.basketmenejeri.GamePageActivity;

import java.util.ArrayList;
public class CalanderPage extends AppCompatActivity {
    private static final String TAG = "CalanderPage";
    GamePageActivity g= new GamePageActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calander_page);
        Log.d(TAG, "OnCreate:Started");


        ListView calander_list = (ListView) findViewById(R.id.CalanderList);
        String calenderteams[] = new String[10];
        calenderteams[0] = "FBahce";
        calenderteams[1] = "GSaray";
        calenderteams[2] = "AEfes";
        calenderteams[3] = "BJK";
        int otherteam1point=getIntent().getExtras().getInt("otherteam1point");
        int otherteam2point=getIntent().getExtras().getInt("otherteam2point");
        int hometeampoints=getIntent().getExtras().getInt("hometeampoints");
        int awayteampoints=getIntent().getExtras().getInt("awayteampoints");

     
        Calander_Team round1 ;
        round1 = new Calander_Team(calenderteams[0],g.sonuclar[0], calenderteams[2]);
        Calander_Team round2 = new Calander_Team(calenderteams[1], g.sonuclar[1], calenderteams[3]);
        Calander_Team round3 = new Calander_Team(calenderteams[2], g.sonuclar[2], calenderteams[3]);
        Calander_Team round4 = new Calander_Team(calenderteams[1], g.sonuclar[3], calenderteams[0]);
        Calander_Team round5 = new Calander_Team(calenderteams[3], g.sonuclar[4], calenderteams[0]);
        Calander_Team round6 = new Calander_Team(calenderteams[1], g.sonuclar[5], calenderteams[2]);
        Calander_Team round7 = new Calander_Team(calenderteams[2], g.sonuclar[6], calenderteams[0]);
        Calander_Team round8 = new Calander_Team(calenderteams[3], g.sonuclar[7], calenderteams[1]);
        Calander_Team round9 = new Calander_Team(calenderteams[1], g.sonuclar[8], calenderteams[0]);
        Calander_Team round10 = new Calander_Team(calenderteams[3],g.sonuclar[9], calenderteams[2]);
        Calander_Team round11 = new Calander_Team(calenderteams[0],g.sonuclar[10], calenderteams[3]);
        Calander_Team round12 = new Calander_Team(calenderteams[2],g.sonuclar[11], calenderteams[1]);

        ArrayList<Calander_Team> calanderList = new ArrayList<>();

        calanderList.add(round1);
        calanderList.add(round2);
        calanderList.add(round3);
        calanderList.add(round4);
        calanderList.add(round5);
        calanderList.add(round6);
        calanderList.add(round7);
        calanderList.add(round8);
        calanderList.add(round9);
        calanderList.add(round10);
        calanderList.add(round11);
        calanderList.add(round12);


        CalanderListAdapter adapter = new CalanderListAdapter(this, R.layout.calander_aage_adapter, calanderList);
        calander_list.setAdapter(adapter);
    }
}