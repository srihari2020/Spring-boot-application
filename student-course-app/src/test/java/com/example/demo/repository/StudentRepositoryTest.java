package com.example.demo.repository;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void testFindAllWithCourses() {
        // Prepare data
        Course course = new Course("Test Course", "Description");
        courseRepository.save(course);

        Student student = new Student("Test Student", "test@example.com", course);
        studentRepository.save(student);

        // Execute query
        List<Student> students = studentRepository.findAllWithCourses();

        // Verify
        assertFalse(students.isEmpty());
        assertEquals("Test Student", students.get(0).getName());
        assertEquals("Test Course", students.get(0).getCourse().getName());
    }
}
