package com.example.degreeplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

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
                        allC.add(new findAllC(crs, off, pre));
                    }
                    ArrayList<String> target = new ArrayList<>();
                    //target.add("CSCA08");
                   // target.add("CSCB09");
                   // target.add("A08");
                    //target.add("A48");
                    target.add("D18");
                    target.add("D01");
                    target.add("D25");

                    ArrayList<String> result = rec(target,allC);
                    ArrayList<String> userCourses = new ArrayList<String>();
                       userCourses.add("A08");
                       userCourses.add("A48");
                       userCourses.add("B09");
                       userCourses.add("B07");
                       userCourses.add("C01");

                    for(int i=0;i<target.size();i++)
                    {
                        result.add(target.get(i));
                    }
                    removeDup(result);
                    //result.remove("A08");
                    System.out.println(result);

                    //remove from taken courses list as well
                    ArrayList<String> empty = new ArrayList<String>();
                    ArrayList<ArrayList<String>> emptyy = new ArrayList<ArrayList<String>>();
                    //System.out.println(result);
                    ArrayList<ArrayList<info>> allCases = new ArrayList<ArrayList<info>>();
                    ArrayList<ArrayList<info>> display = new ArrayList<ArrayList<info>>();
                    ArrayList<info> timeLine = new ArrayList<info>();
                    LinkedHashMap<String ,ArrayList<String>> time = new LinkedHashMap<>();
                    LinkedHashMap<String ,ArrayList<String>> ref = new LinkedHashMap<>();
                    ArrayList<String> a = new ArrayList<>();

                    for(int i=3; i<result.size()+3;i++)
                    {
                        time.put("202"+i + " Winter",new ArrayList<String>() );
                        time.put("202"+i + " Summer", new ArrayList<String>());
                        time.put("202"+i + " Fall", new ArrayList<String>());
                    }

                    ArrayList<String> takenCourses = new ArrayList<String>();
                    empty = getSameTime(emptyy,result,result.size(),takenCourses);

                    System.out.println(empty);
                    //remove taken courses from empty
                    System.out.println("HashMap###################");


