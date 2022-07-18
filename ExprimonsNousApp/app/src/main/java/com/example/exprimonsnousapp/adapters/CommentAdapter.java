package com.example.exprimonsnousapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exprimonsnousapp.R;
import com.example.exprimonsnousapp.models.CommentPost;
import com.example.exprimonsnousapp.models.Post;
import com.example.exprimonsnousapp.retrofit.ApiClient;
import com.example.exprimonsnousapp.retrofit.ApiInterface;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{
    LayoutInflater inflater;
    List<CommentPost> commentsPost;
    ApiInterface apiInterface;
    Context context;

    //constructeur de l'adapteur
    public CommentAdapter(Context context, List<CommentPost> commentsPost) {
        this.inflater = LayoutInflater.from(context);
        this.commentsPost = commentsPost;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_comment, parent, false);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        return new CommentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        //bind the data
        holder.fullnameTXT.setText(commentsPost.get(holder.getAdapterPosition()).getFirstName()+ " " + commentsPost.get(holder.getAdapterPosition()).getLastName());
        holder.commentTXT.setText(commentsPost.get(holder.getAdapterPosition()).getBody());

        // TODO : Not implemented yet
    }

    @Override
    public int getItemCount() {
        return commentsPost.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView fullnameTXT,commentTXT;
        ImageView report;
        MaterialButton postCommentBtn, anonymousCommmentBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fullnameTXT = itemView.findViewById(R.id.fullnameTXT);
            commentTXT = itemView.findViewById(R.id.commentTXT);

            report = itemView.findViewById(R.id.report);

            postCommentBtn = itemView.findViewById(R.id.postCommentBtn);
            anonymousCommmentBtn = itemView.findViewById(R.id.anonymousCommmentBtn);


        }


    }
}
