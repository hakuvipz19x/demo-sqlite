package com.example.sqliteexamination.model;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private int age;
    private double mark;
    private boolean gender;
    private String dateCreated;

    public Student() {
    }

    public Student(String name, int age, double mark, boolean gender, String dateCreated) {
        this.name = name;
        this.age = age;
        this.mark = mark;
        this.gender = gender;
        this.dateCreated = dateCreated;
    }

    public Student(int id, String name, int age, double mark, boolean gender, String dateCreated) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mark = mark;
        this.gender = gender;
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
