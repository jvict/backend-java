package com.brothers.festas.repository;

import com.brothers.festas.model.Tema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {

    Page<Tema> findByDescricaoContainingIgnoreCase(String descricao, Pageable pageable);
}
