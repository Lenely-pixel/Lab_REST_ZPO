package com.project.repository;

import com.project.model.Zadanie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZadanieRepository extends JpaRepository<Zadanie, Integer> {
}
