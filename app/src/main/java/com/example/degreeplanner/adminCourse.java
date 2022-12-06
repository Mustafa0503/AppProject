package com.example.degreeplanner;

import static android.content.ContentValues.TAG;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class adminCourse extends AppCompatActivity implements MultiSpinnerListener{

    EditText course, code, session;
    Spinner spL, spR;
    Button but;
    FirebaseFirestore db;
    TextView pre;
    List<String> preR;
    String co="", cod="", se="";
    ArrayList<String> existingC;
    boolean s = false;
    boolean[] selected, selSession;
    ArrayList<Integer> courseList = new ArrayList<>();
    ArrayList<String> sesh = new ArrayList<>();
    ArrayList<String> sessionDB = new ArrayList<>();
    //ArrayList<String> year = new ArrayList<>();
    //ArrayAdapter<String> leftAdapter, rightAdapter;
    private FirebaseFirestore mDb = FirebaseFirestore.getInstance();
    private static final String COURSE = "course";


    String[] courseArray;
    //= {"CSCA08", "CSCA48", "CSCA67", "CSCB07", "CSCB09", "None"}

    public void onItemsSelected(boolean[] selected){

    }
    public boolean isInArray(String arr[], String code){
        for(int i=0; i<arr.length; i++){
            if(arr[i].equals(code)){
                return true;
            }
        }
        return false;
    }
    public void doWork(){
        db = FirebaseFirestore.getInstance();
        pre = findViewById(R.id.preReqs);

        selected = new boolean[courseArray.length];
        course = (EditText) findViewById(R.id.courseName);
        code = (EditText) findViewById(R.id.courseCode);
        session = findViewById(R.id.offered);
        selSession = MultiSpinner.getSelected();
        but = findViewById(R.id.addC);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                co = course.getText().toString();
                cod = code.getText().toString();
                for(int i=0; i<3; i++){
                    if(selSession[i]==true){
                        sessionDB.add(sesh.get(i));
                    }
                }

                if(!co.equals("") && !cod.equals("") && !existingC.contains(cod) && !sessionDB.isEmpty() && !preR.isEmpty()) {

                    Map<String, Object> newCourse = new HashMap<>();
                    newCourse.put("Course Name", co);
                    newCourse.put("Course Code", cod);
                    newCourse.put("Session Offered", sessionDB);
                    newCourse.put("Prerequisites", preR);
                    db.collection("course").add(newCourse).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(adminCourse.this, "Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), adminCourse.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(adminCourse.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(adminCourse.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                }
            }
        });

        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(adminCourse.this);
                builder.setTitle("Select Prerequisites");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(courseArray, selected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if (b) {
                            //when checkbox selected
                            //add position in course list
                            courseList.add(i);
                            //sort course list
                            Collections.sort(courseList);
                        } else {

                            for (int j = 0; i < courseList.size(); j++) {
                                if (courseList.get(j) == i) {
                                    courseList.remove(j);
                                }
                            }
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();

                        preR = new ArrayList<String>();

                        for (int j = 0; j < courseList.size(); j++) {
                            stringBuilder.append(courseArray[courseList.get(j)]);
                            preR.add(courseArray[courseList.get(j)]);
                            if (j != courseList.size() - 1) {
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
                        for (int j = 0; j < selected.length; j++) {
                            selected[j] = false;
                            courseList.clear();
                            pre.setText("");
                        }
                    }
                });

                builder.show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_course);

//        spL = findViewById(R.id.spinL);
//        spR = findViewById(R.id.spinR);
//        int curYr = Calendar.getInstance().get(Calendar.YEAR);
//
//        sesh.add(0, "Session");
//        sesh.add("Summer");
//        sesh.add("Fall");
//        sesh.add("Winter");
//        year.add(0, "Year");
//        for(int i=0; i<5; i++){
//            year.add(i+1, Integer.toString(curYr + i));
//        }
//        leftAdapter = new ArrayAdapter<>(this, R.layout.item_dropdown, sesh);
//        leftAdapter.setDropDownViewResource(R.layout.dd_item);
//        rightAdapter = new ArrayAdapter<>(this, R.layout.item_dropdown2, year);
//        rightAdapter.setDropDownViewResource(R.layout.dd_item);
//        spL.setAdapter(leftAdapter);
//        spR.setAdapter(rightAdapter);

        sesh.add(0, "Summer");
        sesh.add(1, "Fall");
        sesh.add(2, "Winter");

        MultiSpinner multiSpinner = (MultiSpinner) findViewById(R.id.multi_Spinner);
        multiSpinner.setItems(sesh, getString(R.string.for_all), this);

        mDb.collection(COURSE).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    existingC = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String cou = document.getString("Course Code");
                        existingC.add(cou);
                    }

                    existingC.add("None");
                    courseArray=new String[existingC.size()];
                    courseArray = existingC.toArray(courseArray);
                    doWork();
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }


    public void adDash(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
        finish();
    }

    public void openThis(View view) {
        Intent intent = new Intent(this, adminCourse.class);
        startActivity(intent);
        finish();
    }

    public void delAdmin(View view) {
        Intent intent = new Intent(this, selectDeleteAdmin.class);
        startActivity(intent);
        finish();
    }

}