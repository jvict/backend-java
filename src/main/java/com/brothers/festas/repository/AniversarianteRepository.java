package com.brothers.festas.repository;

import com.brothers.festas.model.Aniversariante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AniversarianteRepository extends JpaRepository<Aniversariante, Long> {
}
