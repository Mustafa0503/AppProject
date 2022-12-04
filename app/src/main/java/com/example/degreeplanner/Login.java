package com.example.degreeplanner;

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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.Contract;

import java.util.Objects;

public class Login extends AppCompatActivity implements View.OnClickListener{

    //private Contract.Presenter presenter;
    TextView mCreateBtn, forgotTextLink;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    int n;
    Model model;

    public String get_email() {
        EditText email = (EditText) findViewById(R.id.Email);
        return email.getText().toString().trim();
        //return "-@gmail.com";
    }

    public String get_pass() {
        EditText pass = (EditText) findViewById(R.id.password);
        return pass.getText().toString().trim();
        //return "1234567";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email = (EditText) findViewById(R.id.Email);
        email.setOnClickListener(this);

        EditText pass = (EditText) findViewById(R.id.password);
        pass.setOnClickListener(this);

        Button mLoginBtn = (Button) findViewById(R.id.registerBtn);
        mLoginBtn.setOnClickListener(this);

        TextView reg = (TextView) findViewById(R.id.createText);
        reg.setOnClickListener(this);

        TextView forgotPass = (TextView) findViewById(R.id.forgotpassword);
        forgotPass.setOnClickListener(this);

        model = new Model();

        //presenter = new Presenter(new Model(), this);
    }



    public void onClick(View view) {
        EditText email = (EditText) findViewById(R.id.Email);
        EditText pass = (EditText) findViewById(R.id.password);

        String email_str = email.getText().toString().trim();
        String pass_str = pass.getText().toString().trim();

        switch (view.getId()) {
            case R.id.createText:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.forgotpassword:
                startActivity(new Intent(this, AlertDialog.Builder.class));
                break;

            case R.id.registerBtn:
                model.all_users(email_str, pass_str,new Model.UserCallBack() {
                @Override
                public void check_user(int exist) {
                    if(exist == 1){
                        startActivity(new Intent(Login.this, MainActivity2.class));
                        finish();
                    }
                    else{
                        startActivity(new Intent(Login.this, MainActivity.class));
                        finish();
                    }
                }
            });

        }
    }

//    && presenter.ruthere(email)==true


    public void OnSuccess(String successfully_logged_in) {
        Toast.makeText(this,successfully_logged_in, Toast.LENGTH_SHORT).show();

    }
    public void OnError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }





//
//    static EditText mEmail;
//    static EditText mPassword;
//    Button mLoginBtn;
//    TextView mCreateBtn, forgotTextLink;
//    ProgressBar progressBar;
//    FirebaseAuth fAuth;
//    FirebaseFirestore fStore;
//    Model model;
//
//    public String get_email(){
//        EditText email = (EditText) findViewById(R.id.Email);
//        return email.getText().toString().trim();
//    }
//    public String get_pass(){
//        EditText pass = (EditText) findViewById(R.id.password);
//        return pass.getText().toString().trim();
//    }
//    //@SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        mEmail = findViewById(R.id.Email);
//        mPassword = findViewById(R.id.password);
//        mLoginBtn = findViewById(R.id.registerBtn);
//        mCreateBtn = findViewById(R.id.createText);
//        fAuth = FirebaseAuth.getInstance();
//        progressBar = findViewById(R.id.progressBar2);
//        forgotTextLink = findViewById(R.id.forgotpassword);
//        model = new Model();
//
//        mLoginBtn.setOnClickListener(view -> {
//            String email = mEmail.getText().toString().trim();
//            String password = mPassword.getText().toString().trim();
//            if (TextUtils.isEmpty(email)) {
//                mEmail.setError("Email is required");
//                return;
//            }
//            if (TextUtils.isEmpty(password)) {
//                mPassword.setError("Password is Required");
//                return;
//            }
//            if (password.length() < 6) {
//                mPassword.setError("Password Must be >=6");
//            }
//            progressBar.setVisibility(View.VISIBLE);
//
//            model.all_users(new Model.UserCallBack() {
//                @Override
//                public void check_user(int exist) {
//                    if(exist == 1){
//                        startActivity(new Intent(getApplicationContext(), MainActivity2.class));
//                        finish();
//                    }
//                    else{
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                        finish();
//                    }
//                }
//            });
//            // authenticate
//
//
//
////            fAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
////                Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
////                if (FirebaseAuth.getInstance().getCurrentUser()!=null){
////                    DocumentReference df = FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
////                    df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
////                        @Override
////                        public void onSuccess(DocumentSnapshot documentSnapshot) {
////                            if(documentSnapshot.getString("isAdmin") != null){
////                                startActivity(new Intent(getApplicationContext(), MainActivity2.class));
////                                finish();
////                            }
////                            else if(documentSnapshot.getString("isStudent") != null){
////                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
////                                finish();
////                            }
////                            else{
////                                startActivity(new Intent(getApplicationContext(), Register.class));
////                                finish();
////                            }
////                        }
////                    }).addOnFailureListener(new OnFailureListener() {
////                        @Override
////                        public void onFailure(@NonNull Exception e) {
////                            FirebaseAuth.getInstance().signOut();
////                            startActivity(new Intent(getApplicationContext(), Login.class));
////                        }
////                    });
////                }
////                //checkUserAccessLevel(Objects.requireNonNull(authResult.getUser()).getUid());
////            }).addOnFailureListener(e -> {
////                Toast.makeText(Login.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
////                progressBar.setVisibility(View.GONE);
////            });
//
//        });
//
//
//        mCreateBtn.setOnClickListener(view -> {
//            startActivity(new Intent(getApplicationContext(), Register.class));
//            finish();
//        });
//
//        forgotTextLink.setOnClickListener(view -> {
//            EditText resetMail = new EditText(view.getContext());
//            AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
//            passwordResetDialog.setTitle("Reset Password?");
//            passwordResetDialog.setMessage("Enter your email id to receive the reset link");
//            passwordResetDialog.setView(resetMail);
//            passwordResetDialog.setPositiveButton("Yes", (dialogInterface, i) -> {
//                String mail = resetMail.getText().toString();
//                fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(unused -> Toast.makeText(Login.this, "Reset Link has been sent to your email", Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(Login.this, "Error ! No link has been sent" + e.getMessage(), Toast.LENGTH_SHORT).show());
//            });
//            passwordResetDialog.setNegativeButton("No", (dialogInterface, i) -> {
//
//            });
//            passwordResetDialog.create().show();
//        });
//    }

}

