package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.entity;

import jakarta.persistence.*;
import java.util.List;

// @Entity: Marca esta clase como una entidad JPA.
@Entity
// @Table: Especifica el nombre de la tabla en la base de datos.
@Table(name = "venues")
public class VenueEntity {

    // @Id: Clave primaria.
    @Id
    // @GeneratedValue: Generación automática del valor de la clave.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false)
    private int capacity;

    // @OneToMany: Define una relación de uno a muchos con EventEntity.
    // mappedBy = "venue": Indica que la relación es gestionada por el campo 'venue' en EventEntity.
    // Esto evita la creación de una tabla intermedia y le dice a JPA que la clave foránea está en la tabla 'events'.
    // cascade = CascadeType.ALL: Cualquier cambio en VenueEntity (persist, merge, remove) se propagará a los EventEntity asociados.
    // orphanRemoval = true: Si un EventEntity se elimina de la lista 'events', se eliminará de la base de datos.
    // fetch = FetchType.LAZY: La lista de eventos no se cargará hasta que se acceda a ella, mejorando el rendimiento.
    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EventEntity> events;

    public VenueEntity() {
    }

    public VenueEntity(Integer id, String name, String city, String address, int capacity) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.capacity = capacity;
    }

    // Getters y Setters...

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<EventEntity> getEvents() {
        return events;
    }

    public void setEvents(List<EventEntity> events) {
        this.events = events;
    }
}
