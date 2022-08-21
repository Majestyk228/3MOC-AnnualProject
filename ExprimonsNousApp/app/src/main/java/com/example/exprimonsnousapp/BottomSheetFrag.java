package com.example.exprimonsnousapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.exprimonsnousapp.models.Post;
import com.example.exprimonsnousapp.models.RewardSend;
import com.example.exprimonsnousapp.retrofit.ApiClient;
import com.example.exprimonsnousapp.retrofit.ApiInterface;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetFrag extends Fragment {

    LinearLayout super_reward_layout;
    LinearLayout interessant_reward_layout;
    LinearLayout bof_reward_layout;
    LinearLayout pas_interessant_reward_layout;

    // API
    ApiInterface apiInterface;

    // IMPORTANT DATA
    private int idPost;
    private int idCommunity;
    private int idUser;

    // TOOLBAR
    Toolbar myToolbar;

    //DATA
    private String token;

    // SHARED PREFERENCES
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USER = "idUser";
    private static final String KEY_COMMUNITY = "idCommunity";
    private static final String KEY_TOKEN = "token";

    public BottomSheetFrag() {
        // Required empty public constructor
    }

    public static BottomSheetFrag newInstance(String param1, String param2) {
        BottomSheetFrag fragment = new BottomSheetFrag();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idPost = getArguments().getInt("idPost");
            idCommunity = getArguments().getInt("idCommunity");
            idUser = getArguments().getInt("idUser");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(KEY_TOKEN, "");

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        super_reward_layout = view.findViewById(R.id.super_reward_layout);
        interessant_reward_layout = view.findViewById(R.id.interessant_reward_layout);
        bof_reward_layout = view.findViewById(R.id.bof_reward_layout);
        pas_interessant_reward_layout = view.findViewById(R.id.pas_interessant_reward_layout);

        // CREATE REWARD SEND OBJECT
        //RewardSend rewardSend = new RewardSend(0,idPost,idUser);

        super_reward_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("REWARDLOGI", "onClick: SUPER");

                RewardSend rewardSend = new RewardSend(1,idPost,idUser);
                sendReward(rewardSend);

                // RETRAIT DU FRAGMENT CreatePostFragment
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                fm.popBackStack("BottomSheetFrag", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                // DESABLING RETURN BUTTON OF TOOLBAR AND CHANGING TITLE
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
            }
        });

        interessant_reward_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("REWARDLOGI", "onClick: INTERESSANT");

                RewardSend rewardSend = new RewardSend(2,idPost,idUser);
                sendReward(rewardSend);

                // RETRAIT DU FRAGMENT CreatePostFragment
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                fm.popBackStack("BottomSheetFrag", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                // DESABLING RETURN BUTTON OF TOOLBAR AND CHANGING TITLE
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
            }
        });

        bof_reward_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("REWARDLOGI", "onClick: BOF");

                RewardSend rewardSend = new RewardSend(3,idPost,idUser);
                sendReward(rewardSend);

                // RETRAIT DU FRAGMENT CreatePostFragment
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                fm.popBackStack("BottomSheetFrag", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                // DESABLING RETURN BUTTON OF TOOLBAR AND CHANGING TITLE
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
            }
        });

        pas_interessant_reward_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("REWARDLOGI", "onClick: PAS INTERESSANT");

                RewardSend rewardSend = new RewardSend(4,idPost,idUser);
                sendReward(rewardSend);

                // RETRAIT DU FRAGMENT CreatePostFragment
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                fm.popBackStack("BottomSheetFrag", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                // DESABLING RETURN BUTTON OF TOOLBAR AND CHANGING TITLE
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
            }
        });

        // RECUPERATION DE LA TOOLBAR DU PARENT, CHANGEMENT DU TITRE ET AJOUT DU BOUTON RETOUR
        myToolbar = getActivity().findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.recompenses_string);
        ((AppCompatActivity) getActivity()).setSupportActionBar(myToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        // MISE EN ÉCOUTE DU BOUTON RETOUR
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // RETRAIT DU FRAGMENT CreatePostFragment
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                fm.popBackStack("BottomSheetFrag", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                // DESABLING RETURN BUTTON OF TOOLBAR AND CHANGING TITLE
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
            }
        });

        /*((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        myToolbar.setTitle("Les posts");*/

        return view;
    }

    private void sendReward(RewardSend rewardSend) {
        Call<Object> call = apiInterface.sendReward(token,rewardSend);
        Log.i("SendReward", "sendReward object: "+rewardSend.toString());
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.i("SendReward", "onResponse success: "+response.body());

                if(response.body()=="{Message=Reward added successfully.}") {
                    //Toast.makeText((FragmentActivity)getContext(), "Reward ajouté !", Toast.LENGTH_LONG).show();
                    //createToast("Reward ajouté !");
                } else {
                    //Toast.makeText((FragmentActivity)getContext(), "Vous avez déjà récompensé ce post.", Toast.LENGTH_LONG).show();
                    //createToast("Vous avez déjà récompensé ce post.");
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                /*if(t.getLocalizedMessage().equals("{\"ERROR\": \"Token expired/incorrect\"}")) {
                    // TOAST NOTIFYING USER TO LOGIN AGAIN
                    Toast.makeText(getContext(), "Veuillez vous reconnecter.", Toast.LENGTH_LONG).show();

                    // SET FRAGMENT STACK TO NULL

                    // GET TO LOGIN ACTIVITY
                    Intent myIntent = new Intent(getContext(), LoginActivity.class);
                    getActivity().startActivity(myIntent);
                } else {
                    Toast.makeText(getContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                }*/

                Toast.makeText(getContext(), "Veuillez vous reconnecter.", Toast.LENGTH_LONG).show();

                //EMPTYING SHARED PREFERENCES
                sharedPreferences.edit().clear();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void createToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}