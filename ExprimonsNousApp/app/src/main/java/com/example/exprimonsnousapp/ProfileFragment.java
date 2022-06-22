package com.example.exprimonsnousapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

    // OTHER VARIABLES
    private int idUser;


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

        // RÉCUPÉRATION DE l'IDUSER PASSÉ EN ARGUMENT DU FRAGMENT
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            idUser = bundle.getInt("idUser", -1);
        }

        // BOUTONS
        this.submit_button = view.findViewById(R.id.submitBtn);
        this.reset_password = view.findViewById(R.id.passwordChangeBtn);

        // CHAMPS TEXTE
        this.firstName = view.findViewById(R.id.firstnameUpdate);
        this.lastName = view.findViewById(R.id.lastnameUpdate);
        this.birthDate = view.findViewById(R.id.bithdateUpdate);
        this.email = view.findViewById(R.id.emailUpdate);
        this.gender = view.findViewById(R.id.genderUpdate);
        this.areaCode = view.findViewById(R.id.areaCodeUpdate);


        this.submit_button.setOnClickListener(view12 -> {
            Log.i("BUTTONSSS", "Bouton Submit");

            String firstNameTxt = firstName.getText().toString();
            String lastNameTxt = lastName.getText().toString();
            String birthDateTxt = birthDate.getText().toString();
            String emailtxt = email.getText().toString();
            String genderTxt = gender.getText().toString();
            String areaCodeTxt = areaCode.getText().toString();

            // MAKE REQUEST TO UPDATE API
            UserUpdatedInfos updatedInfos = new UserUpdatedInfos(idUser,firstNameTxt,lastNameTxt,birthDateTxt,emailtxt,genderTxt,areaCodeTxt);
            //Log.i("UserUpdateInfos",updatedInfos.toString());
            updateUserInfo(updatedInfos);

        });

        this.reset_password.setOnClickListener(view1 -> {
            //Toast.makeText(view.getContext(),"Changement du mot de passe...", Toast.LENGTH_SHORT).show();

            Log.i("BUTTONSSS", "Bouton Reset");
            // OPEN FRAGMENT TO RESET PASSWORD SCREEN
        });
        return view;
    }

    private void updateUserInfo(UserUpdatedInfos userUpdatedInfos) {

        Log.i("UserUpdateInfos",userUpdatedInfos.toString());

        Call<UserUpdatedInfos> call = apiInterface.updateUserInfo(userUpdatedInfos);
        call.enqueue(new Callback<UserUpdatedInfos>(){
            UserUpdatedInfos responseStr;

            @Override
            public void onResponse(Call<UserUpdatedInfos> call, Response<UserUpdatedInfos> response) {
                if (response.isSuccessful()) {
                    responseStr = response.body();
                } else {
                    responseStr = new UserUpdatedInfos(-1,"","","","","","");
                }
            }

            @Override
            public void onFailure(Call<UserUpdatedInfos> call, Throwable t) {
                Log.i("API RESPONSE", "onFailure: "+t.getLocalizedMessage());
            }
        });
    }
}