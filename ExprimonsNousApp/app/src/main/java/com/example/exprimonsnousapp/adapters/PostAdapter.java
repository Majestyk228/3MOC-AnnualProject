package com.example.exprimonsnousapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exprimonsnousapp.models.Post;
import com.example.exprimonsnousapp.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<Post> posts;


    //constructeur de l'adapteur
    public PostAdapter(Context context, List<Post> posts) {
        this.inflater = LayoutInflater.from(context);
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        //bind the data
        holder.fullnameTXT.setText(posts.get(position).getFirstname() + " " +posts.get(position).getLastname());
        holder.bodyTXT.setText(posts.get(position).getBody());
        holder.likesTXT.setText(posts.get(position).getLikes());
        holder.dislikesTXT.setText(posts.get(position).getDislikes());
        holder.commentTXT.setText(posts.get(position).getNbComments());
        holder.rewardTXT.setText(posts.get(position).getNbRewards());

        //holder.fullnameTXT
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }





    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView fullnameTXT,bodyTXT,likesTXT, dislikesTXT, commentTXT, rewardTXT;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fullnameTXT = itemView.findViewById(R.id.fullnameTXT);
            bodyTXT = itemView.findViewById(R.id.bodyTXT);
            likesTXT = itemView.findViewById(R.id.likesTXT);
            dislikesTXT = itemView.findViewById(R.id.dislikesTXT);
            commentTXT = itemView.findViewById(R.id.commentTXT);
            rewardTXT = itemView.findViewById(R.id.rewardTXT);

        }
    }
}
