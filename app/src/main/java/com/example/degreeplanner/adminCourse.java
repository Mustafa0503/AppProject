package com.example.degreeplanner;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;

public class adminCourse extends AppCompatActivity {

    EditText course;
    TextView pre;
    boolean[] selected;
    ArrayList<Integer> courseList = new ArrayList<>();
    String[] courseArray = {"CSCA08", "CSCA48", "CSCA67", "CSCB07", "CSCB09"};
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    Button createCourse;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_course);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        pre = findViewById(R.id.pree);

        selected = new boolean[courseArray.length];

        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(adminCourse.this);
                builder.setTitle("Select Prerequisites");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(courseArray, selected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b){
                            //when checkbox selected
                            //add position in course list
                            courseList.add(i);
                            //sort course list
                            Collections.sort(courseList);
                        } else {
                            courseList.remove(i);
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();

                        for(int j=0; j<courseList.size(); j++){
                            stringBuilder.append(courseArray[courseList.get(j)]);

                            if(j!=courseList.size()-1){
                                stringBuilder.append(", ");

                            }
                        }
                        pre.setText(stringBuilder.toString());

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for(int j=0; j<selected.length; j++){
                            selected[j] = false;
                            courseList.clear();
                            pre.setText("");
                        }
                    }
                });

                builder.show();
            }
        });

        createCourse.setOnClickListener(view -> {
            ArrayList courses = new ArrayList<String>();
            DocumentReference documentReference = fstore.collection("courses").document(userID);



        });




    }
}