package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.controller;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.dto.EventDTO;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.exception.BadRequestException;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.exception.DuplicateResourceException;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.exception.ResourceNotFoundException;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.service.interfaces.IEventService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private IEventService eventService;

    // CREATE
    @PostMapping
    public EventDTO create(@Valid @RequestBody EventDTO dto)
            throws DuplicateResourceException, BadRequestException {

        return eventService.create(dto);
    }

    // GET with pagination + optional filters
    @GetMapping
    public Page<EventDTO> getAll(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) LocalDateTime startDate,
            Pageable pageable
    ) {
        return eventService.getAll(city, category, startDate, pageable);
    }

    // GET by ID
    @GetMapping("/{id}")
    public EventDTO getById(@PathVariable Integer id)
            throws ResourceNotFoundException {

        return eventService.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public EventDTO update(
            @PathVariable Integer id,
            @Valid @RequestBody EventDTO dto)
            throws ResourceNotFoundException, DuplicateResourceException {

        return eventService.update(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id)
            throws ResourceNotFoundException {

        eventService.delete(id);
    }
}
