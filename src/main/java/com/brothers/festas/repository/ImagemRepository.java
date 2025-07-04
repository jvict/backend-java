package com.brothers.festas.repository;

import com.brothers.festas.model.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {
    List<Imagem> findByTemaId(Long temaId);
}
