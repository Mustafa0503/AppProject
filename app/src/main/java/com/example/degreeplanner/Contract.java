package com.example.degreeplanner;

public interface Contract {
    public interface Model{
        public boolean ru_there(String email);
        public void login_btn();

    }
    public interface View{
//        public void display();
        public String get_email();
        public String get_pass();
    }
    public interface Presenter{
        public void login();
        public void error();
    }
}
