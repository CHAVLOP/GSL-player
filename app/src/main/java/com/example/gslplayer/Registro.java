package com.example.gslplayer;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    TextView txtregistrar;

    ImageView ver;

    EditText eEmail, eName, ePassoword;
    Button login;

    Boolean emailNotRegister = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        txtregistrar = findViewById(R.id.txtregistrar);
        eEmail = findViewById(R.id.etEmail);
        eName = findViewById(R.id.etUsuario);
        ePassoword = findViewById(R.id.etContra);
        login = findViewById(R.id.btnentrar);
        ver = findViewById(R.id.ver);

        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ePassoword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    ePassoword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ver.setImageResource(R.drawable.ojo);
                    ePassoword.setSelection(ePassoword.length());
                }
                else{
                    ePassoword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ver.setImageResource(R.drawable.oculta);
                    ePassoword.setSelection(ePassoword.length());
                }


            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        txtregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registro.this,MainActivity.class));

            }
        });
    }
    public void registerUser(){
        if (eName.getText().toString().length() > 0 && eEmail.getText().toString().length() > 0 && ePassoword.getText().toString().length() > 0){
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("users")
                    .whereEqualTo("email", eEmail.getText().toString())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                if (task.getResult().size() > 0){
                                    Toast.makeText(Registro.this, "Email ya registrado", Toast.LENGTH_SHORT).show();
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        System.out.println(document.getId() + " => " + document.getData());
                                    }
                                } else {
                                    insertData();
                                }

                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });
        } else {
            Toast.makeText(this, "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
        }

    }
    public void insertData(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();

        user.put("name", eName.getText().toString());
        user.put("email", eEmail.getText().toString());
        user.put("password", ePassoword.getText().toString());
        user.put("avatar", null);
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        startActivity(new Intent(Registro.this, MainActivity.class));
                        eEmail.setText("");
                        eName.setText("");
                        ePassoword.setText("");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

}