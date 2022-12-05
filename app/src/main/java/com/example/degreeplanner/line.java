package com.example.degreeplanner;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Calendar;
import java.util.Date;
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

import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.LinkedHashSet;
import java.util.Set;

public class line extends AppCompatActivity {

    ArrayList<courseObj> allC = new ArrayList<>();
    ListView l;
    String userid;
    private FirebaseFirestore mDb = FirebaseFirestore.getInstance();
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();

    public ArrayList<String> rec(ArrayList<String> target, ArrayList<courseObj> fire){
        ArrayList<String> result = new ArrayList<String>();
        for(int i=0; i<target.size();i++){
            int a;
            //result.add(target.get(i));
            a = find(fire, target.get(i));
            if(a!=-1 && !fire.get(a).getPre().isEmpty() && !fire.get(a).getPre().get(0).equals("None"))
            {
                //result.add(fire.get(a).pre);
                for(int g=0;g<fire.get(a).getPre().size();g++)
                {
                    if(!result.contains(fire.get(a).getPre().get(g)))
                    {
                        result.add(fire.get(a).getPre().get(g));
                    }
                }

                removeDup(result);
                result.addAll(rec(fire.get(a).pre, fire));
            }
            else
            {
                if(!result.contains(fire.get(a).getCode()))
                {
                    result.add(fire.get(a).getCode());
                    removeDup(result);
                }

            }
        }

        removeDup(result);
        return result;
    }

    public int find(ArrayList<courseObj> fire, String target){
        for(int i=0;i<fire.size();i++){
            if(fire.get(i).getCode().equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public void removeDup(ArrayList<String> target){
        ArrayList<String> ref = new ArrayList<>();
        ref = target;
        for(int i=0;i<ref.size();i++)
        {
            for(int j=i+1;j<ref.size();j++)
            {
                if(ref.get(i).equals(ref.get(j)))
                {

                    target.remove(j);
                }
            }
        }
    }



    public void buildEntireList(){
        mDb.collection("course").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> taskk) {
                //t
                if (taskk.isSuccessful()) {

                    for (int i=0; i<taskk.getResult().getDocuments().size();i++) {
                        DocumentSnapshot documentSnapshot = taskk.getResult().getDocuments().get(i);
                        String crs= documentSnapshot.getString("Course Code");
                        ArrayList<String> off = (ArrayList<String>) documentSnapshot.get("Session Offered");
                        ArrayList<String> pre = (ArrayList<String>) documentSnapshot.get("Prerequisites");
                        allC.add(new courseObj(crs, off, pre));
                    }

                    getCourseList(allC);

                } else {
                    Log.d(TAG, "get failed with ", taskk.getException());
                }
            }
        });
    }
    public int getDate(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);

        if(month>=5 && (month <= 8 || (month==9 && day<=15))){
            return 0;
        } else if ((month>=9 && day>15) && (month<=12 || month==1)){
            return 1;
        } else {
            return 2;
        }

    }

    public courseObj getSession(String str, ArrayList<courseObj> list){
        for (int i=0; i<list.size(); i++){
            courseObj obj = list.get(i);
            if(obj.getCode().equals(str)){
                return obj;
            }
        }
        return null;
    }
