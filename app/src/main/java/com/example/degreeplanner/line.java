package com.example.degreeplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class line extends AppCompatActivity {

    public void timeLine(View view){
        Intent b1 = new Intent(this, CourseLineBuilder.class);
        startActivity(b1);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
    }
}