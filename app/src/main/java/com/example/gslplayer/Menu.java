package com.example.gslplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Menu extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    FavoritesFragment favoritesFragment = new FavoritesFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        bottomNavigationView = findViewById(R.id.navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menu_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;
                    case R.id.salir:
                        Intent login = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(login);
                        finish();

                        return true;
                    case R.id.perfil:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}