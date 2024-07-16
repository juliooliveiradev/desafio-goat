package com.goat.facul.service;

import com.goat.facul.model.Especializacao;
import com.goat.facul.repository.EspecializacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecializacaoService {

    @Autowired
    private EspecializacaoRepository especializacaoRepository;

    public Especializacao findById(Long id) {
        return especializacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Especialização não encontrada com o id: " + id));
    }

    public Especializacao save(Especializacao especializacao) {
        return especializacaoRepository.save(especializacao);
    }

    @Cacheable(value = "especializacoes")
    public List<Especializacao> findAll() {
        return especializacaoRepository.findAll();
    }

    public Especializacao update(Long id, Especializacao especializacaoAtualizada) {
        Especializacao especializacao = findById(id);

        especializacao.setArea(especializacaoAtualizada.getArea());
        especializacao.setTipo(especializacaoAtualizada.getTipo());
        especializacao.setCargaHoraria(especializacaoAtualizada.getCargaHoraria());
        especializacao.setValorTotal(especializacaoAtualizada.getValorTotal());

        return especializacaoRepository.save(especializacao);
    }

    public void delete(Long id) {
        Especializacao especializacao = findById(id);
        especializacaoRepository.delete(especializacao);
    }

    public void aprovarEspecializacao(Long especializacaoId) {
        Especializacao especializacao = findById(especializacaoId);
        // Lógica para aprovação da especialização (exemplo)
        especializacao.setAprovada(true);
        especializacaoRepository.save(especializacao);
    }

    public void indeferirEspecializacao(Long especializacaoId, String motivo) {
        Especializacao especializacao = findById(especializacaoId);
        // Lógica para indeferimento da especialização (exemplo)
        especializacao.setAprovada(false);
        especializacao.setMotivoIndeferimento(motivo);
        especializacaoRepository.save(especializacao);
    }
}
