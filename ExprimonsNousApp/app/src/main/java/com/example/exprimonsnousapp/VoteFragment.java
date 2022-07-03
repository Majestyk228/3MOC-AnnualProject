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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.exprimonsnousapp.adapters.PostAdapter;
import com.example.exprimonsnousapp.adapters.VoteAdapter;
import com.example.exprimonsnousapp.models.IdCommunity;
import com.example.exprimonsnousapp.models.Post;
import com.example.exprimonsnousapp.models.Vote;
import com.example.exprimonsnousapp.retrofit.ApiClient;
import com.example.exprimonsnousapp.retrofit.ApiInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

        //IdCommunity idCommunity = new IdCommunity(communityId);
        /*try {
            extractVoteBis(idCommunity);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        //extractVote(idCommunity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vote, container, false);

        IdCommunity idCommunity = new IdCommunity(communityId);
        try {
            extractVoteBis(idCommunity);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        recyclerView = view.findViewById(R.id.voteList);
        recyclerView.setHasFixedSize(true);

        adapter = new VoteAdapter(getActivity(), votes);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
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
                try {
                    extractVoteBis(idCommunity);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //extractVote(idCommunity);

                //implémeenter le changement de données
                adapter.notifyDataSetChanged();
                swipeRefreshPosts.setRefreshing(false);
            }
        });


        return view;
    }


    private void extractVote(IdCommunity idCommunity) {
        Call<List<Vote>> call = apiInterface.getVotesFromCommunity(idCommunity);
        Log.i("APICALL", call.request().toString());
        call.enqueue(new Callback<List<Vote>>() {

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
                Log.i("API RESPONSE", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    private void extractVoteBis(IdCommunity idCommunity) throws JSONException {
        // BUILDING BODY OBJECT


        JSONArray array = new JSONArray();
        JSONObject obj=new JSONObject();
        try {
            obj.put("idCommunity",String.valueOf(idCommunity.getIdCommunity()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        array.put(obj);


        /*Map<String, String> params = new HashMap<String, String>();
        params.put("idCommunity", String.valueOf(idCommunity.getIdCommunity()));
        Array body = new A*/

        //API call made here
        RequestQueue queue = Volley.newRequestQueue(requireActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL+"/"+idCommunity.getIdCommunity(),
                array,
                new com.android.volley.Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Vote vote;
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject voteObject = response.getJSONObject(i);

                                vote = new Vote();
                                vote.setIdVote(voteObject.getInt("idVote"));
                                vote.setTitle(voteObject.getString("title"));
                                vote.setBody(voteObject.getString("body"));
                                vote.setNbChoice(voteObject.getInt("nbChoices"));
                                vote.setImportant(voteObject.getInt("important"));
                                //vote.setIdUser(voteObject.getInt("idUser"));
                                vote.setIdUser(-1);
                                vote.setIdAdmin(voteObject.getInt("idAdmin"));
                                vote.setVoteBegins(Date.valueOf(voteObject.get("voteBegins").toString()));
                                vote.setVoteEnds(Date.valueOf(voteObject.get("voteEnds").toString()));
                                vote.setIdCommunity(voteObject.getInt("idCommunity"));

                                votes.add(vote);
                                Log.i("responseAPI", vote.toString());


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        adapter = new VoteAdapter(getContext(), votes);
                        recyclerView.setAdapter(adapter);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("SKY_ESGI", "onErrorResponse:" + error.getMessage());
                    }
                });

        //ajouter la requete à la queue d'exécution
        queue.add(jsonArrayRequest);
    }
}