package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
