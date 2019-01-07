package com.fbhacks.standbyme;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class DummyHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button mira = (Button) findViewById(R.id.button);
        mira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToNextActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goToNextActivity);
            }
        });

        Button parita = (Button) findViewById(R.id.button2);
        parita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent goToNextActivity = new Intent(getApplicationContext(), Text.class);
            startActivity(goToNextActivity);
            }
        });

        Button vruti = (Button) findViewById(R.id.button3);
        vruti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToNextActivity = new Intent(getApplicationContext(), GeoLocation.class);
                startActivity(goToNextActivity);
            }
        });


        Button Ringtone = (Button) findViewById(R.id.button6);
        Ringtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToNextActivity = new Intent(getApplicationContext(), Ringtone.class);
                startActivity(goToNextActivity);
            }
        });



        Button SReco = (Button) findViewById(R.id.button7);
        SReco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToNextActivity = new Intent(getApplicationContext(), SpeechText.class);
                startActivity(goToNextActivity);
            }
        });

    }

}
