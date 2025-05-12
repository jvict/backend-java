package com.brothers.festas.repository;

import com.brothers.festas.model.ItemContrato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemContratoRepository extends JpaRepository<ItemContrato, Long> {

    @Query("""
    SELECT i FROM ItemContrato i
    WHERE (:descricao IS NULL OR LOWER(i.descricao) LIKE LOWER(CONCAT('%', :descricao, '%')))
""")
    Page<ItemContrato> findAllByFilters(Pageable pageable,
                                        @Param("descricao") String descricao);
}
