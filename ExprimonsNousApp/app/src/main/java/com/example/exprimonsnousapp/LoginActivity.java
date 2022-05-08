package com.example.exprimonsnousapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private MaterialButton loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        this.email = (EditText) findViewById(R.id.emailLogin);
        this.password = (EditText) findViewById(R.id.passwdLogin);
        this.loginBtn = (MaterialButton) findViewById(R.id.loginAccountBtn);


        this.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Toast toast = Toast.makeText(getApplicationContext(), TEXT, Toast.LENGTH_SHORT);
                toast.show();*/


                String emailTXT = email.getText().toString();
                String passwordTXT = password.getText().toString();
                String emailDB = "admin";
                String passwordDB = "admin";

                //débuggage
                Toast toast;

                if (emailTXT.equals(emailDB) && passwordTXT.equals(passwordDB)) {
                    toast = Toast.makeText(getApplicationContext(), "Connexion autorisée", Toast.LENGTH_SHORT);
                } else {
                    toast = Toast.makeText(getApplicationContext(), "BORDEL", Toast.LENGTH_SHORT);
                }
                toast.show();
            }
        });
    }
}