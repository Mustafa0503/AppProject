package com.example.degreeplanner;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
//import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseLineBuilder extends AppCompatActivity {
//    String[] courses = {"CSCA08", "CSCA48", "CSCA67", "CSCB07", "CSCB09", "CSCB36", "CSCB63",
//            "CSCC24", "CSCC63"};

    ArrayList<courseObj> allC = new ArrayList<>();
    String[] courses;
    boolean val = false;
    String course;
    ArrayList<String> off = new ArrayList<>();
    ArrayList<String> pre = new ArrayList<>();
    ArrayList<String> selectedCourses = new ArrayList<>();

    private FirebaseFirestore mDb = FirebaseFirestore.getInstance();
    FirebaseAuth fAuth = FirebaseAuth.getInstance();;
    String userid;
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;

    public ArrayList<String> getSelectedCourses() {
        return selectedCourses;
    }

    public void csca48() {
        Intent intent = new Intent(this, CSCA48.class);
        startActivity(intent);
        finish();
    }

    public void generateTime(){
        Intent b1 = new Intent(this, line.class);
        startActivity(b1);
        finish();
    }

    public void recurse(){
        Intent b1 = new Intent(this, CourseLineBuilder.class);
        startActivity(b1);
        finish();
    }

    public void mainAct(View view){
        Intent b1 = new Intent(this, MainActivity.class);
        startActivity(b1);
        finish();
    }

    public void makeDrop(){
        CheckBox check = findViewById(R.id.box);
        Button b = findViewById(R.id.button7);
        autoCompleteTxt = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_course,courses);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                course = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Course: " + course,
                        Toast.LENGTH_SHORT).show();
                val=true;
            }
        });

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(val && !selectedCourses.contains(course) && !course.equals("")){
                    check.setButtonTintList(ColorStateList.valueOf(Color.rgb(15,254,174)));
                    selectedCourses.add(course);
                    val=false;
                }
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selectedCourses.isEmpty()) {
                    Map<String, Object> userList = new HashMap<>();
                    userList.put("Wanted", selectedCourses);
                    userid = fAuth.getCurrentUser().getUid();
                    mDb.collection("users").document(userid).update(userList).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(CourseLineBuilder.this, "Successful", Toast.LENGTH_SHORT).show();
                            generateTime();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@androidx.annotation.NonNull Exception e) {
                            Toast.makeText(CourseLineBuilder.this, "Invalid Input", Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {
                    Toast.makeText(CourseLineBuilder.this, "Please Select A Course", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_line_builder);

        mDb.collection("course").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {

                    for (int i=0; i<task.getResult().getDocuments().size();i++) {
                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(i);
                        String crs= documentSnapshot.getString("Course Code");
                        off = (ArrayList<String>) documentSnapshot.get("Session Offered");
                        pre = (ArrayList<String>) documentSnapshot.get("Prerequisites");
                        allC.add(new courseObj(crs, off, pre));
                    }

                    courses = new String[allC.size()];
                    for(int i=0; i<allC.size(); i++){
                        courses[i] = allC.get(i).getCode();
                    }

                    makeDrop();


                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }
}