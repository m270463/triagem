package com.triagem.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MensagemRepository extends JpaRepository<Mensagem, Long>{
     @Query("""
        SELECT m FROM Mensagem m
        WHERE (:status IS NULL OR m.status = :status)
          AND (:prioridade IS NULL OR m.prioridade = :prioridade)
          AND (:categoria IS NULL OR m.categoria = :categoria)
        """)
    Page<Mensagem> buscar(@Param("status") String status,
                          @Param("prioridade") String prioridade,
                          @Param("categoria") String categoria,
                          Pageable pageable);


}
