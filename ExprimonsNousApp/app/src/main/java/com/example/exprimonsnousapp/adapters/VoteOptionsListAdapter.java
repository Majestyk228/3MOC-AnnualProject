package com.example.exprimonsnousapp.adapters;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.motion.utils.StopLogic;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exprimonsnousapp.MainActivity2;
import com.example.exprimonsnousapp.R;
import com.example.exprimonsnousapp.models.Post;
import com.example.exprimonsnousapp.models.UserCreds;
import com.example.exprimonsnousapp.models.UserLoginCreds;
import com.example.exprimonsnousapp.models.VoteOption;
import com.example.exprimonsnousapp.retrofit.ApiClient;
import com.example.exprimonsnousapp.retrofit.ApiInterface;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoteOptionsListAdapter extends RecyclerView.Adapter<VoteOptionsListAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<VoteOption> choices;
    Context context;
    private int lastCheckedPosition = -1;
    Toolbar myToolbar;
    VoteOption selectedChoice;

    MaterialButton submitVoteBtn, cancelVoteBtn;

    // API CALL
    ApiInterface apiInterface;

    public VoteOptionsListAdapter(List<VoteOption> choices, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.choices = choices;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.vote_options, parent, false);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        selectedChoice = new VoteOption();

        //submitVoteBtn = (MaterialButton) view.findViewById(R.id.submitVoteBtn);
        //cancelVoteBtn = (MaterialButton) view.findViewById(R.id.cancelVoteBtn);

        // LISTENING TO BUTTONS
        /*submitVoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // API CALL TO SUBMIT VOTE
                Toast.makeText(view.getContext(), "Votre vote a été pris en compte.", Toast.LENGTH_LONG).show();
            }
        });*/

        /*cancelVoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // POP FRAGMENT OFF
                // RETRAIT DU FRAGMENT CreatePostFragment

                myToolbar = ((FragmentActivity)context).findViewById(R.id.my_toolbar);


                FragmentManager fm = ((FragmentActivity)context).getSupportFragmentManager();
                fm.popBackStack("VoteParticipation", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                // DESABLING RETURN BUTTON OF TOOLBAR AND CHANGING TITLE
                ((MainActivity2) context).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                ((MainActivity2) context).getSupportActionBar().setDisplayShowHomeEnabled(false);
                myToolbar.setTitle("Les votes");
            }
        });*/
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // BIND THE DATA
        holder.choice.setText((choices.get(position)).getLabel());
        holder.choice.setChecked(position == lastCheckedPosition);
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

            choice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int copyOfLastCheckedPosition = lastCheckedPosition;
                    lastCheckedPosition = getAdapterPosition();
                    notifyItemChanged(copyOfLastCheckedPosition);
                    notifyItemChanged(lastCheckedPosition);

                    selectedChoice = choices.get(getAdapterPosition());
                    Log.i("VOTEOPTIONADAPTER", "idVoteOption selectionné : " + selectedChoice);
                }
            });
        }
    }

    public static void sendSelectedChoice() {
        Call<Object> call = apiInterface.sendUserVote(selectedChoice);
        call.enqueue(new Callback<UserCreds>() {

            @Override
            public void onResponse(Call<UserCreds> call, Response<UserCreds> response) {
                if (response.isSuccessful()) {
                    userCreds = response.body();
                } else {
                    userCreds = new UserCreds(-1, "", -1);
                }
            }

            @Override
            public void onFailure(Call<UserCreds> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
            }
        });
    }
    //return selectedChoice();

}
