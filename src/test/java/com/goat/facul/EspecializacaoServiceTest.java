package com.goat.facul;


import com.goat.facul.model.Especializacao;
import com.goat.facul.repository.EspecializacaoRepository;
import com.goat.facul.service.EspecializacaoService;
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
public class EspecializacaoServiceTest {

    @Mock
    private EspecializacaoRepository especializacaoRepository;

    @InjectMocks
    private EspecializacaoService especializacaoService;

    private Especializacao especializacao;

    @BeforeEach
    void setUp() {
        especializacao = new Especializacao();
        especializacao.setId(1L);
        especializacao.setArea("Engenharia");
    }

    @Test
    void testCreateEspecializacao() {
        when(especializacaoRepository.save(any(Especializacao.class))).thenReturn(especializacao);

        Especializacao novaEspecializacao = especializacaoService.save(especializacao);

        verify(especializacaoRepository, times(1)).save(any(Especializacao.class));
        assertNotNull(novaEspecializacao);
        assertEquals("Engenharia", novaEspecializacao.getArea());
    }

    @Test
    void testGetEspecializacaoById() {
        when(especializacaoRepository.findById(1L)).thenReturn(Optional.of(especializacao));

        Especializacao especializacaoEncontrada = especializacaoService.findById(1L);

        verify(especializacaoRepository, times(1)).findById(1L);
        assertNotNull(especializacaoEncontrada);
        assertEquals("Engenharia", especializacaoEncontrada.getArea());
    }
}

