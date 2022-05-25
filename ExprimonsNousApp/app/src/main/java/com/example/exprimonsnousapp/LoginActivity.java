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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.exprimonsnousapp.adapters.PostAdapter;
import com.example.exprimonsnousapp.models.Post;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private MaterialButton loginBtn;
    private String URL_LOGIN = "https://www.titan-photography.com/user/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                String emailDB = "admin";       //will be retrieved from API
                String passwordDB = "admin";    //will be retrieved from API

                JSONObject credentialObjEmail = new JSONObject();
                try {
                    credentialObjEmail.put("email", emailTXT);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JSONObject credentialObjPassword= new JSONObject();
                try {
                    credentialObjPassword.put("password", passwordTXT);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JSONArray credentials = new JSONArray();
                credentials.put(credentialObjEmail);
                credentials.put(credentialObjPassword);



                /**
                 *
                 * API REQUEST STARTED
                 * **/



                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
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

                queue.add(jsonArrayRequest);

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


    public void connexion(JSONObject responseApi) {
        Toast toast;
        try {
            if(responseApi.getString("error") == "Missing email or/and password") {
                toast = Toast.makeText(getApplicationContext(), "Il manque un des deux champs obligatoires", Toast.LENGTH_LONG);
                toast.show();
            }
            if(responseApi.getString("error") == "incorrect password") {
                //toast "mot de passe incorrect"
                toast = Toast.makeText(getApplicationContext(), "Mot de passe incorrect", Toast.LENGTH_LONG);
                toast.show();
            }
            if(responseApi.getInt("idUser") != 0 ){
                //next activity
                Intent nextActivity = new Intent(getApplicationContext(),PostFeedActivity.class);
                //extras will be added
                startActivity(nextActivity);
                finish();

                toast = Toast.makeText(getApplicationContext(), "Connexion autorisée", Toast.LENGTH_LONG);
                toast.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}