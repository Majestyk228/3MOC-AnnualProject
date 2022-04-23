package com.example.exprimonsnousapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class JoinCommunityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_commune_page);

        //getting extras
        Bundle extras = getIntent().getExtras();

        System.out.println("Test");

        String firstname = extras.getString("firstname");
        String lastname = extras.getString("lastname");
        String birthdate = extras.getString("birthdate");

        System.out.println(firstname);
        System.out.println(lastname);
        System.out.println(birthdate);
        System.out.println(extras.getString("email"));
        System.out.println(extras.getString("gender"));
        System.out.println(extras.getString("areaCode"));
        System.out.println(extras.getString("passwd"));
    }
}