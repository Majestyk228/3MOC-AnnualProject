package com.example.exprimonsnousapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exprimonsnousapp.models.NewAccount;
import com.google.android.material.button.MaterialButton;

public class CreateAccountActivity extends AppCompatActivity {

    //Private declaration of all objects in layout
    private EditText firstname;
    private EditText lastname;
    private EditText birthdate;
    private EditText email;
    //private EditText gender;
    private Spinner gender_spinner;
    private EditText areaCode;
    private EditText passwd;
    private MaterialButton signInBtn;

    // OTHER
    private String gender_selection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, R.layout.spinner_item);

        this.firstname = (EditText) findViewById(R.id.firstname);
        this.lastname = (EditText) findViewById(R.id.lastname);
        this.birthdate = (EditText) findViewById(R.id.bithdate);
        this.email = (EditText) findViewById(R.id.email);
        //this.gender = (EditText) findViewById(R.id.gender);
        this.gender_spinner = (Spinner) findViewById(R.id.gender_spinner);
        this.areaCode = (EditText) findViewById(R.id.areaCode);
        this.passwd = (EditText) findViewById(R.id.passwd);
        this.signInBtn = (MaterialButton) findViewById(R.id.signInBtn);


        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        gender_spinner.setAdapter(adapter);

        //Listening to the SignInBtn press
        this.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getting strings from every EditTexts
                String firstnameTXT = firstname.getText().toString();
                String lastnameTXT = lastname.getText().toString();
                String birthdateTXT = birthdate.getText().toString();
                String emailTXT = email.getText().toString();
                String genderTXT = gender_spinner.getSelectedItem().toString();
                String areaCodeTXT = areaCode.getText().toString();
                String passwdTXT = passwd.getText().toString();


                // CONVERT TEXT VARIABLES TO OBJECT
                NewAccount newAccount = new NewAccount(firstnameTXT,lastnameTXT,birthdateTXT,emailTXT,genderTXT,areaCodeTXT,passwdTXT);

                /*
                //adding extras from the strings from EditTexts
                joinCommunityActivity.putExtra("firstname",firstnameTXT);
                joinCommunityActivity.putExtra("lastname",lastnameTXT);
                joinCommunityActivity.putExtra("birthdate",birthdateTXT);
                joinCommunityActivity.putExtra("email",emailTXT);
                joinCommunityActivity.putExtra("gender",genderTXT);
                joinCommunityActivity.putExtra("areaCode",areaCodeTXT);
                joinCommunityActivity.putExtra("passwd",passwdTXT);*/

                //starting activity
                Intent joinCommunityActivity = new Intent(getApplicationContext(),JoinCompanyActivity.class);
                startActivity(joinCommunityActivity);
                finish();

                // API CALL TO CREATE ACCOUNT
                createAccount(newAccount);
            }
        });
    }


    private void createAccount(NewAccount newAccount) {
        // TODO : MAKE API CALL TO INSERT THE NEW ACCOUNT INTO THE DATABASE
    }
}
