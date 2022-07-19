package com.example.exprimonsnousapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exprimonsnousapp.R;
import com.example.exprimonsnousapp.models.Post;
import com.example.exprimonsnousapp.models.VoteOption;
import com.example.exprimonsnousapp.retrofit.ApiClient;
import com.example.exprimonsnousapp.retrofit.ApiInterface;

import java.util.List;

public class VoteOptionsListAdapter extends RecyclerView.Adapter<VoteOptionsListAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<VoteOption> choices;
    Context context;

    public VoteOptionsListAdapter( List<VoteOption> choices, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.choices = choices;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.vote_options, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // BIND THE DATA
        holder.choice.setText((choices.get(position)).getLabel());
    }

    @Override
    public int getItemCount() {
        return choices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RadioButton choice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            choice = itemView.findViewById(R.id.choice);
        }
    }
}
