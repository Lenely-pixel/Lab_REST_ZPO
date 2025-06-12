package com.project.repository;

import com.project.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Page<Student> findByNazwiskoContainingIgnoreCase(String nazwisko, Pageable pageable);
}

