package com.example.degreeplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class select extends AppCompatActivity {

    List<String> takenCourses;
    ListView listViewData;

    ArrayAdapter<String> adapter;
    String[] arrayPeliculas = {"Select all","CSCA67","CSCB36","CSCB63","CSCA08", "CSCA48","CSCB09","CSCB07",
            "CSCB24","None of the above"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        else if(listViewData.isItemChecked(9))
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
                        itemSelected+=listViewData.getItemAtPosition(1)+"\n";
                        itemSelected+=listViewData.getItemAtPosition(2)+"\n";
                        itemSelected+=listViewData.getItemAtPosition(3)+"\n";
                        itemSelected+=listViewData.getItemAtPosition(4)+"\n";
                        itemSelected+=listViewData.getItemAtPosition(5)+"\n";
                        itemSelected+=listViewData.getItemAtPosition(6)+"\n";
                        itemSelected+=listViewData.getItemAtPosition(7)+"\n";
                        itemSelected+=listViewData.getItemAtPosition(8)+"\n";
                        break;
                    }
                    itemSelected+=listViewData.getItemAtPosition(i)+"\n";
                }
            }
            Toast.makeText(this,itemSelected, Toast.LENGTH_SHORT).show();
        }
        if(listViewData.getCount()!=0)
        {
            takenCourses = new ArrayList<String>();
            for(int i=0;i<listViewData.getCount();i++){
                takenCourses.add(listViewData.getItemAtPosition(i).toString());
            }
        }
        return super.onOptionsItemSelected(item);
    }
}