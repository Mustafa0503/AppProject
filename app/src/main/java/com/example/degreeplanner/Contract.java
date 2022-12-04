package com.example.degreeplanner;

import android.view.View;

import java.util.ArrayList;

public interface Contract {

    public interface Model{
        public int login_btn(String email,String pass);
    }
    public interface View2 {
        public String get_email();
        public String get_pass();
        public void OnError(String email_required);
        public void OnSuccess(String email_required);
        public void onClick(View view);
    }
    public interface Presenter{
        public int isAdm(String email);
        public int login(String email,String pass);
        public int error_msg(String email_str, String pass_str);
//        public int error_toast(String email_str, String pass_str);

    }
}