//    public HashMap<String, ArrayList<String>> algorithm(ArrayList<String> res, ArrayList<String> taken, ArrayList<courseObj> list, ArrayList<String> track){
//        //HashMap<String, ArrayList<courseObj>> map1 = new HashMap<String, ArrayList<courseObj>>();
//        HashMap<String, ArrayList<String>> map1 = new HashMap<String, ArrayList<String>>();
////        ArrayList<courseObj> fall = new ArrayList<courseObj>();
////        ArrayList<courseObj> winter = new ArrayList<courseObj>();
////        ArrayList<courseObj> summer = new ArrayList<courseObj>();
//        ArrayList<String> fall = new ArrayList<String>();
//        ArrayList<String> winter = new ArrayList<String>();
//        ArrayList<String> summer = new ArrayList<String>();
//        ArrayList<String> tempSession;
//        for(int i=0; i<res.size(); i++){
//            courseObj temp = getSession(res.get(i), list);
//            if(temp!=null){
//                tempSession = temp.getOfferSession();
//                for(int j=0; j<tempSession.size(); j++){
//                    String season = tempSession.get(j);
//                    switch (season){
//                        case "Fall":
//                            fall.add(temp.getCode());
//                            break;
//                        case "Winter":
//                            winter.add(temp.getCode());
//                            break;
//                        case "Summer":
//                            summer.add(temp.getCode());
//                            break;
//                    }
//                }
//            }
//        }
//        map1.put("Fall", fall);
//        map1.put("Winter", winter);
//        map1.put("Summer", summer);
//
//        String arr[] = {"Fall", "Winter", "Summer"};
//        int iter = getDate();
//        int year = Calendar.getInstance().get(Calendar.YEAR);
//        int month = Calendar.getInstance().get(Calendar.MONTH);
//        if(iter==1 && month!=1){
//            year+=1;
//        }
//
//        ArrayList<String> preTemp;
//        HashMap<String, ArrayList<String>> map2 = new HashMap<String, ArrayList<String>>();
//        while(!map1.get("Fall").isEmpty() || !map1.get("Winter").isEmpty() || !map1.get("Summer").isEmpty()){
//            String se = arr[iter] + " " + Integer.toString(year);
//            track.add(se);
//            ArrayList<courseObj> tempObj = map1.get(arr[iter]);
//            preTemp = new ArrayList<String>();
//
//            for(int k=0; k<tempObj.size(); k++){
//                ArrayList<String> pre = tempObj.get(k).getPre();
//                if(pre.isEmpty() || (pre.size()==1 && pre.contains("None")) || taken.containsAll(pre)){
//                    preTemp.add(tempObj.get(k).getCode());
//                }
//            }
//
//            taken.addAll(preTemp);
//
//            for(int i=0; i<3; i++)
//                map1.get(arr[i]).removeAll(preTemp);
//
//            map2.put(se, preTemp);
//            preTemp.clear();
//            iter=(iter+1)%3;
//        }
//
//        return map2;
//    }


