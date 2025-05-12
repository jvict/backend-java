package com.brothers.festas.repository;

import com.brothers.festas.model.Contrato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

    Page<Contrato> findByClienteNomeContainingIgnoreCase(String nome, Pageable pageable);

    @Query("""
    SELECT c FROM Contrato c
    WHERE (:nome IS NULL OR LOWER(c.cliente.nome) LIKE LOWER(CONCAT('%', :nome, '%')))
      AND (:dataHoraInicial IS NULL OR c.dataHoraInicial >= :dataHoraInicial)
      AND (:dataHoraFinal IS NULL OR c.dataHoraFinal <= :dataHoraFinal)
""")
    Page<Contrato> findAllByFilters(@Param("nome") String nome,
                                    @Param("dataHoraInicial") LocalDateTime dataHoraInicial,
                                    @Param("dataHoraFinal") LocalDateTime dataHoraFinal,
                                    Pageable pageable);

}
