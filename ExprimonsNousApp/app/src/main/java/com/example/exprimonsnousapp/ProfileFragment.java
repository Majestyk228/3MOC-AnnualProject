package com.example.exprimonsnousapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.exprimonsnousapp.models.UserCreds;
import com.example.exprimonsnousapp.models.UserLoginCreds;
import com.example.exprimonsnousapp.models.UserUpdatedInfos;
import com.example.exprimonsnousapp.retrofit.ApiInterface;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // BOUTONS
    private MaterialButton submit_button;
    private MaterialButton reset_password;

    // CHAMPS TEXTE
    private EditText firstName;
    private EditText lastName;
    private EditText birthDate;
    private EditText email;
    private EditText gender;
    private EditText areaCode;

    // API
    ApiInterface apiInterface;


    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        this.submit_button = view.findViewById(R.id.submitBtn);
        this.reset_password = view.findViewById(R.id.passwordChangeBtn);


        this.submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("BUTTONSSS", "Bouton Submit");

                String firstNameTxt = firstName.getText().toString();
                String lastNameTxt = lastName.getText().toString();
                String birthDateTxt = birthDate.getText().toString();
                String emailtxt = email.getText().toString();
                String genderTxt = gender.getText().toString();
                String areaCodeTxt = areaCode.getText().toString();

                // MAKE REQUEST TO UPDATE API
                UserUpdatedInfos updatedInfos = new UserUpdatedInfos(firstNameTxt,lastNameTxt,birthDateTxt,emailtxt,genderTxt,areaCodeTxt);
                updateUserInfo(updatedInfos);

            }
        });

        this.reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(),"Changement du mot de passe...", Toast.LENGTH_SHORT).show();

                Log.i("BUTTONSSS", "Bouton Reset");
                // OPEN FRAGMENT TO RESET PASSWORD SCREEN
            }
        });
        return view;
    }

    private void updateUserInfo(UserUpdatedInfos userUpdatedInfos) {

        Call<String> call = apiInterface.updateUserInfo(userUpdatedInfos);
        call.enqueue(new Callback<String>(){
            String responseStr;

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    responseStr = response.body();
                } else {
                    responseStr = "Erreur";
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("API RESPONSE", "onFailure: "+t.getLocalizedMessage());
            }
        });

        // FAKE DELAY
        /*final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                Log.i("WAIT","WAIT DONE");
                connexion(userCreds);
            }
        }, 1200);*/
    }
}