package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.entity.EventEntity;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.entity.VenueEntity;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.repository.EventRepository;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence.EventJpaEntity;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence.EventJpaRepository;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence.VenueJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EventRepositoryAdapter implements EventRepository {

    private final EventJpaRepository eventJpaRepository;
    private final VenueRepositoryAdapter venueRepositoryAdapter; // Para mapear el venue

    public EventRepositoryAdapter(EventJpaRepository eventJpaRepository, VenueRepositoryAdapter venueRepositoryAdapter) {
        this.eventJpaRepository = eventJpaRepository;
        this.venueRepositoryAdapter = venueRepositoryAdapter;
    }

    @Override
    public EventEntity save(EventEntity eventEntity) {
        EventJpaEntity eventJpaEntity = toJpaEntity(eventEntity);
        EventJpaEntity savedEntity = eventJpaRepository.save(eventJpaEntity);
        return toDomainEntity(savedEntity);
    }

    @Override
    public Optional<EventEntity> findById(Integer id) {
        return eventJpaRepository.findById(id).map(this::toDomainEntity);
    }

    @Override
    public void deleteById(Integer id) {
        eventJpaRepository.deleteById(id);
    }

    @Override
    public Page<EventEntity> findAll(Pageable pageable) {
        return eventJpaRepository.findAll(pageable).map(this::toDomainEntity);
    }

    // --- MAPPERS ---

    private EventEntity toDomainEntity(EventJpaEntity jpaEntity) {
        if (jpaEntity == null) return null;
        VenueEntity venueDomain = venueRepositoryAdapter.toDomainEntity(jpaEntity.getVenue());
        return new EventEntity(
                jpaEntity.getId(),
                jpaEntity.getNameEvent(),
                jpaEntity.getStartTime(),
                jpaEntity.getEndTime(),
                jpaEntity.getDescription(),
                jpaEntity.getCapacity(),
                venueDomain
        );
    }

    public EventJpaEntity toJpaEntity(EventEntity domainEntity) {
        if (domainEntity == null) return null;
        EventJpaEntity jpaEntity = new EventJpaEntity();
        jpaEntity.setId(domainEntity.getId());
        jpaEntity.setNameEvent(domainEntity.getNameEvent());
        jpaEntity.setStartTime(domainEntity.getStartTime());
        jpaEntity.setEndTime(domainEntity.getEndTime());
        jpaEntity.setDescription(domainEntity.getDescription());
        jpaEntity.setCapacity(domainEntity.getCapacity());
        
        VenueJpaEntity venueJpa = venueRepositoryAdapter.toJpaEntity(domainEntity.getVenue());
        jpaEntity.setVenue(venueJpa);
        
        return jpaEntity;
    }
}
