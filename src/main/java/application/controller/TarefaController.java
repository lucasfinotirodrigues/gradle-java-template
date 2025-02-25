package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.model.Tarefa;
import application.record.TarefaDto;
import application.repository.TarefaRepository;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    @Autowired
    private TarefaRepository tarefaRepo;

    @GetMapping
    public Iterable<TarefaDto> list() {
        return tarefaRepo.findAll().stream().map(TarefaDto::new).toList();
    }

    @GetMapping("/{id}")
    public TarefaDto getOne(@PathVariable long id){
        Optional<Tarefa> resultado = tarefaRepo.findById(id);
        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tarefa Não Encontrada"
            );
        }
        
        return new TarefaDto(resultado.get());
    }

    @PostMapping
    public TarefaDto insert(@RequestBody TarefaDto novaTarefa) {
        Tarefa novosDados = new Tarefa(novaTarefa);
        Tarefa tarefaSalva = tarefaRepo.save(novosDados);
        TarefaDto retorno = new TarefaDto(tarefaSalva);
        // return new TarefaDto(tarefaRepo.save(new Tarefa(novaTarefa)));
        return retorno;
    }

    @PutMapping("/{id}")
    public TarefaDto update(@RequestBody TarefaDto dados, @PathVariable long id){
        Optional<Tarefa> resultado = tarefaRepo.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tarefa Não Encontrada"
            );
        }
        resultado.get().setDescricao(dados.descricao());
        return new TarefaDto(tarefaRepo.save(resultado.get()));        
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        if(!tarefaRepo.existsById(id)){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tarefa Não Encontrada"
            );
        }
        tarefaRepo.deleteById((id));
    }
}
