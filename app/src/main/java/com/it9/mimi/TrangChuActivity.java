package com.it9.mimi;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.it9.mimi.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TrangChuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);

        getSupportActionBar().hide();

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.action_home){
                    LoadFragment(new HomeFragment());
                    return true;
                }
                else if(item.getItemId() == R.id.action_notice){
                    LoadFragment(new MessageFragment());
                    return true;
                }
                else if(item.getItemId() == R.id.action_profile){
                    LoadFragment(new ProfileFragment());
                    return true;
                }

                return false;
            }

            public void LoadFragment(Fragment f){
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentContainerView, f);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        if (savedInstanceState == null) {
            navigationView.setSelectedItemId(R.id.action_home);
        }
    }
}