package com.goat.facul.repository;

import com.goat.facul.model.Especializacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspecializacaoRepository extends JpaRepository<Especializacao, Long> {
    List<Especializacao> findEspecializacaoByServidoresId(Long servidorId);
}
