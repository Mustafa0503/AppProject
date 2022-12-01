package com.example.degreeplanner.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;

import com.example.degreeplanner.R;
import com.example.degreeplanner.editAdmin;
import com.example.degreeplanner.selectDeleteAdmin;

public class ListFragment extends Fragment {
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_edit_admin, container, false);
        context = rootView.getContext(); // Assign your rootView to context
        Intent intent = new Intent(context, selectDeleteAdmin.class);
        startActivity(intent);
        return rootView;
        //return inflater.inflate(R.layout.activity_taken_course_list, container, false);
    }
}
