package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.exception;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
