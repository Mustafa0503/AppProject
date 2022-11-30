package com.example.degreeplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    public void csca48() {
        Intent intent = new Intent(this, CSCA48.class);
        startActivity(intent);
        finish();
    }

    public void generateTime(View view){
        Intent b1 = new Intent(this, line.class);
        startActivity(b1);
        finish();
    }
//gg
    public void mainAct(View view){
        Intent b1 = new Intent(this, MainActivity.class);
        startActivity(b1);
        finish();
    }

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

                if(course.equals("CSCA48")){
                    csca48();
                }
            }

        });


    }
}