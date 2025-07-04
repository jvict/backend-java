package com.brothers.festas.repository;

import com.brothers.festas.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByDocumento(String documento);

    Page<Cliente> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

}
