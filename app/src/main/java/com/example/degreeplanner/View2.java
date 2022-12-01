package com.example.degreeplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.FirebaseFirestore;

public class View2 extends AppCompatActivity implements Contract.View {
    private Contract.Presenter presenter;
    public Button btn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    EditText email;
    EditText pass;
    Model ob = new Model();
    public String get_email(){
        EditText email = (EditText) findViewById(R.id.Email);
        return email.getText().toString().trim();
    }
    public String get_pass(){
        EditText pass = (EditText) findViewById(R.id.password);
        return pass.getText().toString().trim();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.Email);
        pass = (EditText) findViewById(R.id.password);
        Button mLoginBtn = (Button)findViewById(R.id.registerBtn);
        TextView reg = (TextView) findViewById(R.id.createText);
        TextView forgotPass = (TextView) findViewById(R.id.forgotpassword);
        presenter = new Presenter(new Model(), this);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), MainActivity2.class));
//                finish();
                presenter.login(email.getText().toString().trim(), pass.getText().toString().trim());
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regBtn();

            }
        });
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.forgot();
            }
        });



    }

    public void PrepWell(){
        TextView textView = findViewById(R.id.textView);
    }
    public void Login(){
        TextView textView = findViewById(R.id.textView2);
    }


//    public void display(){
//       Toast.makeText(View.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
//    }




    public void regBtn(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        finish();
    }


    public void forgotPass(View2 view){
        TextView new_pass = findViewById(R.id.forgotpassword);
    }

//    public void progressB(){
//        ProgressBar proBar = findViewById(R.id.progressBar2);
//    }

    public void error_msg(View2 view){
        presenter.error();
    }


}
