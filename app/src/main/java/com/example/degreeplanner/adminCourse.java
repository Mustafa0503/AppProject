package com.example.degreeplanner;

import static android.content.ContentValues.TAG;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class adminCourse extends AppCompatActivity {

    EditText course, code, session;
    Button but;
    FirebaseFirestore db;
    TextView pre;
    List<String> preR;
    ArrayList<String> existingC;
    boolean s = false;
    boolean[] selected;
    ArrayList<Integer> courseList = new ArrayList<>();

    private FirebaseFirestore mDb = FirebaseFirestore.getInstance();
    private static final String COURSE = "course";


    String[] courseArray;
    //= {"CSCA08", "CSCA48", "CSCA67", "CSCB07", "CSCB09", "None"}


    public void doWork(){
        db = FirebaseFirestore.getInstance();
        pre = findViewById(R.id.preReqs);

        selected = new boolean[courseArray.length];
        course = findViewById(R.id.courseName);
        code = findViewById(R.id.courseCode);
        session = findViewById(R.id.offered);
        but = findViewById(R.id.addC);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String co = course.getText().toString();
                String cod = code.getText().toString();
                String se = session.getText().toString();
                Map<String, Object> newCourse = new HashMap<>();
                newCourse.put("Course Name", co);
                newCourse.put("Course Code", cod);
                newCourse.put("Session Offered", se);
                newCourse.put("Prerequisites", preR);

                db.collection("course").add(newCourse).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(adminCourse.this, "Successful", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(adminCourse.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
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
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_course);

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

//        db = FirebaseFirestore.getInstance();
//        pre = findViewById(R.id.preReqs);
//
//            selected = new boolean[courseArray.length];
//            course = findViewById(R.id.courseName);
//            code = findViewById(R.id.courseCode);
//            session = findViewById(R.id.offered);
//            but = findViewById(R.id.addC);
//            but.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    String co = course.getText().toString();
//                    String cod = code.getText().toString();
//                    String se = session.getText().toString();
//                    Map<String, Object> newCourse = new HashMap<>();
//                    newCourse.put("Course Name", co);
//                    newCourse.put("Course Code", cod);
//                    newCourse.put("Session Offered", se);
//                    newCourse.put("Prerequisites", preR);
//
//                    db.collection("course").add(newCourse).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                        @Override
//                        public void onSuccess(DocumentReference documentReference) {
//                            Toast.makeText(adminCourse.this, "Successful", Toast.LENGTH_SHORT).show();
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(adminCourse.this, "Failed", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//            });
//
//            pre.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(adminCourse.this);
//                    builder.setTitle("Select Prerequisites");
//                    builder.setCancelable(false);
//                    builder.setMultiChoiceItems(courseArray, selected, new DialogInterface.OnMultiChoiceClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
//                            if (b) {
//                                //when checkbox selected
//                                //add position in course list
//                                courseList.add(i);
//                                //sort course list
//                                Collections.sort(courseList);
//                            } else {
//                                courseList.remove(i);
//                            }
//                        }
//                    });
//
//                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            StringBuilder stringBuilder = new StringBuilder();
//
//                            preR = new ArrayList<String>();
//
//                            for (int j = 0; j < courseList.size(); j++) {
//                                stringBuilder.append(courseArray[courseList.get(j)]);
//                                preR.add(courseArray[courseList.get(j)]);
//                                if (j != courseList.size() - 1) {
//                                    stringBuilder.append(", ");
//
//                                }
//                            }
//                            pre.setText(stringBuilder.toString());
//                        }
//                    });
//
//
//                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            dialogInterface.dismiss();
//                        }
//                    });
//
//                    builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            for (int j = 0; j < selected.length; j++) {
//                                selected[j] = false;
//                                courseList.clear();
//                                pre.setText("");
//                            }
//                        }
//                    });
//
//                    builder.show();
//                }
//            });


    }


    public void adDash(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
        finish();
    }

}