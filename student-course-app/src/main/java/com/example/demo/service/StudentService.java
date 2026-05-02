package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        // Utilizing the custom join query required by the assignment
        return studentRepository.findAllWithCourses();
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Student not found for id :: " + id);
        }
    }
}
