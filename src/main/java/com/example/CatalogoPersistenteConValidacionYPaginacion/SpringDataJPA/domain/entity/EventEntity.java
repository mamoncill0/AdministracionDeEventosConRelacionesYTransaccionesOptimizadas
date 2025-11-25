package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.entity;

import jakarta.persistence.*;
import java.util.Date;

// @Entity: Marca esta clase como una entidad JPA, lo que significa que se mapeará a una tabla en la base de datos.
@Entity
// @Table: Especifica el nombre de la tabla en la base de datos.
@Table(name = "events")
public class EventEntity {

    // @Id: Designa este campo como la clave primaria de la tabla.
    @Id
    // @GeneratedValue: Configura la forma en que se genera el valor de la clave primaria.
    // GenerationType.IDENTITY: Indica que la base de datos se encargará de generar e incrementar el valor.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // @Column: Especifica el mapeo entre el atributo y la columna de la tabla.
    @Column(name = "name_event", nullable = false, length = 100)
    private String nameEvent;

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "end_time", nullable = false)
    private Date endTime;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private int capacity;

    // @ManyToOne: Define una relación de muchos a uno con la entidad VenueEntity.
    // fetch = FetchType.LAZY: La entidad VenueEntity no se cargará de la base de datos hasta que se acceda a ella explícitamente.
    // Esto es bueno para el rendimiento, ya que evita cargar datos innecesarios.
    @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn: Especifica la columna de clave foránea en la tabla 'events'.
    // name = "venue_id": Nombre de la columna de clave foránea.
    // nullable = false: La relación no puede ser nula (un evento siempre debe tener un lugar).
    // referencedColumnName = "id": Columna a la que hace referencia en la tabla 'venues'.
    @JoinColumn(name = "venue_id", nullable = false, referencedColumnName = "id")
    private VenueEntity venue;

    public EventEntity() {
    }

    public EventEntity(Integer id, String nameEvent, Date startTime, Date endTime,
                       String description, int capacity, VenueEntity venue) {
        this.id = id;
        this.nameEvent = nameEvent;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.capacity = capacity;
        this.venue = venue;
    }

    // Getters y Setters...

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public VenueEntity getVenue() {
        return venue;
    }

    public void setVenue(VenueEntity venue) {
        this.venue = venue;
    }

    @Override
    public String toString() {
        return "EventEntity{" +
                "id=" + id +
                ", nameEvent='" + nameEvent + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", description='" + description + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
