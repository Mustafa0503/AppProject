package com.example.degreeplanner;

import android.view.View;

public interface Contract {
    public interface Model{
        public boolean ru_there(String email);
        public void forgott();
        public int login_btn(String trim, String trim1);
    }
    public interface View{
//        public void display();
        public String get_email();
        public String get_pass();

        void onClick(android.view.View view);
    }
    public interface Presenter{
        public void error();
        public void forgot();

        public int login(String trim, String trim1);
    }
}
