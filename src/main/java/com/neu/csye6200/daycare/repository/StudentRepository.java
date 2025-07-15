package com.neu.csye6200.daycare.repository;

import com.neu.csye6200.daycare.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    public boolean insertStudent(Student student) {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String query = "INSERT INTO student (ID, Name, Email, Age, FatherName, MotherName, Address, PhoneNumber, GPA) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, student.getId());
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
                    return true;
                } else {
                    System.out.println("Failed to insert student details.");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String query = "SELECT * FROM student";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                
                while (resultSet.next()) {
                    Student student = new Student(
                            resultSet.getString("ID"),
                            resultSet.getString("Name"),
                            resultSet.getString("Email"),
                            resultSet.getInt("Age"),
                            resultSet.getString("FatherName"),
                            resultSet.getString("MotherName"),
                            resultSet.getString("Address"),
                            resultSet.getString("PhoneNumber")
                    );
                    student.setGpa(resultSet.getDouble("GPA"));
                    students.add(student);
                }
            }
            
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
            return students;
        }
    }
}
