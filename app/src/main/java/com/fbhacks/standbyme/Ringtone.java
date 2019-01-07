package com.fbhacks.standbyme;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Ringtone extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringtone);

        Button one = (Button)this.findViewById(R.id.button5);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.bell);
        one.setOnClickListener(new OnClickListener(){

            public void onClick(View v) {
                mp.start();
            }
        });
    }

    public void playcall (View view){
        Intent intent = new Intent (this, Text.class);
        startActivity(intent);
    }


}

