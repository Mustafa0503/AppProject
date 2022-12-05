package com.example.degreeplanner.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.degreeplanner.MainActivity;
import com.example.degreeplanner.R;
import com.example.degreeplanner.adminCourse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddCourseFragment extends Fragment {

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.activity_admin_course, container, false);
        context = rootView.getContext(); // Assign your rootView to context
        Intent intent = new Intent(context, adminCourse.class);
        startActivity(intent);
        return rootView;

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add_course, container, false);;

    }

}