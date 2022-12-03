package com.example.degreeplanner;

public interface Contract {
    public interface Model{
        public int login_btn(String email,String password);

    }
    public interface View2 {
        public void onSuccess(String message);
        public void onError (String message);

       // public int isAdm(String email);
////        public void display();
//      public String get_email();
//      public String get_pass();

//        void onClick(android.view.View view);
//    }
    }
    public interface Presenter{
        public int login(String email, String pass_str);
        //public boolean ruthere(String email);
    }
}


//public boolean ru_there(String email);
//      public boolean check_null();
//  public int data(String email);
//public void addtoArrL();