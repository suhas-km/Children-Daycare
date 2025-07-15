package com.neu.csye6200.daycare.repository;

import com.neu.csye6200.daycare.model.Teacher;
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
public class TeacherRepository {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    public boolean insertTeacher(Teacher teacher) {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String query = "INSERT INTO teacher (ID, Name, Email, Age, Credits, GroupID, ClassroomID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, teacher.getId());
                preparedStatement.setString(2, teacher.getName());
                preparedStatement.setString(3, teacher.getEmail());
                preparedStatement.setInt(4, teacher.getAge());
                preparedStatement.setDouble(5, teacher.getCredits());
                preparedStatement.setInt(6, teacher.getGroupId());
                preparedStatement.setInt(7, teacher.getClassroomId());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Teacher details inserted successfully.");
                    return true;
                } else {
                    System.out.println("Failed to insert teacher details.");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String query = "SELECT * FROM teacher";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                
                while (resultSet.next()) {
                    Teacher teacher = new Teacher(
                            resultSet.getString("ID"),
                            resultSet.getString("Name"),
                            resultSet.getString("Email"),
                            resultSet.getInt("Age"),
                            resultSet.getDouble("Credits")
                    );
                    teacher.setGroupId(resultSet.getInt("GroupID"));
                    teacher.setClassroomId(resultSet.getInt("ClassroomID"));
                    teachers.add(teacher);
                }
            }
            
            return teachers;
        } catch (SQLException e) {
            e.printStackTrace();
            return teachers;
        }
    }
}
