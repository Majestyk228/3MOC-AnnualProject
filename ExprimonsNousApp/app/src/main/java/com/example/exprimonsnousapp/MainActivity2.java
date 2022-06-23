package com.example.exprimonsnousapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity2 extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Toolbar myToolbar;
    private int userId = -1;
    private int communityId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle extras = getIntent().getExtras();
        userId = extras.getInt("userId");
        communityId = extras.getInt("communityId");

        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitle("Les posts");


        bottomNavigationView = findViewById(R.id.activity_main_bottom_navigation);


        bottomNavigationView.setOnItemSelectedListener(item -> {
            Bundle bundle = new Bundle();
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.action_posts:
                    selectedFragment = new PostFragment(communityId);
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

    }
}