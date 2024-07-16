package com.goat.facul.service;

import com.goat.facul.model.Especializacao;
import com.goat.facul.model.PedidoEspecializacao;
import com.goat.facul.model.Servidor;
import com.goat.facul.model.StatusPedido;
import com.goat.facul.repository.EspecializacaoRepository;
import com.goat.facul.repository.PedidoEspecializacaoRepository;
import com.goat.facul.repository.ServidorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ServidorService {

    @Autowired
    private ServidorRepository servidorRepository;

    @Autowired
    private EspecializacaoRepository especializacaoRepository;

    @Autowired
    private PedidoEspecializacaoRepository pedidoEspecializacaoRepository;

    public Servidor findById(Long id) {
        return servidorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Servidor não encontrado com o id: " + id));
    }

    public Servidor save(Servidor servidor) {
        // Verifica se há especializações associadas ao servidor
        Set<Especializacao> especializacoes = servidor.getEspecializacoes();
        if (especializacoes != null && !especializacoes.isEmpty()) {
            for (Especializacao especializacao : especializacoes) {
                especializacao.getServidores().add(servidor);
            }
        }
        return servidorRepository.save(servidor);
    }

    public List<Servidor> findAll() {
        return servidorRepository.findAll();
    }

    public Servidor update(Long id, Servidor servidorAtualizado) {
        Servidor servidor = findById(id);

        servidor.setCpf(servidorAtualizado.getCpf());
        servidor.setEmail(servidorAtualizado.getEmail());
        servidor.setMatricula(servidorAtualizado.getMatricula());
        servidor.setNome(servidorAtualizado.getNome());
        servidor.setDataNascimento(servidorAtualizado.getDataNascimento());
        servidor.setSexo(servidorAtualizado.getSexo());
        servidor.setTipo(servidorAtualizado.getTipo());
        servidor.setEspecializacoes(servidorAtualizado.getEspecializacoes());

        return servidorRepository.save(servidor);
    }

    public void delete(Long id) {
        Servidor servidor = findById(id);
        servidorRepository.delete(servidor);
    }

    public Servidor addEspecializacao(Long servidorId, Long especializacaoId) {
        Servidor servidor = servidorRepository.findById(servidorId)
                .orElseThrow(() -> new EntityNotFoundException("Servidor não encontrado com id: " + servidorId));

        Especializacao especializacao = especializacaoRepository.findById(especializacaoId)
                .orElseThrow(() -> new EntityNotFoundException("Especialização não encontrada com id: " + especializacaoId));

        // Adicionar a especialização ao servidor
        servidor.getEspecializacoes().add(especializacao);
        servidorRepository.save(servidor);

        // Criar um novo pedido de especialização com status "EM_ANALISE"
        PedidoEspecializacao pedido = new PedidoEspecializacao();
        pedido.setServidor(servidor);
        pedido.setEspecializacao(especializacao);
        pedido.setStatus(StatusPedido.EM_ANALISE);
        pedidoEspecializacaoRepository.save(pedido);

        return servidor;
    }


    public void removeEspecializacao(Long servidorId, Long especializacaoId) {
        Servidor servidor = findById(servidorId);
        Especializacao especializacao = especializacaoRepository.findById(especializacaoId)
                .orElseThrow(() -> new EntityNotFoundException("Especialização não encontrada com o id: " + especializacaoId));

        servidor.getEspecializacoes().remove(especializacao);
        especializacao.getServidores().remove(servidor);

        servidorRepository.save(servidor);
        especializacaoRepository.save(especializacao);
    }
}
