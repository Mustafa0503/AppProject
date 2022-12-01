package com.example.degreeplanner;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class editCourseAdmin extends AppCompatActivity {
    EditText course, code, session;
    Button but;
    FirebaseFirestore db;
    TextView pre, coursename;
    List<String> preR;
    ArrayList<String> existingC;
    ListView list;
    boolean s = false;
    boolean[] selected;
    ArrayList<Integer> courseList = new ArrayList<>();
    editAdmin ob = new editAdmin();
    String val = ob.getItemval();

    private FirebaseFirestore mDb = FirebaseFirestore.getInstance();
    private static final String COURSE = "course";


    String[] courseArray;
    //= {"CSCA08", "CSCA48", "CSCA67", "CSCB07", "CSCB09", "None"}


    public void doWork(){
        db = FirebaseFirestore.getInstance();
        pre = findViewById(R.id.prereqs);

        selected = new boolean[courseArray.length];
        course = findViewById(R.id.coursename);
        code = findViewById(R.id.Coursecode);
        session = findViewById(R.id.session);
        but = findViewById(R.id.FinishBtn);
        list = findViewById(R.id.adminlistview);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String co = course.getText().toString();
                String cod = code.getText().toString();
                String se = session.getText().toString();
                if (TextUtils.isEmpty(co)) {
                    course.setError("Course name is Required");
                    return;
                }
                if (TextUtils.isEmpty(cod)) {
                    code.setError("Course code is Required");
                    return;
                }
                if (TextUtils.isEmpty(se)) {
                    session.setError("Session is Required");
                    return;
                }
                Map<String, Object> newCourse = new HashMap<>();
                newCourse.put("Course Name", co);
                newCourse.put("Course Code", cod);
                newCourse.put("Session Offered", se);
                newCourse.put("Prerequisites", preR);
//                db.collection("course").add(newCourse).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Toast.makeText(editCourseAdmin.this, "Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), editCourseAdmin.class));
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
                        String documentID=documentSnapshot.getId();
                        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("course").document(documentID);
                            documentReference.update("Course Code",cod);
                            documentReference.update("Course Name", co);
                            documentReference.update("Session Offered", se);
                            documentReference.update("Prerequisites", preR);

                    }
                });
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