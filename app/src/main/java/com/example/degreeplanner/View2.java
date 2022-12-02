package com.example.degreeplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class View2 extends AppCompatActivity implements View.OnClickListener, Contract.View {
    private Contract.Presenter presenter;
    public Button mLoginBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    private EditText email;
    private EditText pass;

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

    @Override
    public void onClick(View view) {
        String email_str = email.getText().toString().trim();
        String pass_str = pass.getText().toString().trim();

        switch(view.getId()){
            case R.id.createText:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.forgotpassword:
                startActivity(new Intent(this, AlertDialog.class));
                break;
            case R.id.registerBtn:
                int num = presenter.login(email_str, pass_str);
                if(num ==1){
                    startActivity(new Intent(this, MainActivity2.class));
                    finish();
                }
                else if(num == 0) {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
                else{
                startActivity(new Intent(this, Register.class));
                finish();
            } break;


        }

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



//    public void progressB(){
//        ProgressBar proBar = findViewById(R.id.progressBar2);
//    }

    public void error_msg(View2 view){
        presenter.error();
    }


}
