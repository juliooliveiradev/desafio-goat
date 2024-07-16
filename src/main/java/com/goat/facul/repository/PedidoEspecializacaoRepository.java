package com.goat.facul.repository;

import com.goat.facul.model.PedidoEspecializacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PedidoEspecializacaoRepository extends JpaRepository<PedidoEspecializacao, Long> {
}
