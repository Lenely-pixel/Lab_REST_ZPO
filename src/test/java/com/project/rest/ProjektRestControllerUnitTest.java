package com.project.rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.project.controller.ProjektRestController;
import com.project.model.Projekt;
import com.project.service.ProjektService;

@ExtendWith(MockitoExtension.class)
public class ProjektRestControllerUnitTest {

    @Mock
    private ProjektService mockProjektService;

    @InjectMocks
    private ProjektRestController projectController;

    @Test
    void getProject_whenValidId_shouldReturnGivenProject() {
        Projekt projekt = new Projekt(1, "Nazwa1", "Opis1", LocalDateTime.now(), LocalDate.of(2024, 6, 7));
        when(mockProjektService.getProjekt(projekt.getProjektId())).thenReturn(Optional.of(projekt));

        ResponseEntity<Projekt> response = projectController.getProjekt(projekt.getProjektId());

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(projekt, response.getBody())
        );
    }

    @Test
    void getProject_whenInvalidId_shouldReturnNotFound() {
        Integer projektId = 2;
        when(mockProjektService.getProjekt(projektId)).thenReturn(Optional.empty());

        ResponseEntity<Projekt> response = projectController.getProjekt(projektId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getProjects_shouldReturnPageWithProjects() {
        List<Projekt> list = List.of(
                new Projekt(1, "Nazwa1", "Opis1", LocalDateTime.now(), LocalDate.of(2024, 6, 7)),
                new Projekt(2, "Nazwa2", "Opis2", LocalDateTime.now(), LocalDate.of(2024, 6, 7)),
                new Projekt(3, "Nazwa3", "Opis3", LocalDateTime.now(), LocalDate.of(2024, 6, 7))
        );
        PageRequest pageable = PageRequest.of(1, 5);
        Page<Projekt> page = new PageImpl<>(list, pageable, 5);
        when(mockProjektService.getProjekty(pageable)).thenReturn(page);

        Page<Projekt> result = projectController.getProjekty(pageable);

        assertNotNull(result);
        assertThat(result.getContent(), hasSize(3));
    }

    @Test
    void createProject_whenValidData_shouldCreateProject() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Projekt projekt = new Projekt(1, "Nazwa1", "Opis1", LocalDateTime.now(), LocalDate.of(2024, 6, 7));

        when(mockProjektService.setProjekt(any(Projekt.class))).thenReturn(projekt);

        ResponseEntity<Void> response = projectController.createProjekt(projekt);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertThat(response.getHeaders().getLocation().getPath(), is("/" + projekt.getProjektId()));
    }

    @Test
    void updateProject_whenValidData_shouldUpdateProject() {
        Projekt projekt = new Projekt(1, "Nazwa1", "Opis1", LocalDateTime.now(), LocalDate.of(2024, 6, 7));

        when(mockProjektService.getProjekt(projekt.getProjektId())).thenReturn(Optional.of(projekt));

        ResponseEntity<Void> response = projectController.updateProjekt(projekt, projekt.getProjektId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteProject_whenValidId_shouldDeleteProject() {
        Projekt projekt = new Projekt(1, "Nazwa1", "Opis1", LocalDateTime.now(), LocalDate.of(2024, 6, 7));
        when(mockProjektService.getProjekt(projekt.getProjektId())).thenReturn(Optional.of(projekt));

        ResponseEntity<Void> response = projectController.deleteProjekt(projekt.getProjektId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteProject_whenInvalidId_shouldReturnNotFound() {
        Integer projektId = 99;
        when(mockProjektService.getProjekt(projektId)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = projectController.deleteProjekt(projektId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

