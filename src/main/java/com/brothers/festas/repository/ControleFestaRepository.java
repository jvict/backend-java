package com.brothers.festas.repository;

import com.brothers.festas.model.ControleFesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControleFestaRepository extends JpaRepository<ControleFesta, Long> {}

