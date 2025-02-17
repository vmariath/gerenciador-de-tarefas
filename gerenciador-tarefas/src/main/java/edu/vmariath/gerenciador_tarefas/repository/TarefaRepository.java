package edu.vmariath.gerenciador_tarefas.repository;

import edu.vmariath.gerenciador_tarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query("SELECT t FROM Tarefa t WHERE " +
            "(:id IS NULL OR t.id = :id) AND " +
            "(:titulo IS NULL OR t.titulo LIKE %:titulo%) AND " +
            "(:responsavel IS NULL OR t.responsavel = :responsavel) AND " +
            "(:situacao IS NULL OR t.situacao = :situacao) ")
    List<Tarefa> pesquisarTarefas(
            @Param("id") Long id,
            @Param("titulo") String titulo,
            @Param("responsavel") Tarefa.Responsavel responsavel,
            @Param("situacao") Tarefa.Situacao situacao
    );
}
