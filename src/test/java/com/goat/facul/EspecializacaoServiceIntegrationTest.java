package com.goat.facul;

import com.goat.facul.model.Especializacao;
import com.goat.facul.model.TipoEspecializacao;
import com.goat.facul.repository.EspecializacaoRepository;
import com.goat.facul.service.EspecializacaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = FaculApplication.class)
@Transactional
@Rollback
public class EspecializacaoServiceIntegrationTest {

    @Autowired
    private EspecializacaoService especializacaoService;

    @Autowired
    private EspecializacaoRepository especializacaoRepository;


    @Test
    void testCreateEspecializacao() {
        Especializacao especializacao = new Especializacao();
        especializacao.setArea("Engenharia");
        especializacao.setTipo(TipoEspecializacao.MESTRADO);
        especializacao.setCargaHoraria(360);
        especializacao.setValorTotal(BigDecimal.valueOf(15000.0));

        Especializacao novaEspecializacao = especializacaoRepository.save(especializacao);

        assertNotNull(novaEspecializacao);
        assertNotNull(novaEspecializacao.getId());
        assertEquals("Engenharia", novaEspecializacao.getArea());

        // Converta o valor esperado para BigDecimal
        BigDecimal expected = BigDecimal.valueOf(15000.0);
        BigDecimal actual = novaEspecializacao.getValorTotal(); // getValorTotal() deve retornar um BigDecimal

        assertEquals(expected, actual);
    }

    @Test
    void testGetEspecializacaoById() {
        Especializacao especializacao = new Especializacao();
        especializacao.setArea("Engenharia");
        especializacao.setTipo(TipoEspecializacao.MESTRADO);
        especializacao.setCargaHoraria(360);
        especializacao.setValorTotal(BigDecimal.valueOf(15000.0));
        especializacaoRepository.save(especializacao);

        Especializacao especializacaoEncontrada = especializacaoService.findById(especializacao.getId());

        assertNotNull(especializacaoEncontrada);
        assertEquals("Engenharia", especializacaoEncontrada.getArea());
    }
}
