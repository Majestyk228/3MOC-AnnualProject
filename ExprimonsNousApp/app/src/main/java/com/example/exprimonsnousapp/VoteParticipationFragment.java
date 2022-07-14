package com.example.exprimonsnousapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.exprimonsnousapp.adapters.VoteOptionsListAdapter;
import com.example.exprimonsnousapp.models.IdVote;
import com.example.exprimonsnousapp.models.UserCreds;
import com.example.exprimonsnousapp.models.Vote;
import com.example.exprimonsnousapp.models.VoteOption;
import com.example.exprimonsnousapp.retrofit.ApiClient;
import com.example.exprimonsnousapp.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoteParticipationFragment extends Fragment {

    // Add RecyclerView member
    private RecyclerView recyclerView;
    VoteOptionsListAdapter adapter;
    List<VoteOption> voteOptions;
    private Vote vote;

    // INTERFACE
    private TextView title_vote_name, sujet_vote_name;

    // IMPORTANT DATA
    private int pidVote;

    // OTHER
    ApiInterface apiInterface;

    public VoteParticipationFragment() {
        // Required empty public constructor
    }

    public static VoteParticipationFragment newInstance() {
        VoteParticipationFragment fragment = new VoteParticipationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.pidVote = getArguments().getInt("idVote");
        }

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        voteOptions = new ArrayList<>();

        IdVote idVote = new IdVote(pidVote);

        extractVote(idVote);
        extractVoteOptions(idVote);

        // CHANGING TEXTVIEWS TO MATCH TITLE AND SUBJECT

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vote_participation, container, false);

        this.sujet_vote_name = view.findViewById(R.id.sujet_vote_name);
        this.title_vote_name = view.findViewById(R.id.title_vote_name);

        //Log.i("Test",this.vote.toString());

        //this.sujet_vote_name.setText(this.vote.getTitle());
        //this.title_vote_name.setText(this.vote.getBody());

        // Add the following lines to create RecyclerView
        recyclerView = view.findViewById(R.id.voteOptionsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new VoteOptionsListAdapter(voteOptions,getContext()));
        return view;
    }

    public void extractVoteOptions(IdVote idVote) {
        Call<VoteOption> call = apiInterface.getVoteOptions(idVote);
        call.enqueue(new Callback<VoteOption>(){

            @Override
            public void onResponse(Call<VoteOption> call, Response<VoteOption> response) {
                if (response.isSuccessful()) {
                    voteOptions = (List<VoteOption>) response.body();
                } else {
                    Log.i("VOTEOPTIONS","ERROR");
                }
            }

            @Override
            public void onFailure(Call<VoteOption> call, Throwable t) {
                Log.i("VOTEOPTIONS",t.getLocalizedMessage());
            }
        });
    }


    public void extractVote(IdVote idVote) {
        Call<Object> call = apiInterface.getVote(idVote);
        call.enqueue(new Callback<Object>(){

            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    vote = (Vote) response.body();
                    Log.i("VOTEREFERENCE", response.body().toString());
                } else {
                    Log.i("VOTEOPTIONS",response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.i("VOTEOPTIONS",t.getLocalizedMessage());
            }
        });
    }
}