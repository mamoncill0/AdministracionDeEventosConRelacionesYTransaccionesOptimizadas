package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.application.service.interfaces;


import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.in.dto.EventDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface IEventService {
    EventDTO create(EventDTO event);
    EventDTO update(int id, EventDTO event);
    void delete(int id);
    EventDTO getById(int id);
    Page<EventDTO> getAll(String city, String category, LocalDateTime startDate, Pageable pageable);
}
