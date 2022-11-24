package com.example.degreeplanner;

import java.util.List;

public class User {
    String name;
    List<String> courseList;
    //ActivityMainBinding binding;
    public String getName() {
        return name;
    }

    public List<String> getCourseList() {
        return courseList;
    }



    public User(String name, List<String> courseList) {
        this.name = name;
        this.courseList = courseList;
    }

    public User() {
    }
    public void add(String course)
    {
        this.courseList.add(course);
    }
    public void delete(String course)
    {
        this.courseList.remove(course);
    }
}
