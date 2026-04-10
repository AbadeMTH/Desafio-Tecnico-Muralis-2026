## Estrutura do Projeto

#### Esse projeto é um sistema web focado no gerenciamento de uma agenda. Os principais objetivos do projeto são:
- Permitir o cadastro, consulta, edição e deleção de Clientes e seus Contatos
- Mostrar de forma simples e intuíva esses dados corretamente

#### O projeto utiliza das seguintes tecnologias:
- Front-end
  - HTML, CSS e JavaScript
- Back-end
  - Java e  SpringBoot
- Banco de Dados
  - MySQL
- Docker

#### Para garantir que o projeto rode facilmente em qualquer máquina sem grandes dificuldades decidi utilizar o docker para resolver esse problema.

#### Na arquitetura do back-end, utilizei uma arquitetura em camadas para melhor organização do código e separar as responsabilidades da API

## Estrutura de Pastas
```text
.
├── DesafioTecnicoMuralis2026/
│   └── src/
│       ├── main/
│       │   ├── java/
│       │   │   └── com.matheus.desafiotecnicomuralis/
│       │   │       ├── controller/
│       │   │       │   ├── cliente/
│       │   │       │   │   └── ClienteController  # Todos end points relacionados à Cliente
│       │   │       │   └── contato/
│       │   │       │       └── ContatoController  # Todos end points relacionados à Contato
│       │   │       ├── dto/
│       │   │       │   ├── cliente/
│       │   │       │   │   └── ClienteDTO  # Objeto para transferência de dados entre camadas relacionado à Cliente        
│       │   │       │   └── contato/
│       │   │       │       └── ContatoDTO  # Objeto para transferência de dados entre camadas relacionado à Contato
│       │   │       ├── entity/
│       │   │       │   ├── cliente/
│       │   │       │   │   └── ClienteEntity  # Entidade Cliente do banco de dados relacionada a tb_clientes
│       │   │       │   └── contato/
│       │   │       │       └── ContatoEntity  # Entidade Cliente do banco de dados relacionada a tb_contatos
│       │   │       ├── mapper/
│       │   │       │   ├── cliente/
│       │   │       │   │   └── ClienteMapper  # Mapper que transfere dados de um ClienteDTO para um ClienteEntity, e o inverso
│       │   │       │   └── contato/
│       │   │       │       └── ContatoMapper  # Mapper que transfere dados de um ContatoDTO para um ContatoEntity, e o inverso
│       │   │       ├── repository/
│       │   │       │   ├── cliente/
│       │   │       │   │   └── ClienteRepository  # Interface que herda o repositório JPA para consultas na tabela tb_clientes do banco
│       │   │       │   └── contato/
│       │   │       │       └── ContatoRepository  # Interface que herda o repositório JPA para consultas na tabela tb_contatos do banco
│       │   │       ├── service/
│       │   │       │   ├── cliente/
│       │   │       │   │   └── ClienteService  # Regras de negócios relacionadas à Cliente e usa o repository
│       │   │       │   └── contato/
│       │   │       │       └── ContatoService  # Regras de negócios relacionadas à Contato e usa o repository
│       │   │       └── DesafioTecnicoMuralisApplication
│       │   └── resources/
│       │       ├── static/
│       │       │   ├── telaDetalhes/
│       │       │   │   ├── detalhes.html  # Tela que mostra detalhes do Cliente e seus Contatos, e ações relacionadas
│       │       │   │   ├── estilo.css  # Arquivo de estilos
│       │       │   │   └── script.js  # Arquivo de script
│       │       │   ├── estilo.css  # Arquivo de estilos
│       │       │   ├── index.html  # Tela inicial que mostra todos Clientes, ações relacionadas e criação
│       │       │   └── script.js  # Arquivo de script
│       │       ├── templates
│       │       └── application.properties  # Arquivo de configurações da aplicação
│       └── test/
│           └── java/
│               └── com.matheus.desafiotecnicomuralis/
├── .env  # Arquivo para variáveis de ambiente
├── docker-compose.yml  # Arquivo para "orquestrar" diversos containers, com configurações para isso
├── Dockerfile  # Arquivo que armazena configurações para gerar uma imagem da aplicação
├── pom.xml  # Arquivo que armazena todas dependências da aplicação e dados essenciais
└── README.md  # Arquivo de documentação do projeto
```

## Dependências do Projeto

- Spring Web
- Lombok
- Spring Data JPA
- MySQL Driver
- Spring Boot DevTools
- Swagger

## Instrução para Instalação e Execução

1. Baixe e instale o Docker: https://www.docker.com/get-started/
2. Clone o repositório através do prompt de comando (CMD ou outro) em uma pasta de sua preferência (ou baixe o arquivo .ZIP):
   ```git
   git clone https://github.com/AbadeMTH/Desafio-Tecnico-Muralis-2026.git
   ````
3. Abra o projeto no seu editor de código de preferência (recomendo IntelliJ IDEA)
4. Na raiz do projeto, ```Desafio-Tecnico-Muralis-2026/```, crie um arquivo com nome ```.env```
5. Dentro desse arquivo, defina as variáveis de ambiente necessárias:
    ```text
    MYSQL_DOCKER_USER=root
    MYSQL_DOCKER_DB=jdbc:mysql://database:3306/agenda
   ```
6. Salve o arquivo
7. Abra seu Docker
8. Abra o prompt de comando (CMD ou outro) no caminho raiz de onde clonou o projeto, por exemplo: ```C:\Users\muralis\Desafio-Tecnico-Muralis-2026```
9. Rode o comando:
    ```docker
    docker-compose up --build
   ```
10. Aguarde o Docker subir a imagem, pode levar algumas tentativas
11. Após o docker-compose ser concluído, acesse a URL no navegador: http://localhost:8080
12. Para visualização da documentação dos end-points gerada pelo Swagger, acesse a URL no navegador: http://localhost:8080/swagger-ui.html
12. Caso queira finalizar o projeto, no prompt de comando (CMD ou outro) que rodou o comando do docker, pressione simultaneamente as teclas:
    ```CTRL + C```

13. **OBSERVAÇÃO: O MySQL está mapeado para a porta 3306, caso deseje acessar o banco de dados localmente**
   
## Checklist de Implementação
- Cadastro, leitura, edição e deleção de Clientes ✅
- Cadastro, leitura, edição e deleção de Contatos ✅
- Validação de dados ✅
- Cumprimento dos requisitos ✅
- Responsividade mínima ✅
- Containerização ✅

## Uso de IA no Desenvolvimento

Durante o desenvolvimento do back-end, pouquíssimas vezes tive de recorrer a utilização da IA.
Tentei utilizar somente de meu conhecimento, que se mostrou não suficiente ao tentar implementar o end point de PATCH.

Utilizei para aprender sobre o método http PATCH, que ainda não tinha tido contato, e após compreender, pude tentar implementar por conta própria.
Além disso, tive alguns problemas na hora de dockerizar a aplicação, mas que com muitas tentativas, consegui entender e corrigir.

Já no front-end, que não é nem um pouco meu forte, o GitHub Copilot foi um grande amigo. Nunca tive muito contato com manipulação de DOM, então ele me guiou em boa parte do desenvolvimento do front, mas sempre que aceitava uma sugestão, eu parava para ler o código, entender e ver se fazia sentido.
