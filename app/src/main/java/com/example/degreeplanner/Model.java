package com.example.degreeplanner;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

public class Model extends AppCompatActivity implements Contract.Model{

    EditText mEmail, mPassword;
    Button mLoginBtn;
    TextView mCreateBtn, forgotTextLink;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    ArrayList<String> email_id;
    String[] EmailArray;





//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        FirebaseFirestore.getInstance().collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    email_id = new ArrayList<>();
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        String em = document.getString("email");
//                        email_id.add(em);
//                    }
//                    EmailArray = new String[email_id.size()];
//                    EmailArray = email_id.toArray(EmailArray);
//                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
//                }
//            }
//        });
//        login_btn();
//    }

    public boolean ru_there(String email){
        for(int i=0; i<email_id.size(); i++)
        {
            if(email_id.get(i)!=email)
            {
                return false;
            }
        }
        return true;
    }


        public void login_btn () {
            EditText mEmail = findViewById(R.id.Email);
            EditText mPassword = findViewById(R.id.password);
            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();
            fAuth= FirebaseAuth.getInstance();
            fAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
//                Toast.makeText(Model.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                if (fAuth.getCurrentUser() != null) {
                    DocumentReference df = FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.getString("isAdmin") != null) {
                                   // num = 1;
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            } else if (documentSnapshot.getString("isStudent") != null) {
                                   // num = 0;

                                finish();startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            } else {
                                   // num = -1;
                                startActivity(new Intent(getApplicationContext(), Register.class));
                                finish();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(getApplicationContext(), View2.class));
                        }
                    });
                }
            }
            );
        }

        public void forgott(){
        forgotTextLink.setOnClickListener(view -> {
            EditText resetMail = new EditText(view.getContext());
            AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
            passwordResetDialog.setTitle("Reset Password?");
            passwordResetDialog.setMessage("Enter your email id to receive the reset link");
            passwordResetDialog.setView(resetMail);
            passwordResetDialog.setPositiveButton("Yes", (dialogInterface, i) -> {
                String mail = resetMail.getText().toString();
                fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(unused -> Toast.makeText(Model.this, "Reset Link has been sent to your email", Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(Model.this, "Error ! No link has been sent" + e.getMessage(), Toast.LENGTH_SHORT).show());
            });
            passwordResetDialog.setNegativeButton("No", (dialogInterface, i) -> {

            });
            passwordResetDialog.create().show();
        });

        }

    @Override
    public void login_btn(String email, String pass) {

    }


}



