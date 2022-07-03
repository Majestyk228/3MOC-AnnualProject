package com.example.exprimonsnousapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.exprimonsnousapp.adapters.PostAdapter;
import com.example.exprimonsnousapp.models.Post;
import com.example.exprimonsnousapp.models.UserUpdateResponse;
import com.example.exprimonsnousapp.models.UserUpdatedInfos;
import com.example.exprimonsnousapp.retrofit.ApiInterface;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    private EditText newPassword;
    private EditText confirmationPassword;

    // COORDINATOR LAYOUT
    private CoordinatorLayout coordinatorLayout;

    // API
    ApiInterface apiInterface;

    // OTHER VARIABLES
    private int idUser;
    private AlertDialog dialog;
    private TextView newPasswordLb;
    private TextView confirmationPasswordLb;


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

        // DIALOGUE
        createResetPasswordDialog();

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

        // COORDINATOR LAYOUT
        this.coordinatorLayout = view.findViewById(R.id.coordinatorLayout);


        this.submit_button.setOnClickListener(view12 -> {
            Log.i("SKY_ESGI", "Bouton Submit");

            String firstNameTxt = firstName.getText().toString();
            String lastNameTxt = lastName.getText().toString();
            String birthDateTxt = birthDate.getText().toString();
            String emailtxt = email.getText().toString();
            String genderTxt = gender.getText().toString();
            String areaCodeTxt = areaCode.getText().toString();

            // MAKE REQUEST TO UPDATE API
            UserUpdatedInfos updatedInfos = new UserUpdatedInfos(idUser,firstNameTxt,lastNameTxt,birthDateTxt,emailtxt,genderTxt,areaCodeTxt);
            updateUserInfo_bis(updatedInfos);

        });

        this.reset_password.setOnClickListener(view1 -> {
            //Toast.makeText(view.getContext(),"Changement du mot de passe...", Toast.LENGTH_SHORT).show();

            Log.i("SKY_ESGI", "Bouton Reset");
            // OPEN FRAGMENT TO RESET PASSWORD SCREEN
            dialog.show();
        });
        return view;
    }









    private void createResetPasswordDialog() {

        this.newPassword = new EditText(getContext());
        this.confirmationPassword = new EditText(getContext());

        this.newPassword.setHint(R.string.insert_password);
        this.confirmationPassword.setHint(R.string.confirmation_password);
        this.newPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        this.confirmationPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);



        LinearLayout lp = new LinearLayout(getContext());
        lp.setOrientation(LinearLayout.VERTICAL);

        lp.addView(this.newPassword);
        lp.addView(this.confirmationPassword);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Don't answer, yes he is.")
                .setTitle("Is Thanos bae ?")
                .setView(lp)
                .setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(newPassword.getText().toString().equals(confirmationPassword.getText().toString())){
                            Log.i("SKY_ESGI","Same");
                            // POUSSER UN DES DEUX CHAMPS VERS L'API
                            updateUserPassword(newPassword.getText().toString(), idUser);
                        } else {
                            Log.i("SKY_ESGI","Different");
                            // LAISSER LA FENETRE ET FAIRE UN TOAST POUR NOTIFIER L'UTILISATEUR
                            Toast.makeText(getActivity(),R.string.different_passwords,Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        this.dialog = builder.create();
    }






    private void updateUserInfo(UserUpdatedInfos userUpdatedInfos) {

        UserUpdatedInfos userUpdatedInfos1 = userUpdatedInfos;

        Call<UserUpdateResponse> call = apiInterface.updateUserInfo(userUpdatedInfos1);

        Log.i("SKY_ESGI",userUpdatedInfos.toString());


        call.enqueue(new Callback<UserUpdateResponse>(){
            UserUpdateResponse responseStr;

            @Override
            public void onResponse(Call<UserUpdateResponse> call, Response<UserUpdateResponse> response) {
                if (response.isSuccessful()) {
                    responseStr = response.body();
                } else {
                    responseStr = new UserUpdateResponse("Erreur");
                }
            }

            @Override
            public void onFailure(Call<UserUpdateResponse> call, Throwable t) {
                Log.i("SKY_ESGI", "onFailure: "+t.getLocalizedMessage());
            }
        });
    }



    // NEXT API CALL
    private void updateUserInfo_bis(UserUpdatedInfos userUpdatedInfos) {

        String URL = "https://www.titan-photography.com/user/infos/update";
        Map<String, String> params = new HashMap<String, String>();
        params.put("firstName", userUpdatedInfos.getFirstName());
        params.put("lastName", userUpdatedInfos.getLastName());
        params.put("birthDate", userUpdatedInfos.getBirthDate());
        params.put("email", userUpdatedInfos.getEmail());
        params.put("gender", userUpdatedInfos.getGender());
        params.put("areaCode", userUpdatedInfos.getAreaCode());
        params.put("idUser", String.valueOf(idUser));

        //API call made here
        RequestQueue queue = Volley.newRequestQueue(requireContext());
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(
                Request.Method.PUT,
                URL,
                new JSONObject(params),
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.i("responseAPI",response.toString());
                        Snackbar snackbar=  Snackbar.make(coordinatorLayout,"Vos informations ont été mises à jour.", Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.d("errorAPI","onErrorResponse:"+error.getMessage());
                        Snackbar snackbar=  Snackbar.make(coordinatorLayout,"Une erreur est survenue.", Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }
                });

        //ajouter la requete à la queue d'exécution
        queue.add(jsonArrayRequest);
    }



    // RESET PASSWORD FUNCTION
    private void updateUserPassword(String password,int idUser) {

        String URL = "https://www.titan-photography.com/user/password/reset";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("password", password);
        params.put("idUser", idUser);

        //API call made here
        RequestQueue queue = Volley.newRequestQueue(requireContext());
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(
                Request.Method.PUT,
                URL,
                new JSONObject(params),
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.i("responseAPI",response.toString());
                        Snackbar snackbar=  Snackbar.make(coordinatorLayout,"Votre mot de passe a été mises à jour.", Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("errorAPI","onErrorResponse:"+error.getLocalizedMessage());
                        //if(error.getLocalizedMessage().equals())
                        Snackbar snackbar=  Snackbar.make(coordinatorLayout,"Une erreur est survenue.", Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }
                });

        //ajouter la requete à la queue d'exécution
        queue.add(jsonArrayRequest);
    }
}