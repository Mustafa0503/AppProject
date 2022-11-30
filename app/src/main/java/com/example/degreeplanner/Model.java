package com.example.degreeplanner;

import static android.content.ContentValues.TAG;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        FirebaseFirestore.getInstance().collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    email_id = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String em = document.getString("email");
                        email_id.add(em);
                    }
                    EmailArray=new String[email_id.size()];
                    EmailArray = email_id.toArray(EmailArray);
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
        }

    }
