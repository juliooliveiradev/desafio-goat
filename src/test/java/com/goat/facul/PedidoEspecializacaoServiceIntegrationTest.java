package com.goat.facul;

import com.goat.facul.model.*;
import com.goat.facul.repository.EspecializacaoRepository;
import com.goat.facul.repository.PedidoEspecializacaoRepository;
import com.goat.facul.repository.ServidorRepository;
import com.goat.facul.service.PedidoEspecializacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
public class PedidoEspecializacaoServiceIntegrationTest {

    @Autowired
    private PedidoEspecializacaoService pedidoEspecializacaoService;

    @Autowired
    private PedidoEspecializacaoRepository pedidoEspecializacaoRepository;

    @Autowired
    private ServidorRepository servidorRepository;

    @Autowired
    private EspecializacaoRepository especializacaoRepository;

    @Test
    void testReprovarPedidoEspecializacao() {
        Servidor servidor = new Servidor();
        servidor.setCpf("12345678900");
        servidor.setEmail("servidor@exemplo.com");
        servidor.setMatricula("1234");
        servidor.setNome("Servidor Teste");
        servidor.setDataNascimento(LocalDate.of(1980, 1, 1));
        servidor.setSexo("MASCULINO");
        servidor.setTipo(TipoServidor.PROFESSOR);
        servidorRepository.save(servidor);

        Especializacao especializacao = new Especializacao();
        especializacao.setArea("Engenharia");
        especializacao.setTipo(TipoEspecializacao.MESTRADO);
        especializacao.setCargaHoraria(360);
        especializacao.setValorTotal(BigDecimal.valueOf(15000.0));
        especializacaoRepository.save(especializacao);

        PedidoEspecializacao pedido = new PedidoEspecializacao();
        pedido.setServidor(servidor);
        pedido.setEspecializacao(especializacao);
        pedido.setStatus(StatusPedido.EM_ANALISE);
        pedidoEspecializacaoRepository.save(pedido);

        pedidoEspecializacaoService.reprovarPedidoEspecializacao(pedido.getId(), "Motivo da reprovação");

        PedidoEspecializacao pedidoReprovado = pedidoEspecializacaoRepository.findById(pedido.getId()).orElse(null);
        assertNotNull(pedidoReprovado);
        assertEquals(StatusPedido.REPROVADO, pedidoReprovado.getStatus());
        assertEquals("Motivo da reprovação", pedidoReprovado.getMotivoReprovacao());
    }
}

