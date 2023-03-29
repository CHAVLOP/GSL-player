package com.example.gslplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.ImageFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Favoritos extends AppCompatActivity {
    ImageView volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        volver = findViewById(R.id.atras);


        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Favoritos.this, Menus.class);
                startActivity(intent);
                finish();
            }
        });
    }
}