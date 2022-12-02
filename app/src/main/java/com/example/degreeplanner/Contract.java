package com.example.degreeplanner;

import android.view.View;

import java.util.ArrayList;

public interface Contract {
    public interface Model{
//        public boolean ru_there(String email);
      public boolean check_null();
      public abstract ArrayList<Integer> Userfield(String trim, String trim1);
    }
    public interface View2 {
////        public void display();
//      public String get_email();
//      public String get_pass();

//        void onClick(android.view.View view);
//    }
    }
    public interface Presenter{
        boolean check_null();

        //        public void error();
//        public void forgot();
//
       public abstract ArrayList<Integer> Usertfield2(String email, String password);
    }
}
