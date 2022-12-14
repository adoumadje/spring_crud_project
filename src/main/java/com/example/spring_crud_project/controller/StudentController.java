package com.example.spring_crud_project.controller;


import com.example.spring_crud_project.model.Student;
import com.example.spring_crud_project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/student")
@RestController
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/addstudent")
    public void addStudent(@Valid @NonNull @RequestBody Student student) {
        studentService.addStudent(student);
    }

    @GetMapping("/getallstudents")
    public List<Student> getAllStudents() {
        return studentService.getAllSTudents();
    }

    @GetMapping("/getbyId/{id}")
    public Student getStudentById(@PathVariable("id") UUID id) {
        return studentService.getStudentById(id)
                .orElse(null);
    }

    @GetMapping(path = "/getbylastname/{lastname}")
    public List<Student> getStudentsByLastname(@PathVariable("lastname") String lastname) {
        return studentService.getStudentsByLastname(lastname);
    }

    @DeleteMapping(path = "/deletestudent/{id}")
    public void deleteStudentById(@PathVariable("id") UUID id) {
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "/updatestudent/{id}")
    public void updateStudent(@PathVariable("id") UUID id,
                              @Valid @NonNull @RequestBody Student newStudent) {
        studentService.updateStudent(id, newStudent);
    }
}
