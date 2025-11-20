package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
