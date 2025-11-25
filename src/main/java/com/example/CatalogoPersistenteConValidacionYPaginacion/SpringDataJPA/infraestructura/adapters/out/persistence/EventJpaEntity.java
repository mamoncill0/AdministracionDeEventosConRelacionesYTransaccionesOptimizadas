package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
// Se corrige el nombre de la tabla a 'events' para que coincida con la migración de Flyway.
@Table(name = "events")
@Data
public class EventJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Se recomienda especificar los nombres de las columnas para evitar inconsistencias.
    @Column(name = "name_event")
    private String nameEvent;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    private String description;

    private int capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id")
    private VenueJpaEntity venue;
}
