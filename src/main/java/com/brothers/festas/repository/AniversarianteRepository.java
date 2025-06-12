package com.brothers.festas.repository;

import com.brothers.festas.model.Aniversariante;
import com.brothers.festas.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AniversarianteRepository extends JpaRepository<Aniversariante, Long> {

    Page<Aniversariante> findByNomeAniversarianteContainingIgnoreCase(String nome, Pageable pageable);

    @Query("""
    SELECT a FROM Aniversariante a
    WHERE (:nome IS NULL OR LOWER(a.nomeAniversariante) LIKE LOWER(CONCAT('%', :nome, '%')))
""")
    Page<Aniversariante> findAllByFilters(@Param("nome") String nome,
                                          Pageable pageable);
}
