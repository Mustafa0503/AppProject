package com.example.degreeplanner;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
//import android.support.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class editCourseAdmin extends AppCompatActivity implements MultiSpinnerListener{
    EditText course, code, session;
    Button but;
    FirebaseFirestore db;
    TextView pre, coursename;
    String co="", cod="", se="";
    List<String> preR;
    ArrayList<String> existingC;
    ListView list;
    boolean s = false;
    boolean[] selected, selSession;
    ArrayList<Integer> courseList = new ArrayList<>();
    ArrayList<String> sesh = new ArrayList<>();
    ArrayList<String> sessionDB = new ArrayList<>();
    editAdmin ob = new editAdmin();
    String val = ob.getItemval();

    private FirebaseFirestore mDb = FirebaseFirestore.getInstance();
    private static final String COURSE = "course";


    String[] courseArray;
    //= {"CSCA08", "CSCA48", "CSCA67", "CSCB07", "CSCB09", "None"}

    public void onItemsSelected(boolean[] selected){

    }
    public void doWork(){
        db = FirebaseFirestore.getInstance();
        pre = findViewById(R.id.prereqs);

        selected = new boolean[courseArray.length];
        course = findViewById(R.id.coursename);
        code = findViewById(R.id.Coursecode);
        //session = findViewById(R.id.session);
        selSession = MultiSpinner.getSelected();
        but = findViewById(R.id.FinishBtn);
        list = findViewById(R.id.adminlistview);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                co = course.getText().toString();
                cod = code.getText().toString();
                for (int i = 0; i < 3; i++) {
                    if (selSession[i] == true) {
                        sessionDB.add(sesh.get(i));
                    }
                }
                if (!co.equals("") && !cod.equals("") && !existingC.contains(cod) && !sessionDB.isEmpty() && !preR.isEmpty()) {
                    Map<String, Object> newCourse = new HashMap<>();
                    newCourse.put("Course Name", co);
                    newCourse.put("Course Code", cod);
                    newCourse.put("Session Offered", sessionDB);
                    newCourse.put("Prerequisites", preR);
//                db.collection("course").add(newCourse).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Toast.makeText(editCourseAdmin.this, "Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity2.class));
                    finish();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(editCourseAdmin.this, "Failed", Toast.LENGTH_SHORT).show();
//                    }
//                });
                    mDb.collection("course").whereEqualTo("Course Code", val).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                            String documentID = documentSnapshot.getId();
                            DocumentReference documentReference = FirebaseFirestore.getInstance().collection("course").document(documentID);
                            documentReference.update("Course Code", cod);
                            documentReference.update("Course Name", co);
                            documentReference.update("Session Offered", sessionDB);
                            documentReference.update("Prerequisites", preR);

                        }
                    });

                    mDb.collection("course").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> taskk) {
                            //t
                            if (taskk.isSuccessful()) {

                                for (int i = 0; i < taskk.getResult().getDocuments().size(); i++) {
                                    DocumentSnapshot documentSnapshot = taskk.getResult().getDocuments().get(i);
                                    String documentID=documentSnapshot.getId();

                                    ArrayList<String> preQ = (ArrayList<String>) documentSnapshot.get("Prerequisites");
                                    if(preQ.contains(val)){
                                        for(int kk=0; kk<preQ.size(); kk++){
                                            if(preQ.get(kk).equals(val)){
                                                preQ.set(kk, cod);
                                            }
                                        }
                                    }

                                    mDb.collection("course").document(documentID).update("Prerequisites", preQ);

                                }

                            } else {
                                Log.d(TAG, "get failed with ", taskk.getException());
                            }
                        }
                    });


                }else {
                    Toast.makeText(editCourseAdmin.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                }
            }
        });

        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(editCourseAdmin.this);
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
                            courseList.remove(i);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course_admin);
        coursename = findViewById(R.id.textView2);
        coursename.setText("Modify " + val);

        sesh.add(0, "Summer");
        sesh.add(1, "Fall");
        sesh.add(2, "Winter");

        MultiSpinner multiSpinner = (MultiSpinner) findViewById(R.id.multi_spinner2);
        multiSpinner.setItems(sesh, "", this);
        mDb.collection(COURSE).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    existingC = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String cou = document.getString("Course Code");
                        if(!cou.equals(val)){
                            existingC.add(cou);
                        }

                    }

                    existingC.add("None");
                    courseArray = new String[existingC.size()];
                    courseArray = existingC.toArray(courseArray);
                    doWork();
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
    public void gg2(View view) {
        Intent intent = new Intent(this, editAdmin.class);
        startActivity(intent);
        finish();
    }
}