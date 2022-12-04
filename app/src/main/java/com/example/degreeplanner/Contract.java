package com.example.degreeplanner;

import android.view.View;

public interface Contract {

    public interface Model{
//        public int login_btn(String email,String pass);
        public void all_users(String email, String password, com.example.degreeplanner.Model.UserCallBack UserCallBack);
        }
    public interface View2 {
        public String get_email();
        public String get_pass();
//        public void OnError();
//        public void OnSuccess();
        public void onClick(View view);
//        public int isAdm(String email);
//        public void OnSuccessStu();
       // public void check_user(int exist);
        public void checkkk();
        public void checkkk22();
        public void toast_msg();
    }
    public interface Presenter{
       // public int isAdm(String email);
       // public void login(String email,String pass);
       // public int error_msg(String email_str, String pass_str);
//        public int error_toast(String email_str, String pass_str);
       public void all_u(String email_str, String pass_str);
        public void msssssssssssssssssssg();
//        public void yodothis (int num);


        }
}
