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

public class Login extends AppCompatActivity implements View.OnClickListener, Contract.Login {

    private Contract.Presenter presenter;
    TextView mCreateBtn, forgotTextLink;
    ProgressBar progressBar;
    EditText email, pass;
    Button mLoginBtn;




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

        progressBar = findViewById(R.id.progressBar2);

        TextView reg = (TextView) findViewById(R.id.createText);
        reg.setOnClickListener(this);

        presenter = new Presenter(new Model(),this);

    }

    public void onClick(View view) {
        //presenter.onClick();
        String email_str = email.getText().toString().trim();
        String pass_str = pass.getText().toString().trim();

        switch (view.getId()) {
            case R.id.createText:
                startActivity(new Intent(this, Register.class));
                break;

            case R.id.registerBtn:
                progressBar.setVisibility(View.VISIBLE);
                presenter.onClick();
                progressBar.setVisibility(View.GONE);
                break;

        }

        if(email_str.isEmpty()){
            email.setError(getString(R.string.email_error));
            return;
        }
        if(pass_str.isEmpty()){
            pass.setError(getString(R.string.pass_error));
            return;
        }
        if(pass_str.length() < 6){
            pass.setError(getString(R.string.must_be));
        }

    }

    @Override
    public void Admin() {
        Toast.makeText(this,"Admin Log In Success", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), MainActivity2.class));
        finish();
    }

    @Override
    public void Student() {
        Toast.makeText(this,"Student Log In Success", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    @Override
    public void NO() {
        Toast.makeText(this,"Cannot Log in", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

    @Override
    public void showEmailError(int id) {
        email.setError(getString(id));
    }

    @Override
    public void showPassError(int id) {
        pass.setError(getString(id));
    }
    @Override
    public void lenPassError(int id) {
        pass.setError(getString(id));
    }

}




//
//    static EditText mEmail;
//    static EditText mPassword;
//    Button mLoginBtn;
//    TextView mCreateBtn, forgotTextLink;
//    ProgressBar progressBar;
//    FirebaseAuth fAuth;
//    FirebaseFirestore fStore;
//    Model model;
//
//    public String get_email(){
//        EditText email = (EditText) findViewById(R.id.Email);
//        return email.getText().toString().trim();
//    }
//    public String get_pass(){
//        EditText pass = (EditText) findViewById(R.id.password);
//        return pass.getText().toString().trim();
//    }
//    //@SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        mEmail = findViewById(R.id.Email);
//        mPassword = findViewById(R.id.password);
//        mLoginBtn = findViewById(R.id.registerBtn);
//        mCreateBtn = findViewById(R.id.createText);
//        fAuth = FirebaseAuth.getInstance();
//        progressBar = findViewById(R.id.progressBar2);
//        forgotTextLink = findViewById(R.id.forgotpassword);
//        model = new Model();
//
//        mLoginBtn.setOnClickListener(view -> {
//            String email = mEmail.getText().toString().trim();
//            String password = mPassword.getText().toString().trim();
//            if (TextUtils.isEmpty(email)) {
//                mEmail.setError("Email is required");
//                return;
//            }
//            if (TextUtils.isEmpty(password)) {
//                mPassword.setError("Password is Required");
//                return;
//            }
//            if (password.length() < 6) {
//                mPassword.setError("Password Must be >=6");
//            }
//            progressBar.setVisibility(View.VISIBLE);
//
//            model.all_users(new Model.UserCallBack() {
//                @Override
//                public void check_user(int exist) {
//                    if(exist == 1){
//                        startActivity(new Intent(getApplicationContext(), MainActivity2.class));
//                        finish();
//                    }
//                    else{
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                        finish();
//                    }
//                }
//            });
//            // authenticate
//
//
//
////            fAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
////                Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
////                if (FirebaseAuth.getInstance().getCurrentUser()!=null){
////                    DocumentReference df = FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
////                    df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
////                        @Override
////                        public void onSuccess(DocumentSnapshot documentSnapshot) {
////                            if(documentSnapshot.getString("isAdmin") != null){
////                                startActivity(new Intent(getApplicationContext(), MainActivity2.class));
////                                finish();
////                            }
////                            else if(documentSnapshot.getString("isStudent") != null){
////                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
////                                finish();
////                            }
////                            else{
////                                startActivity(new Intent(getApplicationContext(), Register.class));
////                                finish();
////                            }
////                        }
////                    }).addOnFailureListener(new OnFailureListener() {
////                        @Override
////                        public void onFailure(@NonNull Exception e) {
////                            FirebaseAuth.getInstance().signOut();
////                            startActivity(new Intent(getApplicationContext(), Login.class));
////                        }
////                    });
////                }
////                //checkUserAccessLevel(Objects.requireNonNull(authResult.getUser()).getUid());
////            }).addOnFailureListener(e -> {
////                Toast.makeText(Login.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
////                progressBar.setVisibility(View.GONE);
////            });
//
//        });
//
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
//                fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(unused -> Toast.makeText(Login.this, "Reset Link has been sent to your email", Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(Login.this, "Error ! No link has been sent" + e.getMessage(), Toast.LENGTH_SHORT).show());
//            });
//            passwordResetDialog.setNegativeButton("No", (dialogInterface, i) -> {
//
//            });
//            passwordResetDialog.create().show();
//        });
//    }



