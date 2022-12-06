package com.example.degreeplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Model implements Contract.Model{

    ArrayList<String> admin_emails = new ArrayList<>();
    ArrayList<String> student_emails = new ArrayList<>();
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    UserCallBack userCallBack = null;
    //    Login l = new Login();
    String email;
    String pass;


    public interface UserCallBack{
        int check_user(int exist);
    }

//    public int all_users(String email, String password, UserCallBack UserCallBack) {
//        System.out.println("gj0000000000ute");
//        System.out.println(email);
//        fAuth = FirebaseAuth.getInstance();
//        fAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
//            System.out.println("gjgjyfhyeteyteute");
//            if (fAuth.getCurrentUser() != null) {
//                DocumentReference df = FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
//                df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot document) {
//                        if (document != null) {
//                            if (document.getString("isAdmin") != null) {
//                                 UserCallBack.check_user(1);
//                            }
//                            if (document.getString("isStudent") != null) {
//                                 UserCallBack.check_user(2);
//
//                            }
//
//                        }
//                        System.out.println("hellooooo");
//
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        FirebaseAuth.getInstance().signOut();
//                    }
//                });
//
//
//            }
//
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                UserCallBack.check_user(0);
//            }
//        });
//
//        return 0;
//
//    }


    public int login_btn(String email, String password) {
        System.out.println("ge");
        //fAuth.getCurrentUser();
        System.out.println("gj0000000000ute");
        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(authResult -> {
            System.out.println("gjgjyfhyeteyteute");
            if(fAuth.getCurrentUser()!=null) {
                //FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
                DocumentReference df = FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
                df.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document != null) {
                                if (document.getString("isAdmin") != null) {
                                    Presenter.num = 1;
//                                    userCallBack.check_user(1);
                                    System.out.println("num=1" + Presenter.num);
                                } else if (document.getString("isStudent") != null) {
                                    Presenter.num = 2;
//                                    userCallBack.check_user(2);
                                    System.out.println("num=2" + Presenter.num);

                                }

                            }
                        }

                    }
                });
            }

        });

        //return num;
        if ( Presenter.num==1)
        {
            return 1;
        }
        if ( Presenter.num==2)
        {
            return 2;
        }
        else
        {
            return 0;
        }

    }
}



