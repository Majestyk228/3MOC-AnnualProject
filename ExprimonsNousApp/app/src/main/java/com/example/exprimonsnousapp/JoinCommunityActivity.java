package com.example.exprimonsnousapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.button.MaterialButton;

public class JoinCommunityActivity extends AppCompatActivity {

    private Spinner spinner;
    private MaterialButton nextBtn;
    private MaterialButton skipBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_commune_page);

        this.nextBtn = (MaterialButton) findViewById(R.id.nextBtn);
        this.skipBtn = (MaterialButton) findViewById(R.id.skipBtn);

        this.spinner = (Spinner) findViewById(R.id.communeSelector);

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


        this.skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ouverture de la page suivante avec le transfert des infos précédantes
                Intent joinCompany = new Intent(getApplicationContext(), JoinCompanyActivity.class);
                //put extras
                joinCompany.putExtra("firstname", firstname);
                joinCompany.putExtra("lastname", lastname);
                joinCompany.putExtra("birthdate", birthdate);
                joinCompany.putExtra("email", email);
                joinCompany.putExtra("gender", gender);
                joinCompany.putExtra("areaCode", areaCode);
                joinCompany.putExtra("passwd", passwd);
                startActivity(joinCompany);
                finish();
            }
        });

        this.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get the selected value to the spinner
                String selectSpinner = spinner.getSelectedItem().toString();


                //ouverture de la page suivante avec le transfert des infos précédantes + champ selectionné du spinner
                Intent joinCompany = new Intent(getApplicationContext(), JoinCompanyActivity.class);
                //put extras
                joinCompany.putExtra("firstname", firstname);
                joinCompany.putExtra("lastname", lastname);
                joinCompany.putExtra("birthdate", birthdate);
                joinCompany.putExtra("email", email);
                joinCompany.putExtra("gender", gender);
                joinCompany.putExtra("areaCode", areaCode);
                joinCompany.putExtra("passwd", passwd);
                joinCompany.putExtra("communeCode",selectSpinner);
                startActivity(joinCompany);
                finish();
            }
        });
    }
}