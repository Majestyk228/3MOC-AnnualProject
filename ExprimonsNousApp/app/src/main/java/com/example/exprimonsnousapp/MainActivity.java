package com.example.exprimonsnousapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.Voice;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private MaterialButton createAccountBtn;
    private MaterialButton loginBtn;

    // SHARED PREFERENCES
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USER = "idUser";
    private static final String KEY_COMMUNITY = "idCommunity";
    private static final String KEY_TOKEN = "token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        int idUser = sharedPreferences.getInt(KEY_USER, -1);
        int idCommunity = sharedPreferences.getInt(KEY_COMMUNITY, -1);
        String token = sharedPreferences.getString(KEY_TOKEN,"");

        Log.i("PERSISTANCE", "MainActivity -> token: "+token);

        if(idUser != -1 || idCommunity != -1 || token !="") {
            // start mainActivity with shared preferences
            Intent nextActivity = new Intent(getApplicationContext(), MainActivity2.class);
            //extras will be added
            nextActivity.putExtra("userId", idUser);
            nextActivity.putExtra("communityId", idCommunity);
            nextActivity.putExtra("token", token);
            startActivity(nextActivity);
            finish();
        }

        this.createAccountBtn = findViewById(R.id.createAccountBtn);
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(),CreateAccountActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });


        this.loginBtn = (MaterialButton)findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(nextActivity);
                finish();
            }
        });
    }
}