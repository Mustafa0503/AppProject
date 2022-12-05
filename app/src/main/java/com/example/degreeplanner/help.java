package com.example.degreeplanner;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class help extends AppCompatActivity {
    ArrayList<String> listFire;

    public help(ArrayList<String> pre) {
        for(int i = 0;i<pre.size();i++){
            listFire.add(pre.get(i));
        }
    }

    public ArrayList<String> getList() {
        FirebaseFirestore.getInstance().collection("course").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    // List<String> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        listFire.add(document.getString("Course Code"));
                    }
//                    arrayPeliculas=new String[allC.size()];
//                    arrayPeliculas = allC.toArray(arrayPeliculas);

                    //allC.add("djasndkj");
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }

            }
        });
        return listFire;
    }
}
