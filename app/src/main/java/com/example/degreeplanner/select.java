package com.example.degreeplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class select extends AppCompatActivity {

    List<String> takenCourses;
    ListView listViewData;
    String userID;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore ;
    ArrayAdapter<String> adapter;
    String[] arrayPeliculas = {"Select all","CSCA67","CSCA67","CSCA67","CSCA67","CSCA67","CSCB36","CSCB36","CSCB36","CSCB36","CSCB63","CSCA08", "CSCA48","CSCB09","CSCB07",
            "CSCB24","None of the above"};
    //Button finishbtn = findViewById(R.id.et_name);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fstore = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_select);
        TextView textView14 = (TextView)findViewById(R.id.textView14);
        textView14.setPaintFlags(textView14.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        listViewData=findViewById(R.id.listView_data);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice,arrayPeliculas);
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
        if(listViewData.isItemChecked(0))
        {
            listViewData.setItemChecked(9, false);
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
            takenCourses = new ArrayList<String>();
            for(int i=0;i<listViewData.getCount();i++){
                if(listViewData.isItemChecked(i))
                {
                    takenCourses.add(listViewData.getItemAtPosition(i).toString());
                }
               // takenCourses.add(listViewData.getItemAtPosition(i).toString());
            }
        }
       // fAuth=FirebaseAuth.getInstance();
        //userID = fAuth.getCurrentUser().getUid();
        // DocumentReference documentReference = FirebaseFirestore.getInstance().collection("users").document("JEKEYSyvFNcJy3wqnJIROHMwSb53");
        //documentReference.update("courses","takenCourses");


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