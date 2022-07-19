package com.example.exprimonsnousapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exprimonsnousapp.BottomSheetFrag;
import com.example.exprimonsnousapp.MainActivity2;
import com.example.exprimonsnousapp.R;
import com.example.exprimonsnousapp.VoteParticipationFragment;
import com.example.exprimonsnousapp.models.Post;
import com.example.exprimonsnousapp.models.Vote;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<Vote> votes;
    Context context;
    Toolbar myToolbar;

    // ADAPTER'S CONSTRUCTOR
    public VoteAdapter(Context context, List<Vote> votes) {
        this.inflater = LayoutInflater.from(context);
        this.votes = votes;
        this.context = context;
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

                Fragment mFragment = new VoteParticipationFragment();

                Bundle bundle = new Bundle();
                bundle.putInt("idVote", votes.get(holder.getAbsoluteAdapterPosition()).getIdVote());
                mFragment.setArguments(bundle);

                FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.activity_main_frame_layout, mFragment);
                ft.addToBackStack("VoteParticipation");
                ft.commit();

                // RECUPERATION DE LA TOOLBAR DU PARENT, CHANGEMENT DU TITRE ET AJOUT DU BOUTON RETOUR
                myToolbar = ((FragmentActivity)context).findViewById(R.id.my_toolbar);
                myToolbar.setTitle("Participation");
                ((MainActivity2) context).setSupportActionBar(myToolbar);
                ((MainActivity2) context).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                ((MainActivity2) context).getSupportActionBar().setDisplayShowHomeEnabled(true);

                // MISE EN ÉCOUTE DU BOUTON RETOUR
                myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // RETRAIT DU FRAGMENT CreatePostFragment
                        FragmentManager fm = ((FragmentActivity)context).getSupportFragmentManager();
                        fm.popBackStack("VoteParticipation", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                        // DESABLING RETURN BUTTON OF TOOLBAR AND CHANGING TITLE
                        ((MainActivity2) context).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        ((MainActivity2) context).getSupportActionBar().setDisplayShowHomeEnabled(false);
                        myToolbar.setTitle("Les votes");
                    }
                });

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
