package com.example.degreeplanner.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import static android.content.ContentValues.TAG;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.degreeplanner.adminCourse;
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
import com.example.degreeplanner.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCourseFragment extends Fragment {

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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddCourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddCourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCourseFragment newInstance(String param1, String param2) {
        AddCourseFragment fragment = new AddCourseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void doWork(LayoutInflater inflater, ViewGroup container){
        db = FirebaseFirestore.getInstance();
        View view = inflater.inflate(R.layout.fragment_add_course, container, false);
        selected = new boolean[courseArray.length];
        pre = view.findViewById(R.id.preReqs);
        course = view.findViewById(R.id.courseName);
        code = view.findViewById(R.id.courseCode);
        session = view.findViewById(R.id.offered);
        but = view.findViewById(R.id.addC);
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
                        Toast.makeText(getActivity().getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity().getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity().getApplicationContext());
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_course, container, false);
        // Inflate the layout for this fragment

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
                    doWork(inflater, container);
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        return view;
    }
}