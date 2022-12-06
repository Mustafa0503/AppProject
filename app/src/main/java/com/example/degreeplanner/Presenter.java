package com.example.degreeplanner;


import android.text.TextUtils;

public class Presenter implements Contract.Presenter {
    private Contract.Model model;
    public Contract.Login view;
    //    Model.UserCallBack CallBack;
    static int num;

    public Presenter(Contract.Model model, Contract.Login view) {
        this.model = model;
        this.view = view;
    }

    public void onClick() {
        String email = view.get_email();

        if (email.isEmpty()) {
            view.showEmailError(R.string.email_error);
            return;
        }

        String password = view.get_pass();
        if (password.isEmpty()) {
            view.showPassError(R.string.pass_error);
            return;
        }
        if (password.length() < 6) {
            view.lenPassError(R.string.must_be);
            return;
        }
        int n = model.login_btn(email, password);
        if(n == 1){
            view.Admin();
            return;
        }
        int n2 = model.login_btn(email, password);
        if(n2 == 2){
            view.Student();
            return;
        }
//        int n3 = model.login_btn(email, password);
//        if(n3 == 3){
//            view.NO();
//            return;
//        }


//        model.all_users(email, password, new Model.UserCallBack() {
//            @Override
//            public int check_user(int exist) {
//                if (exist == 1){
//                    view.Admin();
//                    return 1;
//                }
//                if(exist == 2){
//                    view.Student();
//                    return 2;
//                }
//                if(exist== 0){
//                    view.NO();
//                    return 0;
//                }
//                return -1;
//            }
//        });
//        int n = model.login_btn(email, password);
//        if (n == 1) {
//            view.Admin();
//            return;
//        }
//        int n2 = model.login_btn(email, password);
//        if (n2 == 2) {
//            view.Student();
//            return;
//        }
//        int n3 = model.login_btn(email, password);
//        if (n3 == 0) {
//            view.NO();
//            return;
//        }
    }
//
//        public void login(String email, String pass) {
//
//        if((model.login_btn(email, pass)==1)){
//            view.Admin();
//        }
//        else if((model.login_btn(email, pass)==2)){
//            view.Student();
//        }
//        else if((model.login_btn(email, pass)==-1)){
//            view.NO();
//        }


}

