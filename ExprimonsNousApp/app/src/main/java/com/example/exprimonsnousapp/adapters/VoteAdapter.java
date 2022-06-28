package com.example.exprimonsnousapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exprimonsnousapp.R;
import com.example.exprimonsnousapp.models.Post;
import com.example.exprimonsnousapp.models.Vote;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<Vote> votes;

    // CONSTRUCTEUR DE L'ADAPTEUR


    public VoteAdapter(Context context, List<Vote> votes) {
        this.inflater = LayoutInflater.from(context);
        this.votes = votes;

        Log.i("VOTEADAPTER", votes.toString());
    }


    @NonNull
    @Override
    public VoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_vote, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoteAdapter.ViewHolder holder, int position) {

        // BINDING DATA
        holder.titleVote.setText(votes.get(holder.getAdapterPosition()).getTitle());
        holder.bodyVote.setText(votes.get(holder.getAdapterPosition()).getBody());

        holder.participationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // APPEL DE LA ROUTE POUR AVOIR LES DETAILS D'UN VOTE
                // OUVERTURE D'UNE MODAL POUR LES INFOS DU VOTE ET LES OPTIONS
            }
        });
    }

    @Override
    public int getItemCount() {
        return votes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleVote, bodyVote;
        MaterialButton participationBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleVote = itemView.findViewById(R.id.title_vote);
            bodyVote = itemView.findViewById(R.id.body_vote);

            participationBtn = itemView.findViewById(R.id.participationBtn);
        }
    }
}
