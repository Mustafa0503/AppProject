package com.example.degreeplanner;

import java.util.ArrayList;

public class info {
    ArrayList<String> offerSession;
    ArrayList<String> pre;
    String courseCode;

    public info(){

    }
    public info(String courseCode, ArrayList<String> offerSession, ArrayList<String> pre) {
        this.courseCode = courseCode;
        this.offerSession = offerSession;
        this.pre = pre;
    }
}
