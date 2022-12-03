package com.example.degreeplanner;

import android.content.Intent;
import android.util.Log;

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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class Model extends AppCompatActivity implements Contract.Model {
    FirebaseAuth fAuth;
    //static boolean num;
    Presenter presenter;



    public int login_btn(String email, String password) {

        System.out.println("ge");
        fAuth = FirebaseAuth.getInstance();
        //fAuth.getCurrentUser();
        System.out.println("gj0000000000ute");
        fAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
            if (fAuth.getCurrentUser() != null) {
                System.out.println("gjgjyfhyeteyteute");
                FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
                DocumentReference df = FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
                df.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document != null) {
                                if (document.getString("isAdmin") != null) {
                                    //num=true;
                                    Presenter.num =1;
//                                    startActivity(new Intent(Model.this, MainActivity2.class));
//                                    finish();
                                    System.out.println("num=1" + Presenter.num);
                                } else if (document.getString("isStudent") != null) {
                                    //num = false;
                                    Presenter.num = 2;
                                    System.out.println("num=2"+ Presenter.num);

                                }

                            }
                        }
                        System.out.println("num=1           " + Presenter.num);

                    }
                });

            }
        });
        System.out.println("num=1 " + Presenter.num);

        //return num;
//        if(a==1)
//            return 1;
//        if(a==2)
//            return 2;
//         else
//             return 0;
        return Presenter.num;
    }
}
















