package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.repository;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.entity.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * 1. ESTO ES EL PUERTO (EN LA CAPA DE DOMINIO)
 * Define el contrato que la capa de aplicación necesita, sin depender de ninguna tecnología.
 */
public interface EventRepository {
    EventEntity save(EventEntity eventEntity);
    Optional<EventEntity> findById(Integer id);
    void deleteById(Integer id);
    Page<EventEntity> findAll(Pageable pageable);
}
