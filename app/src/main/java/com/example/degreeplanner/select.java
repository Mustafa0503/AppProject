package com.example.degreeplanner;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class select extends AppCompatActivity {

    List<String> takenCourses;
    ListView listViewData;
    String userID;
    FirebaseAuth fAuth= FirebaseAuth.getInstance();
    FirebaseFirestore fstore ;
    ArrayAdapter<String> adapter;
    ArrayList<String> allC = new ArrayList<String>();
    String[] arrayPeliculas = {"abc","fty"}  ;
    //Button finishbtn = findViewById(R.id.et_name);

    public void start(){

        FirebaseFirestore.getInstance().collection("course").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                // allC = new ArrayList<String>();
                if (task.isSuccessful()) {
                    // List<String> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        allC.add(document.getString("Course Code"));
                    }

//                    arrayPeliculas=new String[allC.size()];
//                    arrayPeliculas = allC.toArray(arrayPeliculas);

                    //allC.add("djasndkj");
                }
                else
                {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allC.add("Select all");

        //Query a = FirebaseFirestore.getInstance().collection("course").whereGreaterThan("Course Code", null);

        FirebaseFirestore.getInstance().collection("course").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                // allC = new ArrayList<String>();
                if (task.isSuccessful()) {
                    // List<String> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        allC.add(document.getString("Course Code"));
                    }
                    allC.add("None of the above");
                    use();
//                    arrayPeliculas=new String[allC.size()];
//                    arrayPeliculas = allC.toArray(arrayPeliculas);

                    //allC.add("djasndkj");
                }
                else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }

            }
        });
       // FirebaseFirestore.getInstance().collection("course").stream
//        setContentView(R.layout.activity_select);
//        TextView textView14 = (TextView)findViewById(R.id.textView14);
//        textView14.setPaintFlags(textView14.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
//        listViewData=findViewById(R.id.listView_data);
//
//        adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_multiple_choice,allC);
//        listViewData.setAdapter(adapter);
       // allC.add("djasndkj");

    }
    public void use(){
        setContentView(R.layout.activity_select);
        TextView textView14 = (TextView)findViewById(R.id.textView14);
        textView14.setPaintFlags(textView14.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        listViewData=findViewById(R.id.listView_data);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_checked,allC){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
            {
                View view = super.getView(position, convertView, parent);
                if (position % 2 == 1)
                {
                    view.setBackgroundColor(getResources().getColor(R.color.purple_500));
                }
                else
                {
                    view.setBackgroundColor(getResources().getColor(R.color.purple_200));
                }
                if(position==0){
                    view.setBackgroundColor(getResources().getColor(R.color.white));
                }
                if(position==allC.size()-1){
                    view.setBackgroundColor(getResources().getColor(R.color.white));
                }
                return view;
            }

        };;
        listViewData.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
        // return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        takenCourses = new ArrayList<String>();
        if(listViewData.isItemChecked(0))
        {
            listViewData.setItemChecked(listViewData.getCount()-1, false);
            for(int i=0;i<listViewData.getCount()-1;i++) {
                listViewData.setItemChecked(i, true);
            }

        }
        else if(listViewData.isItemChecked(listViewData.getCount()-1))
        {
            for(int i=1;i<listViewData.getCount();i++) {
                listViewData.setItemChecked(i, false);
            }
        }
        if(id==R.id.item_done){
            String itemSelected = "Selected courses: \n";
            for(int i=0;i<listViewData.getCount();i++){
                if(listViewData.isItemChecked(i)){
                    if(i==0){
                        for(int j=1;j<listViewData.getCount()-1;j++)
                        {
                            itemSelected+=listViewData.getItemAtPosition(j)+"\n";
                        }

                        break;
                    }
                    itemSelected+=listViewData.getItemAtPosition(i)+", ";
                }
            }
            Toast.makeText(this,itemSelected, Toast.LENGTH_LONG).show();
        }
        if(listViewData.getCount()!=0)
        {

            for(int i=0;i<listViewData.getCount();i++){
                if(listViewData.isItemChecked(i))
                {
                    if(i!=0)
                        takenCourses.add(listViewData.getItemAtPosition(i).toString());
                }
               // takenCourses.add(listViewData.getItemAtPosition(i).toString());
            }
        }
        //fstore.collection("users").whereEqualTo("courses",userID).get() ;
        userID = fAuth.getCurrentUser().getUid();

        //List<String> old =  (List<String>)fstore.collection("users").whereEqualTo("courses",userID).get();


        FirebaseFirestore.getInstance().collection("users")
                .document(userID).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        DocumentSnapshot document = task.getResult();
                        List<String> old = (List<String>) document.get("courses");
                        if (!old.isEmpty())
                        {


                            for (int o = 0; o < old.size(); o++) {
                                if (!takenCourses.contains(old.get(o))) {
                                    takenCourses.add(old.get(o));


                                }


                            }
                            DocumentReference documentReference = FirebaseFirestore.getInstance().collection("users").document(userID);
                            documentReference.update("courses", takenCourses);

                        }else{
                            DocumentReference documentReference = FirebaseFirestore.getInstance().collection("users").document(userID);
                            documentReference.update("courses",takenCourses);
                        }
                    }
                });



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
    public void finish(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}