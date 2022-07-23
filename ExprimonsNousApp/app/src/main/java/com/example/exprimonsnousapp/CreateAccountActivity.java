package com.example.exprimonsnousapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exprimonsnousapp.models.IdCommunity;
import com.example.exprimonsnousapp.models.NewAccount;
import com.example.exprimonsnousapp.models.NewAccountResponse;
import com.example.exprimonsnousapp.models.UserCreds;
import com.example.exprimonsnousapp.retrofit.ApiClient;
import com.example.exprimonsnousapp.retrofit.ApiInterface;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccountActivity extends AppCompatActivity {

    // PRIVATE DECLARATION OF ALL OBJECTS IN LAYOUT
    private EditText firstname;
    private EditText lastname;
    private EditText birthdate;
    private EditText email;
    private Spinner gender_spinner;
    private EditText areaCode;
    private EditText passwd;
    private EditText companyCode;
    private MaterialButton signInBtn;

    // OTHER
    private String gender_selection;
    private int idUser;
    private int idCommunity;

    // API
    ApiInterface apiInterface;

    // DATE SELECTOR
    final Calendar myCalendar= Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, R.layout.spinner_item);

        this.firstname = (EditText) findViewById(R.id.firstname);
        this.lastname = (EditText) findViewById(R.id.lastname);

        this.email = (EditText) findViewById(R.id.email);
        this.gender_spinner = (Spinner) findViewById(R.id.gender_spinner);
        this.areaCode = (EditText) findViewById(R.id.areaCode);
        this.passwd = (EditText) findViewById(R.id.passwd);
        this.signInBtn = (MaterialButton) findViewById(R.id.signInBtn);
        this.birthdate = (EditText) findViewById(R.id.bithdate);
        this.companyCode = (EditText) findViewById(R.id.companyCode);

        // DATE SELECTOR
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                myCalendar.set(Calendar.YEAR, i);
                myCalendar.set(Calendar.MONTH,i1);
                myCalendar.set(Calendar.DAY_OF_MONTH,i2);
                updateLabel();
            }
        };



        birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(CreateAccountActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // SPECIFY TH ELAYOUT TO USE WHEN THE LIST OF CHOICES APPEARS
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // APPLY THE ADAPTER TO THE SPINNER
        gender_spinner.setAdapter(adapter);

        // LISTENING TO THE SIGNINBTN PRESS
        this.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstnameTXT = firstname.getText().toString();
                String lastnameTXT = lastname.getText().toString();
                String birthdateTXT = birthdate.getText().toString();
                String emailTXT = email.getText().toString();
                String genderTXT = gender_spinner.getSelectedItem().toString();
                String areaCodeTXT = areaCode.getText().toString();
                String passwdTXT = passwd.getText().toString();
                String companyCodeTXT = companyCode.getText().toString();


                if (firstnameTXT == "" || lastnameTXT == "" || birthdateTXT == "0000-00-00" || emailTXT == "" || areaCodeTXT == "" || passwdTXT == "") {
                    // TOAST ASKING FOR USER TO COMPLETE ALL FIELDS IN THE SCREEN
                    Toast.makeText(getApplicationContext(), "Merci de completer les champs manquants", Toast.LENGTH_LONG).show();
                } else {
                    // PUT CODE FOR API CALL AND NEXT ACTIVITY

                    // CONVERT TEXT VARIABLES TO OBJECT
                    //NewAccount newAccount = new NewAccount(firstnameTXT, lastnameTXT, birthdateTXT, emailTXT, genderTXT, areaCodeTXT, passwdTXT);
                    extractIdCommunity(Integer.parseInt(companyCodeTXT), firstnameTXT, lastnameTXT, birthdateTXT,emailTXT, genderTXT, areaCodeTXT, passwdTXT);

                    // FAKE DELAY
                    /*final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // API CALL TO CREATE ACCOUNT
                            userRegister(newAccount);
                        }
                    }, 1200);*/

                    //starting activity
                    /*Intent joinCommunityActivity = new Intent(getApplicationContext(), JoinCompanyActivity.class);
                    joinCommunityActivity.putExtra("idUser",idUser);
                    startActivity(joinCommunityActivity);
                    finish();*/

                }
            }
        });
    }


    private void userRegister(NewAccount newAccount) {
        Call<NewAccountResponse> call = apiInterface.userRegister(newAccount);
        call.enqueue(new Callback<NewAccountResponse>(){

            @Override
            public void onResponse(Call<NewAccountResponse> call, Response<NewAccountResponse> response) {
                if (response.isSuccessful()) {
                    idUser = response.body().getIdUser();
                } else {
                    idUser = -1;
                }
            }

            @Override
            public void onFailure(Call<NewAccountResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Une erreur est survenue", Toast.LENGTH_LONG).show();
            }
        });
    }

    // API CALL FOR COMPANY CODE
    private void extractIdCommunity(int code, String firstnameTXT, String lastnameTXT, String birthdateTXT, String emailTXT, String genderTXT, String areaCodeTXT, String passwdTXT) {

        Call<IdCommunity> call = apiInterface.extractIdCommunity(code);
        call.enqueue(new Callback<IdCommunity>() {
            @Override
            public void onResponse(Call<IdCommunity> call, Response<IdCommunity> response) {
                if (response.isSuccessful()) {
                    idCommunity = response.body().getIdCommunity();
                    NewAccount newAccount = new NewAccount(firstnameTXT, lastnameTXT, birthdateTXT, emailTXT, genderTXT, areaCodeTXT, passwdTXT, response.body().getIdCommunity());

                    userRegister(newAccount);

                    Intent nextActivity = new Intent(getApplicationContext(), MainActivity2.class);
                    nextActivity.putExtra("userId", idUser);
                    nextActivity.putExtra("communityId", response.body().getIdCommunity());
                    startActivity(nextActivity);
                    finish();
                } else {
                    //idCommunity = -1;
                    Toast.makeText(getApplicationContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<IdCommunity> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
            }
        });
    }

    // DATE SELECTOR
    private void updateLabel(){
        String myFormat="yyyy-MM-dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.FRANCE);
        birthdate.setText(dateFormat.format(myCalendar.getTime()));
    }
}
