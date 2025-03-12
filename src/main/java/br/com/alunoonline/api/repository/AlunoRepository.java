package br.com.alunoonline.api.repository;

import br.com.alunoonline.api.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // idicando que e um repositorio de aluno
public interface AlunoRepository extends JpaRepository<Aluno, Long>{//lincando o repositorio e informando a classe e model{

}
