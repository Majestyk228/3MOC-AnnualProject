package com.example.exprimonsnousapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exprimonsnousapp.models.CommunityInsert;
import com.example.exprimonsnousapp.models.IdCommunity;
import com.example.exprimonsnousapp.models.UserCreds;
import com.example.exprimonsnousapp.retrofit.ApiClient;
import com.example.exprimonsnousapp.retrofit.ApiInterface;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinCompanyActivity extends AppCompatActivity {

    private MaterialButton nextBtn;
    private EditText companyCode;
    private int idCommunity;

    // API
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_company_page);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        //retrieving data from extras
        Bundle extras = getIntent().getExtras();
        int idUser = extras.getInt("idUser");

        this.nextBtn = (MaterialButton) findViewById(R.id.nextBtn);
        this.companyCode = (EditText) findViewById(R.id.companyCode);


        this.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String company_codeTXT = companyCode.getText().toString();
                int companyCodeTXT = Integer.parseInt(company_codeTXT);

                // GETTING THE idCommunity FROM API WITH CODE
                extractIdCommunity(companyCodeTXT);

                // FAKE DELAY
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent nextActivity = new Intent(getApplicationContext(), MainActivity2.class);
                        nextActivity.putExtra("userId", idUser);
                        nextActivity.putExtra("communityId", idCommunity);
                        startActivity(nextActivity);
                        finish();
                    }
                }, 1200);
            }
        });
    }

    private void extractIdCommunity(int code) {

        Call<IdCommunity> call = apiInterface.extractIdCommunity(code);
        call.enqueue(new Callback<IdCommunity>() {
            @Override
            public void onResponse(Call<IdCommunity> call, Response<IdCommunity> response) {
                if (response.isSuccessful()) {
                    idCommunity = response.body().getIdCommunity();
                } else {
                    idCommunity = -1;
                }
            }

            @Override
            public void onFailure(Call<IdCommunity> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addUserInCommunity(CommunityInsert communityInsert) {
        // TODO : make api call
        Call<Object> call = apiInterface.addUserInCommunity(communityInsert);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    Log.i("GETIDCOMMUNITY", "onResponse Adding user in community: " + response.body());
                    //userCreds = response.body();
                } else {
                    Log.i("GETIDCOMMUNITY", "onResponse Adding user in community: " + response.body());
                    //userCreds = new UserCreds(-1, "",-1);
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.i("GETIDCOMMUNITY", "onFailure Adding user in community: " + t.getLocalizedMessage());
            }
        });
    }
}