package com.goat.facul.controller;

import com.goat.facul.DTO.PedidoEspecializacaoDTO;
import com.goat.facul.service.PedidoEspecializacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos-especializacao")
public class PedidoEspecializacaoController {
    @Autowired
    private PedidoEspecializacaoService pedidoEspecializacaoService;

    @GetMapping
    public ResponseEntity<List<PedidoEspecializacaoDTO>> listarPedidosEspecializacao() {
        List<PedidoEspecializacaoDTO> pedidos = pedidoEspecializacaoService.listarPedidosEspecializacao();
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping("/{pedidoId}/aprovar")
    public ResponseEntity<Void> aprovarPedidoEspecializacao(@PathVariable Long pedidoId) {
        pedidoEspecializacaoService.aprovarPedidoEspecializacao(pedidoId);
        return ResponseEntity.ok().build();
    }

    // Endpoint para reprovar um pedido de especialização
    @PostMapping("/{pedidoId}/reprovar")
    public ResponseEntity<Void> reprovarPedidoEspecializacao(
            @PathVariable Long pedidoId,
            @RequestParam String motivoReprovacao
    ) {
        pedidoEspecializacaoService.reprovarPedidoEspecializacao(pedidoId, motivoReprovacao);
        return ResponseEntity.ok().build();
    }

}
