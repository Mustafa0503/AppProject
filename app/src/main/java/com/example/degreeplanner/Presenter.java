package com.example.degreeplanner;

import android.text.TextUtils;
import android.widget.Toast;

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

    public void error_msg(String email_str, String pass_str) {
        if (TextUtils.isEmpty(email_str)) {
            view.OnError("Email Required");
        } else if (TextUtils.isEmpty(pass_str)) {
            view.OnError("Password Required");
        } else if (pass_str.length() < 6) {
            view.OnError("Password needs to be at least six characters");
        } else if (!email_str.contains("@gmail.com")) {
            view.OnError("Invalid Email");
        }
    }

    public void OnError(String email_required) {
        Toast.makeText(this, email_required, Toast.LENGTH_SHORT).show();

    }


    public int log(String email) {
        return model.data(email);
    }

    public boolean ruthere(String email) {
        return model.ru_there(email);
    }
}
//   public void doooWork(){
//        view.doWork();
////   }
//   public void add(){
//        model.addtoArrL();
//   }






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
