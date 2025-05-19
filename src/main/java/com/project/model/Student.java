package com.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@Entity
@Table(
        name = "student",
        indexes = {
                @Index(name = "idx_nazwisko", columnList = "nazwisko"),
                @Index(name = "idx_nr_indeksu", columnList = "nr_indeksu", unique = true)
        }
)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;

    @NotBlank(message = "Imię nie może być puste!")
    @Column(nullable = false)
    private String imie;

    @NotBlank(message = "Nazwisko nie może być puste!")
    @Column(nullable = false)
    private String nazwisko;

    @NotBlank(message = "Numer indeksu nie może być pusty!")
    @Column(name = "nr_indeksu", nullable = false, unique = true)
    private String nrIndeksu;

    private String email;

    private Boolean stacjonarny;

    // 🔁 Связь Many-to-Many с Projekt
    @ManyToMany(mappedBy = "studenci")
    @JsonIgnoreProperties("studenci")
    private List<Projekt> projekty;

    // === Конструкторы ===
    public Student() {}

    public Student(String imie, String nazwisko, String nrIndeksu, Boolean stacjonarny) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nrIndeksu = nrIndeksu;
        this.stacjonarny = stacjonarny;
    }

    public Student(String imie, String nazwisko, String nrIndeksu, String email, Boolean stacjonarny) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nrIndeksu = nrIndeksu;
        this.email = email;
        this.stacjonarny = stacjonarny;
    }

    // === Gettery / Settery ===
    // ПКМ → Generate → выбери всё → OK
}
