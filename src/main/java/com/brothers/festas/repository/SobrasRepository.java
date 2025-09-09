package com.brothers.festas.repository;

import com.brothers.festas.model.Sobras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SobrasRepository extends JpaRepository<Sobras, Long> {
}
