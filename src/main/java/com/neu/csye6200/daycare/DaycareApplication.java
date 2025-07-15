package com.neu.csye6200.daycare;

import com.neu.csye6200.daycare.model.Student;
import com.neu.csye6200.daycare.model.Teacher;
import com.neu.csye6200.daycare.service.StudentService;
import com.neu.csye6200.daycare.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DaycareApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaycareApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner demo(
            @Autowired StudentService studentService,
            @Autowired TeacherService teacherService) {
        
        return args -> {
            // Example of creating and registering a student
            Student student = new Student(
                    "S01", 
                    "John Smith", 
                    "john@example.com", 
                    5,
                    "James Smith", 
                    "Mary Smith", 
                    "123 Main St",
                    "(555) 123-4567"
            );
            
            // Register the student using the service
            studentService.registerStudent(student);
            
            // Example of creating and registering a teacher
            Teacher teacher = new Teacher(
                    "T01", 
                    "Jane Doe", 
                    "jane@example.com", 
                    35, 
                    10.5
            );
            
            // Register the teacher using the service
            teacherService.registerTeacher(teacher);
            
            System.out.println("Data initialized successfully!");
        };
    }
}
