package application.record;

import application.model.Tarefa;

public record TarefaDto(long id, String descricao) {
    public TarefaDto(Tarefa tarefa) {
        this(tarefa.getId(), tarefa.getDescricao());
    }
}
