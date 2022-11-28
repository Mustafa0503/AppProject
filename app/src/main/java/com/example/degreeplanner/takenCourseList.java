package com.example.degreeplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class takenCourseList extends AppCompatActivity {
    ListView listView;
    String userID;
    FirebaseAuth fAuth= FirebaseAuth.getInstance();
    FirebaseFirestore fstore ;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taken_course_list);
        userID = fAuth.getCurrentUser().getUid();
        listView = (ListView) findViewById(R.id.listview);
        TextView listview = (TextView)findViewById(R.id.textView16);
        listview.setPaintFlags(listview.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        ArrayList<String> arrayList = new ArrayList<>();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        FirebaseFirestore.getInstance().collection("users")
                .document(userID).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        DocumentSnapshot document = task.getResult();
                        List<String> old = (List<String>) document.get("courses");
                        if (!old.isEmpty())
                        {
                            for (int o = 0; o < old.size(); o++) {
                                if (!arrayList.contains(old.get(o))) {
                                    arrayList.add(old.get(o));
                                }
                            }
                            listView.setAdapter(arrayAdapter);
                        }
                    }
                });


//
//        arrayList.add("CSCA08");
//        arrayList.add("CSCA48");
//        arrayList.add("CSCB07");
//        arrayList.add("CSCB09");
//        arrayList.add("CSCB24");
//        arrayList.add("CSCB25");
//        arrayList.add("CSCB26");
//        arrayList.add("CSCB27");
//        arrayList.add("CSCB28");
//        arrayList.add("CSCB29");
//        arrayList.add("CSCB30");

        //listView.setAdapter(arrayAdapter);

    }
    public void AddNewCourses(View view) {
        Intent intent = new Intent(this, select.class);
        startActivity(intent);
        finish();
    }
    public void Return(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}