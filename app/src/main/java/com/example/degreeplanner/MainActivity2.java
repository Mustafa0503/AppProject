package com.example.degreeplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {
    Presenter pres;

    ImageView im, im2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        im=(ImageView) findViewById(R.id.imageButton2);
        im.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), editAdmin.class));
                finish();
            }
        });

        im2 = (ImageView) findViewById(R.id.imageButton3);
        im2.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), selectDeleteAdmin.class));
                finish();
            }
        });

    }

    public void logoutAdmin(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), View2.class));
        finish();
    }
    public void test1(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), editAdmin.class));
        finish();
    }
    public void openAsk(View view) {
        Intent intent = new Intent(this, ProfilePage.class);
        startActivity(intent);
        finish();
    }
//    public void error(View view) {
//        Intent intent = new Intent(this, Error404.class);
//        startActivity(intent);
//        finish();
//    }

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