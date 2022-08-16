package com.example.spring_crud_project.dao;

import com.example.spring_crud_project.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository("postgres")
public class StudentDataAccessService implements StudentDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertStudent(UUID id, Student student) {
        return jdbcTemplate.update(
                "INSERT INTO student VALUES (?, ?, ?, ?)",
                id,
                student.getFirstname(),
                student.getLastname(),
                student.getGrade()
        );
    }

    @Override
    public List<Student> selectAllStudents() {
        final String sql = "SELECT id, firstname, lastname, grade FROM student";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            int grade = resultSet.getInt("grade");
            return new Student(id, firstname, lastname, grade);
        });
    }

    @Override
    public Optional<Student> selectStudentById(UUID id) {
        final String sql = "SELECT id, firstname, lastname, grade FROM student" +
                " WHERE id = ?";
        Student student = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID studentId = UUID.fromString(
                            resultSet.getString("id")
                    );
                    String firstname = resultSet.getString("firstname");
                    String lastname = resultSet.getString("lastname");
                    int grade = resultSet.getInt("grade");

                    return new Student(studentId, firstname, lastname, grade);
                }
        );
        return Optional.ofNullable(student);
    }

    @Override
    public List<Student> selectStudentsByLastName(String lastname) {

        final String sql = "SELECT id, firstname, lastname, grade FROM student" +
                " WHERE lastname = '"+ lastname+"'";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String firstname = resultSet.getString("firstname");
            String stdLastname = resultSet.getString("lastname");
            int grade = resultSet.getInt("grade");

            return new Student(id, firstname, stdLastname, grade);
        });
    }

    @Override
    public int deleteStudentById(UUID id) {
        return jdbcTemplate.update(
                "DELETE FROM student WHERE id = ?",
                id
        );
    }

    @Override
    public int updateStudentById(UUID id, Student student) {
        return jdbcTemplate.update(
                "UPDATE student" +
                        " SET firstname = ?, lastname = ?, grade = ?" +
                        " WHERE id = ?",
                student.getFirstname(),
                student.getLastname(),
                student.getGrade(),
                id
        );
    }
}
