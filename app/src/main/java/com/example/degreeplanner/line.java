package com.example.degreeplanner;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class line extends AppCompatActivity {

    ArrayList<courseObj> allC = new ArrayList<>();
    ListView l;
    String userid;
    ArrayList<String> course = new ArrayList<>();
    ArrayList<String> prereq = new ArrayList<>();
    private FirebaseFirestore mDb = FirebaseFirestore.getInstance();
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();

    public void showList2(){
        l = findViewById(R.id.list);
        CourseLineBuilder a = new CourseLineBuilder();
        ArrayList<String> arrStr = a.getSelectedCourses();

        String courseArray[] =new String[arrStr.size()];
        courseArray = arrStr.toArray(courseArray);

        ArrayAdapter<String> arr;
        arr = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courseArray);
        l.setAdapter(arr);
    }

    public void getCourseList(){
        userid = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                course = (ArrayList<String>)value.get("Wanted");
                prereq = (ArrayList<String>) value.get("Prerequisites");
            }
        });



    }

    public void showList(){
        l = findViewById(R.id.list);
        ArrayList<String> arrStr = new ArrayList<>();
        for(int i=0; i<allC.size(); i++){
            courseObj obj = allC.get(i);
            String str = "Course Code: " + obj.getCode() + "\t\tOffered in: " + obj.getOfferSession() + "\t\tPrereq: " + obj.getPre();
            //String str = "Course Code: " + obj.getCode();
            arrStr.add(str);
        }

        String courseArray[] =new String[arrStr.size()];
        courseArray = arrStr.toArray(courseArray);

        ArrayAdapter<String> arr;
        arr = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courseArray);
        l.setAdapter(arr);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
        showList2();

        mDb.collection("course").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //t
                if (task.isSuccessful()) {

                    for (int i=0; i<task.getResult().getDocuments().size();i++) {
                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(i);
                        String crs= documentSnapshot.getString("Course Code");
                        ArrayList<String> off = (ArrayList<String>) documentSnapshot.get("Session Offered");
                        ArrayList<String> pre = (ArrayList<String>) documentSnapshot.get("Prerequisites");
                        allC.add(new courseObj(crs, off, pre));
                    }

                    //showList();


                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }


    public void timeLine(View view){
        Intent b1 = new Intent(this, CourseLineBuilder.class);
        startActivity(b1);
        finish();
    }
}