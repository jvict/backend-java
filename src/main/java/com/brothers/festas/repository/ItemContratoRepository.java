package com.brothers.festas.repository;

import com.brothers.festas.model.ItemContrato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemContratoRepository extends JpaRepository<ItemContrato, Long> {

    Page<ItemContrato> findByDescricaoContainingIgnoreCase(String descricao, Pageable pageable);
}
