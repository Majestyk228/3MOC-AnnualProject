package com.example.exprimonsnousapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.exprimonsnousapp.models.CommunityInsert;
import com.example.exprimonsnousapp.retrofit.ApiClient;
import com.example.exprimonsnousapp.retrofit.ApiInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Toolbar myToolbar;
    private int userId = -1;
    private int communityId = -1;

    // API
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Bundle extras = getIntent().getExtras();
        userId = extras.getInt("userId");
        communityId = extras.getInt("communityId");

        CommunityInsert communityInsert = new CommunityInsert(userId,communityId);
        addUserInCommunity(communityInsert);

        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitle("Les posts");


        bottomNavigationView = findViewById(R.id.activity_main_bottom_navigation);


        bottomNavigationView.setOnItemSelectedListener(item -> {
            Bundle bundle = new Bundle();
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.action_posts:
                    selectedFragment = new PostFragment(communityId, userId);
                    bundle = new Bundle();
                    bundle.putInt("idUser", userId);
                    bundle.putInt("idCommunity", communityId);
                    selectedFragment.setArguments(bundle);

                    myToolbar.setTitle("Les posts");
                    break;
                case R.id.action_votes:
                    selectedFragment = new VoteFragment();
                    bundle = new Bundle();
                    bundle.putInt("idUser", userId);
                    bundle.putInt("idCommunity", communityId);
                    selectedFragment.setArguments(bundle);
                    myToolbar.setTitle("Les votes");
                    break;
                case R.id.action_profile:
                    selectedFragment = new ProfileFragment();
                    bundle = new Bundle();
                    bundle.putInt("idUser", userId);
                    selectedFragment.setArguments(bundle);
                    myToolbar.setTitle("Mon profil");
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout,selectedFragment).commit();

            return true;
        });

        bottomNavigationView.setSelectedItemId(R.id.action_posts);

        myToolbar.setTitle("Les posts");

    }

    private void addUserInCommunity(CommunityInsert communityInsert) {
        Call<Object> call = apiInterface.addUserInCommunity(communityInsert);
        Log.i("GETIDCOMMUNITY", "addUserInCommunity request: "+ call.request().toString());
        call.enqueue(new Callback<Object>(){
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    Log.i("GETIDCOMMUNITY", "onResponse Adding user in community: "+response.body());
                    //userCreds = response.body();
                } else {
                    Log.i("GETIDCOMMUNITY", "onResponse Adding user in community: "+response.body());
                    //userCreds = new UserCreds(-1, "",-1);
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.i("GETIDCOMMUNITY", "onFailure Adding user in community: "+t.getLocalizedMessage());
            }
        });
    }
}