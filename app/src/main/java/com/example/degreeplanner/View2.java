package com.example.degreeplanner;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.FirebaseFirestore;

public class View2 extends AppCompatActivity implements View.OnClickListener, Contract.View2 {

    private Contract.Presenter presenter;
    //    EditText email, mPassword;
//    Button mLoginBtn;
    TextView mCreateBtn, forgotTextLink;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    int n;

    public String get_email() {
        EditText email = (EditText) findViewById(R.id.Email);
        return email.getText().toString().trim();
    }

    public String get_pass() {
        EditText pass = (EditText) findViewById(R.id.password);
        return pass.getText().toString().trim();
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

        presenter = new Presenter(new Model(), this);
    }
    public int isAdm(String email){
        if(email.contains("admin") ){
            return 1;
        }
        else if (email.contains("student") ){
            return 0;
        }
        return -1;
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
                if (TextUtils.isEmpty(email_str)) {
                    OnError("Email Required");
                }
                else if (TextUtils.isEmpty(pass_str)) {
                    OnError("Password Required");
                }
                else if (pass_str.length() < 6) {
                    OnError("Password needs to be at least six characters");
                }
                else {

//                    int num = presenter.log(email_str);

                    if (isAdm(email_str)==1) {
                        OnSuccess ("Successfully logged in");
                        startActivity(new Intent(this, MainActivity2.class));
                        finish();
                    } else if  (isAdm(email_str)==0){
                        OnSuccess ("Successfully logged in");
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    } else {
                        OnSuccess ("Successfully logged in");
                        startActivity(new Intent(this, Register.class));
                        finish();
                    }
                }
                break;
        }
    }
//    && presenter.ruthere(email)==true


    private void OnSuccess(String successfully_logged_in) {
        Toast.makeText(this,"Successfully Logged In", Toast.LENGTH_SHORT).show();

    }

    private void OnError(String email_required) {
        Toast.makeText(this, email_required, Toast.LENGTH_SHORT).show();

    }
}
















//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        mEmail = (EditText) findViewById(R.id.Email);
//        mPassword =(EditText)  findViewById(R.id.password);
//        mLoginBtn = (Button) findViewById(R.id.registerBtn);
//        mCreateBtn =(TextView) findViewById(R.id.createText);
//
//        progressBar = findViewById(R.id.progressBar2);
//        forgotTextLink = (TextView) findViewById(R.id.forgotpassword);
////        presenter = new Presenter(new Model(), this);
//
//
//
//
//        mLoginBtn.setOnClickListener(view -> {
//            String email = mEmail.getText().toString().trim();
//            String password = mPassword.getText().toString().trim();
//
//                if (presenter.Usertfield2(email, password).contains(1)) {
//                    startActivity(new Intent(this, MainActivity2.class));
//                    finish();
//
//                } else if (presenter.Usertfield2(email, password).contains(0)) {
//                    startActivity(new Intent(this, MainActivity.class));
//                    finish();
//
//                } else if (presenter.Usertfield2(email, password).contains(-1)) {
//                    startActivity(new Intent(this, Register.class));
//                    finish();
//                } else {
//                    startActivity(new Intent(this, View2.class));
//                    finish();
//                }
//
//
//
//        });
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
////                fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(unused -> Toast.makeText(View2.this, "Reset Link has been sent to your email", Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(View2.this, "Error ! No link has been sent" + e.getMessage(), Toast.LENGTH_SHORT).show());
//            });
//            passwordResetDialog.setNegativeButton("No", (dialogInterface, i) -> {
//
//            });
//            passwordResetDialog.create().show();
//        });
//    }




//
//
//
//
//    public void PrepWell(){
//        TextView textView = findViewById(R.id.textView);
//    }
//    public void Login(){
//        TextView textView = findViewById(R.id.textView2);
//    }
//
//
////    public void display(){
////       Toast.makeText(View.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
////    }
//
//
//
////    public void progressB(){
////        ProgressBar proBar = findViewById(R.id.progressBar2);
////    }
//
//    public void error_msg(View2 view){
//        presenter.error();
//    }
//
//
//}
