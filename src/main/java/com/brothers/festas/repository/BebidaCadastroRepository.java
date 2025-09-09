package com.brothers.festas.repository;

import com.brothers.festas.model.BebidaCadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BebidaCadastroRepository extends JpaRepository<BebidaCadastro, Long> {
}
