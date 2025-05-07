package com.brothers.festas.repository;

import com.brothers.festas.model.Aniversariante;
import com.brothers.festas.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AniversarianteRepository extends JpaRepository<Aniversariante, Long> {

    Page<Aniversariante> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
