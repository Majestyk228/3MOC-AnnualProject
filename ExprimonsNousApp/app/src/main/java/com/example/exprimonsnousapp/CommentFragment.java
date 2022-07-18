package com.example.exprimonsnousapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.exprimonsnousapp.adapters.CommentAdapter;
import com.example.exprimonsnousapp.adapters.PostAdapter;
import com.example.exprimonsnousapp.models.CommentPost;
import com.example.exprimonsnousapp.models.Post;

import java.util.ArrayList;
import java.util.List;

public class CommentFragment extends Fragment {

    // RECYCLER VIEW AND ITS DEPENDANCIES
    private RecyclerView recyclerViewComment;
    CommentAdapter adapter;
    List<CommentPost> commentsPost;

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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_comment, container, false);

        commentsPost = new ArrayList<>();
        //FAKE DATA
        commentsPost.add(new CommentPost("Mama JACKSON","Best restaurant in town !", 1));

        recyclerViewComment = view.findViewById(R.id.recyclerViewComment);
        recyclerViewComment.setHasFixedSize(true);
        recyclerViewComment.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new CommentAdapter(getActivity(), commentsPost);
        recyclerViewComment.setAdapter(adapter);

        return view;
    }
}