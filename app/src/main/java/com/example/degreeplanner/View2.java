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

public class View2 extends AppCompatActivity implements Contract.View {
    private Contract.Presenter presenter;
    private EditText email, pass;
    private Button mLoginBtn;

    public Button btn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    public void loginBtn(View2 view) {
        presenter.login_btn();
        presenter.error();


    }
//        public void error_msg (View2 view){
//            presenter.error();
//        }


    public void PrepWell() {
        TextView textView = findViewById(R.id.textView);
    }

    public void Login() {
        TextView textView = findViewById(R.id.textView2);
    }

    public String get_email() {
        return email.getText().toString();
    }

    public String get_pass() {
        return pass.getText().toString();
    }
//    public void display(){
//       Toast.makeText(View.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
//    }

    public void regBtn(View2 view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        finish();
    }


    public void forgotPass(View2 view) {
        presenter.forgotlink();
        TextView new_pass = findViewById(R.id.forgotpassword);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText email = (EditText) findViewById(R.id.Email);
        EditText pass = (EditText) findViewById(R.id.password);
        TextView new_pass = findViewById(R.id.forgotpassword);
        Button mLoginBtn = findViewById(R.id.registerBtn);
        //forgotTextLink = findViewById(R.id.forgotpassword);
        presenter = new Presenter(new Model(), this);
        new_pass.setOnClickListener(view -> {
            EditText resetMail = new EditText(view.getContext());
            AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
            passwordResetDialog.setTitle("Reset Password?");
            passwordResetDialog.setMessage("Enter your email id to receive the reset link");
            passwordResetDialog.setView(resetMail);
            passwordResetDialog.setPositiveButton("Yes", (dialogInterface, i) -> {
                String mail = resetMail.getText().toString();
                fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(unused -> Toast.makeText(View2.this, "Reset Link has been sent to your email", Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(View2.this, "Error ! No link has been sent" + e.getMessage(), Toast.LENGTH_SHORT).show());
            });
            passwordResetDialog.setNegativeButton("No", (dialogInterface, i) -> {

            });
            passwordResetDialog.create().show();
        });
    }

}







   /*public void progressB(){
       ProgressBar proBar = findViewById(R.id.progressBar2);
   }*/




