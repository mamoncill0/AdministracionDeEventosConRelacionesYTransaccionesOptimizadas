package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.application.service.interfaces;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.in.dto.VenueDTO;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.exception.DuplicateResourceException;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IVenueService {

    VenueDTO create(VenueDTO dto) throws DuplicateResourceException;

    Page<VenueDTO> findAll(Pageable pageable);

    VenueDTO findById(Integer id) throws ResourceNotFoundException;

    VenueDTO update(Integer id, VenueDTO dto)
            throws ResourceNotFoundException, DuplicateResourceException;

    void delete(Integer id) throws ResourceNotFoundException;
}
