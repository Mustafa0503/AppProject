package com.example.degreeplanner;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

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

import java.util.ArrayList;
public class Model extends AppCompatActivity implements Contract.Model {
    FirebaseAuth fAuth=FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    //static boolean num;
    Presenter presenter;

    public int login_btn(String email, String password) {
        System.out.println("ge");

        //fAuth.getCurrentUser();
        System.out.println("gj0000000000ute");
        fAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
            System.out.println("gjgjyfhyeteyteute");
            if(fAuth.getCurrentUser()!=null) {
                FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
                DocumentReference df = FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
                df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot document) {
//                               if (task.isSuccessful()) {
//                                   DocumentSnapshot document = task.getResult();
                        if (document != null) {
                            if (document.getString("isAdmin") != null) {
                                //num=true;
                                Presenter.num = 1;

//                                    startActivity(new Intent(Model.this, MainActivity2.class));
//                                    finish();
                                System.out.println("num=1" + Presenter.num);
                            } else if (document.getString("isStudent") != null) {
                                //num = false;
                                Presenter.num = 2;
                                System.out.println("num=2" + Presenter.num);

                            }
                            else{
                                Presenter.num = -1;
                            }


                        }
                    }
                });

            }


        });
        //FirebaseAuth.getInstance().signOut();

        //return num;
        if ( Presenter.num==1)
        {
            return 1;
        }
        else if ( Presenter.num==2)
        {
            return 2;
        }
        else{
            System.out.println("num= -1    " + Presenter.num);
            return -1;
        }


    }
}
