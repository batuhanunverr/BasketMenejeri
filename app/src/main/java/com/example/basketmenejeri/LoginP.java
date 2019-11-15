package com.example.basketmenejeri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ImageButton;


public class LoginP extends AppCompatActivity {
    Button aboutbutn;
    Button loadcareerbutton;
    Button newcareer1;
    ImageButton volbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_p);

        volbutton = (ImageButton) findViewById(R.id.volibtn);
        aboutbutn = (Button) findViewById(R.id.aboutbtn);
        loadcareerbutton=(Button) findViewById(R.id.loadcareer);
        newcareer1 = (Button)findViewById(R.id.newcareerbtn);

        aboutbutn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"* Example Games *",Toast.LENGTH_LONG).show();
            }
        });

        volbutton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                final Integer resource = (Integer)volbutton.getTag();

                if (resource == R.drawable.mute){
                    volbutton.setImageResource(R.drawable.vol);

                }
                if (resource == R.drawable.vol) {
                    volbutton.setImageResource( R.drawable.mute );
                }
            }
        });


        newcareer1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                Intent intocan = new Intent(LoginP.this , NewCareerPage.class);
                startActivity(intocan);
            }
        } );

    }


}

