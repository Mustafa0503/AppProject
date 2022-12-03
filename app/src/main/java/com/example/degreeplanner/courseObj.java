package com.example.degreeplanner;

import java.util.ArrayList;

public class courseObj {
    String code;
    ArrayList<String> offerSession;
    ArrayList<String> pre;
    public courseObj(){
    }

    public courseObj(String code, ArrayList<String> offerSession, ArrayList<String> pre) {
        this.code = code;
        this.offerSession = offerSession;
        this.pre = pre;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<String> getOfferSession() {
        return offerSession;
    }

    public void setOfferSession(ArrayList<String> offerSession) {
        this.offerSession = offerSession;
    }

    public ArrayList<String> getPre() {
        return pre;
    }

    public void setPre(ArrayList<String> pre) {
        this.pre = pre;
    }
}