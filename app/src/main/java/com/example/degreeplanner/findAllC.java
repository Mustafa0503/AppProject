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
                    target.add("CSCB09");
                    //target.add("CSCB07");
//                    target.add("CSCB31");
                  // target.add("CSCA37");
//                    target.add("CSCB07");
//                    target.add("CSCA22");
//                    target.add("CSCA08");
//                    target.add("CSCA48");
                    ArrayList<String> result = rec(target,allC);
                    for(int i=0;i<target.size();i++)
                    {
                        result.add(target.get(i));
                    }
                    removeDup(result);
                    System.out.println(result);
                    //remove from taken courses list as well
                    ArrayList<ArrayList<String>> empty = new ArrayList<ArrayList<String>>();
                    //System.out.println(result);
                    ArrayList<ArrayList<info>> allCases = new ArrayList<ArrayList<info>>();
                    ArrayList<ArrayList<info>> display = new ArrayList<ArrayList<info>>();
                    ArrayList<info> timeLine = new ArrayList<info>();
                    //timeLine.add(new info("F", 21, "CSCA08"));
                    //System.out.println(timeLine.get(0).offerSession);
                    for(int i=0;i<target.size();i++)
                    {
                        display = singleTimeLine(allCases, timeLine, result,target.get(i),allC);
                    }
                    System.out.println(display.size());

                    for(int i=0;i<display.size();i++)
                    {
                        System.out.println("**********************");
                        for(int j=0;j<display.get(i).size();j++) {
                            System.out.println(display.get(i).get(j).courseCode);
                        }
                        System.out.println("**********************");
                        System.out.println("\n");
                    }





//                    ArrayList<ArrayList<String>> empty = new ArrayList<ArrayList<String>>();
//                    for(int i=0;i<result.size();i++){
//                        int pos = 0;
//                        pos = find(allC, result.get(i));
//                        if(allC.get(pos).pre.isEmpty()||allC.get(pos).pre.get(0).equals("None"))
//                        {
//                            //also check if its taken already by the student
//                            ArrayList<String> sum = new ArrayList<String>();
//                            ArrayList<String> fal = new ArrayList<String>();
//                            ArrayList<String> win = new ArrayList<String>();
//                            if(allC.get(pos).offerSession.contains("Fall"))
//                            {
//
//                                fal.add(allC.get(pos).courseCode);
//                               // empty.add(sum);
//                            }
//                            else if(allC.get(pos).offerSession.contains("Winter"))
//                            {
//
//                                win.add(allC.get(pos).courseCode);
//                                //empty.add(fal);
//                            }
//                            else
//                            {
//
//                                sum.add(allC.get(pos).courseCode);
//                                //empty.add(win);
//                            }
//                            int pp=0;
//                            if(!fal.isEmpty()) {
//
//                                empty.add(pp,fal);
//                                pp++;
//
//                            }
//                            if(!win.isEmpty()) {
//                                empty.add(pp,win);
//                                pp++;
//                            }
//                            if(!sum.isEmpty()){
//                                empty.add(pp,sum);
//                                pp++;
//
//                            }
//                            pp=0;
//                        }
//                    }




















                }
            }
        });

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
                    timeLine.clear();
                    continue;
                }
                for (int j = 0; j < fire.get(pos).pre.size(); j++) {
                    if (allPre.contains(fire.get(pos).pre.get(j)))
                    {
                        timeLine.add(new info("undefined", -1, fire.get(pos).courseCode));
                        allPos.addAll(singleTimeLine(allPos,timeLine,allPre,fire.get(pos).pre.get(j),fire));
                    }
                    allPos.add(timeLine);
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

//        for(int i=0;i<result.size();i++)
//        {
//            for(int j=i+1;j<result.size();j++)
//            {
//                if(result.get(i).equals(result.get(j)))
//                {
//                    result.remove(result.get(j));
//                }
//            }

  //      }
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
    public ArrayList<ArrayList<String>> getTimeLine(ArrayList<String> result){
        ArrayList<ArrayList<String>> empty = new ArrayList<ArrayList<String>>();
        for(int i=0;i<result.size();i++){
            int pos = 0;
            pos = find(allC, result.get(i));
            if(allC.get(pos).pre.isEmpty()||allC.get(pos).pre.get(0).equals("None"))
            {
                //also check if its taken already by the student
                ArrayList<String> sum = new ArrayList<String>();
                ArrayList<String> fal = new ArrayList<String>();
                ArrayList<String> win = new ArrayList<String>();
                if(allC.get(pos).offerSession.contains("Fall"))
                {

                    fal.add(allC.get(pos).courseCode);
                    // empty.add(sum);
                }
                else if(allC.get(pos).offerSession.contains("Winter"))
                {

                    win.add(allC.get(pos).courseCode);
                    //empty.add(fal);
                }
                else
                {

                    sum.add(allC.get(pos).courseCode);
                    //empty.add(win);
                }
                int pp=0;
                if(!fal.isEmpty()) {

                    empty.add(pp,fal);
                    pp++;

                }
                if(!win.isEmpty()) {
                    empty.add(pp,win);
                    pp++;
                }
                if(!sum.isEmpty()){
                    empty.add(pp,sum);
                    pp++;

                }
                pp=0;
            }
        }
        return empty;
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
