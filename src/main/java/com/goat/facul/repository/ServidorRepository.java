package com.goat.facul.repository;

import com.goat.facul.model.Servidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;


import java.util.List;

@Repository
public interface ServidorRepository extends JpaRepository<Servidor, Long> {
    @Query("SELECT s FROM Servidor s JOIN s.especializacoes e WHERE e.id = :especializacaoId")
    List<Servidor> findServidorsByEspecializacaoId(@Param("especializacaoId") Long especializacaoId);
}
