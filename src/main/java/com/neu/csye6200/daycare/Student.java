package com.neu.csye6200.daycare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Student extends Person{
    private String fatherName;
    private String motherName;
    private String address;
    private String phoneNumber;
    private double GPA;

    // JDBC connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ChildrenDaycare";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "csye6200";

    public Student(String ID,String name, String email, int age, String fatherName, String motherName, String address, String phoneNumber){
        super.setID(ID);
        super.setName(name);
        super.setEmail(email);
        super.setAge(age);
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.GPA = 0.0;
    }

    public String getFatherName(){
        return fatherName;
    }

    public String getMotherName(){
        return motherName;
    }

    public String getAddress(){
        return address;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public double getGpa(){
        return GPA;
    }

    public void insertStudentIntoDatabase(Student student) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO student (ID, Name, Email, Age, FatherName, MotherName, Address, PhoneNumber, GPA) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, student.getID());
                preparedStatement.setString(2, student.getName());
                preparedStatement.setString(3, student.getEmail());
                preparedStatement.setInt(4, student.getAge());
                preparedStatement.setString(5, student.getFatherName());
                preparedStatement.setString(6, student.getMotherName());
                preparedStatement.setString(7, student.getAddress());
                preparedStatement.setString(8, student.getPhoneNumber());
                preparedStatement.setDouble(9, student.getGpa());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Student details inserted successfully.");
                } else {
                    System.out.println("Failed to insert student details.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return "Student Details: " +
                super.toString() +
                "\nFather Name: " + fatherName +
                "\nMother Name: " + motherName +
                "\nAddress: " + address +
                "\nPhone Number: " + phoneNumber;
    }
}
