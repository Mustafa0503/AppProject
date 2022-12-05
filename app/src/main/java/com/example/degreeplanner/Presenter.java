package com.example.degreeplanner;

import android.text.TextUtils;


public class Presenter implements Contract.Presenter {
    private Contract.Model model;
    public Contract.View2 view;
    static int num;

    public Presenter(Contract.Model model, Contract.View2 view) {
        this.model = model;
        this.view = view;
    }

    public int error_msg(String email_str, String pass_str) {
        if (TextUtils.isEmpty(email_str)) {
            view.OnError("email required");
            return 1;
        } else if (TextUtils.isEmpty(pass_str)) {
            view.OnError("password required");
            return 1;

        } else if (pass_str.length() < 6) {
            view.OnError("password must be >=6");
            return 1;
        } else if (!email_str.contains("@gmail.com")) {
            view.OnError("invalid email");
            return 1;
        }

        return 0;
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




    public void login(String email, String pass) {
        System.out.println("btn");
//        num = model.login_btn(email, pass);

        if((model.login_btn(email, pass)==1) && (isAdm(email)==1)){
            view.OnSuccess();
        }
        else if((model.login_btn(email, pass)==2) && (isAdm(email)==0)){
            view.OnSuccessStu();
        }
//        else if((model.login_btn(email, pass)==-1)){
//            error_msg(email, pass);
//        }


    }

//    public int error_toast(String email_str, String pass_str){
//         if (model.login_btn(email_str,pass_str)==-1) {
//            view.OnError("cannot login");
//             return -1;
//        }
//         return 0;
//    }


}