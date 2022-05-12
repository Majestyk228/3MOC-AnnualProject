package com.example.exprimonsnousapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    // Store a member variable for the contacts
    private List<Post> mPost;

    // Pass in the contact array into the constructor
    public void ContactsAdapter(List<Post> posts) {
        mPost = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_post, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PostAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Post unPost = mPost.get(position);

        //TextView fullname;
        //TextView body;
        //TextView likes;
        //TextView dislikes;
        //TextView nbComments;
        //TextView nbRewards;

        // Set item views based on your views and data model
        TextView fullname = holder.fullname;
        fullname.setText(unPost.getFirstname()+" "+unPost.getLastname());

        TextView body = holder.body;
        body.setText(unPost.getBody());

        TextView likes = holder.likes;
        likes.setText(unPost.getLikes());

        TextView dislikes = holder.dislikes;
        dislikes.setText(unPost.getDislikes());

        TextView nbComments = holder.nbComments;
        nbComments.setText(unPost.getNbComments());

        TextView nbRewards = holder.nbRewards;
        nbRewards.setText(unPost.getNbRewards());
    }

    @Override
    public int getItemCount() {
        return mPost.size();
    }











    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView fullname;
        public TextView body;
        public TextView likes;
        public TextView dislikes;
        public TextView nbComments;
        public TextView nbRewards;
        public Button likeBtn;
        public Button dislikeBtn;
        public Button commentBtn;
        public Button rewardBtn;


        public ViewHolder(View itemView) {
            super(itemView);

            fullname = (TextView) itemView.findViewById(R.id.fullnameTXT);
            body = (TextView) itemView.findViewById(R.id.bodyTXT);
            likes = (TextView) itemView.findViewById(R.id.likesTXT);
            dislikes = (TextView) itemView.findViewById(R.id.dislikesTXT);
            nbComments = (TextView) itemView.findViewById(R.id.commentTXT);
            nbRewards = (TextView) itemView.findViewById(R.id.rewardTXT);
            likeBtn = (Button) itemView.findViewById(R.id.likeBtn);
            dislikeBtn = (Button) itemView.findViewById(R.id.dislikeBtn);
            commentBtn = (Button) itemView.findViewById(R.id.commentBtn);
            rewardBtn = (Button) itemView.findViewById(R.id.rewardBtn);
        }
    }
}
