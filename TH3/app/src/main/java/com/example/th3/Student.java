package com.example.th3;

public class Student {
    private String id;
    private String gender;
    private String birthDate;
    private String email;
    private String address;
    private String major;
    private double gpa;
    private int year;
    Full_name full_name;




    public Student(String id, Full_name full_name, String gender, String birthDate, String email, String address, String major, double gpa, int year) {
        this.id = id;
        this.full_name = full_name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.address = address;
        this.major = major;
        this.gpa = gpa;
        this.year = year;
    }


    public String getId() {
        return id;
    }

    public Full_name getFullName() {
        return full_name;
    }




    public String getGender() {
        return gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getMajor() {
        return major;
    }

    public double getGpa() {
        return gpa;
    }

    public int getYear() {
        return year;
    }

    public void setId(String id) {
        this.id = id;
    }



    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setFullName(Full_name full_name) {
        this.full_name = full_name;
    }


}
