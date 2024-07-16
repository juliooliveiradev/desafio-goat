package com.goat.facul;



import com.goat.facul.model.Especializacao;
import com.goat.facul.model.Servidor;
import com.goat.facul.model.TipoEspecializacao;
import com.goat.facul.model.TipoServidor;
import com.goat.facul.repository.EspecializacaoRepository;
import com.goat.facul.repository.ServidorRepository;
import com.goat.facul.service.ServidorService;
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
public class ServidorServiceIntegrationTest {

    @Autowired
    private ServidorService servidorService;

    @Autowired
    private ServidorRepository servidorRepository;

    @Autowired
    private EspecializacaoRepository especializacaoRepository;

    @Test
    void testAddEspecializacao() {
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

        servidorService.addEspecializacao(servidor.getId(), especializacao.getId());

        Servidor servidorAtualizado = servidorRepository.findById(servidor.getId()).orElse(null);
        assertNotNull(servidorAtualizado);
        assertTrue(servidorAtualizado.getEspecializacoes().contains(especializacao));
    }

    @Test
    void testRemoveEspecializacao() {
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

        servidor.getEspecializacoes().add(especializacao);
        especializacao.getServidores().add(servidor);
        servidorRepository.save(servidor);
        especializacaoRepository.save(especializacao);

        servidorService.removeEspecializacao(servidor.getId(), especializacao.getId());

        Servidor servidorAtualizado = servidorRepository.findById(servidor.getId()).orElse(null);
        assertNotNull(servidorAtualizado);
        assertFalse(servidorAtualizado.getEspecializacoes().contains(especializacao));
    }
}

