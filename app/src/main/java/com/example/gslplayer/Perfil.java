package com.example.gslplayer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Perfil extends AppCompatActivity {
    EditText etUser,etcorreo;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;


    Button btnsalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        etUser = findViewById(R.id.etUser);
        etcorreo = findViewById(R.id.etcorreo);
        btnsalir = findViewById(R.id.btnsalir);
        firestore  = FirebaseFirestore.getInstance();
        CollectionReference usuarios = firestore.collection("users");
        DocumentReference docRef = usuarios.document("4JfWudisgMOoezkwyNqp");
        firebaseAuth = FirebaseAuth.getInstance();

        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Perfil.this,MainActivity.class));
                finish();

            }
        });



        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    // Maneja errores aquí
                    return;
                }

                if (documentSnapshot != null && documentSnapshot.exists()) {

                    String nombre = documentSnapshot.getString("name");
                    String correo = documentSnapshot.getString("email");
                    etUser.setText(nombre);
                    etcorreo.setText(correo);
                }
            }
        });








    }

}