package com.example.exprimonsnousapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

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

    // SHARED PREFERENCES
    /*SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USER = "idUser";
    private static final String KEY_COMMUNITY = "idCommunity";*/

    // OTHER
    ImageView get_help_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Bundle extras = getIntent().getExtras();
        userId = extras.getInt("userId");
        communityId = extras.getInt("communityId");

        /*sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER,userId);
        editor.putInt(KEY_COMMUNITY,communityId);*/

        CommunityInsert communityInsert = new CommunityInsert(userId,communityId);
        addUserInCommunity(communityInsert);

        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitle("Les posts");

        get_help_logo = findViewById(R.id.get_help_logo);

        get_help_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // APPEL DE LA ROUTE POUR AJOUTER UN REWARD AU POST AVEC L'idPost
                Fragment mFragment = new GetHelpFragment();

                Bundle bundle = new Bundle();
                bundle.putInt("idCommunity", communityId);
                bundle.putInt("idUser", userId);
                mFragment.setArguments(bundle);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.activity_main_frame_layout, mFragment);
                ft.addToBackStack("GetHelpFragment");
                ft.commit();
            }
        });

        /*getSupportActionBar().setLogo(R.drawable.logoexprimonsnous2);
        getSupportActionBar().setDisplayUseLogoEnabled(true);*/


        bottomNavigationView = findViewById(R.id.activity_main_bottom_navigation);


        bottomNavigationView.setOnItemSelectedListener(item -> {
            Bundle bundle;
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.action_posts:
                    selectedFragment = new PostFragment(communityId, userId);
                    bundle = new Bundle();
                    bundle.putInt("idUser", userId);
                    bundle.putInt("idCommunity", communityId);
                    selectedFragment.setArguments(bundle);

                    myToolbar.setTitle(R.string.posts_title);
                    break;
                case R.id.action_votes:
                    selectedFragment = new VoteFragment();
                    bundle = new Bundle();
                    bundle.putInt("idUser", userId);
                    bundle.putInt("idCommunity", communityId);
                    selectedFragment.setArguments(bundle);
                    myToolbar.setTitle(R.string.vote_title);
                    break;
                case R.id.action_profile:
                    selectedFragment = new ProfileFragment();
                    bundle = new Bundle();
                    bundle.putInt("idUser", userId);
                    selectedFragment.setArguments(bundle);
                    myToolbar.setTitle(R.string.profile_title);
                    break;
                default:
                    myToolbar.setTitle(R.string.posts_title);
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