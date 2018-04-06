package com.a3abcarinho.ahmed.thebesdb;

/**
 * Created by ahmed on 07/10/17.
 */

public class student {
    private    int    id;
    private    String name;
    private    int    code ;
    private  String grade;
    private  double gpa;
    public student(String name, int code, String grade, double gpa) {
        this.name = name;
        this.code = code;
        this.grade = grade;
        this.gpa = gpa;
    }
    public student (int id, String name, int code, String grade, double gpa) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.grade = grade;
        this.gpa = gpa;
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
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public double getGpa() {
        return gpa;
    }
    public void setGpa(int gpa) {
        this.gpa = gpa;
    }
}