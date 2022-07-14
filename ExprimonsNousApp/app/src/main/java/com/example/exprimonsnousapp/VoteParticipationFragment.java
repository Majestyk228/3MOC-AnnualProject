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
    Vote vote;

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

        vote = extractVote(idVote);
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

        Log.i("Test",vote.toString());

        this.sujet_vote_name.setText(this.vote.getTitle());
        this.title_vote_name.setText(this.vote.getBody());

        // Add the following lines to create RecyclerView
        recyclerView = view.findViewById(R.id.voteOptionsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new VoteOptionsListAdapter(voteOptions,getContext());
        recyclerView.setAdapter(adapter);
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


    public Vote extractVote(IdVote idVote) {

        Vote voteBuffer = new Vote();

        Call<List<Vote>> call = apiInterface.getVote(idVote);

        //Log.i("VOTEREFERENCE",call.request().body().toString());

        call.enqueue(new Callback<List<Vote>>(){

            @Override
            public void onResponse(Call<List<Vote>> call, Response<List<Vote>>response) {
                if (response.isSuccessful()) {
                    //vote = response.body().get(0);
                    voteBuffer.setIdVote(response.body().get(0).getIdVote());
                    voteBuffer.setTitle(response.body().get(0).getTitle());
                    voteBuffer.setBody(response.body().get(0).getBody());
                    voteBuffer.setNbChoice(response.body().get(0).getNbChoice());
                    voteBuffer.setImportant(response.body().get(0).getImportant());
                    voteBuffer.setIdUser(response.body().get(0).getIdUser());
                    voteBuffer.setIdAdmin(response.body().get(0).getIdAdmin());
                    voteBuffer.setVoteBegins(response.body().get(0).getVoteBegins());
                    voteBuffer.setVoteEnds(response.body().get(0).getVoteEnds());
                    voteBuffer.setIdCommunity(response.body().get(0).getIdCommunity());
                    setVote(voteBuffer);
                } else {
                    Log.i("VOTEOPTIONS","the fuck");
                }
            }

            @Override
            public void onFailure(Call<List<Vote>> call, Throwable t) {
                Log.i("VOTEOPTIONS",t.getLocalizedMessage());
            }
        });

        Log.i("VOTEREFERENCE", voteBuffer.toString());
        return voteBuffer;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }
}