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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button mLoginBtn = (Button)findViewById(R.id.registerBtn);
        presenter = new Presenter(new Model(), this);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.login();
            }
        });

    }

    public void PrepWell(){
        TextView textView = findViewById(R.id.textView);
    }
    public void Login(){
        TextView textView = findViewById(R.id.textView2);
    }
    public String get_email(){
        EditText email = findViewById(R.id.Email);
        return email.getText().toString();
    }
    public String get_pass(){
        EditText pass = findViewById(R.id.password);
        return pass.getText().toString();
    }
//    public void display(){
//       Toast.makeText(View.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
//    }




    public void regBtn(View2 view){
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
