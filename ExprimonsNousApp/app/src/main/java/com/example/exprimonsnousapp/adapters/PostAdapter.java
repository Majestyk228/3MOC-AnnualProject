package com.example.exprimonsnousapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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


    // ADAPTER'S CONSTRUTOR
    public PostAdapter(Context context, List<Post> posts, int communityId, int idUser) {
        this.inflater = LayoutInflater.from(context);
        this.posts = posts;
        this.context = context;
        this.communityId = communityId;
        this.idUser = idUser;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_post, parent, false);
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

        holder.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // APPEL DE LA ROUTE POUR AJOUTER UN LIKE AU POST AVEC L'idPost
                IdPost idPost = new IdPost(posts.get(holder.getAbsoluteAdapterPosition()).getIdPost());
                addLike(idPost);

                // Fake refresh of the number of likes
                int nblikes = Integer.parseInt((String)holder.likesTXT.getText())+1;
                holder.likesTXT.setText(nblikes+"");
            }
        });

        holder.dislikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // APPEL DE LA ROUTE POUR AJOUTER UN DISLIKE AU POST AVEC L'idPost
                IdPost idPost = new IdPost(posts.get(holder.getAbsoluteAdapterPosition()).getIdPost());
                addDislike(idPost);

                // Fake refresh of the number of dislikes
                int nblikes = Integer.parseInt((String)holder.dislikesTXT.getText())+1;
                holder.dislikesTXT.setText(nblikes+"");
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

                FragmentTransaction ft = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.activity_main_frame_layout, mFragment);
                ft.addToBackStack("BottomSheetFrag");
                ft.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView fullnameTXT, bodyTXT, likesTXT, dislikesTXT, commentTXT, rewardTXT;
        Button likeBtn, dislikeBtn, commentBtn, rewardBtn;

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

        }
    }

    private void addLike(IdPost idPost) {
        //IdPost idPost1 = new IdPost("1");
        Call<Object> call = apiInterface.likePost(idPost);

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
        Call<Object> call = apiInterface.dislikePost(idPost);

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
}
