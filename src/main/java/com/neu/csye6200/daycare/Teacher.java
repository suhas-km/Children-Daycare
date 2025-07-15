package com.neu.csye6200.daycare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Teacher extends Person{
    private double credits;
    private int groupID;
    private int classroomID;

    // JDBC connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ChildrenDaycare";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "csye6200";

    public Teacher(String ID,String name, String email, int age, double credits){
        super.setID(ID);
        super.setName(name);
        super.setEmail(email);
        super.setAge(age);
        this.credits = credits;
        this.groupID = 0;
        this.classroomID = 0;
    }

    public double getCredits(){
        return credits;
    }

    public int getGroupID(){
        return groupID;
    }

    public int getClassroomID(){
        return classroomID;
    }

    public void insertTeacherIntoDatabase(Teacher teacher) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO teacher (ID, Name, Email, Age, Credits, GroupID, ClassroomID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, teacher.getID());
                preparedStatement.setString(2, teacher.getName());
                preparedStatement.setString(3, teacher.getEmail());
                preparedStatement.setInt(4, teacher.getAge());
                preparedStatement.setDouble(5, teacher.getCredits());
                preparedStatement.setInt(6, teacher.getGroupID());
                preparedStatement.setInt(7, teacher.getClassroomID());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Teacher details inserted successfully.");
                } else {
                    System.out.println("Failed to insert teacher details.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Teacher Details: " +
                "\nID=" + super.getID() +
                "\n, Name=" + super.getName() +
                "\n, Email=" + super.getEmail() +
                "\n, Age=" + super.getAge() +
                "\n, Credits=" + credits +
                "\n, GroupID=" + groupID +
                "\n, ClassroomID=" + classroomID + '}';
    }
}
