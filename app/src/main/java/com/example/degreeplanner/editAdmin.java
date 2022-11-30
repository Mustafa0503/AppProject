package com.example.degreeplanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class editAdmin extends AppCompatActivity {
//    private RecyclerView admincourselist;
//    private FirebaseFirestore mFireStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_admin);
        ListView adminlist = findViewById(R.id.adminlistview);


//        mFireStore = FirebaseFirestore.getInstance();

//        mFireStore.collection("course").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                for(DocumentChange doc: value.getDocumentChanges()){
//                    String course_name = doc.getString("Course Code");
//                }
//            }
//        });

    }
}