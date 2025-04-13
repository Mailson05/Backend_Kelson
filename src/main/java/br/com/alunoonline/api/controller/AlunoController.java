package br.com.alunoonline.api.controller;


import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // significa boas praticas de comunicação - principio de arquitetura, que o front end se comunica com back
@RequestMapping("/alunos") //path = caminho é em plural = endpoint / endereço mapeado 8080/alunos
public class AlunoController {

    @Autowired  //injentando as dependencias
    AlunoService alunoService; // ele injeta as dependencia

    @ResponseStatus(HttpStatus.CREATED) // resposta 201 creadted ( codigo de status de um post bem sucedido)
    @PostMapping // tipo da requisicção
    public void criarAluno(@RequestBody Aluno aluno) { //transdforma o json em objeto
        alunoService.criarAluno(aluno);
    }

    @GetMapping //usado para pegar ,  ele e a requisição ( tudo que front manda )
    @ResponseStatus(HttpStatus.OK) // 200 ok
    public List<Aluno> listarTodosAlunos(){
        return alunoService.listarTodosAlunos();
    }

    @GetMapping("/{id}") //http
    @ResponseStatus(HttpStatus.OK) // @Pathvariable pega a id e tras
    public Optional<Aluno> buscarAlunoPorId(@PathVariable Long id){
        return alunoService.buscarAlunoPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAlunoPorId(@PathVariable long id){
        alunoService.deletarAlunoPorId(id);
    }


}
