package com.example.degreeplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void logoutAdmin(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
    public void test(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Error404.class));
        finish();
    }
    public void openAsk(View view) {
        Intent intent = new Intent(this, ProfilePage.class);
        startActivity(intent);
        finish();
    }
    public void error(View view) {
        Intent intent = new Intent(this, Error404.class);
        startActivity(intent);
        finish();
    }

    public void openAd(View view) {
        Intent intent = new Intent(this, adminCourse.class);
        startActivity(intent);
        finish();
    }

    public void addCourse(View view) {
        Intent intent = new Intent(this, Courses.class);
        startActivity(intent);
        finish();
    }

    public void Return(View view) {
        Intent intent = new Intent(this, Courses.class);
        startActivity(intent);
        finish();
    }
}