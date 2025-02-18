# Gerenciador de Tarefas

Este é um sistema de gestão de tarefas desenvolvido com o objetivo de gerenciar, organizar e atualizar tarefas de uma equipe ou indivíduo. O projeto foi construído utilizando **Spring Boot**, **JPA**, **Thymeleaf** e **PostgreSQL** como banco de dados. A aplicação segue a arquitetura **MVC** e inclui funcionalidades essenciais para o controle de tarefas, como criação, atualização, remoção, listagem e conclusão.

## Funcionalidades

A aplicação permite que o usuário realize as seguintes ações:

- **Criar Tarefa:** Criação de uma nova tarefa com título, descrição, responsável, prioridade, prazo e situação.
- **Atualizar Tarefa:** Alteração dos dados de uma tarefa existente.
- **Remover Tarefa:** Excluir uma tarefa do sistema.
- **Listar Tarefas:** Exibição de todas as tarefas, com filtros por responsável e prioridade.
- **Concluir Tarefa:** Atualização do status da tarefa para "Concluída" ou "Em andamento".

## Tecnologias Utilizadas

- **Spring Boot**: Framework para simplificar a criação de aplicações Java.
- **JPA (Java Persistence API)**: Utilizado para persistência dos dados no banco de dados.
- **Thymeleaf**: Motor de template utilizado para renderizar as páginas web.
- **PostgreSQL**: Banco de dados relacional utilizado para armazenar as informações.
- **Arquitetura MVC**: A aplicação segue a arquitetura Model-View-Controller, separando claramente as responsabilidades entre a camada de modelo (dados), a camada de visualização (UI) e a camada de controle (lógica).

## Pré-requisitos

Antes de rodar o projeto, você precisará ter as seguintes ferramentas instaladas:

- **Java 17 ou superior**
- **Spring Boot 3.x**
- **PostgreSQL 16 ou superior**
- **Maven 4.x ou superior**
- **IDE**: IntelliJ Ultimate ou Eclipse com suporte para projetos Spring

## Configuração do Banco de Dados

1. **Instale o PostgreSQL** e crie um banco de dados com o nome de gerenciador_tarefas para a aplicação.
   
2. No arquivo `application.properties`, configure as informações de conexão com o banco de dados:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/gerenciador_tarefas
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
3. Importante: Substitua seu_usuario e sua_senha pelos dados corretos de acesso ao PostgreSQL.

## Como Rodar a Aplicação

### 1. Clone este repositório:

Abra o terminal e execute o seguinte comando para clonar o repositório:

```bash
git clone https://github.com/seu_usuario/gerenciador-de-tarefas.git
```

2. Entre na pasta do projeto:
```
cd gerenciador-de-tarefas
```
3. Execute o comando para rodar a aplicação:
Se estiver usando Maven:

```
mvn spring-boot:run
```
Ou, se preferir, você pode rodar a aplicação diretamente pela sua IDE (IntelliJ ou Eclipse).

4. Acesse a aplicação:
Abra seu navegador e vá para o endereço:
```
http://localhost:8080/index.html
```
5. Você verá a interface inicial, onde pode cadastrar tarefas.
6. Na página seguinte, poderá visualizar as tarefas cadastradas, editar, excluir ou marcar como concluídas.
7. Pode pesquisar para encontrar tarefas especificas, de acordo com os parâmetros informados.


## Estrutura de Banco de Dados
A aplicação utiliza uma tabela tarefas no PostgreSQL com a seguinte estrutura:

```
CREATE TABLE tarefas (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL,
    responsavel VARCHAR(100) NOT NULL,
    prioridade VARCHAR(20) NOT NULL,
    prazo DATE NOT NULL,
    situacao VARCHAR(20) NOT NULL
);
```
## Autor
Vinicius Mariath
