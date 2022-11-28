//package com.example.degreeplanner;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.graphics.Color;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//
//public class courseSelection extends AppCompatActivity implements CourseListener {
//    RecyclerView recycler_view;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_course_selection);
//        recycler_view = findViewById(R.id.recycler_view);
//
//        setRecyclerView();
//    }
//    public ArrayList<String> getCourseData(){
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("CSCA67");
//        arrayList.add("CSCA36");
//        arrayList.add("CSCA63");
//        arrayList.add("CSCA08");
//        arrayList.add("CSCA48");
//        arrayList.add("CSCA09");
//        arrayList.add("CSCA07");
//        arrayList.add("CSCA24");
//        arrayList.add("None of the above");
//        return arrayList;
//    }
//    private void setRecyclerView() {
//        recycler_view.setHasFixedSize(true);
//
//        recycler_view.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new CourseAdapter(this, getCourseData(), this);
//        //System.out.println(getCourseData().toString());
//        recycler_view.setAdapter(adapter);
//    }
//
//    @Override
//    public void onCourseChange(ArrayList<String> arrayList) {
//        //TODO
//        Toast.makeText(this, arrayList.toString(), Toast.LENGTH_SHORT).show();
//    }
//}