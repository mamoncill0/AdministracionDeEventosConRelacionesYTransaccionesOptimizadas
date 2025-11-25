package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.entity.VenueEntity;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.repository.VenueRepository;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence.VenueJpaEntity;
import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence.VenueJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VenueRepositoryAdapter implements VenueRepository {

    private final VenueJpaRepository venueJpaRepository;

    public VenueRepositoryAdapter(VenueJpaRepository venueJpaRepository) {
        this.venueJpaRepository = venueJpaRepository;
    }

    @Override
    public VenueEntity save(VenueEntity venueEntity) {
        VenueJpaEntity venueJpaEntity = toJpaEntity(venueEntity);
        VenueJpaEntity savedEntity = venueJpaRepository.save(venueJpaEntity);
        return toDomainEntity(savedEntity);
    }

    @Override
    public Optional<VenueEntity> findById(Integer id) {
        return venueJpaRepository.findById(id).map(this::toDomainEntity);
    }

    @Override
    public void deleteById(Integer id) {
        venueJpaRepository.deleteById(id);
    }

    @Override
    public Page<VenueEntity> findAll(Pageable pageable) {
        return venueJpaRepository.findAll(pageable).map(this::toDomainEntity);
    }

    @Override
    public boolean existsByName(String name) {
        return venueJpaRepository.existsByName(name);
    }

    // --- MAPPERS ---

    public VenueEntity toDomainEntity(VenueJpaEntity jpaEntity) {
        if (jpaEntity == null) return null;
        return new VenueEntity(
                jpaEntity.getId(),
                jpaEntity.getName(),
                jpaEntity.getCity(),
                jpaEntity.getAddress(),
                jpaEntity.getCapacity()
        );
    }

    public VenueJpaEntity toJpaEntity(VenueEntity domainEntity) {
        if (domainEntity == null) return null;
        VenueJpaEntity jpaEntity = new VenueJpaEntity();
        jpaEntity.setId(domainEntity.getId());
        jpaEntity.setName(domainEntity.getName());
        jpaEntity.setCity(domainEntity.getCity());
        jpaEntity.setAddress(domainEntity.getAddress());
        jpaEntity.setCapacity(domainEntity.getCapacity());
        return jpaEntity;
    }
}
