package com.example.degreeplanner;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.MotionEffect;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class selectDeleteAdmin extends AppCompatActivity {

    List<String> selectedCourses;
    ListView listViewData;
    FirebaseAuth fAuth= FirebaseAuth.getInstance();
    String courseID= fAuth.getCurrentUser().getUid();
    FirebaseFirestore fstore ;
    ArrayAdapter<String> adapter;
    ArrayList<String> allCourses= new ArrayList<String>();

//
    //Button finishbtn = findViewById(R.id.et_name);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseFirestore.getInstance().collection("course").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                // allC = new ArrayList<String>();
                if (task.isSuccessful()) {
                    // List<String> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        allCourses.add(document.getString("Course Code"));
                    }

                    rest();
//                    arrayPeliculas=new String[allC.size()];
//                    arrayPeliculas = allC.toArray(arrayPeliculas);

                    //allC.add("djasndkj");
                }
                else {
                    Log.d(MotionEffect.TAG, "Error getting documents: ", task.getException());
                }

            }
        });
        //fstore = FirebaseFirestore.getInstance();




    }
    public void rest(){
        setContentView(R.layout.activity_select_delete_admin);
        TextView textDelete = (TextView)findViewById(R.id.textDelete);
        textDelete.setPaintFlags(textDelete.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        listViewData=findViewById(R.id.listView_data_delete);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_checked,allCourses);
        listViewData.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);

        return true;
        // return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        selectedCourses = new ArrayList<String>();
        if (id == R.id.item_delete) {
            String itemSelected = "Selected courses: \n";
            for (int i = 0; i < listViewData.getCount(); i++) {
                if (listViewData.isItemChecked(i)) {
                    itemSelected += listViewData.getItemAtPosition(i) + ", ";
                }
            }
            Toast.makeText(this, itemSelected, Toast.LENGTH_LONG).show();
        }
        if (listViewData.getCount() != 0) {

            for (int i = 0; i < listViewData.getCount(); i++) {
                if (listViewData.isItemChecked(i)) {
                    selectedCourses.add(listViewData.getItemAtPosition(i).toString());
                }
                // takenCourses.add(listViewData.getItemAtPosition(i).toString());
            }
        }

        for (int i = 0; i < selectedCourses.size(); i++) {
            deleteData(selectedCourses.get(i));
            String target = selectedCourses.get(i);
            deletePre(target);
        }


//        ArrayList test = new ArrayList<>();
//        test.add("tf");
//        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("course").document(courseID);
//        documentReference.update("Prerequisites", test);
//        FirebaseFirestore.getInstance().collection("course").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//                // allC = new ArrayList<String>();
//                if (task.isSuccessful()) {
//                    // List<String> list = new ArrayList<>();
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//
//                        ArrayList<String> pre = (ArrayList<String>) document.get("Prerequisites");
//                        pre.add("TF");
//                        for(int i=0;i<selectedCourses.size();i++)
//                        {
//                            if(pre.contains(selectedCourses.get(i))){
//                                pre.remove(selectedCourses.get(i));
//                                DocumentReference documentReference = FirebaseFirestore.getInstance().collection("course").document(courseID);
//                                documentReference.update("Prerequisites", pre);
//                            }
//                            else{
//                                DocumentReference documentReference = FirebaseFirestore.getInstance().collection("course").document(courseID);
//                                documentReference.update("Prerequisites", pre);
//                            }
//                        }
//                            //
//
//
//
//                    }
//                    //                        allC.add("None of the above");
//                    //                        use();
//                    //                    arrayPeliculas=new String[allC.size()];
//                    //                    arrayPeliculas = allC.toArray(arrayPeliculas);
//
//                    //allC.add("djasndkj");
//                } else {
//                    Log.d(MotionEffect.TAG, "Error getting documents: ", task.getException());
//                }
//
//            }
//        });
        //fstore.collection("users").whereEqualTo("courses",userID).get() ;
 //       userID = fAuth.getCurrentUser().getUid();

        //List<String> old =  (List<String>)fstore.collection("users").whereEqualTo("courses",userID).get();

//        for(int i=0; i<selectedCourses.size();i++) {
//
//
//            FirebaseFirestore.getInstance().collection("course")
//                    .document(courseID).get()
//                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//
//                            DocumentSnapshot document = task.getResult();
//                            List<String> old = (List<String>) document.get("courses");
//                            if (!old.isEmpty()) {
//
//
//                                for (int o = 0; o < old.size(); o++) {
//                                    if (!selectedCourses.contains(old.get(o))) {
//                                        selectedCourses.add(old.get(o));
//
//
//                                    }
//
//
//                                }
//                                DocumentReference documentReference = FirebaseFirestore.getInstance().collection("users").document(courseID);
//                                documentReference.update("courses", selectedCourses);
//
//                            } else {
//                                DocumentReference documentReference = FirebaseFirestore.getInstance().collection("users").document(courseID);
//                                documentReference.update("courses", selectedCourses);
//                            }
//                        }
//                    });


     //   }































        //FirebaseFirestore fstore = FirebaseFirestore.getInstance();
//        Map<String,Object> crs = new HashMap<>();
//        crs.put("courses", takenCourses);
//        crs.put("fName", "abc");
        //  documentReference.update(crs);
        //documentReference.update(crs);
        // fstore.collection("user").document("courses").set(crs, SetOptions.merge())
        // documentReference.set(crs);
        return super.onOptionsItemSelected(item);
    }
    public void deletePre(String target){
        FirebaseFirestore.getInstance().collection("course").whereArrayContains("Prerequisites", target).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                // allC = new ArrayList<String>();
                if (task.isSuccessful()) {
                    // List<String> list = new ArrayList<>();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    CollectionReference Ref = db.collection("course");
                    for(int i=0; i<task.getResult().getDocuments().size();i++)
                    {
                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(i);
                        String documentID=documentSnapshot.getId();
                        Ref.document(documentID).update("Prerequisites",FieldValue.arrayRemove(target));
                    }
                }
            }
        });
    }

    public void deleteData(String target){
        FirebaseFirestore.getInstance().collection("course").whereEqualTo
                ("Course Code", target).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()&&!task.getResult().isEmpty())
                {
                    DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                    String documentID=documentSnapshot.getId();
                    FirebaseFirestore.getInstance().collection("course").document(documentID)
                            .delete();
                }
            }
        });

    }
    public void finishDelete(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
        finish();
    }
}