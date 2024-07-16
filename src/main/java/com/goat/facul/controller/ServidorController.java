package com.goat.facul.controller;

import com.goat.facul.model.Servidor;
import com.goat.facul.service.ServidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servidores")
public class ServidorController {

    @Autowired
    private ServidorService servidorService;

    @GetMapping("/{id}")
    public ResponseEntity<Servidor> getServidorById(@PathVariable Long id) {
        Servidor servidor = servidorService.findById(id);
        return ResponseEntity.ok(servidor);
    }

    @PostMapping
    public ResponseEntity<Servidor> createServidor(@RequestBody Servidor servidor) {
        Servidor novoServidor = servidorService.save(servidor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoServidor);
    }

    @GetMapping
    public ResponseEntity<List<Servidor>> getAllServidores() {
        List<Servidor> servidores = servidorService.findAll();
        return ResponseEntity.ok(servidores);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servidor> updateServidor(@PathVariable Long id, @RequestBody Servidor servidorAtualizado) {
        Servidor servidor = servidorService.update(id, servidorAtualizado);
        return ResponseEntity.ok(servidor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServidor(@PathVariable Long id) {
        servidorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{servidorId}/especializacoes/{especializacaoId}")
    public ResponseEntity<Servidor> addEspecializacao(
            @PathVariable Long servidorId,
            @PathVariable Long especializacaoId) {
        Servidor servidor = servidorService.addEspecializacao(servidorId, especializacaoId);
        return ResponseEntity.ok(servidor);
    }

    @DeleteMapping("/{servidorId}/especializacoes/{especializacaoId}")
    public ResponseEntity<Void> removeEspecializacao(@PathVariable Long servidorId, @PathVariable Long especializacaoId) {
        servidorService.removeEspecializacao(servidorId, especializacaoId);
        return ResponseEntity.noContent().build();
    }
}
