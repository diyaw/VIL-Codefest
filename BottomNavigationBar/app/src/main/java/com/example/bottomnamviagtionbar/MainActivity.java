package com.example.bottomnamviagtionbar;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();

        BottomNavigationView navigation = findViewById(R.id.navigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Uno");
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
                    toolbar.setTitle("Icon 2");
                    loadFragment(new DosFragment());
                    return true;
                case R.id.frag3:
                    toolbar.setTitle("Icon 3");
                    loadFragment(new TresFragment());
                    return true;
                case R.id.frag4:
                    toolbar.setTitle("Icon 4");
                    loadFragment(new CautroFragment());
                    return true;
                case R.id.account:
                    toolbar.setTitle("Account");
                    loadFragment(new AccountFragment());
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
