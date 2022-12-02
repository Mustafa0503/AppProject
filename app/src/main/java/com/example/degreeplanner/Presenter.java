//package com.example.degreeplanner;
//
//import android.content.Intent;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//
//
//
//public class Presenter extends AppCompatActivity implements Contract.Presenter{
//    private Contract.Model model;
//    public Contract.View view;
//    FirebaseAuth fAuth;
//
//
//
//
//    public Presenter(Contract.Model model, Contract.View view) {
//        this.model = model;
//        this.view = view;
//    }
//
//    public View.OnClickListener login_btn() {
//        Button mLoginBtn = findViewById(R.id.registerBtn);
//        EditText mEmail = (EditText)findViewById(R.id.Email);
//        EditText mPassword =(EditText) findViewById(R.id.password);
//
//        mLoginBtn.setOnClickListener(view -> {
//            String email = mEmail.getText().toString().trim();
//            String password = mPassword.getText().toString().trim();
//            fAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
//                Toast.makeText(Presenter.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
//                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
//                    DocumentReference df = FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
//                    df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                        @Override
//                        public void onSuccess(DocumentSnapshot documentSnapshot) {
//                            if (documentSnapshot.getString("isAdmin") != null) {
//                                startActivity(new Intent(getApplicationContext(), MainActivity2.class));
//                                finish();
//                            } else if (documentSnapshot.getString("isStudent") != null) {
//                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                                finish();
//                            } else {
//                                startActivity(new Intent(getApplicationContext(), Register.class));
//                                finish();
//                            }
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            FirebaseAuth.getInstance().signOut();
//                            startActivity(new Intent(getApplicationContext(), View2.class));
//                        }
//                    });
//                }
//            });
//        });
//
//
//
//    public void forgotlink(){
//        TextView forgotTextLink;
//        forgotTextLink.setOnClickListener(view -> {
//            EditText resetMail = new EditText(view.getContext());
//            AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
//            passwordResetDialog.setTitle("Reset Password?");
//            passwordResetDialog.setMessage("Enter your email id to receive the reset link");
//            passwordResetDialog.setView(resetMail);
//            passwordResetDialog.setPositiveButton("Yes", (dialogInterface, i) -> {
//                String mail = resetMail.getText().toString();
//                fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(unused -> Toast.makeText(Presenter.this, "Reset Link has been sent to your email", Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(Presenter.this, "Error ! No link has been sent" + e.getMessage(), Toast.LENGTH_SHORT).show());
//            });
//            passwordResetDialog.setNegativeButton("No", (dialogInterface, i) -> {
//
//            });
//            passwordResetDialog.create().show();
//        });
//    }
//
//    public boolean error() {
//        EditText mEmail = (EditText)findViewById(R.id.Email);
//        EditText mPassword = findViewById(R.id.password);
//        String email= view.get_email();
//        String pass = view.get_pass();
//        if (email == " ") {
//            mEmail.setError("Email is required");
//            return false;
//        }
//        if (pass == " ") {
//            mPassword.setError("Password is Required");
//            return false;
//        }
//        if (pass.length() < 6) {
//            mPassword.setError("Password Must be >=6");
//            return false;
//        }
//        if (model.ru_there(email) == false){
//            mEmail.setError("There is no account with this email");
//            return false;
//        }
//        return true;
//
//    }
//
//}
