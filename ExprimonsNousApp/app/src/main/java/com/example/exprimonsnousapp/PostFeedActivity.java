package com.example.exprimonsnousapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class PostFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts_page);

        // Lookup the recyclerview in activity layout
        RecyclerView rvPosts = (RecyclerView) findViewById(R.id.rvPosts);

        // Initialize contacts
        ArrayList<Post> postList =new ArrayList<Post>();
        postList.add(
                new Post(
                        "Sarah",
                        "KOUTA",
                        "Post de merde je suis fachée"
                        )
        );
        postList.add(
                new Post(
                        "Micheal",
                        "KOUTA",
                        "Deuxieme post"
                )
        );
        // Create adapter passing in the sample user data
        PostAdapter adapter = new PostAdapter();
        // Attach the adapter to the recyclerview to populate items
        rvPosts.setAdapter(adapter);
        // Set layout manager to position the items
        rvPosts.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
    }
}