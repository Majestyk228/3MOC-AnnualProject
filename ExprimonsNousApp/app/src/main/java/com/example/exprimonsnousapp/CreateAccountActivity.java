package com.example.exprimonsnousapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exprimonsnousapp.models.NewAccount;
import com.example.exprimonsnousapp.models.NewAccountResponse;
import com.example.exprimonsnousapp.models.UserCreds;
import com.example.exprimonsnousapp.retrofit.ApiClient;
import com.example.exprimonsnousapp.retrofit.ApiInterface;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private int idUser;

    // API
    ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

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


                if (firstnameTXT == "" || lastnameTXT == "" || birthdateTXT == "0000-00-00" || emailTXT == "" || areaCodeTXT == "" || passwdTXT == "") {
                    // TOAST ASKING FOR USER TO COMPLETE ALL FIELDS IN THE SCREEN
                    Toast.makeText(getApplicationContext(), "Merci de completer les champs manquants", Toast.LENGTH_LONG).show();
                } else {
                    // PUT CODE FOR API CALL AND NEXT ACTIVITY

                    // CONVERT TEXT VARIABLES TO OBJECT
                    NewAccount newAccount = new NewAccount(firstnameTXT, lastnameTXT, birthdateTXT, emailTXT, genderTXT, areaCodeTXT, passwdTXT);

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
                    Intent joinCommunityActivity = new Intent(getApplicationContext(), JoinCompanyActivity.class);
                    joinCommunityActivity.putExtra("idUser",idUser);
                    startActivity(joinCommunityActivity);
                    finish();

                    // API CALL TO CREATE ACCOUNT
                    userRegister(newAccount);
                }
            }
        });
    }


    private void userRegister(NewAccount newAccount) {
        // TODO : MAKE API CALL TO INSERT THE NEW ACCOUNT INTO THE DATABASE
        Call<NewAccountResponse> call = apiInterface.userRegister(newAccount);
        call.enqueue(new Callback<NewAccountResponse>(){

            @Override
            public void onResponse(Call<NewAccountResponse> call, Response<NewAccountResponse> response) {
                if (response.isSuccessful()) {
                    //idUser = (int) response.body();
                    idUser = response.body().getIdUser();
                    Log.i("REGISTER", "onResponse: "+response.body());
                } else {
                    //idUser = -1;
                    Log.i("REGISTER", "onResponse: "+response.body());
                }
            }

            @Override
            public void onFailure(Call<NewAccountResponse> call, Throwable t) {
                Log.i("REGISTER", "onFailure: "+t.getLocalizedMessage());
            }
        });
    }
}
