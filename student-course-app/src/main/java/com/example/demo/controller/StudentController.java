package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.CourseService;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;

    @Autowired
    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    // Read Operation: Display list of all entities
    @GetMapping(value = {"/", "/students"})
    public String viewHomePage(Model model) {
        model.addAttribute("listStudents", studentService.getAllStudents());
        return "list";
    }

    // Create Operation: Show form
    @GetMapping("/showNewStudentForm")
    public String showNewStudentForm(Model model) {
        // create model attribute to bind form data
        Student student = new Student();
        model.addAttribute("student", student);
        model.addAttribute("listCourses", courseService.getAllCourses());
        return "add";
    }

    // Create Operation: Save entity
    @PostMapping("/saveStudent")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listCourses", courseService.getAllCourses());
            return "add";
        }
        
        try {
            // save student to database
            studentService.saveStudent(student);
        } catch (DataIntegrityViolationException e) {
            // Handle exceptions in case of any integrity violations (e.g., duplicate email)
            model.addAttribute("errorMessage", "Error saving student: Data integrity violation. Perhaps the email already exists.");
            model.addAttribute("listCourses", courseService.getAllCourses());
            return "add";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error saving student: " + e.getMessage());
            model.addAttribute("listCourses", courseService.getAllCourses());
            return "add";
        }
        return "redirect:/";
    }

    // Update Operation: Show form for update
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        // get student from the service
        Student student = studentService.getStudentById(id);
        
        // set student as a model attribute to pre-populate the form
        model.addAttribute("student", student);
        model.addAttribute("listCourses", courseService.getAllCourses());
        return "update";
    }

    // Update Operation: Handle update request and save
    @PostMapping("/updateStudent")
    public String updateStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listCourses", courseService.getAllCourses());
            return "update";
        }
        
        try {
            // save updated student to database
            studentService.saveStudent(student);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Error updating student: Data integrity violation. Email might already be taken.");
            model.addAttribute("listCourses", courseService.getAllCourses());
            return "update";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error updating student: " + e.getMessage());
            model.addAttribute("listCourses", courseService.getAllCourses());
            return "update";
        }
        return "redirect:/";
    }
}
