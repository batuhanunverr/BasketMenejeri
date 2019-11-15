package com.example.basketmenejeri;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.CountDownTimer;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import com.example.basketmenejeri.Adapters.Calander_Team;
import com.example.basketmenejeri.Adapters.Clup;
import com.example.basketmenejeri.Adapters.Person;
import com.example.basketmenejeri.Adapters.PersonData;
import com.example.basketmenejeri.Adapters.PersonListAdapter;
import com.example.basketmenejeri.DataBase.DataBaseHelper;
import java.io.IOException;
import com.example.basketmenejeri.GameClass;
import com.example.basketmenejeri.IconTextTabActivity;

public class GamePageActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 20000;
    int x = 0;
    private TextView mTextViewCountDown;
    private CountDownTimer mCountDownTimer;
    Haftasayma hsay = new Haftasayma();
    private boolean mTimerRunning;
    int fbpuan ,fbw,fbl;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    Random rastgele = new Random();
    Cursor f = null;
    Cursor g = null;
    Boolean adamadamaalan=true;
    Button geri;
    int pres =10;
    Boolean ataksavunma=true;
    int hometeampoints = 0;
    int awayteampoints= 0;
    int otherteam1points = 0;
    int otherteam2points= 0;
    int sayi1;
    int sayi2;
    Cursor b = null;
    Cursor a = null;
    boolean durum = true;
    Cursor foveralld = null;
    Cursor aoveralld = null;
    Cursor gsoveralld = null;
    Cursor bjkoveralld = null;
    public int fbgucc;
    public    int aefesgucc;
    public int gsgucc;
    public    int bjkgucc;
    public int aefespuan;
    public int gspuan;
    public int bjkpuan;

    ImageButton gameon;
    TextView hometeampointstext;
    Button taktikbutton;
    TextView awayteampointstext;
    Integer guclutakimsayi[] = {0,0,0,0,0,0,0,0,1,2,2,2,3,3};
    Integer zayiftakimsayi[] = {0,0,0,0,0,0,0,0,1,1,2,2,3,3};
    DataBaseHelper myDb;
    GameClass gameClass = new GameClass();
    ImageView flag;
  Integer flags;
    String username;
    TextView usersurname;
    TextView awayteamname;
    int boy=0;
   static String[] sonuclar = new String[12];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView hometeamname;

        MediaPlayer sesVerisi=MediaPlayer.create(GamePageActivity.this,R.raw.ses);
        sesVerisi.start();
        myDb = new DataBaseHelper(this);
        flags = getIntent().getExtras().getInt("clupflag");
        final String teamname = getIntent().getExtras().getString("clupname");
        username=getIntent().getExtras().getString("usernamesurname");
        int aefesoverall;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        gameon = (ImageButton) findViewById(R.id.gameonbutton);
        hometeamname = (TextView) findViewById(R.id.hometeamname);
        awayteamname = (TextView) findViewById(R.id.awayteamname);
        hometeampointstext =(TextView) findViewById(R.id.hometeampointstext);
        awayteampointstext =(TextView) findViewById(R.id.awayteampointstext);
        taktikbutton =(Button)findViewById(R.id.taktikbutton);
        int boy=0;
        boy = getIntent().getExtras().getInt("kadroboy");


        DataBaseHelper myDbHelper = new DataBaseHelper(GamePageActivity.this);
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
        if (teamname.equals("FBahce")) {

            f = myDbHelper.FBahceteam(null, null, null, null, null, null, null);
            foveralld = myDbHelper.queryFB(null, null, null, null, null, null, null);
            bjkoveralld = myDbHelper.queryBJK(null, null, null, null, null, null, null);
            aoveralld = myDbHelper.queryAEfes(null, null, null, null, null, null, null);
            gsoveralld = myDbHelper.queryGS(null, null, null, null, null, null, null);
            g = myDbHelper.GSarayteam(null, null, null, null, null, null, null);
            b = myDbHelper.BJKteam(null, null, null, null, null, null, null);
            a = myDbHelper.AEfesteam(null, null, null, null, null, null, null);

            if (f.moveToFirst() && a.moveToFirst() && g.moveToFirst() && b.moveToFirst()) {

                do {


                    Clup fb = new Clup(f.getString(1), f.getString(4), f.getString(5), f.getString(6));
                    hometeamname.setText(fb.getClupname());

                    Clup aefes = new Clup(a.getString(1), a.getString(4), a.getString(5), a.getString(6));
                    Clup gs = new Clup(g.getString(1), g.getString(4), g.getString(5), g.getString(6));
                    Clup bjk = new Clup(b.getString(1), b.getString(4), b.getString(5), b.getString(6));

                } while (f.moveToNext() && a.moveToNext() && g.moveToNext() && b.moveToNext());


            }
            String aefesoveralll[] = new String[8];
            String gsoverall[] = new String[8];
            String bjkoveralll[] = new String[8];
            String fboverall[] = new String[foveralld.getCount()];
            if (foveralld.moveToFirst()) {
                do {


                    Person fboverallo = new Person(foveralld.getString(2), foveralld.getString(3), foveralld.getString(4), foveralld.getString(5), foveralld.getString(6) );

                    fboverall[x] = fboverallo.getValue();
                    x++;
                }while (foveralld.moveToNext());

                int temp=0,sayac=0;
                for (int i =0; i<fboverall.length;i++){
                    int deneme = Integer.parseInt(fboverall[i]);
                    temp = deneme + temp;
                    sayac++;
                }
                int fbguc = temp/sayac;
                fbgucc = fbguc;

                gameClass.macyap(fbgucc , ataksavunma , pres);
            }
            int gs=0;
            if (gsoveralld.moveToFirst()) {
                do {
                    Person gsoverallo = new Person(gsoveralld.getString(2), gsoveralld.getString(3), gsoveralld.getString(4), gsoveralld.getString(5), gsoveralld.getString(6) );
                    gsoverall[gs] = gsoverallo.getValue();
                    gs++;
                }while (gsoveralld.moveToNext());
                int temp=0,sayac=0;
                for (int i =0; i<gsoverall.length;i++){
                    int deneme = Integer.parseInt(gsoverall[i]);
                    temp = deneme + temp;
                    sayac++;
                }
                int gsguc = temp/sayac;
                gsgucc = gsguc;


            }
            int y = 0;


            if (aoveralld.moveToFirst()) {
                do {

                    Person aefesoverallo = new Person(aoveralld.getString(2), aoveralld.getString(3), aoveralld.getString(4), aoveralld.getString(5), aoveralld.getString(6) );

                    aefesoveralll[y] = aefesoverallo.getValue();

                    y++;


                } while (aoveralld.moveToNext());
                int temp=0,sayac=0;
                for (int i =0; i<aefesoveralll.length;i++){
                    int deneme = Integer.parseInt(aefesoveralll[i]);
                    temp = deneme + temp;
                    sayac++;
                }
                int aefesguc = temp/sayac;
                aefesgucc =aefesguc;

            }
            int bjk = 0;


            if (bjkoveralld.moveToFirst()) {
                do {

                    Person bjkoverallo = new Person(bjkoveralld.getString(2), bjkoveralld.getString(3), bjkoveralld.getString(4), bjkoveralld.getString(5), bjkoveralld.getString(6) );

                    bjkoveralll[bjk] = bjkoverallo.getValue();

                    bjk++;


                } while (bjkoveralld.moveToNext());
                int temp=0,sayac=0;
                for (int i =0; i<aefesoveralll.length;i++){
                    int deneme = Integer.parseInt(bjkoveralll[i]);
                    temp = deneme + temp;
                    sayac++;
                }
                int bjkguc = temp/sayac;
                bjkgucc =bjkguc;

            }

        }
        taktikbutton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                Intent intocan = new Intent(GamePageActivity.this , Taktik_Page.class);
                startActivity(intocan);
                resetdatabase();
            }
        } );
        gameon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                ataksavunma = getIntent().getExtras().getBoolean("ataksavunma");
                pres  = getIntent().getExtras().getInt("pressdegerii");
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();

                }

            }
        });

    }
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 100) {

            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;

                if (hsay.haftasay == 0 ){
                    awayteamname.setText(a.getString(1) );
                    updateCountDownText(fbgucc, aefesgucc );

                    sonuclar[0] =  (hometeampoints + "-" + awayteampoints) ;




                }
                if(hsay.haftasay == 1){
                    awayteamname.setText(g.getString(1));
                    updateCountDownText(fbgucc, gsgucc );
                    sonuclar[3] =  (awayteampoints + "-" + hometeampoints) ;

                }
                if(hsay.haftasay == 2){
                    awayteamname.setText(b.getString(1));
                    updateCountDownText(fbgucc, bjkgucc );
                    sonuclar[4] =  (awayteampoints + "-" + hometeampoints) ;
                }
                if(hsay.haftasay == 3){
                    awayteamname.setText(a.getString(1));
                    updateCountDownText(fbgucc, aefesgucc );
                    sonuclar[7] =  (awayteampoints + "-" + hometeampoints) ;
                }
                if(hsay.haftasay == 4){
                    awayteamname.setText(g.getString(1));
                    updateCountDownText(fbgucc, gsgucc );
                    sonuclar[8] =  (awayteampoints + "-" + hometeampoints) ;

                }

                if(hsay.haftasay == 5){
                    awayteamname.setText(b.getString(1));
                    updateCountDownText(fbgucc, bjkgucc );
                    sonuclar[10] =  (hometeampoints + "-" + awayteampoints) ;
                }



            }

            @Override
            public void onFinish() {
                mTimerRunning = false;

                gameon.setVisibility(View.INVISIBLE);

                Toast.makeText(getApplicationContext(), "Maç Bitti" , Toast.LENGTH_LONG).show();


                int haftasayısı = hsay.getHaftasay() + 1;
                hsay.setHaftasay(haftasayısı);

                CalanderPage calendar = new CalanderPage();

                if(haftasayısı==1){


                    otherteam1points = rastgele.nextInt(30) + 70;
                    otherteam2points = rastgele.nextInt(30)+ 70;
                    dataupdatefunction1();
                    Toast.makeText(getApplicationContext(),"GSARAY" + " " + otherteam1points + "-" + " " + otherteam2points + " " + "BJK",Toast.LENGTH_LONG).show();

                    sonuclar[1] =  otherteam1points + "-" + " " + otherteam2points ;


                }
                if(haftasayısı==2){


                    otherteam1points = rastgele.nextInt(30) + 70;
                    otherteam2points = rastgele.nextInt(30)+ 70;
                    dataupdatefunction2();
                    Toast.makeText(getApplicationContext(),"AEFES" + " " + otherteam1points + "-" + " " + otherteam2points + " " + "BJK",Toast.LENGTH_LONG).show();
                    sonuclar[2] =  otherteam1points + "-" + " " + otherteam2points ;

                }
                if(haftasayısı==3){
                    otherteam1points = rastgele.nextInt(30) + 70;
                    otherteam2points = rastgele.nextInt(30)+ 70;
                    dataupdatefunction3();
                    Toast.makeText(getApplicationContext(),"GSARAY" + " " + otherteam1points + "-" + " " + otherteam2points + " " + "AEFES",Toast.LENGTH_LONG).show();
                    sonuclar[5] =  otherteam1points + "-" + " " + otherteam2points ;
                }
                if(haftasayısı==4){
                    otherteam1points = rastgele.nextInt(30) + 70;
                    otherteam2points = rastgele.nextInt(30)+ 70;
                    dataupdatefunction4();
                    Toast.makeText(getApplicationContext(),"GSARAY" + " " + otherteam1points + "-" + " " + otherteam2points + " " + "BJK",Toast.LENGTH_LONG).show();
                    sonuclar[7] =  otherteam1points + "-" + " " + otherteam2points ;
                }
                if(haftasayısı==5){


                    otherteam1points = rastgele.nextInt(30) + 70;
                    otherteam2points = rastgele.nextInt(30)+ 70;
                    dataupdatefunction5();
                    Toast.makeText(getApplicationContext(),"AEFES" + " " + otherteam1points + "-" + " " + otherteam2points + " " + "BJK",Toast.LENGTH_LONG).show();
                    sonuclar[9] =  otherteam1points + "-" + " " + otherteam2points ;
                }
                if(haftasayısı==6){


                    otherteam1points = rastgele.nextInt(30) + 70;
                    otherteam2points = rastgele.nextInt(30)+ 70;
                    dataupdatefunction6();
                    Toast.makeText(getApplicationContext(),"GSARAY" + " " + otherteam1points + "-" + " " + otherteam2points + " " + "AEFES",Toast.LENGTH_LONG).show();
                    sonuclar[11] =  otherteam1points + "-" + " " + otherteam2points ;
                }
                Handler hndler= new Handler();
                hndler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intocan = new Intent(GamePageActivity.this , IconTextTabActivity.class);
                        flags = getIntent().getExtras().getInt("clupflag");
                         String teamname = getIntent().getExtras().getString("clupname");
                        username=getIntent().getExtras().getString("usernamesurname");
                        intocan.putExtra( "clupname",teamname );
                        intocan.putExtra( "clupflag",flags);
                        intocan.putExtra( "usernamesurname" , username + usersurname );
                        intocan.putExtra( "clupname",teamname );
                        intocan.putExtra("otherteam1point", otherteam1points);
                        intocan.putExtra("otherteam2point",otherteam2points);
                        intocan.putExtra("homepoint", hometeampoints);
                        intocan.putExtra("awaypoint",awayteampoints);
                        startActivity(intocan);
                    }

                },8000);
            }
        }.start();
        mTimerRunning = true;

    }
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;


    }
    private void updateCountDownText(int birinci , int ikinci ) {
        int minutes = (int) (mTimeLeftInMillis / 100) / 60;
        int seconds = (int) (mTimeLeftInMillis / 100) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);


        int p = rastgele.nextInt(13);
        sayi1 = guclutakimsayi[p];
        sayi2 = zayiftakimsayi[p];

        if(birinci > ikinci){
            if (durum==true){
                hometeampoints = hometeampoints + sayi1;

                hometeampointstext.setText(String.valueOf(hometeampoints));
                durum = false;
            }
            else{
                awayteampoints = awayteampoints + sayi2;
                awayteampointstext.setText(String.valueOf(awayteampoints));
                durum = true;
            }

        }

        mTextViewCountDown.setText(timeLeftFormatted);
    }
    private void dataupdatefunction1(){
        int fbpuan =hsay.getFbpuan();
        int aefespuan = hsay.getAefespuan();
        int bjkpuan= hsay.getBjkpuan();
        int gspuan = hsay.getGspuan();
        int fbw = hsay.getFbw();
        int gsw=hsay.getGsw();
        int bjkw = hsay.getBjkw();
        int aefesw = hsay.getAefesw();
        int fbl=hsay.getFbl();
        int gsl=hsay.getGsl();
        int bjkl=hsay.getBjkl();
        int aefesl=hsay.getAefesl();

        String fbpuann = Integer.toString(fbpuan);
        String gspuann = Integer.toString(gspuan);
        String bjkpuann = Integer.toString(bjkpuan);
        String aefespuann = Integer.toString(aefespuan);
        String fbwin = Integer.toString(fbw);
        String gswin = Integer.toString(gsw);
        String bjkwin = Integer.toString(bjkw);
        String aefeswin = Integer.toString(aefesw);
        String fblose = Integer.toString(fbl);
        String gslose = Integer.toString(gsl);
        String bjklose = Integer.toString(bjkl);
        String aefeslose = Integer.toString(aefesl);


        if (hometeampoints > awayteampoints) {
            fbpuan = fbpuan + 3;
            hsay.setFbpuan(fbpuan);
            fbpuann = Integer.toString(fbpuan);
            fbw = fbw + 1;
            hsay.setFbw(fbw);
            fbwin = Integer.toString(fbw);
            fbl = fbl + 0;
            hsay.setFbl(fbl);
            fblose = Integer.toString(fbl);
            aefespuan = aefespuan + 0;
            hsay.setAefespuan(aefespuan);
            aefespuann = Integer.toString(aefespuan);
            aefesw = aefesw + 0;
            hsay.setAefesw(aefesw);
            aefeswin = Integer.toString(aefesw);
            aefesl = aefesl + 1;
            hsay.setAefesl(aefesl);
            aefeslose = Integer.toString(aefesl);
            myDb.updateData("100", fbpuann, fbwin, fblose);
            myDb.updateData("103", aefespuann, aefeswin, aefeslose);
        }
        if(awayteampoints > hometeampoints){

            fbpuan = fbpuan + 0;
            hsay.setFbpuan(fbpuan);
            fbpuann = Integer.toString(fbpuan);
            fbw = fbw + 0;
            hsay.setFbw(fbw);
            fbwin = Integer.toString(fbw);
            fbl = fbl + 1;
            hsay.setFbl(fbl);
            fblose = Integer.toString(fbl);
            aefespuan = aefespuan + 3;
            hsay.setAefespuan(aefespuan);
            aefespuann = Integer.toString(aefespuan);
            aefesw = aefesw + 1;
            hsay.setAefesw(aefesw);
            aefeswin = Integer.toString(aefesw);
            aefesl = aefesl + 0;
            hsay.setAefesl(aefesl);
            aefeslose = Integer.toString(aefesl);
            myDb.updateData("100", fbpuann, fbwin, fblose);
            myDb.updateData("103", aefespuann, aefeswin, aefeslose);
        }


        if (otherteam1points > otherteam2points){

            gspuan = gspuan + 3;
            hsay.setGspuan(gspuan);
            gspuann = Integer.toString(gspuan);
            gsw = gsw + 1;
            hsay.setGsw(gsw);
            gswin = Integer.toString(gsw);
            gsl = gsl + 0;
            hsay.setGsl(gsl);
            gslose = Integer.toString(gsl);
            bjkpuan = bjkpuan + 0;
            hsay.setBjkpuan(bjkpuan);
            bjkpuann = Integer.toString(bjkpuan);
            bjkw = bjkw + 0;
            hsay.setBjkw(bjkw);
            bjkwin = Integer.toString(bjkw);
            bjkl = bjkl + 1;
            hsay.setBjkl(bjkl);
            bjklose = Integer.toString(bjkl);
            myDb.updateData("101", gspuann, gswin, gslose);
            myDb.updateData("102", bjkpuann, bjkwin, bjklose);

        }
        if (otherteam2points > otherteam1points){

            gspuan = gspuan + 0;
            hsay.setGspuan(gspuan);
            gspuann = Integer.toString(gspuan);
            gsw = gsw + 0;
            hsay.setGsw(gsw);
            gswin = Integer.toString(gsw);
            gsl = gsl + 1;
            hsay.setGsl(gsl);
            gslose = Integer.toString(gsl);
            bjkpuan = bjkpuan + 3;
            hsay.setBjkpuan(bjkpuan);
            bjkpuann = Integer.toString(bjkpuan);
            bjkw = bjkw + 1;
            hsay.setBjkw(bjkw);
            bjkwin = Integer.toString(bjkw);
            bjkl = bjkl + 0;
            hsay.setBjkl(bjkl);
            bjklose = Integer.toString(bjkl);
            myDb.updateData("101", gspuann, gswin, gslose);
            myDb.updateData("102", bjkpuann, bjkwin, bjklose);

        }



    }
    private void dataupdatefunction2(){
        int fbpuan =hsay.getFbpuan();
        int aefespuan = hsay.getAefespuan();
        int bjkpuan= hsay.getBjkpuan();
        int gspuan = hsay.getGspuan();
        int fbw = hsay.getFbw();
        int gsw=hsay.getGsw();
        int bjkw = hsay.getBjkw();
        int aefesw = hsay.getAefesw();
        int fbl=hsay.getFbl();
        int gsl=hsay.getGsl();
        int bjkl=hsay.getBjkl();
        int aefesl=hsay.getAefesl();

        String fbpuann = Integer.toString(fbpuan);
        String gspuann = Integer.toString(gspuan);
        String bjkpuann = Integer.toString(bjkpuan);
        String aefespuann = Integer.toString(aefespuan);
        String fbwin = Integer.toString(fbw);
        String gswin = Integer.toString(gsw);
        String bjkwin = Integer.toString(bjkw);
        String aefeswin = Integer.toString(aefesw);
        String fblose = Integer.toString(fbl);
        String gslose = Integer.toString(gsl);
        String bjklose = Integer.toString(bjkl);
        String aefeslose = Integer.toString(aefesl);


        if (hometeampoints > awayteampoints) {
            fbpuan = fbpuan + 3;
            hsay.setFbpuan(fbpuan);
            fbpuann = Integer.toString(fbpuan);
            fbw = fbw + 1;
            hsay.setFbw(fbw);
            fbwin = Integer.toString(fbw);
            fbl = fbl + 0;
            hsay.setFbl(fbl);
            fblose = Integer.toString(fbl);
            gspuan = gspuan + 0;
            hsay.setGspuan(gspuan);
            gspuann = Integer.toString(gspuan);
            gsw = gsw + 0;
            hsay.setGsw(gsw);
            gswin = Integer.toString(gsw);
            gsl = gsl + 1;
            hsay.setGsl(gsl);
            gslose = Integer.toString(gsl);
            myDb.updateData("100", fbpuann, fbwin, fblose);
            myDb.updateData("101", gspuann, gswin, gslose);
        }
        if(awayteampoints > hometeampoints){

            fbpuan = fbpuan + 0;
            hsay.setFbpuan(fbpuan);
            fbpuann = Integer.toString(fbpuan);
            fbw = fbw + 0;
            hsay.setFbw(fbw);
            fbwin = Integer.toString(fbw);
            fbl = fbl + 1;
            hsay.setFbl(fbl);
            fblose = Integer.toString(fbl);
            gspuan = gspuan + 3;
            hsay.setGspuan(gspuan);
            gspuann = Integer.toString(gspuan);
            gsw = gsw + 1;
            hsay.setGsw(gsw);
            gswin = Integer.toString(gsw);
            gsl = gsl + 0;
            hsay.setGsl(gsl);
            gslose = Integer.toString(gsl);
            myDb.updateData("100", fbpuann, fbwin, fblose);
            myDb.updateData("101", gspuann, gswin, gslose);
        }


        if (otherteam1points > otherteam2points){

            aefespuan = aefespuan + 3;
            hsay.setAefespuan(aefespuan);
            aefespuann = Integer.toString(aefespuan);
            aefesw = aefesw + 1;
            hsay.setAefesw(aefesw);
            aefeswin = Integer.toString(aefesw);
            aefesl = aefesl + 0;
            hsay.setAefesl(aefesl);
            aefeslose = Integer.toString(aefesl);
            bjkpuan = bjkpuan + 0;
            hsay.setBjkpuan(bjkpuan);
            bjkpuann = Integer.toString(bjkpuan);
            bjkw = bjkw + 0;
            hsay.setBjkw(bjkw);
            bjkwin = Integer.toString(bjkw);
            bjkl = bjkl + 1;
            hsay.setBjkl(bjkl);
            bjklose = Integer.toString(bjkl);
            myDb.updateData("103", aefespuann, aefeswin, aefeslose);
            myDb.updateData("102", bjkpuann, bjkwin, bjklose);

        }
        if (otherteam2points > otherteam1points){

            aefespuan = aefespuan + 0;
            hsay.setAefespuan(aefespuan);
            aefespuann = Integer.toString(aefespuan);
            aefesw = aefesw + 0;
            hsay.setAefesw(aefesw);
            aefeswin = Integer.toString(aefesw);
            aefesl = aefesl + 1;
            hsay.setAefesl(aefesl);
            aefeslose = Integer.toString(aefesl);
            bjkpuan = bjkpuan + 3;
            hsay.setBjkpuan(bjkpuan);
            bjkpuann = Integer.toString(bjkpuan);
            bjkw = bjkw + 1;
            hsay.setBjkw(bjkw);
            bjkwin = Integer.toString(bjkw);
            bjkl = bjkl + 0;
            hsay.setBjkl(bjkl);
            bjklose = Integer.toString(bjkl);
            myDb.updateData("103", aefespuann, aefeswin, aefeslose);
            myDb.updateData("102", bjkpuann, bjkwin, bjklose);

        }



    }
    private void dataupdatefunction3(){
        int fbpuan =hsay.getFbpuan();
        int aefespuan = hsay.getAefespuan();
        int bjkpuan= hsay.getBjkpuan();
        int gspuan = hsay.getGspuan();
        int fbw = hsay.getFbw();
        int gsw=hsay.getGsw();
        int bjkw = hsay.getBjkw();
        int aefesw = hsay.getAefesw();
        int fbl=hsay.getFbl();
        int gsl=hsay.getGsl();
        int bjkl=hsay.getBjkl();
        int aefesl=hsay.getAefesl();

        String fbpuann = Integer.toString(fbpuan);
        String gspuann = Integer.toString(gspuan);
        String bjkpuann = Integer.toString(bjkpuan);
        String aefespuann = Integer.toString(aefespuan);
        String fbwin = Integer.toString(fbw);
        String gswin = Integer.toString(gsw);
        String bjkwin = Integer.toString(bjkw);
        String aefeswin = Integer.toString(aefesw);
        String fblose = Integer.toString(fbl);
        String gslose = Integer.toString(gsl);
        String bjklose = Integer.toString(bjkl);
        String aefeslose = Integer.toString(aefesl);


        if (hometeampoints > awayteampoints) {
            fbpuan = fbpuan + 3;
            hsay.setFbpuan(fbpuan);
            fbpuann = Integer.toString(fbpuan);
            fbw = fbw + 1;
            hsay.setFbw(fbw);
            fbwin = Integer.toString(fbw);
            fbl = fbl + 0;
            hsay.setFbl(fbl);
            fblose = Integer.toString(fbl);
            bjkpuan = bjkpuan + 0;
            hsay.setBjkpuan(bjkpuan);
            bjkpuann = Integer.toString(bjkpuan);
            bjkw = bjkw + 0;
            hsay.setBjkw(bjkw);
            bjkwin = Integer.toString(bjkw);
            bjkl = bjkl + 1;
            hsay.setBjkl(bjkl);
            bjklose = Integer.toString(bjkl);
            myDb.updateData("100", fbpuann, fbwin, fblose);
            myDb.updateData("102", bjkpuann, bjkwin, bjklose);
        }
        if(awayteampoints > hometeampoints){

            fbpuan = fbpuan + 0;
            hsay.setFbpuan(fbpuan);
            fbpuann = Integer.toString(fbpuan);
            fbw = fbw + 0;
            hsay.setFbw(fbw);
            fbwin = Integer.toString(fbw);
            fbl = fbl + 1;
            hsay.setFbl(fbl);
            fblose = Integer.toString(fbl);
            bjkpuan = bjkpuan + 3;
            hsay.setBjkpuan(bjkpuan);
            bjkpuann = Integer.toString(bjkpuan);
            bjkw = bjkw + 1;
            hsay.setBjkw(bjkw);
            bjkwin = Integer.toString(bjkw);
            bjkl = bjkl + 0;
            hsay.setBjkl(bjkl);
            bjklose = Integer.toString(bjkl);
            myDb.updateData("100", fbpuann, fbwin, fblose);
            myDb.updateData("102", bjkpuann, bjkwin, bjklose);
        }


        if (otherteam1points > otherteam2points){
            gspuan = gspuan + 3;
            hsay.setGspuan(gspuan);
            gspuann = Integer.toString(gspuan);
            gsw = gsw + 1;
            hsay.setGsw(gsw);
            gswin = Integer.toString(gsw);
            gsl = gsl + 0;
            hsay.setGsl(gsl);
            gslose = Integer.toString(gsl);

            aefespuan = aefespuan + 0;
            hsay.setAefespuan(aefespuan);
            aefespuann = Integer.toString(aefespuan);
            aefesw = aefesw + 0;
            hsay.setAefesw(aefesw);
            aefeswin = Integer.toString(aefesw);
            aefesl = aefesl + 1;
            hsay.setAefesl(aefesl);
            aefeslose = Integer.toString(aefesl);
            myDb.updateData("103", aefespuann, aefeswin, aefeslose);
            myDb.updateData("101", gspuann, gswin, gslose);

        }
        if (otherteam2points > otherteam1points){

            aefespuan = aefespuan + 3;
            hsay.setAefespuan(aefespuan);
            aefespuann = Integer.toString(aefespuan);
            aefesw = aefesw + 1;
            hsay.setAefesw(aefesw);
            aefeswin = Integer.toString(aefesw);
            aefesl = aefesl + 0;
            hsay.setAefesl(aefesl);
            aefeslose = Integer.toString(aefesl);
            gspuan = gspuan + 0;
            hsay.setGspuan(gspuan);
            gspuann = Integer.toString(gspuan);
            gsw = gsw + 0;
            hsay.setGsw(gsw);
            gswin = Integer.toString(gsw);
            gsl = gsl + 1;
            hsay.setGsl(gsl);
            gslose = Integer.toString(gsl);
            myDb.updateData("103", aefespuann, aefeswin, aefeslose);
            myDb.updateData("101", gspuann, gswin, gslose);

        }



    }
    private void dataupdatefunction4(){
        int fbpuan =hsay.getFbpuan();
        int aefespuan = hsay.getAefespuan();
        int bjkpuan= hsay.getBjkpuan();
        int gspuan = hsay.getGspuan();
        int fbw = hsay.getFbw();
        int gsw=hsay.getGsw();
        int bjkw = hsay.getBjkw();
        int aefesw = hsay.getAefesw();
        int fbl=hsay.getFbl();
        int gsl=hsay.getGsl();
        int bjkl=hsay.getBjkl();
        int aefesl=hsay.getAefesl();

        String fbpuann = Integer.toString(fbpuan);
        String gspuann = Integer.toString(gspuan);
        String bjkpuann = Integer.toString(bjkpuan);
        String aefespuann = Integer.toString(aefespuan);
        String fbwin = Integer.toString(fbw);
        String gswin = Integer.toString(gsw);
        String bjkwin = Integer.toString(bjkw);
        String aefeswin = Integer.toString(aefesw);
        String fblose = Integer.toString(fbl);
        String gslose = Integer.toString(gsl);
        String bjklose = Integer.toString(bjkl);
        String aefeslose = Integer.toString(aefesl);


        if (hometeampoints > awayteampoints) {
            fbpuan = fbpuan + 3;
            hsay.setFbpuan(fbpuan);
            fbpuann = Integer.toString(fbpuan);
            fbw = fbw + 1;
            hsay.setFbw(fbw);
            fbwin = Integer.toString(fbw);
            fbl = fbl + 0;
            hsay.setFbl(fbl);
            fblose = Integer.toString(fbl);
            aefespuan = aefespuan + 0;
            hsay.setAefespuan(aefespuan);
            aefespuann = Integer.toString(aefespuan);
            aefesw = aefesw + 0;
            hsay.setAefesw(aefesw);
            aefeswin = Integer.toString(aefesw);
            aefesl = aefesl + 1;
            hsay.setAefesl(aefesl);
            aefeslose = Integer.toString(aefesl);
            myDb.updateData("100", fbpuann, fbwin, fblose);
            myDb.updateData("103", aefespuann, aefeswin, aefeslose);
        }
        if(awayteampoints > hometeampoints){

            fbpuan = fbpuan + 0;
            hsay.setFbpuan(fbpuan);
            fbpuann = Integer.toString(fbpuan);
            fbw = fbw + 0;
            hsay.setFbw(fbw);
            fbwin = Integer.toString(fbw);
            fbl = fbl + 1;
            hsay.setFbl(fbl);
            fblose = Integer.toString(fbl);
            aefespuan = aefespuan + 3;
            hsay.setAefespuan(aefespuan);
            aefespuann = Integer.toString(aefespuan);
            aefesw = aefesw + 1;
            hsay.setAefesw(aefesw);
            aefeswin = Integer.toString(aefesw);
            aefesl = aefesl + 0;
            hsay.setAefesl(aefesl);
            aefeslose = Integer.toString(aefesl);
            myDb.updateData("100", fbpuann, fbwin, fblose);
            myDb.updateData("103", aefespuann, aefeswin, aefeslose);
        }


        if (otherteam1points > otherteam2points){

            gspuan = gspuan + 3;
            hsay.setGspuan(gspuan);
            gspuann = Integer.toString(gspuan);
            gsw = gsw + 1;
            hsay.setGsw(gsw);
            gswin = Integer.toString(gsw);
            gsl = gsl + 0;
            hsay.setGsl(gsl);
            gslose = Integer.toString(gsl);
            bjkpuan = bjkpuan + 0;
            hsay.setBjkpuan(bjkpuan);
            bjkpuann = Integer.toString(bjkpuan);
            bjkw = bjkw + 0;
            hsay.setBjkw(bjkw);
            bjkwin = Integer.toString(bjkw);
            bjkl = bjkl + 1;
            hsay.setBjkl(bjkl);
            bjklose = Integer.toString(bjkl);
            myDb.updateData("101", gspuann, gswin, gslose);
            myDb.updateData("102", bjkpuann, bjkwin, bjklose);

        }
        if (otherteam2points > otherteam1points){

            gspuan = gspuan + 0;
            hsay.setGspuan(gspuan);
            gspuann = Integer.toString(gspuan);
            gsw = gsw + 0;
            hsay.setGsw(gsw);
            gswin = Integer.toString(gsw);
            gsl = gsl + 1;
            hsay.setGsl(gsl);
            gslose = Integer.toString(gsl);
            bjkpuan = bjkpuan + 3;
            hsay.setBjkpuan(bjkpuan);
            bjkpuann = Integer.toString(bjkpuan);
            bjkw = bjkw + 1;
            hsay.setBjkw(bjkw);
            bjkwin = Integer.toString(bjkw);
            bjkl = bjkl + 0;
            hsay.setBjkl(bjkl);
            bjklose = Integer.toString(bjkl);
            myDb.updateData("101", gspuann, gswin, gslose);
            myDb.updateData("102", bjkpuann, bjkwin, bjklose);

        }



    }
    private void dataupdatefunction5(){
        int fbpuan =hsay.getFbpuan();
        int aefespuan = hsay.getAefespuan();
        int bjkpuan= hsay.getBjkpuan();
        int gspuan = hsay.getGspuan();
        int fbw = hsay.getFbw();
        int gsw=hsay.getGsw();
        int bjkw = hsay.getBjkw();
        int aefesw = hsay.getAefesw();
        int fbl=hsay.getFbl();
        int gsl=hsay.getGsl();
        int bjkl=hsay.getBjkl();
        int aefesl=hsay.getAefesl();

        String fbpuann = Integer.toString(fbpuan);
        String gspuann = Integer.toString(gspuan);
        String bjkpuann = Integer.toString(bjkpuan);
        String aefespuann = Integer.toString(aefespuan);
        String fbwin = Integer.toString(fbw);
        String gswin = Integer.toString(gsw);
        String bjkwin = Integer.toString(bjkw);
        String aefeswin = Integer.toString(aefesw);
        String fblose = Integer.toString(fbl);
        String gslose = Integer.toString(gsl);
        String bjklose = Integer.toString(bjkl);
        String aefeslose = Integer.toString(aefesl);


        if (hometeampoints > awayteampoints) {
            fbpuan = fbpuan + 3;
            hsay.setFbpuan(fbpuan);
            fbpuann = Integer.toString(fbpuan);
            fbw = fbw + 1;
            hsay.setFbw(fbw);
            fbwin = Integer.toString(fbw);
            fbl = fbl + 0;
            hsay.setFbl(fbl);
            fblose = Integer.toString(fbl);
            gspuan = gspuan + 0;
            hsay.setGspuan(gspuan);
            gspuann = Integer.toString(gspuan);
            gsw = gsw + 0;
            hsay.setGsw(gsw);
            gswin = Integer.toString(gsw);
            gsl = gsl + 1;
            hsay.setGsl(gsl);
            gslose = Integer.toString(gsl);
            myDb.updateData("100", fbpuann, fbwin, fblose);
            myDb.updateData("101", gspuann, gswin, gslose);
        }
        if(awayteampoints > hometeampoints){

            fbpuan = fbpuan + 0;
            hsay.setFbpuan(fbpuan);
            fbpuann = Integer.toString(fbpuan);
            fbw = fbw + 0;
            hsay.setFbw(fbw);
            fbwin = Integer.toString(fbw);
            fbl = fbl + 1;
            hsay.setFbl(fbl);
            fblose = Integer.toString(fbl);
            gspuan = gspuan + 3;
            hsay.setGspuan(gspuan);
            gspuann = Integer.toString(gspuan);
            gsw = gsw + 1;
            hsay.setGsw(gsw);
            gswin = Integer.toString(gsw);
            gsl = gsl + 0;
            hsay.setGsl(gsl);
            gslose = Integer.toString(gsl);
            myDb.updateData("100", fbpuann, fbwin, fblose);
            myDb.updateData("101", gspuann, gswin, gslose);
        }


        if (otherteam1points > otherteam2points){

            aefespuan = aefespuan + 3;
            hsay.setAefespuan(aefespuan);
            aefespuann = Integer.toString(aefespuan);
            aefesw = aefesw + 1;
            hsay.setAefesw(aefesw);
            aefeswin = Integer.toString(aefesw);
            aefesl = aefesl + 0;
            hsay.setAefesl(aefesl);
            aefeslose = Integer.toString(aefesl);
            bjkpuan = bjkpuan + 0;
            hsay.setBjkpuan(bjkpuan);
            bjkpuann = Integer.toString(bjkpuan);
            bjkw = bjkw + 0;
            hsay.setBjkw(bjkw);
            bjkwin = Integer.toString(bjkw);
            bjkl = bjkl + 1;
            hsay.setBjkl(bjkl);
            bjklose = Integer.toString(bjkl);
            myDb.updateData("103", aefespuann, aefeswin, aefeslose);
            myDb.updateData("102", bjkpuann, bjkwin, bjklose);

        }
        if (otherteam2points > otherteam1points){

            aefespuan = aefespuan + 0;
            hsay.setAefespuan(aefespuan);
            aefespuann = Integer.toString(aefespuan);
            aefesw = aefesw + 0;
            hsay.setAefesw(aefesw);
            aefeswin = Integer.toString(aefesw);
            aefesl = aefesl + 1;
            hsay.setAefesl(aefesl);
            aefeslose = Integer.toString(aefesl);
            bjkpuan = bjkpuan + 3;
            hsay.setBjkpuan(bjkpuan);
            bjkpuann = Integer.toString(bjkpuan);
            bjkw = bjkw + 1;
            hsay.setBjkw(bjkw);
            bjkwin = Integer.toString(bjkw);
            bjkl = bjkl + 0;
            hsay.setBjkl(bjkl);
            bjklose = Integer.toString(bjkl);
            myDb.updateData("103", aefespuann, aefeswin, aefeslose);
            myDb.updateData("102", bjkpuann, bjkwin, bjklose);

        }



    }
    private void dataupdatefunction6(){
        int fbpuan =hsay.getFbpuan();
        int aefespuan = hsay.getAefespuan();
        int bjkpuan= hsay.getBjkpuan();
        int gspuan = hsay.getGspuan();
        int fbw = hsay.getFbw();
        int gsw=hsay.getGsw();
        int bjkw = hsay.getBjkw();
        int aefesw = hsay.getAefesw();
        int fbl=hsay.getFbl();
        int gsl=hsay.getGsl();
        int bjkl=hsay.getBjkl();
        int aefesl=hsay.getAefesl();

        String fbpuann = Integer.toString(fbpuan);
        String gspuann = Integer.toString(gspuan);
        String bjkpuann = Integer.toString(bjkpuan);
        String aefespuann = Integer.toString(aefespuan);
        String fbwin = Integer.toString(fbw);
        String gswin = Integer.toString(gsw);
        String bjkwin = Integer.toString(bjkw);
        String aefeswin = Integer.toString(aefesw);
        String fblose = Integer.toString(fbl);
        String gslose = Integer.toString(gsl);
        String bjklose = Integer.toString(bjkl);
        String aefeslose = Integer.toString(aefesl);


        if (hometeampoints > awayteampoints) {
            fbpuan = fbpuan + 3;
            hsay.setFbpuan(fbpuan);
            fbpuann = Integer.toString(fbpuan);
            fbw = fbw + 1;
            hsay.setFbw(fbw);
            fbwin = Integer.toString(fbw);
            fbl = fbl + 0;
            hsay.setFbl(fbl);
            fblose = Integer.toString(fbl);
            bjkpuan = bjkpuan + 0;
            hsay.setBjkpuan(bjkpuan);
            bjkpuann = Integer.toString(bjkpuan);
            bjkw = bjkw + 0;
            hsay.setBjkw(bjkw);
            bjkwin = Integer.toString(bjkw);
            bjkl = bjkl + 1;
            hsay.setBjkl(bjkl);
            bjklose = Integer.toString(bjkl);
            myDb.updateData("100", fbpuann, fbwin, fblose);
            myDb.updateData("102", bjkpuann, bjkwin, bjklose);
        }
        if(awayteampoints > hometeampoints){

            fbpuan = fbpuan + 0;
            hsay.setFbpuan(fbpuan);
            fbpuann = Integer.toString(fbpuan);
            fbw = fbw + 0;
            hsay.setFbw(fbw);
            fbwin = Integer.toString(fbw);
            fbl = fbl + 1;
            hsay.setFbl(fbl);
            fblose = Integer.toString(fbl);
            bjkpuan = bjkpuan + 3;
            hsay.setBjkpuan(bjkpuan);
            bjkpuann = Integer.toString(bjkpuan);
            bjkw = bjkw + 1;
            hsay.setBjkw(bjkw);
            bjkwin = Integer.toString(bjkw);
            bjkl = bjkl + 0;
            hsay.setBjkl(bjkl);
            bjklose = Integer.toString(bjkl);
            myDb.updateData("100", fbpuann, fbwin, fblose);
            myDb.updateData("102", bjkpuann, bjkwin, bjklose);
        }


        if (otherteam1points > otherteam2points){
            gspuan = gspuan + 3;
            hsay.setGspuan(gspuan);
            gspuann = Integer.toString(gspuan);
            gsw = gsw + 1;
            hsay.setGsw(gsw);
            gswin = Integer.toString(gsw);
            gsl = gsl + 0;
            hsay.setGsl(gsl);
            gslose = Integer.toString(gsl);

            aefespuan = aefespuan + 0;
            hsay.setAefespuan(aefespuan);
            aefespuann = Integer.toString(aefespuan);
            aefesw = aefesw + 0;
            hsay.setAefesw(aefesw);
            aefeswin = Integer.toString(aefesw);
            aefesl = aefesl + 1;
            hsay.setAefesl(aefesl);
            aefeslose = Integer.toString(aefesl);
            myDb.updateData("103", aefespuann, aefeswin, aefeslose);
            myDb.updateData("101", gspuann, gswin, gslose);

        }
        if (otherteam2points > otherteam1points){

            aefespuan = aefespuan + 3;
            hsay.setAefespuan(aefespuan);
            aefespuann = Integer.toString(aefespuan);
            aefesw = aefesw + 1;
            hsay.setAefesw(aefesw);
            aefeswin = Integer.toString(aefesw);
            aefesl = aefesl + 0;
            hsay.setAefesl(aefesl);
            aefeslose = Integer.toString(aefesl);
            gspuan = gspuan + 0;
            hsay.setGspuan(gspuan);
            gspuann = Integer.toString(gspuan);
            gsw = gsw + 0;
            hsay.setGsw(gsw);
            gswin = Integer.toString(gsw);
            gsl = gsl + 1;
            hsay.setGsl(gsl);
            gslose = Integer.toString(gsl);
            myDb.updateData("103", aefespuann, aefeswin, aefeslose);
            myDb.updateData("101", gspuann, gswin, gslose);

        }



    }

    private  void resetdatabase(){

        myDb.updateData("100", "0", "0", "0");
        myDb.updateData("101", "0", "0", "0");
        myDb.updateData("102", "0", "0", "0");
        myDb.updateData("103", "0", "0", "0");

    }
}





