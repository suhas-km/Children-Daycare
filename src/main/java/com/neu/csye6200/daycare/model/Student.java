package com.neu.csye6200.daycare.model;

public class Student extends Person {
    private String fatherName;
    private String motherName;
    private String address;
    private String phoneNumber;
    private double gpa;

    public Student() {
    }

    public Student(String id, String name, String email, int age, String fatherName, String motherName, String address, String phoneNumber) {
        super(id, name, email, age);
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gpa = 0.0;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student Details: " +
                super.toString() +
                "\nFather Name: " + fatherName +
                "\nMother Name: " + motherName +
                "\nAddress: " + address +
                "\nPhone Number: " + phoneNumber +
                "\nGPA: " + gpa;
    }
}
