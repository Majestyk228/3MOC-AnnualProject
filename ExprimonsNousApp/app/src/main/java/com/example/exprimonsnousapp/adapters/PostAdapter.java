package com.example.exprimonsnousapp.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exprimonsnousapp.BottomSheetFrag;
import com.example.exprimonsnousapp.CommentFragment;
import com.example.exprimonsnousapp.CreatePostFragment;
import com.example.exprimonsnousapp.models.IdPost;
import com.example.exprimonsnousapp.models.Post;
import com.example.exprimonsnousapp.R;
import com.example.exprimonsnousapp.retrofit.ApiClient;
import com.example.exprimonsnousapp.retrofit.ApiInterface;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<Post> posts;
    ApiInterface apiInterface;
    Context context;
    int communityId;
    int idUser;
    private String token;

    // SHARED PREFERENCES
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USER = "idUser";
    private static final String KEY_COMMUNITY = "idCommunity";
    private static final String KEY_TOKEN = "token";


    // ADAPTER'S CONSTRUTOR
    public PostAdapter(Context context, List<Post> posts, int communityId, int idUser) {
        this.inflater = LayoutInflater.from(context);
        this.posts = posts;
        this.context = context;
        this.communityId = communityId;
        this.idUser = idUser;

        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(KEY_TOKEN, "");
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_post2, parent, false);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        //bind the data
        holder.fullnameTXT.setText(posts.get(holder.getAdapterPosition()).getFirstname() + " " + posts.get(holder.getAdapterPosition()).getLastname());
        holder.bodyTXT.setText(posts.get(holder.getAdapterPosition()).getBody());
        holder.likesTXT.setText(posts.get(holder.getAdapterPosition()).getLikes());
        holder.dislikesTXT.setText(posts.get(holder.getAdapterPosition()).getDislikes());
        holder.commentTXT.setText(posts.get(holder.getAdapterPosition()).getNbComments());
        holder.rewardTXT.setText(posts.get(holder.getAdapterPosition()).getNbRewards());
        holder.avatar_textfield.setText(posts.get(holder.getAdapterPosition()).getUserInitials());

        // IF A POST IS NOT AN ADMIN POST, HIDE THE ADMIN LOGO FROM THE POST
        if (!posts.get(position).isAdmin()) {
            holder.is_admin_post.setVisibility(View.INVISIBLE);
        }

        holder.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // APPEL DE LA ROUTE POUR AJOUTER UN LIKE AU POST AVEC L'idPost
                IdPost idPost = new IdPost(posts.get(holder.getAbsoluteAdapterPosition()).getIdPost());
                addLike(idPost);

                // Fake refresh of the number of likes
                int nblikes = Integer.parseInt((String) holder.likesTXT.getText()) + 1;
                holder.likesTXT.setText(nblikes + "");
            }
        });

        holder.dislikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // APPEL DE LA ROUTE POUR AJOUTER UN DISLIKE AU POST AVEC L'idPost
                IdPost idPost = new IdPost(posts.get(holder.getAbsoluteAdapterPosition()).getIdPost());
                addDislike(idPost);

                // Fake refresh of the number of dislikes
                int nblikes = Integer.parseInt((String) holder.dislikesTXT.getText()) + 1;
                holder.dislikesTXT.setText(nblikes + "");
            }
        });

        holder.commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // APPEL DE LA ROUTE POUR OUVRIR LA PAGE DES COMMENTAIRES
                Fragment mFragment = new CommentFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("idPost", Integer.parseInt(posts.get(holder.getAdapterPosition()).getIdPost()));
                bundle.putInt("idCommunity", communityId);
                bundle.putInt("idUser", idUser);
                mFragment.setArguments(bundle);

                FragmentTransaction ft = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.activity_main_frame_layout, mFragment);
                ft.addToBackStack("CommentFragment");
                ft.commit();
            }
        });

        holder.rewardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // APPEL DE LA ROUTE POUR AJOUTER UN REWARD AU POST AVEC L'idPost
                Fragment mFragment = new BottomSheetFrag();

                Bundle bundle = new Bundle();
                bundle.putInt("idPost", Integer.parseInt(posts.get(holder.getAdapterPosition()).getIdPost()));
                bundle.putInt("idCommunity", communityId);
                bundle.putInt("idUser", idUser);
                mFragment.setArguments(bundle);

                FragmentTransaction ft = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.activity_main_frame_layout, mFragment);
                ft.addToBackStack("BottomSheetFrag");
                ft.commit();
            }
        });

        holder.menu_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // CREATING POPUP MENU
                PopupMenu popup = new PopupMenu(context, holder.menu_post);
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
                                IdPost idPost = new IdPost(posts.get(holder.getAbsoluteAdapterPosition()).getIdPost());
                                reportPost(Integer.parseInt(idPost.getIdPost()));
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
        return posts.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView fullnameTXT, bodyTXT, likesTXT, dislikesTXT, commentTXT, rewardTXT, avatar_textfield;
        //Button likeBtn, dislikeBtn, commentBtn, rewardBtn;
        LinearLayout likeBtn, dislikeBtn, commentBtn, rewardBtn;

        ImageView menu_post, is_admin_post;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fullnameTXT = itemView.findViewById(R.id.fullnameTXT);
            bodyTXT = itemView.findViewById(R.id.bodyTXT);
            likesTXT = itemView.findViewById(R.id.likesTXT);
            dislikesTXT = itemView.findViewById(R.id.dislikesTXT);
            commentTXT = itemView.findViewById(R.id.commentTXT);
            rewardTXT = itemView.findViewById(R.id.rewardTXT);

            likeBtn = itemView.findViewById(R.id.likeBtn);
            dislikeBtn = itemView.findViewById(R.id.dislikeBtn);
            commentBtn = itemView.findViewById(R.id.commentBtn);
            rewardBtn = itemView.findViewById(R.id.rewardBtn);

            avatar_textfield = itemView.findViewById(R.id.avatar_textfield);

            menu_post = itemView.findViewById(R.id.menu_post);

            is_admin_post = itemView.findViewById(R.id.is_admin_post);
        }
    }

    private void addLike(IdPost idPost) {
        //IdPost idPost1 = new IdPost("1");
        Call<Object> call = apiInterface.likePost(token, idPost);

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Toast.makeText(inflater.getContext(), R.string.like_toast, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(inflater.getContext(), R.string.error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addDislike(IdPost idPost) {
        Call<Object> call = apiInterface.dislikePost(token, idPost);

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Toast.makeText(inflater.getContext(), R.string.dislike_toast, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(inflater.getContext(), R.string.error, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void reportPost(int idPost) {
        Call<Object> call = apiInterface.reportPost(token, idPost);
        Log.i("REPORT", "Request  : " + call.request().body());

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.i("REPORT", "onResponse: Success : " + response.body());
                Toast.makeText(inflater.getContext(), R.string.dislike_toast, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.i("REPORT", "onResponse: Success : " + t.getLocalizedMessage());
                Toast.makeText(inflater.getContext(), R.string.error, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
