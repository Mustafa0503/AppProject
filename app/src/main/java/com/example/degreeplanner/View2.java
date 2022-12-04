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

//        EditText email = (EditText) findViewById(R.id.Email);
//        email.setOnClickListener(this);
//
//        EditText pass = (EditText) findViewById(R.id.password);
//        pass.setOnClickListener(this);

        Button mLoginBtn = (Button) findViewById(R.id.registerBtn);
        mLoginBtn.setOnClickListener(this);

        TextView reg = (TextView) findViewById(R.id.createText);
        reg.setOnClickListener(this);


        presenter = new Presenter(new Model(), this);
    }



    public void onClick(View view) {
        EditText email = (EditText) findViewById(R.id.Email);
        EditText pass = (EditText) findViewById(R.id.password);

        String email_str = get_email();
        String pass_str = get_pass();

        switch (view.getId()) {
            case R.id.createText:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.registerBtn:

                if(presenter.error_msg(email_str,pass_str)==0) {
                    if ((presenter.login(email_str,pass_str)== 1) && presenter.isAdm(email_str)==1){
                        //progressBar.setVisibility(View.VISIBLE);
                        OnSuccess("Successfully logged in");
                        startActivity(new Intent(this, MainActivity2.class));
                        finish();
                        break;

                    }
                    if (presenter.login(email_str,pass_str)==2 && presenter.isAdm(email_str)==0) {
                        OnSuccess("Successfully logged in");
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                        break;
                    }
//                    if(presenter.error_toast(email_str,pass_str)==-1){
//                        presenter.error_toast(email_str,pass_str);
//
//                        break;
//                    }
//                    else {
//                        Toast.makeText(this, "shutup", Toast.LENGTH_SHORT).show();
//                        OnError("Failed to log in");
//                        startActivity(new Intent(this, View2.class));
//                        finish();
//                        break;
//                    }
                }
                else if(presenter.error_msg(email_str,pass_str)==1){
                    presenter.error_msg(email_str,pass_str);
                    break;
                }

              break;
        }
    }

//    && presenter.ruthere(email)==true


    public void OnSuccess(String successfully_logged_in) {
        Toast.makeText(this,successfully_logged_in, Toast.LENGTH_SHORT).show();

    }
    public void OnError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

}