// src/main/java/com/brothers/festas/repository/ContratoRepository.java
package com.brothers.festas.repository;

import com.brothers.festas.model.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {
}
