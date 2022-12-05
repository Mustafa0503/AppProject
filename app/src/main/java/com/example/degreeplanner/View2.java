package com.example.degreeplanner;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

import java.util.Objects;

public class View2 extends AppCompatActivity implements com.example.degreeplanner.Contract.View2,View.OnClickListener{

    private Contract.Presenter presenter;
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

//        EditText email = (EditText) findViewById(R.id.Email);
//        email.setOnClickListener(this);
//
//        EditText pass = (EditText) findViewById(R.id.password);
//        pass.setOnClickListener(this);

        Button mLoginBtn = (Button) findViewById(R.id.registerBtn);
        mLoginBtn.setOnClickListener(this);

        TextView reg = (TextView) findViewById(R.id.createText);
        reg.setOnClickListener(this);


        presenter = new Presenter(new Model(),this);
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

            case R.id.registerBtn:
                if (error_msg(email_str, pass_str) == 0) {
                    presenter.all_u(email_str, pass_str);
                } else {
                    error_msg(email_str, pass_str);
                }
        }
    }



    public void checkkk(){

             Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(View2.this, MainActivity2.class));
//            finish();

    }
    public void checkkk22() {
            startActivity(new Intent(View2.this, MainActivity.class));
            finish();
        }
    public int error_msg(String email_str, String pass_str) {
        if (TextUtils.isEmpty(email_str)) {
            OnError("Email Required");
            return 1;
        } else if (TextUtils.isEmpty(pass_str)) {
            OnError("Password Required");
            return 1;

        } else if (pass_str.length() < 6) {
            OnError("Password needs to be at least six characters");
            return 1;
        } else if (!email_str.contains("@gmail.com")) {
            OnError("Invalid Email");
            return 1;
        }
        return 0;
    }
    public void toast_msg() {
////
////        final Toast text=Toast.makeText(this, "Invalid Login", Toast.LENGTH_SHORT).show();
    }
    public void OnError(String message) {
        final Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 1000);

    }




}

