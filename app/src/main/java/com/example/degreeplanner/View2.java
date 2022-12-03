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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class View2 extends AppCompatActivity implements View.OnClickListener, Contract.View2 {


    private Contract.Presenter presenter;
    public Button mLoginBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    private EditText email;
    private EditText pass;

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

        email = (EditText) findViewById(R.id.Email);
        email.setOnClickListener(this);

        pass = (EditText) findViewById(R.id.password);
        pass.setOnClickListener(this);

        mLoginBtn = (Button) findViewById(R.id.registerBtn);
        mLoginBtn.setOnClickListener(this);

        TextView reg = (TextView) findViewById(R.id.createText);
        reg.setOnClickListener(this);

        TextView forgotPass = (TextView) findViewById(R.id.forgotpassword);
        forgotPass.setOnClickListener(this);

        presenter = new Presenter(new Model(), this);

    }
    public void OnSuccess ( String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
    public void OnError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {
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
                    int num = presenter.login(email_str,pass_str);
                    if (num==1) {
                        OnSuccess ("Successfully logged in");
                        startActivity(new Intent(this, MainActivity2.class));
                        finish();
                    } else if (num==2) {
                        OnSuccess ("Successfully logged in");
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    }
                    else {
                        startActivity(new Intent(this, Register.class));
                        finish();
                    }
                }
                break;


        }

    }
}

