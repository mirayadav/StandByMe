package com.fbhacks.standbyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Typeface;
import android.view.View;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.standbyme.MESSAGE";
    CallbackManager callbackManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();
        AppEventsLogger.activateApp(this);
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("main activity", "login successful");
                    }

                    @Override
                    public void onCancel() {
                        Log.d("main activity", "not successful");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.d("main activity", "error");
                    }
                });
    }

    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("main activity", "login successful");
            }

            @Override
            public void onCancel() {
                Log.d("main activity", "not successful");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.d("main activity", "error");
            }

        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("public_profile"));
            }
        });
        return view;

    }
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void sendMessage(View view) {
        Log.d("Main", "sending message");
        /*Intent intent = new Intent(this, DisplayMessageActivity.class;
        EditText editText = (EditText) view.findViewbyId(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);*/
    }
    public void nextpage (View view){
        Intent intent = new Intent (this, Confirmation.class);
        startActivity(intent);
    }

}
