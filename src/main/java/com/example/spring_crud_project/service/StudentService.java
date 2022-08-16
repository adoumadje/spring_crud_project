package com.example.spring_crud_project.service;

import com.example.spring_crud_project.dao.StudentDao;
import com.example.spring_crud_project.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class StudentService {
    private final StudentDao studentDao;

    @Autowired
    public StudentService(@Qualifier("postgres") StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public int addStudent(Student student) {
        return studentDao.insertStudent(student);
    }

    public List<Student> getAllSTudents() {
        return studentDao.selectAllStudents();
    }

    public Optional<Student> getStudentById(UUID id) {
        return studentDao.selectStudentById(id);
    }

    public List<Student> getStudentsByLastname(String lastname) {
        return studentDao.selectStudentsByLastName(lastname);
    }

    public int deleteStudent(UUID id) {
        return studentDao.deleteStudentById(id);
    }

    public int updateStudent(UUID id, Student newStudent) {
        return studentDao.updateStudentById(id, newStudent);
    }
}
