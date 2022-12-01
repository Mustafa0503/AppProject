package com.example.degreeplanner;

public interface Contract {
    public interface Model{
        public boolean ru_there(String email);
        public void forgott();

        public void login_btn(String email, String pass);
    }
    public interface View{
//        public void display();
        public String get_email();
        public String get_pass();
    }
    public interface Presenter{
        public void error();
        public void forgot();

        void login(String trim, String trim1);
    }
}
