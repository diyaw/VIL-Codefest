package com.example.otplogin;

import android.os.Bundle;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();

        BottomNavigationView navigation = findViewById(R.id.navigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(new UnoFragment());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    toolbar.setTitle("Home");
                    loadFragment(new UnoFragment());
                    return true;
                case R.id.frag2:
                    toolbar.setTitle("Recharge");
                    loadFragment(new DosFragment());
                    return true;
                case R.id.frag3:
                    toolbar.setTitle("Earn VodaCoins");
                    loadFragment(new TresFragment());
                    return true;
                case R.id.frag4:
                    toolbar.setTitle("Explore");
                    loadFragment(new CautroFragment());
                    return true;
                case R.id.account:
                    toolbar.setTitle("Profile");
                    loadFragment(new AccountFragment());
                    return true;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
