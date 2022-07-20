package com.example.exprimonsnousapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.exprimonsnousapp.adapters.VoteOptionsListAdapter;
import com.example.exprimonsnousapp.models.IdVote;
import com.example.exprimonsnousapp.models.UserCreds;
import com.example.exprimonsnousapp.models.Vote;
import com.example.exprimonsnousapp.models.VoteOption;
import com.example.exprimonsnousapp.retrofit.ApiClient;
import com.example.exprimonsnousapp.retrofit.ApiInterface;
import com.google.android.material.button.MaterialButton;

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
    private MaterialButton submitVoteBtn, cancelVoteBtn;

    // IMPORTANT DATA
    private int pidVote;

    // OTHER
    ApiInterface apiInterface;
    Toolbar myToolbar;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vote_participation, container, false);

        this.sujet_vote_name = view.findViewById(R.id.sujet_vote_name);
        this.title_vote_name = view.findViewById(R.id.title_vote_name);

        submitVoteBtn = (MaterialButton) view.findViewById(R.id.submitVoteBtn);
        cancelVoteBtn = (MaterialButton) view.findViewById(R.id.cancelVoteBtn);

        // LISTENING TO BUTTONS
        submitVoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // API CALL TO SUBMIT VOTE
                Toast.makeText(view.getContext(), "Votre vote a été pris en compte.", Toast.LENGTH_LONG).show();
            }
        });

        cancelVoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // POP FRAGMENT OFF
                // RETRAIT DU FRAGMENT CreatePostFragment

                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                fm.popBackStack("VoteParticipation", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        // Add the following lines to create RecyclerView
        recyclerView = view.findViewById(R.id.voteOptionsList);
        recyclerView.setHasFixedSize(true);

        // SEND
        //VoteOptionsListAdapter.selectedChoice();

        return view;
    }

    public void extractVoteOptions(IdVote idVote) {
        Call<List<VoteOption>> call = apiInterface.getVoteOptions(idVote);
        call.enqueue(new Callback<List<VoteOption>>() {

            @Override
            public void onResponse(Call<List<VoteOption>> call, Response<List<VoteOption>> response) {
                if (response.isSuccessful()) {
                    //voteOptions = (List<VoteOption>) response.body();
                    Log.i("VOTEDETAIL", "Erreur Else : " + response.body());
                    for(int i=0 ; i<response.body().size() ; i++) {
                        voteOptions.add(response.body().get(i));
                    }

                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new VoteOptionsListAdapter(voteOptions, getContext());
                    recyclerView.setAdapter(adapter);

                } else {
                    Toast.makeText(getContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<VoteOption>> call, Throwable t) {
                Toast.makeText(getContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                Log.i("VOTEDETAIL", "Erreur Else : " + t.getLocalizedMessage());
            }
        });
    }


    public Vote extractVote(IdVote idVote) {

        Vote voteBuffer = new Vote();

        Call<List<Vote>> call = apiInterface.getVote(idVote);
        call.enqueue(new Callback<List<Vote>>() {

            @Override
            public void onResponse(Call<List<Vote>> call, Response<List<Vote>> response) {
                if (response.isSuccessful()) {
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

                    sujet_vote_name.setText(voteBuffer.getTitle());
                    title_vote_name.setText(voteBuffer.getBody());

                } else {
                    Toast.makeText(getContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Vote>> call, Throwable t) {
                Toast.makeText(getContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
            }
        });

        return voteBuffer;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }
}