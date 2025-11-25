package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.in.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VenueDTO {

    private Integer id;

    @NotBlank(message = "Venue name cannot be empty")
    @Size(min = 2, message = "Venue name must have at least 2 characters")
    private String name;

    @NotBlank(message = "City cannot be empty")
    private String city;

    @NotBlank(message = "Address cannot be empty")
    private String address;

    private int capacity;
}
