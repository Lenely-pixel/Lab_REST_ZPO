package com.project.service;

import com.project.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Integer id);

    Optional<Student> getStudent(Integer studentId);

    Student saveStudent(Student student);
    void deleteStudent(Integer id);

    Page<Student> getAllStudents(Pageable pageable);

    Page<Student> searchByNazwisko(String nazwisko, Pageable pageable);
}
