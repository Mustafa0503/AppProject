package com.example.degreeplanner;

import android.view.View;

public interface Contract {

    public interface Model{
        public int login_btn(String email, String password);
        //public int all_users(String email, String password, com.example.degreeplanner.Model.UserCallBack userCallBack);
        }

    public interface Login {
        public String get_email();
        public String get_pass();
        public void onClick(View view);
        public void Admin();
        public void Student();
        public void NO();
        public void showEmailError(int id);
        public void showPassError(int id);
        public void lenPassError(int id);
    }
    public interface Presenter{
        public void onClick();
        //public void login(String email, String pass);
        //public void oneClick(String email, String password);
        //public void all_u(String email_str, String pass_str);
    }
}
