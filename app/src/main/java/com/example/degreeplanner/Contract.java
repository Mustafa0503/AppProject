package com.example.degreeplanner;

import android.view.View;

import java.util.ArrayList;

public interface Contract {
    public interface Model{
        public boolean ru_there(String email);
//      public boolean check_null();
        public int data(String email);
        public void addtoArrL();
    }
    public interface View2 {
        public int isAdm(String email);

        void OnError(String email_required);
//        public void doWork();
////        public void display();
//      public String get_email();
//      public String get_pass();

//        void onClick(android.view.View view);
//    }
    }
    public interface Presenter{
        public int log(String email);
        public boolean ruthere(String email);

        void error_msg(String email_str, String pass_str);
//        public void doooWork();
//        public void add();
    }
}
