package com.example.exprimonsnousapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
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

import com.example.exprimonsnousapp.LoginActivity;
import com.example.exprimonsnousapp.R;
import com.example.exprimonsnousapp.models.CommentPost;
import com.example.exprimonsnousapp.models.IdPost;
import com.example.exprimonsnousapp.models.Post;
import com.example.exprimonsnousapp.retrofit.ApiClient;
import com.example.exprimonsnousapp.retrofit.ApiInterface;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<CommentPost> commentsPost;
    ApiInterface apiInterface;
    Context context;
    private static String token;

    // SHARED PREFERENCES
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USER = "idUser";
    private static final String KEY_COMMUNITY = "idCommunity";
    private static final String KEY_TOKEN = "token";

    //constructeur de l'adapteur
    public CommentAdapter(Context context, List<CommentPost> commentsPost) {
        this.inflater = LayoutInflater.from(context);
        this.commentsPost = commentsPost;
        this.context = context;

        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(KEY_TOKEN, "");
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
                popup.inflate(R.menu.report_menu_comment);
                popup.setForceShowIcon(true);
                // LISTENING TO EVENTS
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.report:
                                //handle menu1 click
                                //IdPost idPost = new IdPost(posts.get(holder.getAbsoluteAdapterPosition()).getIdPost());
                                int idComment = commentsPost.get(holder.getAdapterPosition()).getIdComment();
                                Log.i("REPPORT", "Comment: "+commentsPost.get(holder.getAdapterPosition()));
                                reportComment(idComment);
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

    private void reportComment(int idComment) {
        Call<Object> call = apiInterface.reportComment(token, idComment);
        Log.i("REPORT", "Request  : "+idComment);

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.i("REPORTCOMMENT", "onResponse: Success : "+response.body());
                Toast.makeText(inflater.getContext(), R.string.dislike_toast, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.i("REPORTCOMMENT", "onResponse: Success : "+t.getLocalizedMessage());
                Toast.makeText(inflater.getContext(), R.string.error, Toast.LENGTH_SHORT).show();

                if(t.getLocalizedMessage().equals("{\"ERROR\": \"Token expired/incorrect\"}")) {
                    // TOAST NOTIFYING USER TO LOGIN AGAIN
                    Toast.makeText(context, "Veuillez vous reconnecter.", Toast.LENGTH_LONG).show();

                    // SET FRAGMENT STACK TO NULL

                    // GET TO LOGIN ACTIVITY
                    Intent myIntent = new Intent(context, LoginActivity.class);
                    context.startActivity(myIntent);
                } else {
                    Toast.makeText(context, "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
