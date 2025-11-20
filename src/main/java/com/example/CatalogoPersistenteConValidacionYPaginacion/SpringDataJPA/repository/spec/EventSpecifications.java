package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.repository.spec;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.entity.EventEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class EventSpecifications {

    public static Specification<EventEntity> hasCity(String city) {
        return (root, query, builder) ->
                (city == null || city.isEmpty())
                        ? null
                        : builder.equal(root.get("venue").get("city"), city);
    }

    public static Specification<EventEntity> hasCategory(String category) {
        return (root, query, builder) ->
                (category == null || category.isEmpty())
                        ? null
                        : builder.equal(root.get("venue").get("category"), category);
    }

    public static Specification<EventEntity> startDateAfter(LocalDateTime date) {
        return (root, query, builder) ->
                (date == null)
                        ? null
                        : builder.greaterThanOrEqualTo(root.get("startTime"), date);
    }
}
