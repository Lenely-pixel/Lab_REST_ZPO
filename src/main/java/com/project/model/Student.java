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

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getNrIndeksu() {
        return nrIndeksu;
    }

    public void setNrIndeksu(String nrIndeksu) {
        this.nrIndeksu = nrIndeksu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStacjonarny() {
        return stacjonarny;
    }

    public void setStacjonarny(Boolean stacjonarny) {
        this.stacjonarny = stacjonarny;
    }

    public List<Projekt> getProjekty() {
        return projekty;
    }

    public void setProjekty(List<Projekt> projekty) {
        this.projekty = projekty;
    }
// === Gettery / Settery ===
    // ПКМ → Generate → выбери всё → OK
}

