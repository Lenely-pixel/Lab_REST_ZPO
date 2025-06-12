package com.project.controller;

import com.project.model.Projekt;
import com.project.service.ProjektService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Controller
@RequestMapping("/projekty")
public class ProjektWebController {

    private final ProjektService projektService;

    @Autowired
    public ProjektWebController(ProjektService projektService) {
        this.projektService = projektService;
    }

    // GET /projekty — показать список
    @GetMapping
    public String getProjekty(Model model) {
        List<Projekt> projekty = projektService.getProjekty(Pageable.unpaged()).getContent();
        model.addAttribute("projekty", projekty);
        return "projekty"; // шаблон templates/projekty.html
    }

    // GET /projekty/edit/{id} — открыть форму редактирования
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Projekt projekt = projektService.getProjekt(id)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono projektu o id: " + id));
        model.addAttribute("projekt", projekt);
        return "edit"; // шаблон templates/edit.html
    }

    // POST /projekty — сохранить изменения (и новые, и редактирование)
    @PostMapping
    public String saveProjekt(@ModelAttribute Projekt projekt) {
        projektService.setProjekt(projekt);
        return "redirect:/projekty";
    }
}


