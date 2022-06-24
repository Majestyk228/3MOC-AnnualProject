package com.example.exprimonsnousapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.exprimonsnousapp.adapters.VoteAdapter;
import com.example.exprimonsnousapp.models.IdCommunity;
import com.example.exprimonsnousapp.models.Vote;
import com.example.exprimonsnousapp.retrofit.ApiClient;
import com.example.exprimonsnousapp.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoteFragment extends Fragment {

    // RECYCLER VIEW AND ITS DEPENDANCIES
    private RecyclerView recyclerView;
    VoteAdapter adapter;
    List<Vote> votes;
    private final String URL = "https://www.titan-photography.com/vote/voteList";

    // SWIPE REFRESH LAYOUT - PULL DOWN TO REFRESH
    SwipeRefreshLayout swipeRefreshPosts;

    // IMPORTANTES DATA
    private int communityId;
    private int userId;

    // OTHER
    ApiInterface apiInterface;

    public VoteFragment() {
        // Required empty public constructor
    }



    public static VoteFragment newInstance(String param1, String param2) {
        VoteFragment fragment = new VoteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        votes = new ArrayList<>();



        // GET DATA FROM BUNDLE
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            this.userId = bundle.getInt("idUser", -1);
            this.communityId = bundle.getInt("idCommunity", -1);
        }

        IdCommunity idCommunity = new IdCommunity(communityId);
        extractVote(idCommunity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vote, container, false);

        recyclerView = view.findViewById(R.id.voteList1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new VoteAdapter(getActivity(),votes);
        recyclerView.setAdapter(adapter);

        swipeRefreshPosts = view.findViewById(R.id.swipeRefreshVotes);
        swipeRefreshPosts.setColorSchemeColors(getResources().getColor(R.color.backgroundBlue));
        swipeRefreshPosts.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //wipe out de la liste des posts
                votes = new ArrayList<>();

                //réextraction de la liste des posts
                IdCommunity idCommunity = new IdCommunity(communityId);
                extractVote(idCommunity);

                //implémeenter le changement de données
                adapter.notifyDataSetChanged();
                swipeRefreshPosts.setRefreshing(false);
            }
        });


        return view;
    }





    private void extractVote(IdCommunity idCommunity) {
        Call<List<Vote>> call = apiInterface.getVotesFromCommunity(idCommunity);
        Log.i("APICALL",call.request().toString());
        call.enqueue(new Callback<List<Vote>>(){

            @Override
            public void onResponse(Call<List<Vote>> call, Response<List<Vote>> response) {
                if (response.isSuccessful()) {
                    votes = response.body();
                    Log.i("API RESPONSE", votes.toString());
                } else {
                    // BAD REQUEST CASE
                    Log.i("API RESPONSE", response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Vote>> call, Throwable t) {
                Log.i("API RESPONSE", "onFailure: "+t.getLocalizedMessage());
            }
        });
    }
}