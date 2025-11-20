package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.service;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.dto.VenueDTO;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.entity.VenueEntity;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.exception.DuplicateResourceException;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.exception.ResourceNotFoundException;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.repository.VenueRepository;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.service.interfaces.IVenueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VenueService implements IVenueService {

    @Autowired
    private VenueRepository repository;

    // -------------------------------------
    // CREATE
    // -------------------------------------
    @Override
    public VenueDTO create(@Valid VenueDTO dto) throws DuplicateResourceException {

        if (repository.existsByName(dto.getName())) {
            throw new DuplicateResourceException("Ya existe un venue con este nombre");
        }

        VenueEntity entity = toEntity(dto);
        entity = repository.save(entity);

        return toDTO(entity);
    }

    // -------------------------------------
    // FIND ALL (paginado)
    // -------------------------------------
    @Override
    public Page<VenueDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toDTO);
    }

    // -------------------------------------
    // FIND BY ID
    // -------------------------------------
    @Override
    public VenueDTO findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Venue no encontrado"));
    }

    // -------------------------------------
    // UPDATE
    // -------------------------------------
    @Override
    public VenueDTO update(Integer id, @Valid VenueDTO dto)
            throws ResourceNotFoundException, DuplicateResourceException {

        VenueEntity existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venue no encontrado"));

        // Validación de nombre duplicado
        if (!existing.getName().equals(dto.getName()) &&
                repository.existsByName(dto.getName())) {

            throw new DuplicateResourceException("Ya existe un venue con este nombre");
        }

        existing.setName(dto.getName());
        existing.setCity(dto.getCity());
        existing.setAddress(dto.getAddress());
        existing.setCapacity(dto.getCapacity());

        repository.save(existing);

        return toDTO(existing);
    }

    // -------------------------------------
    // DELETE
    // -------------------------------------
    @Override
    public void delete(Integer id) throws ResourceNotFoundException {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Venue no encontrado");
        }

        repository.deleteById(id);
    }

    // -------------------------------------
    // MAPPERS
    // -------------------------------------

    private VenueDTO toDTO(VenueEntity e) {
        VenueDTO dto = new VenueDTO();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setCity(e.getCity());
        dto.setAddress(e.getAddress());
        dto.setCapacity(e.getCapacity());
        return dto;
    }

    private VenueEntity toEntity(VenueDTO dto) {
        VenueEntity e = new VenueEntity();
        e.setId(dto.getId());
        e.setName(dto.getName());
        e.setCity(dto.getCity());
        e.setAddress(dto.getAddress());
        e.setCapacity(dto.getCapacity());
        return e;
    }
}
