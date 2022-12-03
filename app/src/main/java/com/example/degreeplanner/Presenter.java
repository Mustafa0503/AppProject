package com.example.degreeplanner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class Presenter extends AppCompatActivity implements Contract.Presenter {
    private Contract.Model model;
    public Contract.View2 view;
    static int num;

    public Presenter(Contract.Model model, Contract.View2 view) {
        this.model = model;
        this.view = view;
   }



   public int log(String email){
        return model.data(email);
   }



}


//    private Contract.Model model;
//    public Contract.View view;
//    FirebaseAuth fAuth;
//public Presenter(Contract.Model model, Contract.View view) {
////        this.model = model;
////        this.view = view;
////    }
//
//    public Presenter(Contract.Model model, Contract.View view) {
//        this.model = model;
//        this.view = view;
//    }
//
//    public void error() {
//        EditText mEmail = findViewById(R.id.Email);
//        EditText mPassword = findViewById(R.id.password);
//        String email= view.get_email();
//        String pass = view.get_pass();
//        if (email == "") {
//            mEmail.setError("Email is required");
//        }
//        if (pass == "") {
//            mPassword.setError("Password is Required");
//        }
//        if (pass.length() < 6) {
//            mPassword.setError("Password Must be >=6");
//        }
//        if (model.ru_there(email) == false){
//            mEmail.setError("There is no account with this email");
//        }
//
//    }
//
//    public void forgot(){
//        model.forgott();
//    }
//
//    public int login(String email, String pass) {
//        return model.login_btn(email, pass);
//
//    }
//
//
//
//}
