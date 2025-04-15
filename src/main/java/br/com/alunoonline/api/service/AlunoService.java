package br.com.alunoonline.api.service;

import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService { // ele que tem a regra de negocio

    @Autowired
    AlunoRepository alunoRepository; // ele injetar as dependencias, Repository que tem comunicação com Banco de dados

    public void criarAluno(Aluno aluno){
        alunoRepository.save(aluno);

    }

    public List<Aluno> listarTodosAlunos(){
        return alunoRepository.findAll(); // fazendo com que retorne todos alunos atarves do repository
    }

    public Optional<Aluno> buscarAlunoPorId(Long id){
        return alunoRepository.findById(id);
    }

    public void deletarAlunoPorId(Long id){
        Optional<Aluno> aluno=alunoRepository.findById(id);
        if (aluno.isPresent()){
                alunoRepository.deleteById(id);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Não encontrado o Aluno");
        }
    }

    public void atualizarAlunoPorId(Long id, Aluno aluno){ //(aluno vem do frontend )colocando para o front manda como JSON
        //PRIMEIRO PASSO : Ver se o aluno esxiste no BD
        Optional<Aluno> alunoDoBancoDeDados = buscarAlunoPorId(id);

        // E SE NAO EXISTIR ESSE ALUNO ?
        if (alunoDoBancoDeDados.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrato no banco de daods");  //Lançando uma nova respondes 404 not found
        }
        // SE CHEGAR AQUI, SIGINIFICA QUE EXISTE ALUNO COM ESSE ID!
        // VOU ARMAZENAR-LO EM UMA VARIAVEL PARA DEPOIS EDITA-LO.
        Aluno alunoParaEditar = alunoDoBancoDeDados.get();

        // COM ESSE ALUNO PARA SER EDITADO ACIMA, FAÇO
        // OS SETS NECESSARIOS PARA ATUALIZAR OS ATRIBUTOS DELE
        alunoParaEditar.setNome(aluno.getNome()); //ele pega o aluno ja editado pelo front e assim pegamos ja pronto
        alunoParaEditar.setCpf(aluno.getCpf());
        alunoParaEditar.setEmail(aluno.getEmail());

        // COM O ALUNO TOTALMENTE EDITADO ACIMA
        // EU DEVOLVO ELE ATUALIZADO PARA O BD
        alunoRepository.save(alunoParaEditar);//
    }
}
