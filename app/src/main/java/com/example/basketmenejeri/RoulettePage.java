package com.example.basketmenejeri;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.basketmenejeri.Adapters.Clup;
import com.example.basketmenejeri.DataBase.DataBaseHelper;

import java.io.IOException;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoulettePage extends AppCompatActivity {
    Button kırmızı;
    Button siyah;
    Button yesil;
    Boolean kırmızık=false;
    Boolean siyahh =false;
    Boolean yesill = false;
    Cursor f = null;
    String username;
    TextView usersurname;
    int cost;
    Integer flags;
    String teamname;
    Clup fb;
    DataBaseHelper myDb;
    String text = null;
    int i;
    private static final String[] sectors = { "red", "black",
            "red", "black", "red", "black", "red", "black", "red",
            "black", "red","black", "red", "black", "red", "black",
            "red", "black", "red", "black", "red", "black",
            "red", "black", "red", "black", "red", "black",
            "red", "black", "red", "black", "red", "black",
            "red", "black", "green"
    };
    @BindView(R.id.spinBtn)
    Button spinBtn;
    @BindView(R.id.resultTv)
    TextView resultTv;
    @BindView(R.id.wheel)
    ImageView wheel;


    private static final Random RANDOM = new Random();
    private int degree = 0, degreeOld = 0;

    private static final float HALF_SECTOR = 360f / 37f / 2f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette_page);
        flags = getIntent().getExtras().getInt("clupflag");
        final String teamname = getIntent().getExtras().getString("clupname");
        username=getIntent().getExtras().getString("usernamesurname");
        ButterKnife.bind(this);
        kırmızı = (Button) findViewById(R.id.redbutton);
        siyah = (Button) findViewById(R.id.blackbutton);
        yesil = (Button) findViewById(R.id.greenbuutton);

        DataBaseHelper myDbHelper = new DataBaseHelper(RoulettePage.this);
        Haftasayma hsay= new Haftasayma();
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
        if (f.moveToFirst() ) {

            do {

                fb = new Clup(f.getString(2), f.getString(4), f.getString(5), f.getString(6));


            } while (f.moveToNext() );


        }
        myDb = new DataBaseHelper(this);

        kırmızı.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                kırmızı.setBackgroundColor(Color.parseColor("#FF0000"));
                siyah.setBackgroundColor(Color.parseColor("#808080"));
                yesil.setBackgroundColor(Color.parseColor("#808080"));
                kırmızık =true;
                siyahh =false;
                yesill = false;
                yesil.setEnabled(false);
                siyah.setEnabled(false);


            }
        } );
        siyah.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                siyah.setBackgroundColor(Color.parseColor("#000000"));
                kırmızı.setBackgroundColor(Color.parseColor("#808080"));
                yesil.setBackgroundColor(Color.parseColor("#808080"));
                kırmızık =false;
                siyahh =true;
                yesill = false;
                yesil.setEnabled(false);
                kırmızı.setEnabled(false);
            }
        } );
        yesil.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                yesil.setBackgroundColor(Color.parseColor("#5bcc27"));
                siyah.setBackgroundColor(Color.parseColor("#808080"));
                kırmızı.setBackgroundColor(Color.parseColor("#808080"));
                kırmızık=false;
                siyahh =false;
                yesill = true;
                kırmızı.setEnabled(false);
                siyah.setEnabled(false);
            }
        } );
    }


    @OnClick(R.id.spinBtn)
    public void spin(View v) {
        degreeOld = degree % 360;
        degree = RANDOM.nextInt(360) + 720;

        RotateAnimation rotateAnim = new RotateAnimation(degreeOld, degree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setDuration(3600);
        rotateAnim.setFillAfter(true);
        rotateAnim.setInterpolator(new DecelerateInterpolator());
        rotateAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                resultTv.setText("");
                String scost = fb.getClupname();
                cost = Integer.parseInt(scost);
                cost = cost - 1;
                myDb.updateDataTeam("100", Integer.toString(cost));

            }

            @Override

            public void onAnimationEnd(Animation animation) {

                resultTv.setText(getSector(360 - (degree % 360)));
                if(text == "red" && kırmızık == true ){
                    Toast.makeText(getApplicationContext(),"Kazandınız",Toast.LENGTH_LONG).show();
                    cost =cost + 3;
                    myDb.updateDataTeam("100", Integer.toString(cost));
                    Handler hndler= new Handler();
                    hndler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intocan = new Intent(RoulettePage.this , IconTextTabActivity.class);
                            flags = getIntent().getExtras().getInt("clupflag");
                             String teamname = getIntent().getExtras().getString("clupname");
                            username=getIntent().getExtras().getString("usernamesurname");
                            intocan.putExtra( "clupname",teamname );
                            intocan.putExtra( "clupflag",flags);
                            intocan.putExtra( "usernamesurname" , username + usersurname );
                            intocan.putExtra( "clupname",teamname );

                            startActivity(intocan);
                        }

                    },4000);

                }
                if(text == "green" && yesill == true ){
                    Toast.makeText(getApplicationContext(),"Kazandınız",Toast.LENGTH_LONG).show();
                    cost =cost + 21;
                    myDb.updateDataTeam("100", Integer.toString(cost));
                    Handler hndler= new Handler();
                    hndler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intocan = new Intent(RoulettePage.this , IconTextTabActivity.class);
                            flags = getIntent().getExtras().getInt("clupflag");
                            String teamname = getIntent().getExtras().getString("clupname");
                            username=getIntent().getExtras().getString("usernamesurname");
                            intocan.putExtra( "clupname",teamname );
                            intocan.putExtra( "clupflag",flags);
                            intocan.putExtra( "usernamesurname" , username + usersurname );
                            intocan.putExtra( "clupname",teamname );

                            startActivity(intocan);
                        }

                    },4000);

                }
                if(text == "black" && siyahh == true ){
                    Toast.makeText(getApplicationContext(),"Kazandınız",Toast.LENGTH_LONG).show();
                    cost =cost + 3;
                    myDb.updateDataTeam("100", Integer.toString(cost));
                    Handler hndler= new Handler();
                    hndler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intocan = new Intent(RoulettePage.this , IconTextTabActivity.class);
                            flags = getIntent().getExtras().getInt("clupflag");
                            String teamname = getIntent().getExtras().getString("clupname");
                            username=getIntent().getExtras().getString("usernamesurname");
                            intocan.putExtra( "clupname",teamname );
                            intocan.putExtra( "clupflag",flags);
                            intocan.putExtra( "usernamesurname" , username + usersurname );
                            intocan.putExtra( "clupname",teamname );

                            startActivity(intocan);
                        }

                    },4000);
                }
                kırmızı.setEnabled(true);
                siyah.setEnabled(true);
                yesil.setEnabled(true);
                Handler hndler= new Handler();
                hndler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intocan = new Intent(RoulettePage.this , IconTextTabActivity.class);
                        flags = getIntent().getExtras().getInt("clupflag");
                        String teamname = getIntent().getExtras().getString("clupname");
                        username=getIntent().getExtras().getString("usernamesurname");
                        intocan.putExtra( "clupname",teamname );
                        intocan.putExtra( "clupflag",flags);
                        intocan.putExtra( "usernamesurname" , username + usersurname );
                        intocan.putExtra( "clupname",teamname );

                        startActivity(intocan);
                    }

                },4000);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        wheel.startAnimation(rotateAnim);
    }

    private String getSector(int degrees) {
        i=0;


        do {
            float start = HALF_SECTOR * (i * 2 + 1);
            float end = HALF_SECTOR * (i * 2 + 3);

            if (degrees >= start && degrees < end) {



                text = sectors[i];


            }

            i++;


        } while (text == null  &&  i < sectors.length);

        return text;
    }
}