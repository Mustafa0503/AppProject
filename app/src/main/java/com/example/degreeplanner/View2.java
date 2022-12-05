package com.example.degreeplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
                presenter.login(email_str, pass_str);

                if(presenter.error_msg(email_str,pass_str)==0) {
                    if ((Presenter.num== 1)){
                        //progressBar.setVisibility(View.VISIBLE);
                        OnSuccess();
                        startActivity(new Intent(this, MainActivity2.class));
                        finish();
                        break;

                    }
                    if (Presenter.num==2 && presenter.isAdm(email_str)==0) {
                        OnSuccess();
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

    public void OnError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    public void OnSuccess() {
        Toast.makeText(this,"Successfully Logged in", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity2.class));
        finish();

    }

    public void OnSuccessStu() {
        Toast.makeText(this,"Successfully Logged in", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }

    public void displayAdm(String email_str, String pass_str){
        OnSuccess();
        startActivity(new Intent(this, MainActivity2.class));
        finish();
    }

}