package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// Se corrige la referencia para que apunte a VenueJpaEntity, que es la entidad de persistencia.
public interface VenueJpaRepository extends JpaRepository<VenueJpaEntity, Integer> {
    boolean existsByName(String name);

    // Las consultas JPQL operan sobre las entidades JPA, por lo que también se actualiza aquí.
    @Query("SELECT v FROM VenueJpaEntity v WHERE v.capacity >= :minCapacity")
    List<VenueJpaEntity> findByCapacityGreaterThanEqual(@Param("minCapacity") int minCapacity);

    List<VenueJpaEntity> findByCity(String city);
}
