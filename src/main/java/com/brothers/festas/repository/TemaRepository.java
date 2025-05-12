package com.brothers.festas.repository;

import com.brothers.festas.model.Tema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {

    @Query("""
    SELECT t FROM Tema t
    WHERE (:descricao IS NULL OR LOWER(t.descricao) LIKE LOWER(CONCAT('%', :descricao, '%')))
""")
    Page<Tema> findAllByFilters(Pageable pageable,
                                @Param("descricao") String descricao);
}
