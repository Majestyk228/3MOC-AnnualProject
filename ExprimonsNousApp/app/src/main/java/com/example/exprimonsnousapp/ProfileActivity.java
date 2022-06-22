package com.example.exprimonsnousapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class ProfileActivity extends AppCompatActivity {

    //déclaration des boutons
    private MaterialButton submit_button;
    private MaterialButton reset_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.submit_button = findViewById(R.id.submitBtn);
        this.reset_password = findViewById(R.id.passwordChangeBtn);


        this.submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("BUTTONSSS", "Bouton Submit");
            }
        });

        this.reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(),"Changement du mot de passe...", Toast.LENGTH_SHORT).show();

                Log.i("BUTTONSSS", "Bouton Reset");
            }
        });
    }
}