package com.example.degreeplanner;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class findAllC extends AppCompatActivity {
    boolean succ=false;
    ArrayList<findAllC> allC = new ArrayList<findAllC>();
    ArrayList<findAllC> data = new ArrayList<findAllC>();
    String courseCode;
    int a;
    ArrayList<String> offerSession;
    ArrayList<String> pre;
    ArrayList<findAllC> tt = new ArrayList<>();;

    private FirebaseFirestore mDb = FirebaseFirestore.getInstance();
    private static final String COURSE = "course";
    public findAllC(String j){
        this.courseCode = j;
    }
    public findAllC(){

    }
    public findAllC(String courseCode, ArrayList<String> offerSession, ArrayList<String> pre) {
        //this.allC = allC;
        this.courseCode = courseCode;
        this.offerSession = offerSession;
        this.pre = pre;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
    public void getAll(){
        ArrayList<findAllC> tt = new ArrayList<>();
//        ArrayList<findAllC> allC = new ArrayList<findAllC>();
        mDb.collection("course").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //t
                if (task.isSuccessful()) {

                    succ=true;
                    //for (QueryDocumentSnapshot document : task.getResult()) {
                    for(int i=0; i<task.getResult().getDocuments().size();i++)
                    {
                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(i);
                        String crs= documentSnapshot.getString("Course Code");
                        ArrayList<String> off = (ArrayList<String>) documentSnapshot.get("Session Offered");
                        ArrayList<String> pre = (ArrayList<String>) documentSnapshot.get("Prerequisites");
                        tt.add(new findAllC(crs, off, pre));
                    }

                    //setList(tt);

                    //System.out.println(Register.all.size());


                }
            }
        });

        //System.out.println(tt.size());
        //System.out.println("*******************");
       // System.out.println(allC.size());
       // Collections.copy(allC,tt);

    }

    public String getCourseCode(){
        return this.courseCode;
    }

    public void setList(ArrayList<findAllC> name){

        this.allC = name;
        //System.out.println(allC.size());
    }
    public ArrayList<findAllC> getList(){
        getAll();
        return allC;
    }
    /*public static void callback(ArrayList<findAllC> f) {
        all = f;
        System.out.println(all.size());


        Register.callback(tt);




        findAllC a = new findAllC();
        a.getAll();
    }*/
}
