package com.neu.csye6200.daycare.model;

public class Teacher extends Person {
    private double credits;
    private int groupId;
    private int classroomId;

    public Teacher() {
    }

    public Teacher(String id, String name, String email, int age, double credits) {
        super(id, name, email, age);
        this.credits = credits;
        this.groupId = 0;
        this.classroomId = 0;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    @Override
    public String toString() {
        return "Teacher Details: " +
                "\nID=" + super.getId() +
                "\n, Name=" + super.getName() +
                "\n, Email=" + super.getEmail() +
                "\n, Age=" + super.getAge() +
                "\n, Credits=" + credits +
                "\n, GroupID=" + groupId +
                "\n, ClassroomID=" + classroomId;
    }
}
