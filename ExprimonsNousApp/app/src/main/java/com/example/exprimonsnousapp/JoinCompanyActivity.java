package com.example.exprimonsnousapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class JoinCompanyActivity extends AppCompatActivity {

    private MaterialButton nextBtn;
    private MaterialButton skipBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_company_page);

        //retrieving data from extras
        Bundle extras = getIntent().getExtras();
        String firstname = extras.getString("firstname");
        String lastname = extras.getString("lastname");
        String birthdate = extras.getString("birthdate");
        String email = extras.getString("email");
        String gender = extras.getString("gender");
        String areaCode = extras.getString("areaCode");
        String passwd = extras.getString("passwd");


        //dans le cas où l'utilisateur n'a pas spécifier de région
        try {
            String communeCode = extras.getString("communeCode");
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(getApplicationContext(),PostFeedActivity.class);
                startActivity(nextActivity);
                finish();
            }
        });

        this.skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(getApplicationContext(),PostFeedActivity.class);
                startActivity(nextActivity);
                finish();
            }
        });
    }
}