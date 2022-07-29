package com.example.exprimonsnousapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exprimonsnousapp.R;
import com.example.exprimonsnousapp.models.CommentPost;
import com.example.exprimonsnousapp.models.IdPost;
import com.example.exprimonsnousapp.models.Post;
import com.example.exprimonsnousapp.retrofit.ApiClient;
import com.example.exprimonsnousapp.retrofit.ApiInterface;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
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
        if (commentsPost.get(holder.getAdapterPosition()).getAnonymous() == 1) {
            // change fullname
            holder.fullnameTXT.setText(R.string.anonymous_fullname);

        } else {
            //keep the line
            holder.fullnameTXT.setText(commentsPost.get(holder.getAdapterPosition()).getFirstName() + " " + commentsPost.get(holder.getAdapterPosition()).getLastName());
        }
        holder.commentTXT.setText(commentsPost.get(holder.getAdapterPosition()).getBody());

        holder.report_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(context, holder.report_comment);
                // INFLATE OBJECT
                popup.inflate(R.menu.report_menu);
                popup.setForceShowIcon(true);
                // LISTENING TO EVENTS
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.report:
                                //handle menu1 click
                                //IdPost idPost = new IdPost(posts.get(holder.getAbsoluteAdapterPosition()).getIdPost());
                                //reportPost(Integer.parseInt(idPost.getIdPost()));
                                Toast.makeText(inflater.getContext(), "Report done.", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return commentsPost.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView fullnameTXT, commentTXT;
        ImageView report_comment;
        MaterialButton postCommentBtn, anonymousCommmentBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fullnameTXT = itemView.findViewById(R.id.fullnameTXT);
            commentTXT = itemView.findViewById(R.id.commentTXT);

            report_comment = itemView.findViewById(R.id.report_comment);

            postCommentBtn = itemView.findViewById(R.id.postCommentBtn);
            anonymousCommmentBtn = itemView.findViewById(R.id.anonymousCommmentBtn);
        }
    }
}
