package com.example.exprimonsnousapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exprimonsnousapp.models.NewPost;
import com.example.exprimonsnousapp.retrofit.ApiClient;
import com.example.exprimonsnousapp.retrofit.ApiInterface;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePostFragment extends Fragment {

    private EditText postBody;
    private EditText postTitle;
    private MaterialButton postButton;
    private MaterialButton cancelButton;

    private int idUser;
    private int idCommunity;
    private String token;

    // API
    ApiInterface apiInterface;

    // COORDINATOR LAYOUT
    private CoordinatorLayout coordinatorLayout;

    // SHARED PREFERENCES
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USER = "idUser";
    private static final String KEY_COMMUNITY = "idCommunity";
    private static final String KEY_TOKEN = "token";

    public CreatePostFragment() {
        // Required empty public constructor
    }

    public static CreatePostFragment newInstance() {
        CreatePostFragment fragment = new CreatePostFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);

        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(KEY_TOKEN, "");

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        // RÉCUPÉRATION DE l'IDUSER ET L'IDCOMMUNITY PASSÉ EN ARGUMENT DU FRAGMENT
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            this.idUser = bundle.getInt("idUser", -1);
            this.idCommunity = bundle.getInt("idCommunity", -1);
        }

        postBody = view.findViewById(R.id.newPostEdittext);
        postTitle = view.findViewById(R.id.newPostTitleEdittext);
        postButton = view.findViewById(R.id.postBtn);
        cancelButton = view.findViewById(R.id.cancelBtn);

        this.coordinatorLayout = view.findViewById(R.id.coordinatorLayout);

        postButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //code...
                NewPost newPost = new NewPost(postTitle.getText().toString(),postBody.getText().toString(),idCommunity,idUser,"null");
                sendNewPost(newPost);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                fm.popBackStack ("CreatePostFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        return view;
    }

    private void sendNewPost(NewPost newPost) {
        Call<Object> call = apiInterface.postPost(token,newPost);

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                // RETURN TO LAST FRANGMENT
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                fm.popBackStack ("CreatePostFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                Snackbar.make(coordinatorLayout,"Le post a été créé", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                /*if(t.getLocalizedMessage().equals("{\"ERROR\": \"Token expired/incorrect\"}")) {
                    // TOAST NOTIFYING USER TO LOGIN AGAIN
                    Toast.makeText(getContext(), "Veuillez vous reconnecter.", Toast.LENGTH_LONG).show();

                    // SET FRAGMENT STACK TO NULL

                    // GET TO LOGIN ACTIVITY
                    Intent myIntent = new Intent(getContext(), LoginActivity.class);
                    getActivity().startActivity(myIntent);
                } else {
                    Toast.makeText(getContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                }*/

                Toast.makeText(getContext(), "Veuillez vous reconnecter.", Toast.LENGTH_LONG).show();

                //EMPTYING SHARED PREFERENCES
                sharedPreferences.edit().clear();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}