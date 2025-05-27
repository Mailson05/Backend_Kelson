#Projeto de backend do professor Kelson
Sumário

- [Visão Geral]
    - Desenvolvido um sistema academico, onde sera feito todo manuseio dos alunos,disciplinas e professores.
  
  
- [Tecnologias Utilizadas]
   - Java ( Linguagem )
   - Spring( Framework)
   - PostgreSQL ( banco de dados )
   - Swagger, insominia ( demais ferraentas )
  
- [Documentação da API]

  Link Swagger : http://localhost:8080/swagger-ui/index.html#/
----------------------------------------------------------------------------------

[ENDPOINTS - FUNCIONALIDADES]
  
🔹 Professor Controller
GET /professores
 - Retorna a lista de todos os professores.

GET /professores/{id}
 - Retorna os dados de um professor específico por ID.

POST /professores
 - Cria um novo professor.

PUT /professores/{id}
 - Atualiza os dados de um professor existente.

DELETE /professores/{id}
 - Remove um professor por ID.

---------------------------------------------------------------

🔹 Aluno Controller
GET /alunos
 - Retorna a lista de todos os alunos.

GET /alunos/{id}
 - Retorna os dados de um aluno específico.

POST /alunos
 - Cria um novo aluno.

PUT /alunos/{id}
 - Atualiza as informações de um aluno.

DELETE /alunos/{id}
 - Remove um aluno por ID.

-------------------------------------------------------------

🔹 Disciplina Controller
GET /disciplinas
 - Lista todas as disciplinas.

GET /disciplinas/{id}
 - Retorna os dados de uma disciplina por ID.

POST /disciplinas
 - Cria uma nova disciplina.

PUT /disciplinas/{id}
 - Atualiza os dados de uma disciplina.

DELETE /disciplinas/{id}
 - Remove uma disciplina específica.

GET /disciplinas/professor/{professorId}
 - Retorna todas as disciplinas ministradas por um professor.

----------------------------------------------------------------

🔹 Matrícula Aluno Controller
GET /matriculas
 - Retorna a lista de todas as matrículas.

GET /matriculas/{id}
 - Retorna os dados de uma matrícula específica.

POST /matriculas
 - Realiza uma nova matrícula de aluno em disciplina(s).

PUT /matriculas/{id}
 - Atualiza uma matrícula existente.

DELETE /matriculas/{id}
 - Remove uma matrícula por ID.

PATCH /matriculas/trancar/{id}
 - Tranca uma matrícula (status atualizado).

PATCH /matriculas/atualizar-notas/{id}
 - Atualiza as notas da matrícula de um aluno.

GET /matriculas/emitir-historico/{alunoId}
 - Emite o histórico escolar de um aluno.

-------------------------------------------------------------------------------------
 Exemplos de Requisição

* Criar um aluno

json
![image](https://github.com/user-attachments/assets/49253db7-7e5f-444a-829c-bb8219cedbb7)

* Criar uma disciplina
  
json

![image](https://github.com/user-attachments/assets/831039c4-1b3f-439b-a72d-739463741315)

*Efetuar matrícula

json

![image](https://github.com/user-attachments/assets/0b162960-99c2-4602-99ca-ee20cdf365a6)

-------------------------------------------------------------------------------------------

Banco de dados - criarAluno
![image](https://github.com/user-attachments/assets/d0371d4e-3b9b-4b79-b8b8-1c7dc7037dd4)

------------------------------------------------------------------------------------

Insomnia - criarAluno
![image](https://github.com/user-attachments/assets/cdb54195-7194-48ef-a2eb-9808833b70e9)

--------------------------------------------------------------------------------------
Banco de dados - listarTodosAlunos
![image](https://github.com/user-attachments/assets/a1904f12-bb9f-4baa-92c5-ce10babb9942)

--------------------------------------------------------------------------------------
Insomnia - listarTodosAlunos
![image](https://github.com/user-attachments/assets/77508c64-49f7-4afe-92a9-bdcb53ba9709)

----------------------------------------------------------------------------------------
Insomnia - buscarAlunoPorId
![image](https://github.com/user-attachments/assets/99a49245-3623-47ba-b37a-7a19ef4418de)

----------------------------------------------------------------------------------------
Insomnia - deletarAlunoPorId
![image](https://github.com/user-attachments/assets/4480d6e7-bf3a-44bb-acd4-da87aa22051c)

---------------------------------------------------------------------------------------
Banco de dados - deletarAlunoPorId
![image](https://github.com/user-attachments/assets/109f08c7-b1ed-4f1d-aa0d-5f91a0d0d9f7)

----------------------------------------------------------------------------------------
Banco de dados - criarDisciplina
![image](https://github.com/user-attachments/assets/4c2d442b-25ed-4033-aa37-cd1c2a4b94f2)

------------------------------------------------------------------------------------------
Insomnia - criarDisciplina
![image](https://github.com/user-attachments/assets/8b46a4d3-2877-48aa-b9af-8226ab0e70a9)

------------------------------------------------------------------------------------------
Banco de dados - listarTodasDisciplinas
![image](https://github.com/user-attachments/assets/044a3dda-c38d-4a1b-9a53-25f73e9b9b80)

------------------------------------------------------------------------------------------
Insomnia - listarTodasDisciplinas
![image](https://github.com/user-attachments/assets/9a100673-b869-433a-bbb5-52bf4661b8fc)

------------------------------------------------------------------------------------------
Insomnia - buscarDisciplinaPorId
![image](https://github.com/user-attachments/assets/349bfb23-0936-42f2-b375-73298926ebbd)

------------------------------------------------------------------------------------------
Insomnia - deletarDisciplinaPorId
![image](https://github.com/user-attachments/assets/b721e0a9-41ce-4a16-8f9c-d986e96b8706)

------------------------------------------------------------------------------------------
Banco de dados - deletarDisciplinaPorId
![image](https://github.com/user-attachments/assets/74095313-39e7-46bd-8dfc-3a06e229d3b6)

------------------------------------------------------------------------------------------
Insomnia - atualizarDisciplinaPorId
listando
![image](https://github.com/user-attachments/assets/df902390-78f6-4c46-9946-752945bd4b59)
atualizando
![image](https://github.com/user-attachments/assets/f43f6359-755d-4af7-8a67-dc252bea7397)

------------------------------------------------------------------------------------------
Banco de dados - atualizarDisciplinaPorId
![image](https://github.com/user-attachments/assets/8b8506a6-94c6-4e18-9f61-a00d746e7cab)

------------------------------------------------------------------------------------------
Insomnia - trancarMatricula
![image](https://github.com/user-attachments/assets/3997644b-7704-428f-84db-5916c7613032)

------------------------------------------------------------------------------------------
Banco de dados - trcancarMatricula
![image](https://github.com/user-attachments/assets/469c2166-eec7-4c60-9023-8b0d1b35e69c)

------------------------------------------------------------------------------------------
Documentação - Swagger
![image](https://github.com/user-attachments/assets/4bacb196-c696-45d9-a959-623b46e702df)
![image](https://github.com/user-attachments/assets/fbc69551-ce32-433d-b797-c7012006a1eb)
![image](https://github.com/user-attachments/assets/c5632fa0-4e43-4173-95b3-97a4f4370f1a)


































