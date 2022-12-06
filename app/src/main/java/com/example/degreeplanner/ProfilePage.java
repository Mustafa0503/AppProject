package com.example.degreeplanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ProfilePage extends AppCompatActivity {
//    TextView fullName, email;
//    FirebaseAuth fAuth;
//    FirebaseFirestore fStore;
//    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
    }
    public void gg(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void mentalHealth(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.utsc.utoronto.ca/home/mental-health-resources"));
        startActivity(intent);
        finish();
    }


    public void openAsk(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

//    public void openProfile(View view){
//        Intent intent = new Intent(this, ProfilePage.class);
//        startActivity(intent);
//        finish();
//    }

    }
