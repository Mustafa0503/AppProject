package com.example.degreeplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Error404 extends AppCompatActivity {
//gg
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error404);
    }
    public void mentalHealth(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.utsc.utoronto.ca/home/mental-health-resources"));
        startActivity(intent);
        finish();
    }

    public void error(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
        finish();
    }
}