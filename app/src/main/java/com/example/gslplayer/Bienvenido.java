package com.example.gslplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class Bienvenido extends AppCompatActivity {
    Button btniniciar,btnregistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
        btniniciar= findViewById(R.id.btniniciar);
        btnregistrar = findViewById(R.id.btnregistrar);




        btniniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Bienvenido.this, MainActivity.class));
            }
        });
        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Bienvenido.this, Registro.class));

            }
        });



    }
    @Override
    public void onBackPressed() {
        // No hacer nada
    }
}