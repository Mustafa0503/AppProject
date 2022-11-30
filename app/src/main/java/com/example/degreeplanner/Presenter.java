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
    EditText mEmail = findViewById(R.id.Email);
    EditText mPassword = findViewById(R.id.password);
    Button mLoginBtn = findViewById(R.id.registerBtn);
    String email = mEmail.getText().toString().trim();
    String password = mPassword.getText().toString().trim();

    public Presenter(Contract.Model model, Contract.View view) {
        this.model = model;
        this.view = view;
    }

    public void login_btn(){
        fAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
            Toast.makeText(Presenter.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
            if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                DocumentReference df = FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
                df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.getString("isAdmin") != null){
                            startActivity(new Intent(getApplicationContext(), MainActivity2.class));
                            finish();
                        }
                        else if(documentSnapshot.getString("isStudent") != null){
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                        else{
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
        });
    }

    public void error() {
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

}
