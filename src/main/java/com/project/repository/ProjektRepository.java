package com.project.repository;

import com.project.model.Projekt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjektRepository extends JpaRepository<Projekt, Integer> {
}
