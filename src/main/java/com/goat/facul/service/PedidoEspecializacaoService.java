package com.goat.facul.service;

import com.goat.facul.DTO.PedidoEspecializacaoDTO;
import com.goat.facul.model.PedidoEspecializacao;
import com.goat.facul.model.StatusPedido;
import com.goat.facul.repository.PedidoEspecializacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoEspecializacaoService {

    @Autowired
    private PedidoEspecializacaoRepository pedidoEspecializacaoRepository;

    @Autowired
    private ServidorService servidorService;

    @Autowired
    private JavaMailSender javaMailSender;

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

    @Transactional
    public void aprovarPedidoEspecializacao(Long pedidoId) {
        PedidoEspecializacao pedido = pedidoEspecializacaoRepository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido de especialização não encontrado com o id: " + pedidoId));

        // Altera o status para "aprovado"
        pedido.setStatus(StatusPedido.APROVADO);

        // Salva a alteração no banco de dados
        pedidoEspecializacaoRepository.save(pedido);

        // Envia e-mail de aprovação
        enviarEmail(pedido.getServidor().getEmail(), "Pedido de especialização aprovado", "Seu pedido foi aprovado.");
    }


    // Método para reprovar um pedido de especialização
    @Transactional
    public void reprovarPedidoEspecializacao(Long pedidoId, String motivoReprovacao) {
        PedidoEspecializacao pedido = pedidoEspecializacaoRepository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido de especialização não encontrado com o id: " + pedidoId));

        pedido.setStatus(StatusPedido.REPROVADO);
        pedido.setMotivoReprovacao(motivoReprovacao);

        // Remove a especialização do servidor
        servidorService.removeEspecializacao(pedido.getServidor().getId(), pedido.getEspecializacao().getId());

        // Salva a alteração no banco de dados
        pedidoEspecializacaoRepository.save(pedido);

        // Envia e-mail de reprovação
        enviarEmail(pedido.getServidor().getEmail(), "Pedido de especialização reprovado", "Seu pedido foi reprovado.");
    }

    public void enviarEmail(String destinatario, String assunto, String corpo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destinatario);
        message.setSubject(assunto);
        message.setText(corpo);

        javaMailSender.send(message);
    }
}

