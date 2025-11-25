package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.repository;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.entity.VenueEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Puerto de dominio para Venue.
 */
public interface VenueRepository {
    VenueEntity save(VenueEntity venueEntity);
    Optional<VenueEntity> findById(Integer id);
    void deleteById(Integer id);
    Page<VenueEntity> findAll(Pageable pageable);
    boolean existsByName(String name);
}
