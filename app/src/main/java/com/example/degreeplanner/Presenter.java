package com.example.degreeplanner;

import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class Presenter extends AppCompatActivity implements Contract.Presenter {
    private Contract.Model model;
    public Contract.View2 view;
    static int num;
//    static ArrayList<String>

    public Presenter(Contract.Model model, Contract.View2 view) {
        this.model = model;
        this.view = view;
   }
   public int Error_msg (String email, String pass){
       if (TextUtils.isEmpty(email_str)) {
           view.OnError("Email Required");
           return 1;
       }
       else if (TextUtils.isEmpty(pass_str)) {
           view.OnError("Password Required");
           return 1;
       }
       else if (pass_str.length() < 6) {
           view.OnError("Password needs to be at least six characters");
           return 1;
       }
        return 0;

     }
    public int login(String email, String pass) {
        System.out.println("btn");
        model.login_btn(email, pass);
        while(num == 0){
            try{
                Thread.sleep(500);
            }
            catch(Exception ex){

            }
        }

        return num;

    }


//   public int log(String email){
//        return model.data(email);
//   }
//
//   public boolean ruthere(String email){
//       return model.ru_there(email);
//   }



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
