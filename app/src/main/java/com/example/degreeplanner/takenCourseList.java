package com.example.degreeplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class takenCourseList extends AppCompatActivity {
    ListView listView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taken_course_list);

        listView = (ListView) findViewById(R.id.listview);
        TextView listview = (TextView)findViewById(R.id.textView16);
        listview.setPaintFlags(listview.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("CSCA08");
        arrayList.add("CSCA48");
        arrayList.add("CSCB07");
        arrayList.add("CSCB09");
        arrayList.add("CSCB24");
        arrayList.add("CSCB63");
        arrayList.add("CSCB36");
        arrayList.add("CSCA67");
        arrayList.add("CSCA08");
        arrayList.add("CSCA48");
        arrayList.add("CSCB07");
        arrayList.add("CSCB09");
        arrayList.add("CSCB24");
        arrayList.add("CSCB63");
        arrayList.add("CSCB36");
        arrayList.add("CSCA67");
        arrayList.add("CSCA48");
        arrayList.add("CSCB07");
        arrayList.add("CSCB09");
        arrayList.add("CSCB24");
        arrayList.add("CSCB63");
        arrayList.add("CSCB36");
        arrayList.add("CSCA67");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

    }
    public void AddNewCourses(View view) {
        Intent intent = new Intent(this, select.class);
        startActivity(intent);
        finish();
    }
}