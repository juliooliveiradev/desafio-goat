package com.goat.facul;

import com.goat.facul.model.Especializacao;
import com.goat.facul.model.PedidoEspecializacao;
import com.goat.facul.model.Servidor;
import com.goat.facul.repository.EspecializacaoRepository;
import com.goat.facul.repository.PedidoEspecializacaoRepository;
import com.goat.facul.repository.ServidorRepository;
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
public class ServidorServiceTest {

    @Mock
    private ServidorRepository servidorRepository;

    @Mock
    private EspecializacaoRepository especializacaoRepository;

    @Mock
    private PedidoEspecializacaoRepository pedidoEspecializacaoRepository;

    @InjectMocks
    private ServidorService servidorService;

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
    }


    @Test
    public void testAddEspecializacao() {

        Long servidorId = 1L;
        Long especializacaoId = 2L;


        Servidor servidor = new Servidor();
        Especializacao especializacao = new Especializacao();
        especializacao.setId(especializacaoId);

        when(servidorRepository.findById(servidorId)).thenReturn(Optional.of(servidor));
        when(especializacaoRepository.findById(especializacaoId)).thenReturn(Optional.of(especializacao));


        Servidor resultado = servidorService.addEspecializacao(servidorId, especializacaoId);


        verify(servidorRepository, times(1)).findById(servidorId);
        verify(especializacaoRepository, times(1)).findById(especializacaoId);
        verify(servidorRepository, times(1)).save(servidor);
        verify(pedidoEspecializacaoRepository, times(1)).save(any(PedidoEspecializacao.class));


        assertEquals(servidor, resultado);
    }




    @Test
    void testRemoveEspecializacao() {
        servidor.getEspecializacoes().add(especializacao);
        especializacao.getServidores().add(servidor);

        when(servidorRepository.findById(1L)).thenReturn(Optional.of(servidor));
        when(especializacaoRepository.findById(1L)).thenReturn(Optional.of(especializacao));

        servidorService.removeEspecializacao(1L, 1L);

        verify(servidorRepository, times(1)).save(any(Servidor.class));
        verify(especializacaoRepository, times(1)).save(any(Especializacao.class));

        assertFalse(servidor.getEspecializacoes().contains(especializacao));
        assertFalse(especializacao.getServidores().contains(servidor));
    }
}

