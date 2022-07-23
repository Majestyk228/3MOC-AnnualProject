package com.example.exprimonsnousapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.exprimonsnousapp.models.Post;
import com.example.exprimonsnousapp.retrofit.ApiClient;
import com.example.exprimonsnousapp.retrofit.ApiInterface;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetFrag extends BottomSheetDialogFragment {

    LinearLayout super_reward_layout;
    LinearLayout interessant_reward_layout;
    LinearLayout bof_reward_layout;
    LinearLayout pas_interessant_reward_layout;

    // API
    ApiInterface apiInterface;

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

        super_reward_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("REWARDLOGI", "onClick: SUPER");
            }
        });

        interessant_reward_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("REWARDLOGI", "onClick: INTERESSANT");
            }
        });

        bof_reward_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("REWARDLOGI", "onClick: BOF");
            }
        });

        pas_interessant_reward_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("REWARDLOGI", "onClick: PAS INTERESSANT");
            }
        });

        return view;
    }

    /*private void sendReward(int idReward) {
        Call<Post> call = apiInterface.sendReward(idPost);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                fullnameTXT.setText(response.body().getFirstname() + " " + response.body().getLastname());
                bodyTXT.setText(response.body().getBody());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getContext(), "Une erreur est survenue", Toast.LENGTH_LONG).show();
            }
        });
    }*/
}