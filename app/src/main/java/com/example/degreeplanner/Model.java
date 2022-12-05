package com.example.degreeplanner;

import android.content.Intent;
import android.widget.Toast;

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

public class Model extends AppCompatActivity implements Contract.Model {
    private FirebaseFirestore mDb = FirebaseFirestore.getInstance();
    private Contract.Presenter presenter;

    ArrayList<String> admin_emails = new ArrayList<>();
    ArrayList<String> student_emails = new ArrayList<>();
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    //    Login l = new Login();
    String email;
    String pass;

    public interface UserCallBack {
        int check_user(int exist);
        boolean isUser(boolean u);
    }


    public void all_users(String email, String password, UserCallBack UserCallBack) {
        System.out.println("gj0000000000ute");
        System.out.println(email);
        fAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
            System.out.println("gjgjyfhyeteyteute");
            if (fAuth.getCurrentUser() != null) {
                DocumentReference df = FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
                df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot document) {
                        if (document != null) {
                            if (document.getString("isAdmin") != null) {
                                UserCallBack.check_user(1);
                            }
                            if (document.getString("isStudent") != null) {
                                UserCallBack.check_user(2);

                            }

                        }
                        System.out.println("hellooooo");

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        FirebaseAuth.getInstance().signOut();
                        }
                    });


            }
            UserCallBack.isUser(true);

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                UserCallBack.check_user(0);
            }
        });

    }

//    public void all_us(String email_str, String pass_str){
//        all_users(email_str, pass_str,new Model.UserCallBack() {
//            @Override
//            public int check_user(int exist) {
//                if (exist == 1){
//                    return 1;
//                }
//                if(exist == 2){
//                    return 2;
//                }
//                if(exist== 0){
//                    return 0;
//                }
//                return -1;
//            }
//
//            @Override
//            public boolean isUser(boolean u) {
//                if(u){
//                    return true;
//                }
//                return false;
//            }
//
//        });
//    }
}
