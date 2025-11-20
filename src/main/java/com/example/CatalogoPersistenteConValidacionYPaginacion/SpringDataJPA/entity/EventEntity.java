package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events", uniqueConstraints = @UniqueConstraint(columnNames = "name_event"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Event name cannot be blank")
    @Column(name = "name_event", nullable = false)
    private String nameEvent;

    @NotNull(message = "Start time cannot be null")
    @Future(message = "Start time must be in the future")
    private LocalDateTime startTime;

    @NotNull(message = "End time cannot be null")
    @Future(message = "End time must be in the future")
    private LocalDateTime endTime;

    @Size(max = 500, message = "Description max 500 characters")
    private String description;

    @Min(value = 1, message = "Capacity must be at least 1")
    private int capacity;

    // RELACIÓN CORRECTA
    @ManyToOne
    @JoinColumn(name = "venue_id", nullable = false)
    private VenueEntity venue;
}
