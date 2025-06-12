package com.project.controller;

import com.project.model.Student;
import com.project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/studenty")
public class StudentWebController {

    private final StudentService studentService;

    @Autowired
    public StudentWebController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 🔹 Отображение списка студентов
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("studenty", studentService.getAllStudents(org.springframework.data.domain.Pageable.unpaged()).getContent());
        return "students";
    }

    // 🔹 Показ формы для добавления нового
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    // 🔹 Сохранение нового или обновленного студента
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "redirect:/studenty";
    }

    // 🔹 Редактирование
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Student student = studentService.getStudent(id)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono studenta o id: " + id));
        model.addAttribute("student", student);
        return "student-form";
    }

    // 🔹 Удаление
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id) {
        studentService.deleteStudent(id);
        return "redirect:/studenty";
    }
}
