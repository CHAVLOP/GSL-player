package com.example.gslplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class PantallCarga extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        SharedPreferences preferences = getSharedPreferences("sesion", Context.MODE_PRIVATE);


        int Tiempo= 5000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(PantallCarga.this,Bienvenido.class));

            }
        },Tiempo);
    }

}
