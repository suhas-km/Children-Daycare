package com.neu.csye6200.daycare.service;

import com.neu.csye6200.daycare.model.Student;
import com.neu.csye6200.daycare.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public boolean registerStudent(Student student) {
        // Add any business logic/validation here before saving to database
        if (student.getAge() < 0) {
            System.out.println("Invalid age for student");
            return false;
        }
        
        return studentRepository.insertStudent(student);
    }
    
    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }
}
