package com.example.degreeplanner;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
public class Model extends AppCompatActivity implements Contract.Model {
        FirebaseAuth fAuth=FirebaseAuth.getInstance();
       private FirebaseAuth.AuthStateListener mAuthListener;
       private FirebaseAuth mAuth;
        //static boolean num;
        Presenter presenter;
        public int login_btn(String email, String password) {
            System.out.println("ge");

            //fAuth.getCurrentUser();
            System.out.println("gj0000000000ute");
            fAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
                    System.out.println("gjgjyfhyeteyteute");
                   if(fAuth.getCurrentUser()!=null) {
                       FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
                       DocumentReference df = FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
                       df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                           @Override
                           public void onSuccess(DocumentSnapshot document) {
//                               if (task.isSuccessful()) {
//                                   DocumentSnapshot document = task.getResult();
                               if (document != null) {
                                   if (document.getString("isAdmin") != null) {
                                       //num=true;
                                       Presenter.num = 1;

//                                    startActivity(new Intent(Model.this, MainActivity2.class));
//                                    finish();
                                       System.out.println("num=1" + Presenter.num);
                                   } else if (document.getString("isStudent") != null) {
                                       //num = false;
                                       Presenter.num = 2;
                                       System.out.println("num=2" + Presenter.num);

                                   }
                                   else{
                                       Presenter.num = -1;
                                   }


                               }
                           }
                       });

                   }


            });
            //FirebaseAuth.getInstance().signOut();

            //return num;
            if ( Presenter.num==1)
            {
                return 1;
            }
            else if ( Presenter.num==2)
            {
                return 2;
            }
            else{
                System.out.println("num= -1    " + Presenter.num);
                return -1;
            }


        }
    }














































//    public boolean check_null(){
//        return FirebaseAuth.getInstance().getCurrentUser()==null;
//    }



//    public ArrayList<Integer> Userfield(String email, String password) {
//        DocumentReference docRef = mDb.collection("users").document("isAdmin");
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
////                if (task.isSuccessful()) {
////                    DocumentSnapshot document = task.getResult();
////                    if (document.getString("isAdmin") != null) {
////
////
////                    }
////                }
//            }
//        });
//        return Num;

//        public void is(String e, String p){
//
//            FirebaseFirestore.getInstance().collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    email_id = new ArrayList<>();
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        String em = document.getString("email");
//                        email_id.add(em);
//                    }
//                    EmailArray = new String[email_id.size()];
//                    EmailArray = email_id.toArray(EmailArray);
//                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
//                }
//            }
//        });
//        }







//       email = presenter.get_email();
//       password = presenter.get_pass();

//        fAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
//            Num.add(1);
//            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
//                DocumentReference df = FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
//                df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        if (documentSnapshot.getString("isAdmin") != null) {
//                            Num.add(1);
//                        } else if (documentSnapshot.getString("isStudent") != null) {
//                            Num.add(0);
//                        } else {
//                            Num.add(-1);
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        FirebaseAuth.getInstance().signOut();
//                        Num.add(2);
//                    }
//                });
//
//
//            }
//        });
//        return Num;




//    public void Reset_pass(String mail) {
//        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(unused -> Toast.makeText(View2.this, "Reset Link has been sent to your email", Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(Login.this, "Error ! No link has been sent" + e.getMessage(), Toast.LENGTH_SHORT).show());
//
//
//    }


//





//
//    EditText mEmail, mPassword;
//    Button mLoginBtn;
//    TextView mCreateBtn, forgotTextLink;
//    ProgressBar progressBar;
//    FirebaseAuth fAuth = FirebaseAuth.getInstance();
//    FirebaseFirestore fStore;
//    ArrayList<String> email_id;
//    String[] EmailArray;
//    int num;
//
//
//
//



//
//
//        public int login_btn (String email, String password) {
////            EditText mEmail = findViewById(R.id.Email);
////            EditText mPassword = findViewById(R.id.password);
////            String email = mEmail.getText().toString().trim();
////            String password =
////            fAuth= FirebaseAuth.getInstance();
//            fAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
////                Toast.makeText(Model.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
//               // if (fAuth.getCurrentUser() != null) {
//                    //FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
//                DocumentReference df = FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
//                    df.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                            if(task.isSuccessful()){
//                                DocumentSnapshot document = task.getResult();
//                                if(document != null){
//                                    if(document.getString("isAdmin") != null){
//                                        num = 1;
//                                    }
//                                    else if(document.getString("isStudent") != null){
//                                        num = 0;
//                                    }
//                                    else{
//                                        num = -1;
//                                    }
//
//                                }
//                            }
//                        }
//                    });
////                    {
////
//                //}
//            });
//            return num;
//    }
//
//        public void forgott(){
//        forgotTextLink.setOnClickListener(view -> {
//            EditText resetMail = new EditText(view.getContext());
//            AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
//            passwordResetDialog.setTitle("Reset Password?");
//            passwordResetDialog.setMessage("Enter your email id to receive the reset link");
//            passwordResetDialog.setView(resetMail);
//            passwordResetDialog.setPositiveButton("Yes", (dialogInterface, i) -> {
//                String mail = resetMail.getText().toString();
//                fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(unused -> Toast.makeText(Model.this, "Reset Link has been sent to your email", Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(Model.this, "Error ! No link has been sent" + e.getMessage(), Toast.LENGTH_SHORT).show());
//            });
//            passwordResetDialog.setNegativeButton("No", (dialogInterface, i) -> {
//
//            });
//            passwordResetDialog.create().show();
//        });
//
//        }
//
//
//
//}
//
//
//
