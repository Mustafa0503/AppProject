package com.example.degreeplanner;

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
        void check_user(int exist);
    }

    public void all_users(String email, String password, UserCallBack UserCallBack) {
        System.out.println("gj0000000000ute");
        System.out.println(email);
        fAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
            System.out.println("gjgjyfhyeteyteute");
            if (fAuth.getCurrentUser() != null) {
//                FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
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
                });


            }

        }).addOnFailureListener(e -> {
            System.out.println("shutup");
            Presenter.num = -1;
            System.out.println("presenter num = " + Presenter.num);

//                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        });

    }
}
