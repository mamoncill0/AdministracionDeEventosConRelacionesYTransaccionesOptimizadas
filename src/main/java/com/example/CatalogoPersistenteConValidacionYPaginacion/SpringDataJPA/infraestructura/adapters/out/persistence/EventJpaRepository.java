package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
// Se corrige la referencia a EventJpaEntity y se mantiene JpaSpecificationExecutor para las consultas dinámicas.
public interface EventJpaRepository extends JpaRepository<EventJpaEntity, Integer>, JpaSpecificationExecutor<EventJpaEntity> {

    // Se actualiza la consulta JPQL para que opere sobre EventJpaEntity.
    @Query("SELECT e FROM EventJpaEntity e WHERE e.venue.id = :venueId")
    List<EventJpaEntity> findByVenueId(@Param("venueId") Integer venueId);

    @Query("SELECT e FROM EventJpaEntity e WHERE e.startTime BETWEEN :startDate AND :endDate")
    List<EventJpaEntity> findByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    // El EntityGraph también debe hacer referencia a la entidad de persistencia.
    @Override
    @EntityGraph(attributePaths = "venue")
    List<EventJpaEntity> findAll();
}
