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
////        public void display();
//      public String get_email();
//      public String get_pass();

//        void onClick(android.view.View view);
//    }
    }
    public interface Presenter{
        public int log(String email);
    }
}
