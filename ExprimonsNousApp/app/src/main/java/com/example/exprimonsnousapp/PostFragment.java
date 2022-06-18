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
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.exprimonsnousapp.adapters.PostAdapter;
import com.example.exprimonsnousapp.models.Post;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostFragment extends Fragment {

    private RecyclerView recyclerView;
    PostAdapter adapter;
    List<Post> posts;
    private String URL = "https://www.titan-photography.com/post/formatted/";
    SwipeRefreshLayout swipeRefreshPosts;
    private int communityId;

    //buttons
    /*Button likeBtn;
    Button dislikeBtn;
    Button commentBtn;
    Button rewardBtn;*/

    //floating button
    FloatingActionButton fab;

    public PostFragment(int communityId) {
        this.communityId = communityId;
    }

    public static PostFragment newInstance(int communityId) {
        PostFragment fragment = new PostFragment(communityId);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        posts = new ArrayList<>();

        extractPost();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post, container, false);

        recyclerView = view.findViewById(R.id.postsList1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new PostAdapter(getActivity(),posts);
        recyclerView.setAdapter(adapter);

        swipeRefreshPosts = view.findViewById(R.id.swipeRefreshPosts);
        swipeRefreshPosts.setColorSchemeColors(getResources().getColor(R.color.backgroundBlue));
        swipeRefreshPosts.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //wipe out de la liste des posts
                posts = new ArrayList<>();

                //réextraction de la liste des posts
                extractPost();

                //implémeenter le changement de données
                adapter.notifyDataSetChanged();
                swipeRefreshPosts.setRefreshing(false);
            }
        });

        fab = view.findViewById(R.id.add_post);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return view;
    }


    private void extractPost() {
        //API call made here
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL+communityId,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("responseAPI",response.toString());
                        for(int i = 0 ; i<response.length() ; i++){
                            try {
                                JSONObject postObject = response.getJSONObject(i);

                                Post post = new Post();
                                post.setFirstname(postObject.getString("firstName"));
                                post.setLastname(postObject.getString("lastName"));
                                post.setBody(postObject.getString("body"));
                                //post.setLikes(postObject.getInt("likes") ? postObject.getInt("likes") : 0);
                                if (postObject.get("likes") != null) {
                                    post.setLikes(postObject.getInt("likes"));
                                } else {
                                    post.setLikes(0);
                                }

                                if (postObject.get("dislikes") != null) {
                                    post.setDislikes(postObject.getInt("dislikes"));
                                } else {
                                    post.setDislikes(0);
                                }

                                if (postObject.get("comments") != null) {
                                    post.setNbComments(postObject.getInt("comments"));
                                } else {
                                    post.setNbComments(0);
                                }


                                if (postObject.get("rewards") != null) {
                                    post.setNbRewards(postObject.getInt("rewards"));
                                } else {
                                    post.setNbRewards(0);
                                }
                                //post.setDislikes(postObject.getInt("dislikes"));
                                //post.setNbComments(postObject.getInt("comments"));
                                //post.setNbRewards(postObject.getInt("rewards"));

                                posts.add(post);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        adapter = new PostAdapter(getContext(), posts);
                        recyclerView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("errorAPI","onErrorResponse:"+error.getMessage());
                    }
                });

        //ajouter la requete à la queue d'exécution
        queue.add(jsonArrayRequest);
    }
}