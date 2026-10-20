package com.aprendendo.api_tarefas.controller;

import com.aprendendo.api_tarefas.entity.Tarefa;
import com.aprendendo.api_tarefas.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<Tarefa> cadastrar(@Valid @RequestBody Tarefa tarefa) {
        var tarefaSalva = tarefaService.save(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaSalva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> busarPorId(@PathVariable Long id) {
        var tarefaEncontrada = tarefaService.findById(id);
        return ResponseEntity.ok(tarefaEncontrada);
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> buscarTodos() {
        var listTarefas = tarefaService.findAll();
        return ResponseEntity.ok(listTarefas);
    }

    @GetMapping("/quantidade")
    public ResponseEntity<Long> contarTarefas() {
        var quantidadeTarefas = tarefaService.countTarefas();
        return ResponseEntity.ok(quantidadeTarefas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        var tarefaAtualizada = tarefaService.update(id, tarefa);
        return ResponseEntity.ok(tarefaAtualizada);
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<Tarefa> concluir(@PathVariable Long id) {
        Tarefa tarefaAtualizada = tarefaService.concluir(id);
        return ResponseEntity.ok(tarefaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tarefaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
