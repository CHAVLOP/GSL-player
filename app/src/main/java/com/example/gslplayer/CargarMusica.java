package com.example.gslplayer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.airbnb.lottie.LottieAnimationView;

public class CargarMusica extends AppCompatActivity {
    LottieAnimationView subir;
    EditText etArchivo;
    ProgressDialog progressDialog;
    private static final int MUSIC_INTENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_musica);
        subir = findViewById(R.id.subir);
        etArchivo = findViewById(R.id.etArchivo);
        progressDialog = new ProgressDialog(this);



        etArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("audio/*");
                startActivityForResult(intent,MUSIC_INTENT);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MUSIC_INTENT && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            progressDialog.setTitle("Subiendo cancion");
            progressDialog.setMessage("subiendo cancion por favor espere");
            progressDialog.setCancelable(false);
            progressDialog.show();


        }
    }
}