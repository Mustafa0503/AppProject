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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.FirebaseFirestore;

public class View2 extends AppCompatActivity implements View.OnClickListener, Contract.View {
    private Contract.Presenter presenter;
    public Button Loginbtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    EditText email;
    EditText pass;
    TextView Register;
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
        Loginbtn= (Button) findViewById(R.id.registerBtn);
        TextView reg = (TextView) findViewById(R.id.createText);
        TextView forgotPass = (TextView) findViewById(R.id.forgotpassword);
        Loginbtn.setOnClickListener(this);
        pass.setOnClickListener(this);
        presenter = new Presenter(new Model(), this);


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

//presenter.login(email.getText().toString().trim(), pass.getText().toString().trim());

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registerBtn:
                //startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.forgotpassword:
                startActivity(new Intent(this, AlertDialog.class));
                break;

            case R.id.createText:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }
}
