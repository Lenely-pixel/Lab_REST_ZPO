package com.project.controller;

import com.project.model.Projekt;
import com.project.service.ProjektService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class ProjektRestController {

    private static final Logger logger = LoggerFactory.getLogger(ProjektRestController.class);

    private final ProjektService projektService;

    @Autowired
    public ProjektRestController(ProjektService projektService) {
        this.projektService = projektService;
    }

    @GetMapping("/projekty")
    public Page<Projekt> getProjekty(Pageable pageable) {
        return projektService.getProjekty(pageable);
    }

    @GetMapping("/projekty/{projektId}")
    public ResponseEntity<Projekt> getProjekt(@PathVariable Integer projektId) {
        logger.info("Saving project: {}", projektId);
        return projektService.getProjekt(projektId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*@PostMapping("/projekty")
    public ResponseEntity<Void> createProjekt(@Valid @RequestBody Projekt projekt) {
        Projekt createdProjekt = projektService.setProjekt(projekt);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProjekt.getProjektId())
                .toUri();
        return ResponseEntity.created(location).build();
    }*/

    @PostMapping(path = "/projekty")
    public ResponseEntity<Void> createProjekt(@Valid @RequestBody Projekt projekt) {
        logger.info("POST /projekty -> {}", projekt);
        Projekt createdProjekt = projektService.setProjekt(projekt);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{projektId}").buildAndExpand(createdProjekt.getProjektId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/projekty/{projektId}")
    public ResponseEntity<Void> updateProjekt(@Valid @RequestBody Projekt projekt,
                                              @PathVariable Integer projektId) {
        Optional<Projekt> optionalProjekt = projektService.getProjekt(projektId);
        if (optionalProjekt.isPresent()) {
            projekt.setProjektId(projektId);
            projektService.setProjekt(projekt);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/projekty/{projektId}")
    public ResponseEntity<Void> deleteProjekt(@PathVariable Integer projektId) {

        logger.info("Deleting project by ID: {}", projektId);
        Optional<Projekt> optionalProjekt = projektService.getProjekt(projektId);
        if (optionalProjekt.isPresent()) {
            projektService.deleteProjekt(projektId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }



    }
}

