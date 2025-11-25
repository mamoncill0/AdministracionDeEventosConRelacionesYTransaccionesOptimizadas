package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.service.interfaces;


import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.dto.EventDTO;
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
