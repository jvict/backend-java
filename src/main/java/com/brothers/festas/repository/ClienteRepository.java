package com.brothers.festas.repository;

import com.brothers.festas.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByDocumento(String documento);

    @Query("""
    SELECT c FROM Cliente c
    WHERE (:nome IS NULL OR LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%')))
""")
    Page<Cliente> findAllByFilters(@Param("nome") String nome,
                                   Pageable pageable);

}
