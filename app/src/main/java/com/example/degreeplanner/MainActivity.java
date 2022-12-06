package com.example.degreeplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    //ImageButton button = findViewById(R.id.imageButton2);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase.getInstance().getReference().child("COURSES").child("CSCA48");
    }

    public void openProfile(View view) {
        Intent intent = new Intent(this, pfp.class);
        startActivity(intent);
        finish();
    }
    public void openAsk(View view) {
        Intent intent = new Intent(this, ProfilePage.class);
        startActivity(intent);
        finish();
    }

    public void openTakenCourses(View view) {
        Intent intent = new Intent(this, takenCourseList.class);
        startActivity(intent);
        finish();
    }
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Presenter.num=0;
        startActivity(new Intent(getApplicationContext(), View2.class));
        finish();
    }

    public void testLowkey(View view) {
        Intent intent = new Intent(this, CourseLineBuilder.class);
        startActivity(intent);
        finish();
    }

    public void mainAct(View view){
        Intent b1 = new Intent(this, MainActivity.class);
        startActivity(b1);
        finish();
    }



//    public void dashboard(View view){
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        finish();
//    }



}