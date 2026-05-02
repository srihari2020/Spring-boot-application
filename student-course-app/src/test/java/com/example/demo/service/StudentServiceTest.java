package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStudents() {
        Student s1 = new Student();
        Student s2 = new Student();
        when(studentRepository.findAllWithCourses()).thenReturn(Arrays.asList(s1, s2));

        List<Student> students = studentService.getAllStudents();

        assertEquals(2, students.size());
        verify(studentRepository, times(1)).findAllWithCourses();
    }

    @Test
    void testSaveStudent() {
        Student student = new Student();
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        studentService.saveStudent(student);

        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testGetStudentById() {
        Student student = new Student();
        student.setId(1L);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student result = studentService.getStudentById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void testGetStudentByIdNotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            studentService.getStudentById(1L);
        });

        assertEquals("Student not found for id :: 1", exception.getMessage());
    }
}
