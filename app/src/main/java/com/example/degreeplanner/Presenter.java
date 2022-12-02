package com.example.degreeplanner;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;



public class Presenter extends AppCompatActivity implements Contract.Presenter{
    private Contract.Model model;
    public Contract.View view;
    FirebaseAuth fAuth;


    public Presenter(Contract.Model model, Contract.View view) {
        this.model = model;
        this.view = view;
    }

    public void error() {
        EditText mEmail = findViewById(R.id.Email);
        EditText mPassword = findViewById(R.id.password);
        String email= view.get_email();
        String pass = view.get_pass();
        if (email == "") {
            mEmail.setError("Email is required");
        }
        if (pass == "") {
            mPassword.setError("Password is Required");
        }
        if (pass.length() < 6) {
            mPassword.setError("Password Must be >=6");
        }
        if (model.ru_there(email) == false){
            mEmail.setError("There is no account with this email");
        }

    }

    public void forgot(){
        model.forgott();
    }

    public void login(String email, String pass) {
        model.login_btn(email, pass);

    }



}
