package com.example.degreeplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Model extends AppCompatActivity{
    private FirebaseFirestore mDb = FirebaseFirestore.getInstance();
    ArrayList<String> admin_emails = new ArrayList<>();
    ArrayList<String> student_emails= new ArrayList<>();
    FirebaseAuth fAuth=FirebaseAuth.getInstance();
    Login l = new Login();
    static int num;
    String email;
    String pass;

    public interface UserCallBack {
        void check_user(int exist);
    }

    public void all_users(String email, String password, UserCallBack UserCallBack){
        System.out.println("gj0000000000ute");
        System.out.println(email);
        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(authResult -> {
            System.out.println("gjgjyfhyeteyteute");
            if(fAuth.getCurrentUser()!=null) {
                FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
                DocumentReference df = FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
                df.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
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

                    }
                });
            }

        });



//    public void check_field(String email, String password){
//        fAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
//            if (FirebaseAuth.getInstance().getCurrentUser()!=null){
//                DocumentReference df = FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
//                df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        if(documentSnapshot.getString("isAdmin") != null){
//                            startActivity(new Intent(getApplicationContext(), MainActivity2.class));
//                            finish();
//                        }
//                        else if(documentSnapshot.getString("isStudent") != null){
//                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                            finish();
//                        }
//                        else{
//                            startActivity(new Intent(getApplicationContext(), Register.class));
//                            finish();
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        FirebaseAuth.getInstance().signOut();
//                        startActivity(new Intent(getApplicationContext(), Login.class));
//                    }
//                });
//            }
//        });
//    }

    }

}


