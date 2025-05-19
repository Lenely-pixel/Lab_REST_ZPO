package com.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@Entity
@Table(name = "zadanie")
public class Zadanie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zadanie_id")
    private Integer zadanieId;

    @NotBlank(message = "Pole nazwa nie może być puste!")
    @Size(min = 3, max = 100, message = "Nazwa musi zawierać od {min} do {max} znaków!")
    @Column(nullable = false, length = 100)
    private String nazwa;

    @Column(length = 300)
    private String opis;

    @Column
    private Integer kolejnosc;

    @Column(name = "dataczas_dodania", nullable = false, updatable = false)
    private LocalDateTime dataCzasDodania = LocalDateTime.now();

    // 🔁 Связь с projektem (Many-to-One)
    @ManyToOne
    @JoinColumn(name = "projekt_id")
    @JsonIgnoreProperties("zadania")  // избегаем рекурсии
    private Projekt projekt;

    // === Конструкторы ===
    public Zadanie() {}

    public Zadanie(String nazwa, String opis, Integer kolejnosc) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.kolejnosc = kolejnosc;
        this.dataCzasDodania = LocalDateTime.now();
    }

    // === Геттеры / Сеттеры ===
    // ПКМ → Generate → Getters and Setters → выбрать всё → OK
}
