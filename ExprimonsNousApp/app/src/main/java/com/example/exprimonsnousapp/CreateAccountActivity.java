package com.example.exprimonsnousapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class CreateAccountActivity extends AppCompatActivity {

    //Private declaration of all objects in layout
    private EditText firstname;
    private EditText lastname;
    private EditText birthdate;
    private EditText email;
    private EditText gender;
    private EditText areaCode;
    private EditText passwd;
    private MaterialButton signInBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);

        this.firstname = (EditText) findViewById(R.id.firstname);
        this.lastname = (EditText) findViewById(R.id.lastname);
        this.birthdate = (EditText) findViewById(R.id.bithdate);
        this.email = (EditText) findViewById(R.id.email);
        this.gender = (EditText) findViewById(R.id.gender);
        this.areaCode = (EditText) findViewById(R.id.areaCode);
        this.passwd = (EditText) findViewById(R.id.passwd);
        this.signInBtn = (MaterialButton) findViewById(R.id.signInBtn);

        //Listening to the SignInBtn press
        this.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getting strings from every EditTexts
                String firstnameTXT = firstname.getText().toString();
                String lastnameTXT = lastname.getText().toString();
                String birthdateTXT = birthdate.getText().toString();
                String emailTXT = email.getText().toString();
                String genderTXT = gender.getText().toString();
                String areaCodeTXT = areaCode.getText().toString();
                String passwdTXT = passwd.getText().toString();

                Intent joinCommunityActivity = new Intent(getApplicationContext(),JoinCommunityActivity.class);

                //adding extras from the strings from EditTexts
                joinCommunityActivity.putExtra("firstname",firstnameTXT);
                joinCommunityActivity.putExtra("lastname",lastnameTXT);
                joinCommunityActivity.putExtra("birthdate",birthdateTXT);
                joinCommunityActivity.putExtra("email",emailTXT);
                joinCommunityActivity.putExtra("gender",genderTXT);
                joinCommunityActivity.putExtra("areaCode",areaCodeTXT);
                joinCommunityActivity.putExtra("passwd",passwdTXT);

                //starting activity
                startActivity(joinCommunityActivity);
                finish();
            }
        });
    }
}
