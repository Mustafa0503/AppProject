package com.example.degreeplanner;

public interface Contract {
    public interface Model{
        public boolean ru_there(String email);

    }
    public interface View{
//        public void display();
        public String get_email();
        public String get_pass();
    }
    public interface Presenter{
        public android.view.View.OnClickListener login_btn();
        public boolean error();
        public void forgotlink();
    }
}
