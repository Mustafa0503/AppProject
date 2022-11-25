package com.example.degreeplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void openProfile(View view){
        Intent intent = new Intent(this, ProfilePage.class);
        startActivity(intent);
        finish();
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

    public void test(View view) {
        Intent intent = new Intent(this, CourseLineBuilder.class);
        startActivity(intent);
        finish();
    }


//    @Override
//    public void onClick(View view) {
//        openprofilepage();
//
//    }
}