package com.example.degreeplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CSCA48 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csca48);
    }

    public void test(View view) {
        Intent intent = new Intent(this, CourseLineBuilder.class);
        startActivity(intent);
        finish();
    }




}