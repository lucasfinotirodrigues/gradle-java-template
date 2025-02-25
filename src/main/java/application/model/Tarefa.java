package application.model;

import application.record.TarefaDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tarefa {
    @Id
    private Long id;
    private String descricao;

    public Tarefa(TarefaDto dto) {
        this.id = dto.id();
        this.descricao = dto.descricao();
    }
}
