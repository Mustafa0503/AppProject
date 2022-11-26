package com.example.degreeplanner;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText mFullname, mEmail, mPassword, mstudentnumber;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    String userID;
    FirebaseFirestore fstore;
    CheckBox isAdminBox, isStudentBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullname = findViewById(R.id.FullName);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        mstudentnumber = findViewById(R.id.StudentNumber);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mLoginBtn = findViewById(R.id.createText);
        isAdminBox = findViewById(R.id.admincheckbox);
        isStudentBox = findViewById(R.id.studentcheckbox);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);

        // check checkboxes :)
        isStudentBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (compoundButton.isChecked()) {
                isAdminBox.setChecked(false);
            }
        });

        isAdminBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (compoundButton.isChecked()) {
                isStudentBox.setChecked(false);
            }
        });

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        mRegisterBtn.setOnClickListener(v -> {
            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();
            String fullName = mFullname.getText().toString();
            ArrayList courses = new ArrayList<String>();

            // checkbox validation
            if (!(isAdminBox.isChecked() || isStudentBox.isChecked())) {
                Toast.makeText(Register.this, "Select the Account Type", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(email)) {
                mEmail.setError("Email is required");
                return;
            }
            if (TextUtils.isEmpty(password)) {
                mPassword.setError("Password is Required");
                return;
            }
            if (password.length() < 6) {
                mPassword.setError("Password must be more than 6");
            }

            progressBar.setVisibility(View.VISIBLE);

            fAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
                Toast.makeText(Register.this, "Account Created", Toast.LENGTH_SHORT).show();
                userID = fAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fstore.collection("users").document(userID);
                Map<String, Object> user = new HashMap<>();
                user.put("fName", fullName);
                user.put("email", email);
                user.put("courses", courses);
                // specify admin
                if (isAdminBox.isChecked()) {
                    user.put("isAdmin", "0");
                }
                if (isStudentBox.isChecked()) {
                    user.put("isStudent", "1");
                }
                documentReference.set(user);
                documentReference.set(user).addOnSuccessListener(unused -> Log.d("TAG", "onSuccess: user profile is created for " + userID));

                if (isAdminBox.isChecked()) {
                    startActivity(new Intent(getApplicationContext(), MainActivity2.class));
                    finish();
                }

                if (isStudentBox.isChecked()) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }).addOnFailureListener(e -> {
                Toast.makeText(Register.this, "Error!" + e.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            });
        });
        mLoginBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), Login.class)));
    }
}