package com.example.basketmenejeri;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;


public class Taktik_Page extends AppCompatActivity {
ImageButton hucumbutton;
ImageButton defansbutton;
boolean ataksavunma;
int pressdegeri;
SeekBar seekBar;
ImageButton alanbutton;
ImageButton adambutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taktik__page);
        hucumbutton = (ImageButton) findViewById(R.id.hucumbutton);
        defansbutton = (ImageButton) findViewById(R.id.defansbutton);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        alanbutton =(ImageButton) findViewById(R.id.alansavunmasıbutton) ;
        adambutton = (ImageButton) findViewById(R.id.adamadamabutton);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar seekBar){

            }
            public void onStartTrackingTouch(SeekBar seekBar){

            }
            public void onProgressChanged(SeekBar seekBar ,int progress, boolean fromUser ){

                pressdegeri = seekBar.getProgress();

            }
        });

        hucumbutton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                hucumbutton.setBackgroundColor(Color.parseColor("#2D660C"));
                defansbutton.setBackgroundColor(Color.parseColor("#9e9e9e"));
                ataksavunma=true;

                Intent intocane = new Intent(Taktik_Page.this,GamePageActivity.class);
                intocane.putExtra( "ataksavunma",ataksavunma);
                intocane.putExtra("pressdegerii", pressdegeri);


            }
        } );
        defansbutton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                defansbutton.setBackgroundColor(Color.parseColor("#2D660C"));
                hucumbutton.setBackgroundColor(Color.parseColor("#9e9e9e"));
                ataksavunma=false;
                Intent i = new Intent(Taktik_Page.this,GamePageActivity.class);
                i.putExtra("ataksavunma",ataksavunma);
                i.putExtra("pressdegerii", pressdegeri);
            }
        } );
        adambutton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                adambutton.setBackgroundColor(Color.parseColor("#2D660C"));
                alanbutton.setBackgroundColor(Color.parseColor("#9e9e9e"));
               // adambutton=true;

                Intent intocane = new Intent(Taktik_Page.this,GamePageActivity.class);
                //intocane.putExtra( "ataksavunma",adambutton);
               // intocane.putExtra("pressdegerii", pressdegeri);


            }
        } );
        alanbutton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                alanbutton.setBackgroundColor(Color.parseColor("#2D660C"));
                adambutton.setBackgroundColor(Color.parseColor("#9e9e9e"));
               // ataksavunma=true;

                /*Intent intocane = new Intent(Taktik_Page.this,GamePageActivity.class);
                intocane.putExtra( "ataksavunma",ataksavunma);
                intocane.putExtra("pressdegerii", pressdegeri);

*/
            }
        } );
    }
}
