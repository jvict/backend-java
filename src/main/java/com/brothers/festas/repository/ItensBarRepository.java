package com.brothers.festas.repository;

import com.brothers.festas.model.ItensBar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensBarRepository extends JpaRepository<ItensBar, Long> {
}
