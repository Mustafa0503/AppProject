//package com.example.degreeplanner;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.text.TextUtils;
//
//import org.jetbrains.annotations.Contract;
//
//import java.util.ArrayList;
//
//public class Presenter {
//
//    Login view;
//    Model model = new Model();
//    static int num;
//
//
//    public Presenter(Model model, Login view) {
//        this.model = model;
//        this.view = view;
//    }
//
////    public int check_user(String email, String password){
////        return model.check_user(email, password);
////    }
////    public boolean check_admin(String email){
////        return model.findAdmin(email);
////    }
////    public boolean check_student(String email){
////        return model.findStudent(email);
////    }
//
//    public void display_admin(){
//        view.display_admin();
//    }
//    public void display_Student(){
//
//    }
//
//
//
//    public int error_msg(String email_str, String pass_str) {
//        if (TextUtils.isEmpty(email_str)) {
//            view.OnError("Email Required");
//            return 1;
//        } else if (TextUtils.isEmpty(pass_str)) {
//            view.OnError("Password Required");
//            return 1;
//
//        } else if (pass_str.length() < 6) {
//            view.OnError("Password needs to be at least six characters");
//            return 1;
//        } else if (!email_str.contains("@gmail.com")) {
//            view.OnError("Invalid Email");
//            return 1;
//        }
//        return 0;
//    }
//
//    public String getInfo(String email, String password){
//        if (TextUtils.isEmpty(email)) {
//            return "Email is required";
//        }
//        if (TextUtils.isEmpty(password)) {
//            return "Password is Required";
//        }
//        if (password.length() < 6) {
//            return "Password Must be >=6";
//        }
//        return "Login Successful";
//    }
//
//
//}
