package com.brothers.festas.repository;

import com.brothers.festas.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {
}
