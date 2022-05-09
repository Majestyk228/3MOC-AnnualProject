package com.example.exprimonsnousapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.Voice;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        Log.i("ASS","API test starts here");

        /*API Call*/
        String URL="http://192.168.1.48:8080/post/all";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //code...
                try {
                    JSONArray jsonArray = response.getJSONArray("posts");
                    for (int i=0 ; i<jsonArray.length() ; i++) {
                        JSONObject jorb = jsonArray.getJSONObject(i);
                        Log.i("JORB",jorb.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    JSONArray jsonArray = response.getJSONArray("posts");
                    Log.e("rest Response", jsonArray.getJSONObject(0).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Log.e("rest Response", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //code
                Log.e("rest Response", error.toString());
            }
        });

        requestQueue.add(objectRequest);
        /*API Call*/

        this.createAccountBtn = (MaterialButton)findViewById(R.id.createAccountBtn);
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