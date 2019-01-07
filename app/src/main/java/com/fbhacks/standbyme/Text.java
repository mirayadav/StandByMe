package com.fbhacks.standbyme;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Text extends Activity implements TextToSpeech.OnInitListener{
    private TextToSpeech tts;
    private Button btnSpeak;
    //private EditText txtText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

    /*    Log.v(TAG, "Initializing sounds...");

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.sound);

        Button play_button = (Button)this.findViewById(R.id.button);
        play_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v(TAG, "Playing sound...");
                mp.start();
            }
        });*/

        tts = new TextToSpeech(this, this);
        String text = "Hi how are ya??";
        Log.d("Text", "In On Create");
        speakOut(text);
        /*
        speakOut(text);

        text = "hope you are doing alright??";
        speakOut(text);

        text = "im doing okay just life ya know? ";
        speakOut(text);
*/
      /*btnSpeak = (Button) findViewById(R.id.btnSpeak);

        //txtText = (EditText) findViewById(R.id.txtText);
        // button on click event
        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                speakOut("How?");}

        });*/
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }


    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {
            Log.d("Text", "In On Init");
            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                //btnSpeak.setEnabled(true);
                speakOut("Hello!, how are you?");
            }
        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }


    private void speakOut(String text) {

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        int speech = tts.setSpeechRate((float) 0.7);
    }
    public void voicedetect (View view){
        Intent intent = new Intent (this, SpeechText.class);
        startActivity(intent);
    }
    public void ending (View view){
        Intent intent = new Intent (this, safe.class);
        startActivity(intent);
    }

}
