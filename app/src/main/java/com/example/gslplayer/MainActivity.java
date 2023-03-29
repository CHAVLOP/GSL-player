package com.example.gslplayer;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;

public class MainActivity extends AppCompatActivity {
    TextView txtregistrar;
    Button btnentrar;

    ImageView contra;
    ImageView Agoogle;
    GoogleSignInClient googleSignInClient;
    private final static int RC_SIGN_IN = 100;


    EditText eUser, ePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        txtregistrar = findViewById(R.id.txtregistrar);
        btnentrar = findViewById(R.id.btnentrar);
        ePassword = findViewById(R.id.etContra);
        eUser = findViewById(R.id.etUsuario);
        contra = findViewById(R.id.contras);
        Agoogle = findViewById(R.id.google);



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        Agoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    signIn();
            }
        });
        contra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ePassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    ePassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    contra.setImageResource(R.drawable.ojo);
                    ePassword.setSelection(ePassword.length());
                }
                else{
                    ePassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    contra.setImageResource(R.drawable.oculta);
                    ePassword.setSelection(ePassword.length());
                }


            }
        });

        btnentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();

            }
        });

        txtregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Registro.class));
                finish();
            }
        });

    }
    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();
            }
            startActivity(new Intent(MainActivity.this, Menus.class));

        } catch (ApiException e) {

            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());

        }
    }


    public void login() {
        if (eUser.getText().toString().length() > 0 && ePassword.getText().toString().length() > 0) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("users")
                    .whereEqualTo("email", eUser.getText().toString())
                    .whereEqualTo("password", ePassword.getText().toString())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                if (task.getResult().size() > 0) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                        System.out.println(document.getId() + " => " + document.getData());
                                        startActivity(new Intent(MainActivity.this, Menus.class));
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this, "Contraseña y/o correo no coinciden", Toast.LENGTH_LONG).show();
                                }

                            } else {
                                Toast.makeText(MainActivity.this, "Contraseña y/o correo no coinciden", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(this, "Todos los campos son requeridos", Toast.LENGTH_LONG).show();
        }
    }



}