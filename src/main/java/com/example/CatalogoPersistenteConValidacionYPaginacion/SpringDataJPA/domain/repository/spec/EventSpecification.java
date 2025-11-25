package com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.domain.repository.spec;

import com.example.CatalogoPersistenteConValidacionYPaginacion.SpringDataJPA.infraestructura.adapters.out.persistence.EventJpaEntity;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// La especificación debe operar sobre la entidad de persistencia (EventJpaEntity).
public class EventSpecification {

    public static Specification<EventJpaEntity> findByCriteria(String status, Date startDate, Date endDate, Integer venueId) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (status != null && !status.isEmpty()) {
                // Se asume que EventJpaEntity tiene un campo 'nameEvent' para simular el estado.
                predicates.add(criteriaBuilder.equal(root.get("nameEvent"), status));
            }

            if (startDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startTime"), startDate));
            }

            if (endDate != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("endTime"), endDate));
            }

            if (venueId != null) {
                predicates.add(criteriaBuilder.equal(root.get("venue").get("id"), venueId));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
