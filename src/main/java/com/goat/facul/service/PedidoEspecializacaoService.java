package com.goat.facul.service;

import com.goat.facul.DTO.PedidoEspecializacaoDTO;
import com.goat.facul.model.PedidoEspecializacao;
import com.goat.facul.model.StatusPedido;
import com.goat.facul.repository.PedidoEspecializacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoEspecializacaoService {

    @Autowired
    private PedidoEspecializacaoRepository pedidoEspecializacaoRepository;

    @Autowired
    private ServidorService servidorService;

    // Método para listar todos os pedidos de especialização
    public List<PedidoEspecializacaoDTO> listarPedidosEspecializacao() {
        List<PedidoEspecializacao> pedidos = pedidoEspecializacaoRepository.findAll();
        return pedidos.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Método auxiliar para converter PedidoEspecializacao para PedidoEspecializacaoDTO
    private PedidoEspecializacaoDTO convertToDTO(PedidoEspecializacao pedido) {
        return new PedidoEspecializacaoDTO(
                pedido.getId(),
                pedido.getServidor().getId(),
                pedido.getEspecializacao().getId(),
                pedido.getStatus(),
                pedido.getMotivoReprovacao()
        );
    }

    public void aprovarPedidoEspecializacao(Long pedidoId) {
        PedidoEspecializacao pedido = pedidoEspecializacaoRepository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido de especialização não encontrado com o id: " + pedidoId));

        // Altera o status para "aprovado"
        pedido.setStatus(StatusPedido.APROVADO);

        // Salva a alteração no banco de dados
        pedidoEspecializacaoRepository.save(pedido);
    }


    // Método para reprovar um pedido de especialização
    public void reprovarPedidoEspecializacao(Long pedidoId, String motivoReprovacao) {
        PedidoEspecializacao pedido = pedidoEspecializacaoRepository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido de especialização não encontrado com o id: " + pedidoId));

        pedido.setStatus(StatusPedido.REPROVADO);
        pedido.setMotivoReprovacao(motivoReprovacao);

        servidorService.removeEspecializacao(pedido.getServidor().getId(), pedido.getEspecializacao().getId());

        pedidoEspecializacaoRepository.save(pedido);
    }
}

