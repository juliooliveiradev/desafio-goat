package com.goat.facul;
import com.goat.facul.model.Especializacao;
import com.goat.facul.model.PedidoEspecializacao;
import com.goat.facul.model.Servidor;
import com.goat.facul.model.StatusPedido;
import com.goat.facul.repository.PedidoEspecializacaoRepository;
import com.goat.facul.service.PedidoEspecializacaoService;
import com.goat.facul.service.ServidorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PedidoEspecializacaoServiceTest {

    @Mock
    private PedidoEspecializacaoRepository pedidoEspecializacaoRepository;

    @Mock
    private ServidorService servidorService;

    @InjectMocks
    private PedidoEspecializacaoService pedidoEspecializacaoService;

    private PedidoEspecializacao pedido;
    private Servidor servidor;
    private Especializacao especializacao;

    @BeforeEach
    void setUp() {
        servidor = new Servidor();
        servidor.setId(1L);
        servidor.setNome("Servidor Teste");

        especializacao = new Especializacao();
        especializacao.setId(1L);
        especializacao.setArea("Engenharia");

        pedido = new PedidoEspecializacao();
        pedido.setId(1L);
        pedido.setServidor(servidor);
        pedido.setEspecializacao(especializacao);
        pedido.setStatus(StatusPedido.EM_ANALISE);
    }

    @Test
    void testReprovarPedidoEspecializacao() {
        when(pedidoEspecializacaoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        pedidoEspecializacaoService.reprovarPedidoEspecializacao(1L, "Motivo da reprovação");

        verify(servidorService, times(1)).removeEspecializacao(servidor.getId(), especializacao.getId());
        verify(pedidoEspecializacaoRepository, times(1)).save(any(PedidoEspecializacao.class));

        assertEquals(StatusPedido.REPROVADO, pedido.getStatus());
        assertEquals("Motivo da reprovação", pedido.getMotivoReprovacao());
    }
}

