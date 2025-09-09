package com.brothers.festas.repository;

import com.brothers.festas.model.ItensCardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensCardapioRepository extends JpaRepository<ItensCardapio, Long> {}

