package com.example.exprimonsnousapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exprimonsnousapp.adapters.CommentAdapter;
import com.example.exprimonsnousapp.adapters.PostAdapter;
import com.example.exprimonsnousapp.models.CommentPost;
import com.example.exprimonsnousapp.models.NewPost;
import com.example.exprimonsnousapp.models.Post;
import com.example.exprimonsnousapp.retrofit.ApiClient;
import com.example.exprimonsnousapp.retrofit.ApiInterface;
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

    // UI ELEMENTS
    TextView fullnameTXT;
    TextView bodyTXT;
    TextView textview_comment;

    // OTHER
    ApiInterface apiInterface;

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
        }

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        commentsPost = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_comment, container, false);


        fullnameTXT = view.findViewById(R.id.fullnameTXT);
        bodyTXT = view.findViewById(R.id.bodyTXT);
        textview_comment = view.findViewById(R.id.textview_comment);


        extractComments(idPost);
        getPost(idPost);

        // FAKE DELAY
        /*final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                Log.i("WAIT","WAIT DONE");
            }
        }, 1200);
        Log.i("EXTRACTCOMMENT", "onCreateView: commentsPosts = " + commentsPost.toString());*/

        recyclerViewComment = view.findViewById(R.id.recyclerViewComment);
        recyclerViewComment.setHasFixedSize(true);

        return view;
    }

    private void extractComments(int idPost) {
        Call<List<CommentPost>> call = apiInterface.getComments(idPost);
        call.enqueue(new Callback<List<CommentPost>>() {
            @Override
            public void onResponse(Call<List<CommentPost>> call, Response<List<CommentPost>> response) {

                for (int i = 0; i < response.body().size(); i++) {
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
                Toast.makeText(getContext(), "Une erreur est survenue", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void getPost(int idPost) {
        Call<Object> call = apiInterface.getPost(idPost);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.i("GETPOST", "onResponse getPost: " + response.body());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                //Toast.makeText(getContext(), "Une erreur est survenue", Toast.LENGTH_LONG).show();
                Log.i("GETPOST", "onFail getPost: " + t.getLocalizedMessage());
            }
        });
    }
}