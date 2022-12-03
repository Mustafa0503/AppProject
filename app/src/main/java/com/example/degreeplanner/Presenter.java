package com.example.degreeplanner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class Presenter extends AppCompatActivity implements Contract.Presenter {
    private Contract.Model model;
    public Contract.View2 view;
    public static int num;

    public Presenter(Contract.Model model, Contract.View2 view) {
        this.model = model;
        this.view = view;
    }


    public int login(String email, String pass) {
        System.out.println("btn");
        num = 0;
        model.login_btn(email, pass);
        while(num == 0){
            try{
                Thread.sleep(500);
            }
            catch(Exception ex){

            }
        }

        return num;

    }
//    public void on_click(){
//        view.onClick(view22);
//    }
}

