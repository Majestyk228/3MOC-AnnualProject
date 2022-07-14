package com.example.exprimonsnousapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

public class JoinCompanyActivity extends AppCompatActivity {

    private MaterialButton nextBtn;
    private MaterialButton skipBtn;
    private EditText companyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_company_page);

        //retrieving data from extras
        /*Bundle extras = getIntent().getExtras();
        String firstname = extras.getString("firstname");
        String lastname = extras.getString("lastname");
        String birthdate = extras.getString("birthdate");
        String email = extras.getString("email");
        String gender = extras.getString("gender");
        String areaCode = extras.getString("areaCode");
        String passwd = extras.getString("passwd");
        String communeCode = extras.getString("communeCode");*/

        this.nextBtn = (MaterialButton) findViewById(R.id.nextBtn);
        this.skipBtn = (MaterialButton) findViewById(R.id.skipBtn);
        this.companyCode = (EditText) findViewById(R.id.companyCode);



        this.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String companyCodeTXT = companyCode.getText().toString();

                // GETTING THE idCommunity FROM API WITH CODE
                int idCommunity = extractIdCommunity(companyCodeTXT);


                Intent nextActivity = new Intent(getApplicationContext(),MainActivity2.class);
                nextActivity.putExtra("idCommunity",idCommunity);
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

    private int extractIdCommunity(String company_code) {
        // TODO : RETURN idCommunity
        return 0;
    }
}