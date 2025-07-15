package com.neu.csye6200.daycare.service;

import com.neu.csye6200.daycare.model.Teacher;
import com.neu.csye6200.daycare.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public boolean registerTeacher(Teacher teacher) {
        // Add any business logic/validation here before saving to database
        if (teacher.getAge() < 18) {
            System.out.println("Invalid age for teacher, must be at least 18");
            return false;
        }
        
        return teacherRepository.insertTeacher(teacher);
    }
    
    public List<Teacher> getAllTeachers() {
        return teacherRepository.getAllTeachers();
    }
}
