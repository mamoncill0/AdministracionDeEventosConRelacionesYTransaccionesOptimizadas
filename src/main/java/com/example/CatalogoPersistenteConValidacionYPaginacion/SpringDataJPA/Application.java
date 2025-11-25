package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