//    ArrayList<String> fall = new ArrayList<String>();
//    ArrayList<String> winter = new ArrayList<String>();
//    ArrayList<String> summer = new ArrayList<String>();
//    ArrayList<String> tempSession;
//            for(int i=0; i<result.size(); i++){
//        courseObj temp = getSession(result.get(i), allC);
//        if(temp!=null){
//            tempSession = temp.getOfferSession();
//            for(int j=0; j<tempSession.size(); j++){
//                String season = tempSession.get(j);
//                switch (season){
//                    case "Fall":
//                        fall.add(temp.getCode());
//                        break;
//                    case "Winter":
//                        winter.add(temp.getCode());
//                        break;
//                    case "Summer":
//                        summer.add(temp.getCode());
//                        break;
//                }
//            }
//        }
//    }
//    //HashMap<String, ArrayList<courseObj>> map1 = new HashMap<String, ArrayList<courseObj>>();
//    HashMap<String, ArrayList<String>> map1 = new HashMap<String, ArrayList<String>>();
//            map1.put("Fall", fall);
//            map1.put("Winter", winter);
//            map1.put("Summer", summer);
//
//    String arr[] = {"Fall", "Winter", "Summer"};



    @SuppressLint("NewApi")
    public void getCourseList(ArrayList<courseObj> allC){
        userid = fAuth.getCurrentUser().getUid();
        fStore.collection("users").document(userid).get().addOnCompleteListener(task ->{
            ArrayList<String> wanted = (ArrayList<String>) task.getResult().get("Wanted");


            ArrayList<String> result = rec(wanted, allC);
            result.addAll(wanted);
            removeDup(result);

            //HashMap<String, ArrayList<courseObj>> map1 = new HashMap<String, ArrayList<courseObj>>();
            ArrayList<courseObj> fall = new ArrayList<courseObj>();
            ArrayList<courseObj> winter = new ArrayList<courseObj>();
            ArrayList<courseObj> summer = new ArrayList<courseObj>();
//            ArrayList<String> fall = new ArrayList<String>();
//            ArrayList<String> winter = new ArrayList<String>();
//            ArrayList<String> summer = new ArrayList<String>();
            ArrayList<String> tempSession;
            courseObj temp=null;

            for(int i=0; i<result.size(); i++){
                temp = getSession(result.get(i), allC);
                if(temp!=null){
                    tempSession = temp.getOfferSession();
                    for(int j=0; j<tempSession.size(); j++){
                        String season = tempSession.get(j);
                        switch (season){
                            case "Fall":
                                fall.add(temp);
                                break;
                            case "Winter":
                                winter.add(temp);
                                break;
                            case "Summer":
                                summer.add(temp);
                                break;
                        }
                    }
                }
            }
            HashMap<String, ArrayList<courseObj>> map1 = new HashMap<String, ArrayList<courseObj>>();
            //HashMap<String, ArrayList<String>> map1 = new HashMap<String, ArrayList<String>>();
            map1.put("Fall", fall);
            map1.put("Winter", winter);
            map1.put("Summer", summer);

            String arr[] = {"Fall", "Winter", "Summer"};
            int iter = getDate();
            int year = Calendar.getInstance().get(Calendar.YEAR);
            int month = Calendar.getInstance().get(Calendar.MONTH);

            ArrayList<String> preTemp= new ArrayList<String>();
            HashMap<String, ArrayList<String>> map2 = new HashMap<String, ArrayList<String>>();
            ArrayList<String> pre;
            ArrayList<courseObj> tempObj;
            ArrayList<String> track = new ArrayList<String>();
            String se = "";
            ArrayList<String> course = (ArrayList<String>) task.getResult().get("courses");
            ArrayList<courseObj> tempOption = new ArrayList<courseObj>();
            Set<String> keys = map1.keySet();
            //!map1.get("Fall").isEmpty() || !map1.get("Winter").isEmpty() || !map1.get("Summer").isEmpty()

            while(!map1.get("Fall").isEmpty() || !map1.get("Winter").isEmpty() || !map1.get("Summer").isEmpty()){
                if (iter==1 && month!=1) year++;
                se = arr[iter] + " " + year;
                track.add(se);

                tempObj = map1.get(arr[iter]);

                for(int k=0; k<tempObj.size(); k++){
                    pre = tempObj.get(k).getPre();
                    if(pre.isEmpty() || (pre.size()==1 && pre.contains("None")) || course.containsAll(pre)){
                        preTemp.add(tempObj.get(k).getCode());
                    }
                }

                course.addAll(preTemp);

                for(String key: keys){
                    tempOption = map1.get(key);
                    tempOption.removeAll(preTemp);
                    map1.replace(key, tempOption);
                }

                map2.put(se, preTemp);
                preTemp.clear();
                iter=(iter+1)%3;

            }

            //displaying timeline
            ArrayList<String> disList = new ArrayList<String>();
            for(int i=0; i<track.size(); i++){
                String str = track.get(i) + ": \t\t" + map2.get(track.get(i));
                disList.add(str);
            }
            for(int i=0; i<arr.length; i++){
                String str = arr[i] + ": \t\t" + map1.get(arr[i]);
                disList.add(str);
            }

            displayLine(disList);

        });


        // Set<String> yours = new LinkedHashSet<>(wanted);
        // yours.addAll(course);
        //yoursGood = new ArrayList<>(wanted);

    }


    public void displayLine(ArrayList<String> strL){
        l = findViewById(R.id.list);
        String courseArray[] =new String[strL.size()];
        courseArray = strL.toArray(courseArray);

        ArrayAdapter<String> arr;
        arr = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courseArray);
        l.setAdapter(arr);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
        buildEntireList();
    }


    public void timeLine(View view){
        Intent b1 = new Intent(this, CourseLineBuilder.class);
        startActivity(b1);
        finish();
    }
}