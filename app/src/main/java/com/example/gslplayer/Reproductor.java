package com.example.gslplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Reproductor extends AppCompatActivity {
    ImageView volver;
    ImageView pause, repet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);
        volver = findViewById(R.id.volver);
        pause = findViewById(R.id.pause);
        repet = findViewById(R.id.repet);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Reproductor.this,Menus.class));
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause.setImageResource(R.drawable.ic_play);
            }
        });
        repet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repet.setImageResource(R.drawable.ic_repetir1);
            }
        });
    }
}