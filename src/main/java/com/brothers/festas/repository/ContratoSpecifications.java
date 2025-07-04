package com.brothers.festas.repository;

import com.brothers.festas.model.Contrato;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContratoSpecifications {

    public static Specification<Contrato> byFilters(String nomeCliente, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtro por nome do cliente
            if (nomeCliente != null && !nomeCliente.isBlank()) {
                // Cuidado com o relacionamento: root.get("cliente").get("nome")
                // Se o Cliente está em tb_cliente e Contrato em tb_contrato
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("cliente").get("nome")),
                        "%" + nomeCliente.toLowerCase() + "%"
                ));
            }

            // Filtro por data inicial
            if (dataInicial != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataHoraInicial"), dataInicial));
            }

            // Filtro por data final
            if (dataFinal != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataHoraFinal"), dataFinal));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
