package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.application.service;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.in.dto.EventDTO;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.entity.EventEntity;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.entity.VenueEntity;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.exception.ResourceNotFoundException;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.repository.EventRepository;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.repository.VenueRepository;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.application.service.interfaces.IEventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
// @Transactional a nivel de clase: Todos los métodos públicos serán transaccionales por defecto.
// Si un mét0do lanza una excepción (no capturada), la transacción se revierte (rollback).
// Esto es ideal para los métodos de escritura (create, update, delete).
@Transactional
public class EventService implements IEventService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;

    public EventService(EventRepository eventRepository,
                        VenueRepository venueRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }

    // -------------------------------------
    // CREATE
    // -------------------------------------
    @Override
    // Este mét0do hereda el @Transactional de la clase. Es una transacción de escritura.
    public EventDTO create(EventDTO dto) {

        VenueEntity venue = venueRepository.findById(dto.getVenueId())
                .orElseThrow(() -> new ResourceNotFoundException("Venue not found"));

        EventEntity entity = toEntity(dto, venue);
        entity = eventRepository.save(entity);

        return toDTO(entity);
    }

    // -------------------------------------
    // UPDATE
    // -------------------------------------
    @Override
    // Hereda el @Transactional de la clase.
    public EventDTO update(int id, EventDTO dto) {

        EventEntity existing = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        VenueEntity venue = venueRepository.findById(dto.getVenueId())
                .orElseThrow(() -> new ResourceNotFoundException("Venue not found"));

        existing.setNameEvent(dto.getNameEvent());
        existing.setStartTime(dto.getStartTime());
        existing.setEndTime(dto.getEndTime());
        existing.setDescription(dto.getDescription());
        existing.setCapacity(dto.getCapacity());
        existing.setVenue(venue);

        eventRepository.save(existing);

        return toDTO(existing);
    }

    // -------------------------------------
    // DELETE
    // -------------------------------------
    @Override
    // Hereda el @Transactional de la clase.
    public void delete(int id) {
        if (eventRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Event not found");
        }

        eventRepository.deleteById(id);
    }

    // -------------------------------------
    // GET BY ID
    // -------------------------------------
    @Override
    // @Transactional(readOnly = true): Sobrescribe la configuración de la clase.
    // Indica que esta transacción es de solo lectura, lo que permite a JPA y al proveedor de BD
    // aplicar optimizaciones de rendimiento.
    @Transactional(readOnly = true)
    public EventDTO getById(int id) {
        EventEntity entity = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        return toDTO(entity);
    }

    // -------------------------------------
    // GET ALL
    // -------------------------------------
    @Override
    @Transactional(readOnly = true)
    public Page<EventDTO> getAll(String city,
                                 String category,
                                 LocalDateTime startDate,
                                 Pageable pageable) {

        return eventRepository.findAll(pageable)
                .map(this::toDTO);
    }


    // -------------------------------------
    // MAPPERS
    // -------------------------------------

    private EventDTO toDTO(EventEntity e) {
        EventDTO dto = new EventDTO();
        dto.setId(e.getId());
        dto.setNameEvent(e.getNameEvent());
        dto.setStartTime(e.getStartTime());
        dto.setEndTime(e.getEndTime());
        dto.setDescription(e.getDescription());
        dto.setCapacity(e.getCapacity());
        if (e.getVenue() != null) {
            dto.setVenueId(e.getVenue().getId());
        }
        return dto;
    }

    private EventEntity toEntity(EventDTO dto, VenueEntity venue) {
        EventEntity e = new EventEntity();
        e.setId(dto.getId());
        e.setNameEvent(dto.getNameEvent());
        e.setStartTime(dto.getStartTime());
        e.setEndTime(dto.getEndTime());
        e.setDescription(dto.getDescription());
        e.setCapacity(dto.getCapacity());
        e.setVenue(venue);
        return e;
    }
}
