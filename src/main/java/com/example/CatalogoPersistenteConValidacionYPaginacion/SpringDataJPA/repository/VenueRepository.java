package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.repository;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.entity.VenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends JpaRepository<VenueEntity, Integer> {

    boolean existsByName(String name);   // para evitar duplicados
}