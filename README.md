# Sistema Hospitalar

## **Descrição**
Este projeto é uma aplicação de gerenciamento hospitalar que permite o controle de **Atendimentos**, **Medicamentos** e **Receitas**. A aplicação foi desenvolvida utilizando arquitetura em camadas e conta com endpoints CRUD para cada um desses recursos.

## **Arquitetura**
A aplicação segue o padrão de arquitetura em camadas:
- **Controller:** Camada responsável por lidar com as requisições HTTP e devolver as respostas.
- **Service:** Contém a lógica de negócios.
- **Repository:** Responsável pela interação com o banco de dados.
- **Model:** Representa as entidades no sistema.
- **DTO e Mapper:** Garante a separação entre a entidade de domínio e os dados trafegados na API.

## **Tecnologias Utilizadas**
- **Java 17**
- **Spring Boot**
- **PostgreSQL**
- **Hibernate/JPA**
- **Maven**

## **Execução do Projeto**
### **Pré-requisitos**
- Java 17, Maven e PostgreSQL

### **Passos**
1. Clone o repositório:
   ```bash
   git clone https://github.com/edrikfsteiner/hospital
   cd hospital
   ```
2. Configure o banco de dados no arquivo application.properties localizado em src/main/resources. Substitua as variáveis pelos valores adequados:
   ```
    spring.datasource.url=jdbc:postgresql://localhost:5432/<nome_do_banco>
    spring.datasource.username=<usuario>
    spring.datasource.password=<senha>
   ```
3. Rode o projeto com o Maven:
   ```bash
   mvn spring-boot:run
   ```
4. A aplicação estará disponível em `http://localhost:8080`. Para interagir com os endpoints, você pode usar ferramentas como **Postman**, **Insomnia**, ou comandos `curl`.

## **Endpoints**
### Recurso `atendimentos`

| Descrição                            | URI                          | Método HTTP | Corpo                                                                 | Resposta esperada | Erros esperados                                     |
|--------------------------------------|------------------------------|-------------|-----------------------------------------------------------------------|-------------------|----------------------------------------------------|
| Listar todos os atendimentos         | `/atendimentos`              | `GET`       | Vazio                                                                 | `302 FOUND`          | `404 Not Found` - Não há atendimentos listados   |
| Obter detalhes de um atendimento     | `/atendimentos/{id}`         | `GET`       | Vazio                                                                 | `302 FOUND`          | `404 Not Found` - Atendimento não encontrado      |
| Criar um novo atendimento            | `/atendimentos`              | `POST`      | `{ "paciente": "João", "medico": "Dr. Silva", "data": "2024-11-15" }` | `201 Created`     | `400 Bad Request` - {dado} inválido               |
| Atualizar os dados de um atendimento | `/atendimentos`         | `PUT`       | `{ "paciente": "Maria", "medico": "Dr. Lima", "data": "2024-12-01" }` | `200 OK`          | `404 Not Found` - Atendimento não encontrado      |
| Deletar um atendimento               | `/atendimentos/{id}`         | `DELETE`    | Vazio                                                                 | `200 OK` - Atendimento deletado          | `404 Not Found` - Atendimento não encontrado      |

---

### Recurso `medicamentos`

| Descrição                        | URI                    | Método HTTP | Corpo                                        | Resposta Esperada | Erros esperados                              |
|----------------------------------|------------------------|-------------|----------------------------------------------|-------------------|---------------------------------------------|
| Listar todos os medicamentos     | `/medicamentos`        | `GET`       | Vazio                                        | `200 OK`          | `404 Not Found` - Não há medicamentos listados |
| Obter detalhes de um medicamento | `/medicamentos/{id}`   | `GET`       | Vazio                                        | `200 OK`          | `404 Not Found` - Medicamento não encontrado |
| Criar um novo medicamento        | `/medicamentos`        | `POST`      | `{ "nome": "Paracetamol", "dosagem": "500mg" }` | `201 Created`     | `400 Bad Request` - {dado} inválido        |
| Atualizar os dados de um medicamento | `/medicamentos` | `PUT`       | `{ "nome": "Dipirona", "dosagem": "1g" }` | `200 OK`          | `404 Not Found` - Medicamento não encontrado |
| Deletar um medicamento           | `/medicamentos/{id}`   | `DELETE`    | Vazio                                        | `200 OK` - Medicamento deletado          | `404 Not Found` - Medicamento não encontrado |

---

### Recurso `receitas`

| Descrição                     | URI                      | Método HTTP | Corpo                                                                                              | Resposta Esperada | Erros esperados                                |
|-------------------------------|--------------------------|-------------|----------------------------------------------------------------------------------------------------|-------------------|-----------------------------------------------|
| Listar todas as receitas      | `/receitas`              | `GET`       | Vazio                                                                                              | `200 OK`          | `404 Not Found` - Nenhuma receita encontrada |
| Obter detalhes de uma receita | `/receitas/{id}`         | `GET`       | Vazio                                                                                              | `200 OK`          | `404 Not Found` - Receita não encontrada     |
| Criar uma nova receita        | `/receitas`              | `POST`      | `{ "atendimento": 1, "medicamentos": [1, 2], "instrucoes": "Tomar após as refeições." }`           | `201 Created`     | `400 Bad Request` - {dado} inválido          |
| Atualizar os dados de uma receita | `/receitas`    | `PUT`       | `{ "medicamentos": [3], "instrucoes": "Tomar antes de dormir." }`                                  | `200 OK`          | `404 Not Found` - Receita não encontrada     |
| Deletar uma receita           | `/receitas/{id}`         | `DELETE`    | Vazio                                                                                              | `200 OK` - Receita deletada          | `404 Not Found` - Receita não encontrada     |

---
