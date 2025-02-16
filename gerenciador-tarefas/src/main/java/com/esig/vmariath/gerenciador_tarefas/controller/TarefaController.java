package com.esig.vmariath.gerenciador_tarefas.controller;

import com.esig.vmariath.gerenciador_tarefas.model.Tarefa;
import com.esig.vmariath.gerenciador_tarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping("/listar")
    public String listarTarefas(Model model) {
        List<Tarefa> tarefas = tarefaService.listarTodas();
        tarefas.removeIf(t -> t.getSituacao().equals(Tarefa.Situacao.CONCLUIDA));

        tarefas.forEach(tarefa -> {
            if (tarefa.getPrazo() != null) {
                tarefa.setPrazoFormatado(tarefa.getPrazo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
        });

        model.addAttribute("tarefas", tarefas);
        model.addAttribute("responsaveis", Tarefa.Responsavel.values());
        model.addAttribute("prazo", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return "listagem_tarefa";
    }

    @GetMapping("/nova")
    public String novaTarefa(Model model) {
        model.addAttribute("tarefa", new Tarefa());
        model.addAttribute("prioridades", Tarefa.Prioridade.values());
        model.addAttribute("responsaveis", Tarefa.Responsavel.values());
        return "/cadastrar_tarefa";
    }

    @PostMapping("/salvar")
    public String salvarTarefa(@ModelAttribute Tarefa tarefa, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("prioridades", Tarefa.Prioridade.values());
            model.addAttribute("responsaveis", Tarefa.Responsavel.values());
            return "/cadastrar_tarefa";
        }

        if (tarefa.getId() != null) {
            tarefaService.atualizar(tarefa);
        } else {
            tarefaService.salvar(tarefa);
        }

        return "redirect:/tarefa/listar";
    }

    @PostMapping("/concluir/{id}")
    public String concluirTarefa(@PathVariable Long id) {
        tarefaService.concluirTarefa(id);
        return "redirect:/tarefa/listar";
    }

    @GetMapping("/excluir/{id}")
    public String deletarTarefa(@PathVariable Long id) {
        tarefaService.excluir(id);
        return "redirect:/tarefa/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarTarefa(@PathVariable Long id, Model model) {
        Tarefa tarefa = tarefaService.buscarPorId(id);
        model.addAttribute("tarefa", tarefa);
        model.addAttribute("prioridades", Tarefa.Prioridade.values());
        model.addAttribute("responsaveis", Tarefa.Responsavel.values());
        return "/cadastrar_tarefa";
    }

    @GetMapping("/pesquisar")
    public String pesquisarTarefas(
            @RequestParam(value = "numero", required = false) Long numero,
            @RequestParam(value = "titulo", required = false) String titulo,
            @RequestParam(value = "responsavel", required = false) Tarefa.Responsavel responsavel,
            @RequestParam(value = "situacao", required = false) Tarefa.Situacao situacao,
            Model model) {

        List<Tarefa> tarefas = tarefaService.pesquisarTarefas(numero, titulo, responsavel, situacao);

        model.addAttribute("tarefas", tarefas);
        model.addAttribute("responsaveis", Tarefa.Responsavel.values());
        model.addAttribute("prioridades", Tarefa.Prioridade.values());
        model.addAttribute("prazo", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return "resultado_pesquisa";
    }
}
