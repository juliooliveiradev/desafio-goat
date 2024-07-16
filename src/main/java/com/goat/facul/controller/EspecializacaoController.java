package com.goat.facul.controller;

import com.goat.facul.model.Especializacao;
import com.goat.facul.service.EspecializacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especializacoes")
public class EspecializacaoController {

    @Autowired
    private EspecializacaoService especializacaoService;

    @GetMapping("/{id}")
    public ResponseEntity<Especializacao> getEspecializacaoById(@PathVariable Long id) {
        Especializacao especializacao = especializacaoService.findById(id);
        return ResponseEntity.ok(especializacao);
    }

    @PostMapping
    public ResponseEntity<Especializacao> createEspecializacao(@RequestBody Especializacao especializacao) {
        Especializacao novaEspecializacao = especializacaoService.save(especializacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEspecializacao);
    }

    @GetMapping
    public ResponseEntity<List<Especializacao>> getAllEspecializacoes() {
        List<Especializacao> especializacoes = especializacaoService.findAll();
        return ResponseEntity.ok(especializacoes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especializacao> updateEspecializacao(@PathVariable Long id, @RequestBody Especializacao especializacaoAtualizada) {
        Especializacao especializacao = especializacaoService.update(id, especializacaoAtualizada);
        return ResponseEntity.ok(especializacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspecializacao(@PathVariable Long id) {
        especializacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
