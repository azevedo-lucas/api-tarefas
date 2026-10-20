package com.aprendendo.api_tarefas.service;

import com.aprendendo.api_tarefas.entity.Tarefa;
import com.aprendendo.api_tarefas.repository.TarefaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Tarefa save(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Tarefa findById(Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        if (tarefa.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
        }
        return tarefa.get();
    }

    public void delete(Long id) {
        this.findById(id);
        tarefaRepository.deleteById(id);
    }

    public List<Tarefa> findAll() {
        return tarefaRepository.findAll();
    }

    public long countTarefas() {
        return tarefaRepository.count();
    }

    public Tarefa update(Long id, Tarefa tarefa) {
        Optional<Tarefa> tarefaAtualizada = tarefaRepository.findById(id);
        if (tarefaAtualizada.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
        }
        Tarefa updateTarefa = tarefaAtualizada.get();
        updateTarefa.setTitulo(tarefa.getTitulo());
        updateTarefa.setDescricao(tarefa.getDescricao());
        updateTarefa.setPrioridade(tarefa.getPrioridade());
        return tarefaRepository.save(updateTarefa);
    }
    public Tarefa concluir(Long id) {
        var tarefa = this.findById(id);
        tarefa.setConcluida(true);
        return tarefaRepository.save(tarefa);
    }
}
