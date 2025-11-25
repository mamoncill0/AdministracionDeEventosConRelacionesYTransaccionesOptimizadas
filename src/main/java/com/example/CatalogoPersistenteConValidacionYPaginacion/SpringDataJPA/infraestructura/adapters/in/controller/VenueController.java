package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.in.controller;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.in.dto.VenueDTO;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.exception.DuplicateResourceException;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.exception.ResourceNotFoundException;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.application.service.interfaces.IVenueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venues")
public class VenueController {

    @Autowired
    private IVenueService venueService;

    // CREATE
    @PostMapping
    public VenueDTO create(@Valid @RequestBody VenueDTO dto)
            throws DuplicateResourceException {
        return venueService.create(dto);
    }

    // GET ALL (PAGINADO)
    @GetMapping
    public Page<VenueDTO> getAll(Pageable pageable) {
        return venueService.findAll(pageable);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public VenueDTO getById(@PathVariable Integer id)
            throws ResourceNotFoundException {
        return venueService.findById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public VenueDTO update(
            @PathVariable Integer id,
            @Valid @RequestBody VenueDTO dto)
            throws ResourceNotFoundException, DuplicateResourceException {

        return venueService.update(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id)
            throws ResourceNotFoundException {

        venueService.delete(id);
    }
}
