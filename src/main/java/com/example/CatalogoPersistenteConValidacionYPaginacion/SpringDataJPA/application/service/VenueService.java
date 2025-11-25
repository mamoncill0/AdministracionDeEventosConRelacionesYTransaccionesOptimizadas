package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.application.service;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.in.dto.VenueDTO;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.entity.VenueEntity;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.exception.DuplicateResourceException;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.exception.ResourceNotFoundException;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.repository.VenueRepository;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.application.service.interfaces.IVenueService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VenueService implements IVenueService {

    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    // -------------------------------------
    // CREATE
    // -------------------------------------
    @Override
    public VenueDTO create(@Valid VenueDTO dto) throws DuplicateResourceException {

        if (venueRepository.existsByName(dto.getName())) {
            throw new DuplicateResourceException("Ya existe un venue con este nombre");
        }

        VenueEntity entity = toEntity(dto);
        entity = venueRepository.save(entity);

        return toDTO(entity);
    }

    // -------------------------------------
    // FIND ALL (paginado)
    // -------------------------------------
    @Override
    public Page<VenueDTO> findAll(Pageable pageable) {
        return venueRepository.findAll(pageable).map(this::toDTO);
    }

    // -------------------------------------
    // FIND BY ID
    // -------------------------------------
    @Override
    public VenueDTO findById(Integer id) throws ResourceNotFoundException {
        return venueRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Venue no encontrado"));
    }

    // -------------------------------------
    // UPDATE
    // -------------------------------------
    @Override
    public VenueDTO update(Integer id, @Valid VenueDTO dto)
            throws ResourceNotFoundException, DuplicateResourceException {

        VenueEntity existing = venueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venue no encontrado"));

        // Validación de nombre duplicado
        if (!existing.getName().equals(dto.getName()) &&
                venueRepository.existsByName(dto.getName())) {

            throw new DuplicateResourceException("Ya existe un venue con este nombre");
        }

        existing.setName(dto.getName());
        existing.setCity(dto.getCity());
        existing.setAddress(dto.getAddress());
        existing.setCapacity(dto.getCapacity());

        venueRepository.save(existing);

        return toDTO(existing);
    }

    // -------------------------------------
    // DELETE
    // -------------------------------------
    @Override
    public void delete(Integer id) throws ResourceNotFoundException {

        if (venueRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Venue no encontrado");
        }

        venueRepository.deleteById(id);
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
