package com.example.exprimonsnousapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.exprimonsnousapp.adapters.PostAdapter;
import com.example.exprimonsnousapp.models.Post;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PostFragment extends Fragment {

    // RECYCLER VIEW AND ITS DEPENDANCIES
    private RecyclerView recyclerView;
    PostAdapter adapter;
    List<Post> posts;
    private final String URL = "https://www.titan-photography.com/post/formatted/";

    // SWIPE REFRESH LAYOUT - PULL DOWN TO REFRESH
    SwipeRefreshLayout swipeRefreshPosts;

    // IMPORTANTES DATA
    private final int communityId;
    private int userId;

    // FLOATING ACTION BUTTON
    FloatingActionButton fab;

    // TOOLBAR
    Toolbar myToolbar;

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

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            this.userId = bundle.getInt("idUser", -1);
        }

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

                /*
                *
                * Sur action du floating action button, on passe sur la stack le fragment de
                * création de poste en identifiant le fragment d'un tag 'CreatePostFragment'
                *
                * ce tag servira à retirer le fragment de la stack sur l'appui du bouton retour
                * (ou de l'envoi d'un post en ligne)
                *
                * */


                Fragment mFragment = new CreatePostFragment();

                Bundle bundle = new Bundle();
                bundle.putInt("idUser", userId);
                bundle.putInt("idCommunity", communityId);
                mFragment.setArguments(bundle);

                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.activity_main_frame_layout, mFragment);
                ft.addToBackStack("CreatePostFragment");
                ft.commit();


                // RECUPERATION DE LA TOOLBAR DU PARENT, CHANGEMENT DU TITRE ET AJOUT DU BOUTON RETOUR
                myToolbar = getActivity().findViewById(R.id.my_toolbar);
                myToolbar.setTitle("Nouveau post");
                ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);
                ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

                // MISE EN ÉCOUTE DU BOUTON RETOUR
                myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // RETRAIT DU FRAGMENT CreatePostFragment
                        FragmentManager fm = getActivity()
                                .getSupportFragmentManager();
                        fm.popBackStack ("CreatePostFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                        // DESABLING RETURN BUTTON OF TOOLBAR AND CHANGING TITLE
                        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
                        myToolbar.setTitle("Les posts");
                    }
                });
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
                        Log.d("SKY_ESGI","onErrorResponse:"+error.getMessage());
                    }
                });

        //ajouter la requete à la queue d'exécution
        queue.add(jsonArrayRequest);
    }
}