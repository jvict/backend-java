package com.brothers.festas.repository;

import com.brothers.festas.model.Contrato;
import com.brothers.festas.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    List<Pagamento> findByContrato(Contrato contrato);
}
