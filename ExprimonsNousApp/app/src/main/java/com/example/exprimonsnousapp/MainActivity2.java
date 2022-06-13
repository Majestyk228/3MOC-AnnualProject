package com.example.exprimonsnousapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity2 extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bottomNavigationView = findViewById(R.id.activity_main_bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()){
                    case R.id.action_posts:
                        selectedFragment = new PostFragment();
                        break;
                    case R.id.action_votes:
                        selectedFragment = new VoteFragment();
                        break;
                    case R.id.action_profile:
                        selectedFragment = new ProfileFragment();
                        break;
                    /*case R.id.nav_notification:
                        selectedFragment = new NotificationFragment();
                        break;*/

                }

                assert selectedFragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout,selectedFragment).commit();

                return true;

            }
        });
    }
}