package com.example.exprimonsnousapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
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

                /*JSONObject credentialObj = new JSONObject();
                try {
                    credentialObj.put("email", emailTXT);
                    credentialObj.put("password", passwordTXT);
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

                /*JSONArray credentials = new JSONArray();
                credentials.put(credentialObjEmail);
                credentials.put(credentialObjPassword);*/


                /**
                 *
                 * API REQUEST STARTED
                 * **/



                /*RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                        URL_LOGIN,
                        credentials,
                        (Response.Listener<JSONArray>) response -> {
                            for(int i = 0 ; i<response.length() ; i++){
                                try {
                                    JSONObject responseApi = response.getJSONObject(i);

                                    //connexion test depending on API response
                                    connexion(responseApi);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("errorAPI","onErrorResponse:"+error.getMessage());
                            }
                        });

                queue.add(jsonArrayRequest);*/

                /*RequestQueue queue = Volley.newRequestQueue(getApplication());
                JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                        Request.Method.GET,
                        URL_LOGIN,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    Log.i("response",response.toString());
                                    connexion(response);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("errorAPI","onErrorResponse:"+error.getMessage());
                    }
                });

                queue.add(jsonObjReq);*/

                /**
                 *
                 * API REQUEST ENDED
                 * **/

                /*débuggage
                Toast toast;

                if (emailTXT.equals(emailDB) && passwordTXT.equals(passwordDB)) {
                    toast = Toast.makeText(getApplicationContext(), "Connexion autorisée", Toast.LENGTH_LONG);

                    //next activity
                    Intent nextActivity = new Intent(getApplicationContext(),PostFeedActivity.class);
                    //extras will be added
                    startActivity(nextActivity);
                    finish();
                } else {
                    toast = Toast.makeText(getApplicationContext(), "Login ou/et mot de passe erroné. Veuillez réessayer.", Toast.LENGTH_LONG);
                }
                toast.show();*/
            }
        });
    }

    private void getUserCreds( UserLoginCreds userLoginCreds) {
        Call<UserCreds> call = apiInterface.getUserCreds(userLoginCreds);
        call.enqueue(new Callback<UserCreds>(){

            @Override
            public void onResponse(Call<UserCreds> call, Response<UserCreds> response) {
                Log.i("API RESPONSE", "onResponse: "+response.body());
            }

            @Override
            public void onFailure(Call<UserCreds> call, Throwable t) {
                Log.i("API RESPONSE", "onFailure: "+t.getLocalizedMessage());
            }
        });
    }


    public void connexion(JSONObject responseApi) {
        Toast toast;
        try {
            if (responseApi.getString("error") == "Missing email or/and password") {
                toast = Toast.makeText(getApplicationContext(), "Il manque un des deux champs obligatoires", Toast.LENGTH_LONG);
                toast.show();
            }
            if (responseApi.getString("error") == "incorrect password") {
                //toast "mot de passe incorrect"
                toast = Toast.makeText(getApplicationContext(), "Mot de passe incorrect", Toast.LENGTH_LONG);
                toast.show();
            }
            if (responseApi.getInt("idUser") != 0) {
                //next activity
                Intent nextActivity = new Intent(getApplicationContext(), PostFeedActivity.class);
                //extras will be added
                startActivity(nextActivity);
                finish();

                toast = Toast.makeText(getApplicationContext(), "Connexion autorisée", Toast.LENGTH_LONG);
                toast.show();
            }
        } catch (Exception e) {
            Log.e("ERR",e.toString());
        }
    }
}