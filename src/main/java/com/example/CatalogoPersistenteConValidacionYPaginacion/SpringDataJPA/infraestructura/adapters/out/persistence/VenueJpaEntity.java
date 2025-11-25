package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
// Se corrige el nombre de la tabla a 'venues' para que coincida con la migración de Flyway.
@Table(name = "venues")
@Data
public class VenueJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String city;
    private String address;
    private int capacity;

    // Es importante también definir la relación inversa aquí para que JPA la conozca.
    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EventJpaEntity> events;
}
