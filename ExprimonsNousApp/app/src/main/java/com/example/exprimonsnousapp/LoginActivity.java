package com.example.exprimonsnousapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.exprimonsnousapp.adapters.PostAdapter;
import com.example.exprimonsnousapp.models.Post;
import com.example.exprimonsnousapp.models.UserCreds;
import com.example.exprimonsnousapp.models.UserLoginCreds;
import com.example.exprimonsnousapp.retrofit.ApiClient;
import com.example.exprimonsnousapp.retrofit.ApiInterface;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private MaterialButton loginBtn;
    private String URL_LOGIN = "https://www.titan-photography.com/user/login";
    ApiInterface apiInterface;
    UserCreds userCreds = new UserCreds(-1,"", -1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        this.email = (EditText) findViewById(R.id.emailLogin);
        this.password = (EditText) findViewById(R.id.passwdLogin);
        this.loginBtn = (MaterialButton) findViewById(R.id.loginAccountBtn);


        this.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailTXT = email.getText().toString();
                String passwordTXT = password.getText().toString();

                UserLoginCreds userLoginCreds = new UserLoginCreds(emailTXT,passwordTXT);

                getUserCreds(userLoginCreds);
            }
        });
    }

    private void getUserCreds(UserLoginCreds userLoginCreds) {
        Call<UserCreds> call = apiInterface.getUserCreds(userLoginCreds);
        call.enqueue(new Callback<UserCreds>(){

            @Override
            public void onResponse(Call<UserCreds> call, Response<UserCreds> response) {
                if (response.isSuccessful()) {
                    userCreds = response.body();
                } else {
                    userCreds = new UserCreds(-1, "",-1);
                }
            }

            @Override
            public void onFailure(Call<UserCreds> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
            }
        });

        // FAKE DELAY
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                connexion(userCreds);
            }
        }, 1200);
    }

    public void connexion(UserCreds userCreds) {
        // CHECKS VALIDITY OF RETREIVED USER BEFORE SWITCHING SCREEN
        if(userCreds.getIdUser() == -1) {
            // TOAST
            Toast.makeText(getApplicationContext(), "Une erreur est survenue", Toast.LENGTH_LONG).show();
        } else {
            Intent nextActivity = new Intent(getApplicationContext(), MainActivity2.class);
            //extras will be added
            nextActivity.putExtra("userId", userCreds.getIdUser());
            nextActivity.putExtra("communityId", userCreds.getIdCommunity());
            startActivity(nextActivity);
            finish();
        }
    }
}