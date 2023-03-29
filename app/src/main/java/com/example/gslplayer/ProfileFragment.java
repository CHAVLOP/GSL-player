package com.example.gslplayer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment {
     DatabaseReference mDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ImageView imfavoritas = (ImageView) view.findViewById(R.id.imFavoritos);
        LottieAnimationView lottieAnimationView = (LottieAnimationView) view.findViewById(R.id.uploat);
        TextView user = (TextView) view.findViewById(R.id.tvUser);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String nombre = snapshot.child("name").getValue().toString();
                    user.setText(" "+nombre);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        lottieAnimationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CargarMusica.class);
                startActivity(intent);

            }
        });
        imfavoritas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Favoritos.class);
                startActivity(intent);
            }
        });
        return view;
    }



}
