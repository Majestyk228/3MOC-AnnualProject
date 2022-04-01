package com.example.exprimonsnousapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.landing_page);
        //setContentView(R.layout.signin_page);
        setContentView(R.layout.login_page);
    }
}