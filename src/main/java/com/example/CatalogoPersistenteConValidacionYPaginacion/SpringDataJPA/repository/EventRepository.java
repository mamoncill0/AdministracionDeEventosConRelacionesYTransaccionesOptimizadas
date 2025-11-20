package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.repository;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Integer> {
}
