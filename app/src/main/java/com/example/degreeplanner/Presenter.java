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

    public int error_msg(String email_str, String pass_str) {
        if (TextUtils.isEmpty(email_str)) {
            view.OnError("Email Required");
            return 1;
        } else if (TextUtils.isEmpty(pass_str)) {
            view.OnError("Password Required");
            return 1;

        } else if (pass_str.length() < 6) {
            view.OnError("Password needs to be at least six characters");
            return 1;
        } else if (!email_str.contains("@gmail.com")) {
            view.OnError("Invalid Email");
            return 1;
        }

        return 0;
    }

    public int isAdm(String email){
        if(email.contains("admin")){
            return 1;
        }
        else if (email.contains("student") ){
            return 0;
        }
        return -1;
    }
    public int login(String email, String pass) {
        System.out.println("btn");
        return model.login_btn(email, pass);
    }

    public int error_toast(String email_str, String pass_str){
         if (model.login_btn(email_str,pass_str)==-1) {
            view.OnError("cannot login");
             return -1;
        }
         return 0;
    }


}