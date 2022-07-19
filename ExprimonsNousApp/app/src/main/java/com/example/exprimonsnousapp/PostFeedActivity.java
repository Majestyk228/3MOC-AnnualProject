package com.example.exprimonsnousapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.exprimonsnousapp.adapters.PostAdapter;
import com.example.exprimonsnousapp.models.Post;
import com.example.exprimonsnousapp.models.UserCreds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PostFeedActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Post> posts;
    private String URL = "https://www.titan-photography.com/post/all";
    PostAdapter adapter;
    private int userId = -1;

    SwipeRefreshLayout swipeRefreshPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts_page);

        // RETRIEVING userId FROM EXTRAS
        Bundle extras = getIntent().getExtras();
        userId = extras.getInt("userId");

        //link the recycler view to external data
        recyclerView = findViewById(R.id.postsList);
        posts = new ArrayList<>();

        extractPost();

        swipeRefreshPosts = findViewById(R.id.swipeRefreshPosts);
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
    }

    private void extractPost() {
        //API call made here
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0 ; i<response.length() ; i++){
                            try {
                                JSONObject postObject = response.getJSONObject(i);

                                Post post = new Post();
                                post.setFirstname("#");
                                post.setLastname(String.valueOf(postObject.getInt("idUser")));
                                post.setBody(postObject.getString("body"));
                                post.setLikes(postObject.getInt("likes"));
                                post.setDislikes(postObject.getInt("dislikes"));
                                post.setNbComments(0);
                                post.setNbRewards(0);

                                posts.add(post);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        //adapter = new PostAdapter(getApplicationContext(), posts);
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