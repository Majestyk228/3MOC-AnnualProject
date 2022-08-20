package com.example.exprimonsnousapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.exprimonsnousapp.adapters.PostAdapter;
import com.example.exprimonsnousapp.models.IdPost;
import com.example.exprimonsnousapp.models.Post;
import com.example.exprimonsnousapp.retrofit.ApiClient;
import com.example.exprimonsnousapp.retrofit.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class PostFragment extends Fragment {

    // RECYCLER VIEW AND ITS DEPENDANCIES
    private RecyclerView recyclerView;
    PostAdapter adapter;
    List<Post> posts;
    private final String URL = "https://www.titan-photography.com/post/formatted/";
    private final String URLAdmin = "https://www.titan-photography.com/post/formatted/admin/";

    // SWIPE REFRESH LAYOUT - PULL DOWN TO REFRESH
    SwipeRefreshLayout swipeRefreshPosts;

    // IMPORTANTES DATA
    private final int communityId;
    private int userId;

    // FLOATING ACTION BUTTON
    FloatingActionButton fab;

    // TOOLBAR
    Toolbar myToolbar;

    // OTHER
    ApiInterface apiInterface;
    String token = "";

    // SHARED PREFERENCES
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USER = "idUser";
    private static final String KEY_COMMUNITY = "idCommunity";
    private static final String KEY_TOKEN = "token";

    public PostFragment(int communityId, int userId) {
        this.communityId = communityId;
        this.userId = userId;
    }

    public static PostFragment newInstance(int communityId, int userId) {
        PostFragment fragment = new PostFragment(communityId, userId);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(KEY_TOKEN, "");

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        posts = new ArrayList<>();

        try {
            extractPost();
            extractAdminPost();
        } catch (AuthFailureError e) {
            e.printStackTrace();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            posts.sort(Comparator.comparing(Post::getIdPost));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post, container, false);

        myToolbar = getActivity().findViewById(R.id.my_toolbar);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            this.userId = bundle.getInt("idUser", -1);
        }

        recyclerView = view.findViewById(R.id.postsList1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new PostAdapter(getActivity(), posts, communityId, userId);
        recyclerView.setAdapter(adapter);

        swipeRefreshPosts = view.findViewById(R.id.swipeRefreshPosts);
        swipeRefreshPosts.setColorSchemeColors(getResources().getColor(R.color.backgroundBlue));
        swipeRefreshPosts.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //wipe out de la liste des posts
                posts = new ArrayList<>();

                //réextraction de la liste des posts
                try {
                    extractPost();
                    extractAdminPost();
                } catch (AuthFailureError e) {
                    e.printStackTrace();
                }

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
                 *  Sur action du floating action button, on passe sur la stack le fragment de
                 *  création de poste en identifiant le fragment d'un tag 'CreatePostFragment'
                 *
                 *  ce tag servira à retirer le fragment de la stack sur l'appui du bouton retour
                 *  (ou de l'envoi d'un post en ligne)
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
                        fm.popBackStack("CreatePostFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                        // DESABLING RETURN BUTTON OF TOOLBAR AND CHANGING TITLE
                        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
                        myToolbar.setTitle("Les posts");
                    }
                });

                /*((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
                myToolbar.setTitle("Les posts");*/

            }
        });

        myToolbar.setTitle(R.string.posts_title);

        return view;
    }


    public void extractPost() throws AuthFailureError {
        //API call made here
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL + communityId,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject postObject = response.getJSONObject(i);

                                Post post = new Post();
                                post.setIdPost(postObject.getInt("idPost"));
                                post.setFirstname(postObject.getString("firstName"));
                                post.setLastname(postObject.getString("lastName"));
                                post.setBody(postObject.getString("body"));
                                post.setLikes(postObject.getInt("likes"));
                                post.setDislikes(postObject.getInt("dislikes"));

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
                                post.setUserInitials(postObject.getString("firstName"), postObject.getString("lastName"));

                                // CLASSICAL USER POST
                                post.setAdmin(false);
                                posts.add(post);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        adapter = new PostAdapter(getContext(), posts, communityId, userId);
                        recyclerView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // NOTIFY USER SESSION HAS EXPIRED
                        // CLEAR SHARED PREFERENCES
                        // GO TO LOGIN ACTIVITY
                        Log.d("SKY_ESGI", "onErrorResponse:" + error.getMessage());
                    }
                }) {
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", token);

                return params;
            }
        };

        //ajouter la requete à la queue d'exécution
        queue.add(jsonArrayRequest);
    }

    // TODO : TEST API CALL
    public void extractAdminPost() throws AuthFailureError {
        //API call made here
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URLAdmin + communityId,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject postObject = response.getJSONObject(i);

                                Post post = new Post();
                                post.setIdPost(postObject.getInt("idPost"));
                                post.setFirstname(postObject.getString("firstName"));
                                post.setLastname(postObject.getString("lastName"));
                                post.setBody(postObject.getString("body"));
                                post.setLikes(postObject.getInt("likes"));
                                post.setDislikes(postObject.getInt("dislikes"));

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
                                post.setUserInitials(postObject.getString("firstName"), postObject.getString("lastName"));

                                // ADMIN POST
                                post.setAdmin(true);
                                posts.add(post);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        adapter = new PostAdapter(getContext(), posts, communityId, userId);
                        recyclerView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        // NOTIFY USER SESSION HAS EXPIRED
                        // CLEAR SHARED PREFERENCES
                        // GO TO LOGIN ACTIVITY
                        Log.d("SKY_ESGI", "onErrorResponse:" + error.getMessage());
                    }
                }) {
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", token);

                return params;
            }
        };

        Log.i("POSTSADMIN", "extractPost: request = " + jsonArrayRequest.getHeaders());

        //ajouter la requete à la queue d'exécution
        queue.add(jsonArrayRequest);
    }
}