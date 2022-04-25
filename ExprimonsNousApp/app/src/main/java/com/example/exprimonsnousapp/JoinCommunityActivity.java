package com.example.exprimonsnousapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class JoinCommunityActivity extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_commune_page);

        spinner =  (Spinner)findViewById(R.id.communeSelector);

        //getting extras
        Bundle extras = getIntent().getExtras();
        String firstname = extras.getString("firstname");
        String lastname = extras.getString("lastname");
        String birthdate = extras.getString("birthdate");
        String email = extras.getString("email");
        String gender = extras.getString("gender");
        String areaCode = extras.getString("areaCode");
        String passwd = extras.getString("passwd");


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.commune_list, R.layout.spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }
}