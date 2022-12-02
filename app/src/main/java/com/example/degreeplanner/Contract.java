package com.example.degreeplanner;

import android.view.View;

import java.util.ArrayList;

public interface Contract {
    public interface Model{
//        public boolean ru_there(String email);
          public boolean login_btn(String email,String password);
    }
    public interface View2 {

        public void OnError(String message);
        //public void OnSucess(String message);

//        void onClick(android.view.View view);
//    }
    }
    public interface Presenter{
//        boolean check_null();

        //        public void error();
//        public void forgot();
//
//       public abstract ArrayList<Integer> Usertfield2(String email, String password);

        boolean login(String email_str, String pass_str);
    }
}
////        public void display();
//      public String get_email();
//      public String get_pass();