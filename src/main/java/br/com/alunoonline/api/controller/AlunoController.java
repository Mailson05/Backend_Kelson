package br.com.alunoonline.api.controller;


import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController // significa boas praticas de comunicação - principio de arquitetura
@RequestMapping("/alunos") //path = caminho é em plural / endereço mapeado 8080/alunos
public class AlunoController {

    @Autowired
    AlunoService alunoService; // ele injeta as dependencia

    @ResponseStatus(HttpStatus.CREATED) // resposta 201 ok
    @PostMapping // tipo da requisicção
    public void criarAluno(@RequestBody Aluno aluno) {
        alunoService.criarAluno(aluno);


    }

}
