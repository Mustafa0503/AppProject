package com.example.degreeplanner;


import android.text.TextUtils;

public class Presenter implements Contract.Presenter {
    private Contract.Model model;
    public Contract.Login view;
    static int num;

    public Presenter(Contract.Model model, Contract.Login view) {
        this.model = model;
        this.view = view;
    }

//    public int error_msg(String email_str, String pass_str) {
//        if (TextUtils.isEmpty(email_str)) {
//            view.OnError();
//            return 1;
//        } else if (TextUtils.isEmpty(pass_str)) {
//            view.OnError();
//            return 1;
//
//        } else if (pass_str.length() < 6) {
//            view.OnError();
//            return 1;
//        } else if (!email_str.contains("@gmail.com")) {
//            view.OnError();
//            return 1;
//        }
//
//        return 0;
//    }
        public String getInfo(String email, String password){
            if (email == "" || email == null) {
                return "Email is required";
            }
            if (password == "" || password == null) {
                return "Password is Required";
            }
            if (password.length() < 6) {
                return "Password Must be >=6";
            }
            return "Login Successful";
        }

    public void all_u(String email_str, String pass_str){
        model.all_users(email_str, pass_str,new Model.UserCallBack() {
            @Override
            public int check_user(int exist) {
                if (exist == 1){
                    view.Admin();
                    return 1;
                }
                if(exist == 2){
                    view.Student();
                    return 2;
                }
                if(exist== 0){
                    view.NO();
                    return 0;
                }
                return -1;
            }

            @Override
            public boolean isUser(boolean u) {
                if(u){
                    return true;
                }
                return false;
            }

        });
    }


    public void msssssssssssssssssssg(){
        view.toast_msg();
    }


    public int isAdm(String email){
        if(email.contains("admin")){
            return 1;
        }
        else if (email.contains("student") ){
            return 0;
        }
        return -1;
    }




//    public void login(String email, String pass) {
//        System.out.println("btn");
////        num = model.login_btn(email, pass);
//
//        if((model.login_btn(email, pass)==1) && (isAdm(email)==1)){
//            view.OnSuccess();
//        }
//        else if((model.login_btn(email, pass)==2) && (isAdm(email)==0)){
//            view.OnSuccessStu();
//        }
////        else if((model.login_btn(email, pass)==-1)){
////            error_msg(email, pass);
////        }


    }

//    public int error_toast(String email_str, String pass_str){
//         if (model.login_btn(email_str,pass_str)==-1) {
//            view.OnError("cannot login");
//             return -1;
//        }
//         return 0;
//    }


