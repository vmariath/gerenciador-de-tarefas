package com.esig.vmariath.gerenciador_tarefas.service;

import com.esig.vmariath.gerenciador_tarefas.model.Tarefa;
import com.esig.vmariath.gerenciador_tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> pesquisarTarefas(Long numero, String titulo, Tarefa.Responsavel responsavel, Tarefa.Situacao situacao) {
        return tarefaRepository.pesquisarTarefas(numero, titulo, responsavel, situacao);
    }

    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }

    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    public Tarefa salvar(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public void atualizar(Tarefa tarefa) {
        if (tarefa.getId() != null) {
            tarefaRepository.save(tarefa);
        }
    }

    public void excluir(Long id) {
        tarefaRepository.deleteById(id);
    }

    public void concluirTarefa(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        tarefa.setSituacao(Tarefa.Situacao.CONCLUIDA);
        tarefaRepository.save(tarefa);
    }
}
