package com.example.exprimonsnousapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exprimonsnousapp.adapters.CommentAdapter;
import com.example.exprimonsnousapp.adapters.PostAdapter;
import com.example.exprimonsnousapp.models.CommentPost;
import com.example.exprimonsnousapp.models.NewComment;
import com.example.exprimonsnousapp.models.NewPost;
import com.example.exprimonsnousapp.models.Post;
import com.example.exprimonsnousapp.retrofit.ApiClient;
import com.example.exprimonsnousapp.retrofit.ApiInterface;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentFragment extends Fragment {

    // RECYCLER VIEW AND ITS DEPENDANCIES
    private RecyclerView recyclerViewComment;
    CommentAdapter adapter;
    List<CommentPost> commentsPost;
    int idPost;
    int idCommunity;
    int idUser;
    private String token;

    // SHARED PREFERENCES
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USER = "idUser";
    private static final String KEY_COMMUNITY = "idCommunity";
    private static final String KEY_TOKEN = "token";

    // UI ELEMENTS
    TextView fullnameTXT;
    TextView bodyTXT;
    TextView textview_comment;
    MaterialButton postCommentBtn;
    MaterialButton anonymousCommmentBtn;
    EditText newCommentEdittext;

    // OTHER
    ApiInterface apiInterface;
    private CoordinatorLayout coordinatorLayout;

    // TOOLBAR
    Toolbar myToolbar;


    public CommentFragment() {
        // Required empty public constructor
    }

    public static CommentFragment newInstance(String param1, String param2) {
        CommentFragment fragment = new CommentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idPost = getArguments().getInt("idPost");
            idCommunity = getArguments().getInt("idCommunity");
            idUser = getArguments().getInt("idUser");
        }

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        commentsPost = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_comment, container, false);

        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(KEY_TOKEN, "");


        fullnameTXT = view.findViewById(R.id.fullnameTXT);
        bodyTXT = view.findViewById(R.id.bodyTXT);
        textview_comment = view.findViewById(R.id.textview_comment);

        postCommentBtn = view.findViewById(R.id.postCommentBtn);
        anonymousCommmentBtn = view.findViewById(R.id.anonymousCommmentBtn);

        newCommentEdittext = view.findViewById(R.id.newCommentEdittext);

        this.coordinatorLayout = view.findViewById(R.id.coordinatorLayout);

        // LISTENING TO BUTTONS

        postCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewComment newComment = new NewComment(idCommunity, newCommentEdittext.getText().toString(), false, idPost, idUser);
                sendComment(newComment);
                Toast.makeText(getContext(), "Le commentaire a été ajouté.", Toast.LENGTH_LONG).show();

                commentsPost=new ArrayList<>();
                extractComments(idPost);
                adapter.notifyDataSetChanged();

                // EMPTY THE EDITTEXT
                newCommentEdittext.setText("");
            }
        });

        anonymousCommmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewComment newComment = new NewComment(idCommunity, newCommentEdittext.getText().toString(), true, idPost, idUser);
                sendComment(newComment);
                Toast.makeText(getContext(), "Le commentaire a été ajouté en anonyme.", Toast.LENGTH_LONG).show();


                commentsPost=new ArrayList<>();
                extractComments(idPost);
                adapter.notifyDataSetChanged();

                // EMPTY THE EDITTEXT
                newCommentEdittext.setText("");
            }
        });


        extractComments(idPost);
        getPost(idPost);


        // RECUPERATION DE LA TOOLBAR DU PARENT, CHANGEMENT DU TITRE ET AJOUT DU BOUTON RETOUR
        myToolbar = getActivity().findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Commentaires");
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
                fm.popBackStack("CommentFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                // DESABLING RETURN BUTTON OF TOOLBAR AND CHANGING TITLE
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
            }
        });

        /*((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        myToolbar.setTitle("Les posts");*/

        recyclerViewComment = view.findViewById(R.id.recyclerViewComment);
        recyclerViewComment.setHasFixedSize(true);

        return view;
    }

    private void extractComments(int idPost) {
        Call<List<CommentPost>> call = apiInterface.getComments(token, idPost);
        call.enqueue(new Callback<List<CommentPost>>() {
            @Override
            public void onResponse(Call<List<CommentPost>> call, Response<List<CommentPost>> response) {

                for (int i = 0; i < response.body().size(); i++) {
                    Log.i("REPPORT", "onResponse: "+response.body().get(i));
                    commentsPost.add(response.body().get(i));
                }
                recyclerViewComment.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new CommentAdapter(getActivity(), commentsPost);
                recyclerViewComment.setAdapter(adapter);


                // DISPLAYING THE NUMBER OF COMMENTS
                switch (commentsPost.size()) {
                    case 0:
                        textview_comment.setText("Commentaire");
                        break;
                    case 1:
                        textview_comment.setText("Commentaire (1)");
                        break;
                    default:
                        textview_comment.setText("Commentaires (" + commentsPost.size() + ")");
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<CommentPost>> call, Throwable t) {
                if(t.getLocalizedMessage().equals("{\"ERROR\": \"Token expired/incorrect\"}")) {
                    // TOAST NOTIFYING USER TO LOGIN AGAIN
                    Toast.makeText(getContext(), "Veuillez vous reconnecter.", Toast.LENGTH_LONG).show();

                    // SET FRAGMENT STACK TO NULL

                    // GET TO LOGIN ACTIVITY
                    Intent myIntent = new Intent(getContext(), LoginActivity.class);
                    getActivity().startActivity(myIntent);
                } else {
                    Toast.makeText(getContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void getPost(int idPost) {
        Call<Post> call = apiInterface.getPost(token,idPost);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                fullnameTXT.setText(response.body().getFirstname() + " " + response.body().getLastname());
                bodyTXT.setText(response.body().getBody());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                if(t.getLocalizedMessage().equals("{\"ERROR\": \"Token expired/incorrect\"}")) {
                    // TOAST NOTIFYING USER TO LOGIN AGAIN
                    Toast.makeText(getContext(), "Veuillez vous reconnecter.", Toast.LENGTH_LONG).show();

                    // SET FRAGMENT STACK TO NULL

                    // GET TO LOGIN ACTIVITY
                    Intent myIntent = new Intent(getContext(), LoginActivity.class);
                    getActivity().startActivity(myIntent);
                } else {
                    Toast.makeText(getContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void sendComment(NewComment newComment) {
        // API CALL
        Call<Object> call = apiInterface.sendComment(token,newComment);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                /*FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                fm.popBackStack("CommentFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);*/
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                if(t.getLocalizedMessage().equals("{\"ERROR\": \"Token expired/incorrect\"}")) {
                    // TOAST NOTIFYING USER TO LOGIN AGAIN
                    Toast.makeText(getContext(), "Veuillez vous reconnecter.", Toast.LENGTH_LONG).show();

                    // SET FRAGMENT STACK TO NULL

                    // GET TO LOGIN ACTIVITY
                    Intent myIntent = new Intent(getContext(), LoginActivity.class);
                    getActivity().startActivity(myIntent);
                } else {
                    Toast.makeText(getContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}