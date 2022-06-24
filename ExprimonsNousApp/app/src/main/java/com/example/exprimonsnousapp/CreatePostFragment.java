package com.example.exprimonsnousapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

public class CreatePostFragment extends Fragment {

    private EditText postBody;
    private EditText postTitle;
    private MaterialButton postButton;
    private MaterialButton cancelButton;

    private int idUser;
    private int idCommunity;

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

        postButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //code...
                Log.i("SKY_ESGI","New post created for userID = "+ idUser + " et idCommunity = " + idCommunity);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //code...
                Log.i("SKY_ESGI","New post cancelled");
            }
        });

        return view;
    }
}