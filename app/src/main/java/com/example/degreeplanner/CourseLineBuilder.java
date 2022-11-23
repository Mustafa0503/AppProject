package com.example.degreeplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class CourseLineBuilder extends AppCompatActivity {
    String[] courses = {"CSCA08", "CSCA48", "CSCA67", "CSCB07", "CSCB09", "CSCB36", "CSCB63",
            "CSCC24", "CSCC63"};

    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_line_builder);

        autoCompleteTxt = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_course,courses);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String course = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Course: " + course,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}