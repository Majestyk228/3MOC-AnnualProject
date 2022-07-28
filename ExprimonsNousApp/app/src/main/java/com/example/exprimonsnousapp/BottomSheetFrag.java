package com.example.exprimonsnousapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
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
            }
        });

        interessant_reward_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("REWARDLOGI", "onClick: INTERESSANT");

                RewardSend rewardSend = new RewardSend(2,idPost,idUser);
                sendReward(rewardSend);
            }
        });

        bof_reward_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("REWARDLOGI", "onClick: BOF");

                RewardSend rewardSend = new RewardSend(3,idPost,idUser);
                sendReward(rewardSend);
            }
        });

        pas_interessant_reward_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("REWARDLOGI", "onClick: PAS INTERESSANT");

                RewardSend rewardSend = new RewardSend(4,idPost,idUser);
                sendReward(rewardSend);
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
        Call<Object> call = apiInterface.sendReward(rewardSend);
        Log.i("SendReward", "sendReward object: "+rewardSend.toString());
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.i("SendReward", "onResponse success: "+response.body());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(getContext(), "Une erreur est survenue", Toast.LENGTH_LONG).show();
                Log.i("SendReward", "onResponse fail: "+t.getLocalizedMessage());
            }
        });
    }
}