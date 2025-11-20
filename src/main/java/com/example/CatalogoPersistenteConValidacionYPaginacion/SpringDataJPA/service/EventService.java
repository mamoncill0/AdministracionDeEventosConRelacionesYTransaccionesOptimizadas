package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.service;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.dto.EventDTO;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.entity.EventEntity;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.entity.VenueEntity;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.exception.ResourceNotFoundException;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.repository.EventRepository;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.repository.VenueRepository;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.service.interfaces.IEventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
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
    public void delete(int id) {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event not found");
        }

        eventRepository.deleteById(id);
    }

    // -------------------------------------
    // GET BY ID
    // -------------------------------------
    @Override
    public EventDTO getById(int id) {
        EventEntity entity = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        return toDTO(entity);
    }

    // -------------------------------------
    // GET ALL
    // -------------------------------------
    @Override
    public Page<EventDTO> getAll(String city,
                                 String category,
                                 LocalDateTime startDate,
                                 Pageable pageable) {

        // Por ahora sin filtros, devolvemos paginado
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
        dto.setVenueId(e.getVenue().getId());
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
