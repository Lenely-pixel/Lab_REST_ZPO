package com.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "projekt")
public class Projekt {

    public void setProjektId(Integer projektId) {
        this.projektId = projektId;
    }

    public Integer getProjektId() {
        return projektId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projekt_id")
    private Integer projektId;

    @NotBlank(message = "Pole nazwa nie może być puste!")
    @Size(min = 3, max = 50, message = "Nazwa musi zawierać od {min} do {max} znaków!")
    @Column(nullable = false, length = 50)
    private String nazwa;

    public String getNazwa() {
        return nazwa;
    }

    @Column(length = 200)
    private String opis;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setDataOddania(LocalDate dataOddania) {
        this.dataOddania = dataOddania;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setStudenci(List<Student> studenci) {
        this.studenci = studenci;
    }

    public void setZadania(List<Zadanie> zadania) {
        this.zadania = zadania;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public List<Zadanie> getZadania() {
        return zadania;
    }

    public List<Student> getStudenci() {
        return studenci;
    }

    public String getOpis() {
        return opis;
    }

    public LocalDate getDataOddania() {
        return dataOddania;
    }

    private LocalDate dataOddania;

    @Column(name = "dataczas_utworzenia", nullable = false, updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "dataczas_modyfikacji")
    private LocalDateTime lastModifiedDate;

    @OneToMany(mappedBy = "projekt")
    @JsonIgnoreProperties("projekt")
    private List<Zadanie> zadania;

    @ManyToMany
    @JoinTable(
            name = "projekt_student",
            joinColumns = @JoinColumn(name = "projekt_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> studenci;

    // === КОНСТРУКТОРЫ ===

    public Projekt() {}

    public Projekt(String nazwa, String opis, LocalDate dataOddania) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.dataOddania = dataOddania;
        this.createdDate = LocalDateTime.now();
    }

    public Projekt(Integer id, String nazwa, String opis, LocalDateTime utw, LocalDate oddania) {
        this.projektId = id;
        this.nazwa = nazwa;
        this.opis = opis;
        this.createdDate = utw;
        this.dataOddania = oddania;
    }

    // === GETTERY / SETTERY ===
    // ПКМ → Generate → Getters and Setters → Выделить все → OK
}
