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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        Log.i("Rest Response","API test starts here");

        /*API Call
        //1) create a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //String URL = "https://api.coindesk.com/v1/bpi/currentprice.json";
        //String URL = "http://thecocktaildb.com/api/json/v1/1/search.php?s=margarita";
        String URL = "http://192.168.1.48:8080/post/all";


        //2) create the request
        //3 methods possible : JSONArrayRequest / JSONObjectRequest<= / StringRequest
        /*JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //code
                        Log.i("Rest Response", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //code
                        Log.e("Rest Response", error.toString());
                    }
                }
        );*/

        /*JsonArrayRequest objectRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.i("Rest Response", response.getString(0));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //code
                        Log.e("Rest Response", error.toString());
                    }
                }
        );

        //3) Add the objectRequest to the requestQueue
        requestQueue.add(objectRequest);*/
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