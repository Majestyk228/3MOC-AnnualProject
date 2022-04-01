package com.example.exprimonsnousapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private MaterialButton createAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        //setContentView(R.layout.signin_page);
        //setContentView(R.layout.login_page);

        this.createAccountBtn = (MaterialButton)findViewById(R.id.createAccountBtn);
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(),CreateAccountActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });
    }

    /*
    *
    * this.btProduit = (Button)findViewById(R.id.btProduit);
        btProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(),ProduitActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });
    * */
}