//                    ArrayList<String>t = new ArrayList<>();
//                    t = time.get("2023 Summer");
//                    t.add("why");
//                    time.put("2023 Summer",t);
//                    System.out.println(time.get("2023 Summer"));
//                    System.out.println(time.get("2024 Summer"));


                    ArrayList<String> takenCourses1 = new ArrayList<String>();
                    time = generator(time, empty, allC, takenCourses1,userCourses);
                    changeHash(time, userCourses);
                    ref = time;
                    //removing the empty values
                    ArrayList<String>allKey = new ArrayList<>();
                    for(String key : time.keySet())
                    {
                        if(time.get(key).isEmpty())
                        {
                            allKey.add(key);
                        }
                    }
                    for(int i =0;i<allKey.size();i++)
                    {
                        time.remove(allKey.get(i));
                    }

                    System.out.println(time);

                }
            }
        });

    }

    public void changeHash(LinkedHashMap<String,ArrayList<String>>time, ArrayList<String> takenCourses)
    {
        for(int i = 0; i<takenCourses.size();i++)
        {
            for(String key:time.keySet())
            {
                if(time.get(key).contains(takenCourses.get(i)))
                {
                    ArrayList<String> temp  = new ArrayList<String >();
                    temp = time.get(key);
                    temp.remove(takenCourses.get(i));
                }
            }
        }
    }

    public LinkedHashMap<String, ArrayList<String>> generator(LinkedHashMap<String, ArrayList<String>>line,
                                                              ArrayList<String>sameLevel,
                                                              ArrayList<findAllC>fire,ArrayList<String>planned,
                                                              ArrayList<String>userCourses){
       // System.out.println(userCourses);
        ArrayList<String> ref = sameLevel;
        while(!ref.isEmpty()){
            //System.out.println(ref);
            String temp = ref.get(0);
                //// for(int i=0;i<temp.size();i++)
                ////{
                int pos = 0;
                pos = find(fire, temp);
                ArrayList<String> offered = fire.get(pos).offerSession;
                //System.out.println(fire.get(pos).courseCode);

                for (String data : line.keySet()) {

                    //System.out.println(planned);
                    if (offered.contains("Winter") && data.contains("Winter")) {
                        // planned.addAll(line.get(data));
                        if (fire.get(pos).pre.isEmpty() || fire.get(pos).pre.get(0).equals("None") || checkAllPre(fire.get(pos).pre, planned) || checkAllPre(fire.get(pos).pre, userCourses)) {
                            line.get(data).add(fire.get(pos).courseCode);
                            planned.clear();
                            break;

                        } else {
                            planned.addAll(line.get(data));
                            continue;
                        }
                    } else if (offered.contains("Summer") && data.contains("Summer")) {

                        //planned.addAll(line.get(data));
                        if (fire.get(pos).pre.isEmpty() || fire.get(pos).pre.get(0).equals("None") || checkAllPre(fire.get(pos).pre, planned) || checkAllPre(fire.get(pos).pre, userCourses)) {
                            line.get(data).add(fire.get(pos).courseCode);
                            planned.clear();
                            break;

                        } else {
                            planned.addAll(line.get(data));
                            continue;
                        }
                    } else if (offered.contains("Fall") && data.contains("Fall")) {

                        //planned.addAll(line.get(data));
                        if (fire.get(pos).pre.isEmpty() || fire.get(pos).pre.get(0).equals("None") || checkAllPre(fire.get(pos).pre, planned) || checkAllPre(fire.get(pos).pre, userCourses)) {
                            line.get(data).add(fire.get(pos).courseCode);
                            planned.clear();
                            break;

                        } else {
                            planned.addAll(line.get(data));
                            continue;
                        }
                    }
                    // System.out.println(line.get(data));
                    planned.addAll(line.get(data));
//                    if(temp.size()>1)
//                    {
//                        break;
//                    }
                }

            ///////}
           // planned.addAll(ref.get(0));
            planned.clear();
            ref.remove(0);

        }
        return line;

    }

    public ArrayList<ArrayList<info>> singleTimeLine(ArrayList<ArrayList<info>> allPos,ArrayList<info> timeLine,
                                                     ArrayList<String>allPre,
                                                     String target,ArrayList<findAllC> fire){
        //ArrayList<info> timeLine = new ArrayList<info>();
        for(int i=0;i<allPre.size();i++)
        {
            System.out.println(allPre.get(i));
            System.out.println(allPre);
            int position = 0;
            position = find(fire, target);
            if(!allPre.get(i).equals(target)&&fire.get(position).pre.contains(allPre.get(i))) {
                int pos = 0;
                pos = find(fire, allPre.get(i));
                if (fire.get(pos).pre.isEmpty() || fire.get(pos).pre.get(0).equals("None")) {

                    timeLine.add(new info("undefined", -1, fire.get(pos).courseCode));
                    allPos.add(timeLine);
                    timeLine.clear();
                    continue;
                }
                for (int j = 0; j < fire.get(pos).pre.size(); j++) {
                    if (allPre.contains(fire.get(pos).pre.get(j)))
                    {
                        timeLine.add(new info("undefined", -1, fire.get(pos).courseCode));
                        allPos.addAll(singleTimeLine(allPos,timeLine,allPre,fire.get(pos).pre.get(j),fire));
                    }
                   // allPos.add(timeLine);
                }
            }
        }
        return allPos;
    }

    public ArrayList<String> rec(ArrayList<String> target, ArrayList<findAllC> fire){
        ArrayList<String> result = new ArrayList<String>();
        for(int i=0; i<target.size();i++){
            int a=0;
            //result.add(target.get(i));
            a = find(fire, target.get(i));
            if(!fire.get(a).pre.isEmpty()&&!fire.get(a).pre.get(0).equals("None"))
            {
                //result.add(fire.get(a).pre);
                for(int g=0;g<fire.get(a).pre.size();g++)
                {
                    if(!result.contains(fire.get(a).pre.get(g)))
                    {
                        result.add(fire.get(a).pre.get(g));
                    }
                }
                removeDup(result);
                result.addAll(rec(fire.get(a).pre, fire));
            }
            else
            {
                if(!result.contains(fire.get(a).courseCode))
                {

                    result.add(fire.get(a).courseCode);
                    removeDup(result);
                }

            }
        }
        removeDup(result);
        return result;
    }
    public int find(ArrayList<findAllC> fire, String target){
        for(int i=0;i<fire.size();i++){
            if(fire.get(i).courseCode.equals(target))
            {
                return i;
            }
        }
        return 0;
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
    public ArrayList<String> getSameTime(ArrayList<ArrayList<String>>empty,ArrayList<String> result,
                                                    int size,ArrayList<String>planned){
        ArrayList<String>ref = new ArrayList<String>();
        ref = result;
        int order = 0;
        ArrayList<String> sum = new ArrayList<String>();
        while(!ref.isEmpty())
        {

            for (int i = 0; i < result.size(); i++)
            {
                int pos = 0;
                pos = find(allC, result.get(i));
                if (allC.get(pos).pre.isEmpty() || allC.get(pos).pre.get(0).equals("None") ||checkAllPre(allC.get(pos).pre, planned))
                {
                    //if(!result.contains(allC.get(pos).courseCode))
                   // {
                        sum.add(order, allC.get(pos).courseCode);
                        order++;
                        planned.add(allC.get(pos).courseCode);
                        ref.remove(allC.get(pos).courseCode);
                    //}

                }
            }
            //empty.add(order,sum);
            //order++;
        }
        return sum;
    }
    public boolean checkAllPre(ArrayList<String>pre, ArrayList<String>has){
        for(int i=0;i<pre.size();i++)
        {
            if(!has.contains(pre.get(i)))
            {
                return false;
            }
        }
        return true;
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
