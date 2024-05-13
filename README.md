<h1 align="center">
Vaga Programador Java
</h1>

<p align="justify">
Criar uma solução java em formato de API que atenda aos seguintes requisitos para a recepção de pedidos dos clientes:<br>
</p>

Criar um serviço que receba pedidos no formato xml e json com 6 campos:<br>
``` bash
número controle - número aleatório informado pelo cliente.
data cadastro (opcional) 
nome - nome do produto
valor - valor monetário unitário produto
quantidade (opcional) - quantidade de produtos.
codigo cliente - identificação numérica do cliente.
```


<p align="justify">
Critérios aceitação e manipulação do arquivo:
</p>

``` bash
O arquivo pode conter 1 ou mais pedidos, limitado a 10.
Não poderá aceitar um número de controle já cadastrado.
Caso a data de cadastro não seja enviada o sistema deve assumir a data atual.
Caso a quantidade não seja enviada considerar 1.
Caso a quantidade seja maior que 5 aplicar 5% de desconto no valor total, para quantidades a partir de 10 aplicar 10% de desconto no valor total.
O sistema deve calcular e gravar o valor total do pedido.
Assumir que já existe 10 clientes cadastrados, com códigos de 1 a 10.
```

<p align="justify">
Criar um serviço onde possa consultar os pedidos enviados pelos clientes.<br>
Critérios aceitação: <br>
O retorno deve trazer todos os dados do pedido.
</p>

<p align="justify">
filtros da consulta:<br>
número pedido, data cadastro, todos
</p>

<p align="justify">
Frameworks:<br>
Fica a critério do candidato utilizar ou não, sem restrições de escolha.
</p>

<p align="justify">
desejável:<br>
Injeção de dependência<br>
Padrões de projeto<br>
Testes unitários<br><br>

Obrigatório<br>
banco de dados mysql<br>
utilizar framework ORM
</p>


## Requisitos
- Git instalado - [**Download**](https://git-scm.com/downloads).
- JDK 17 instalado - [**Download**](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
- MySQL instalado - [**Download**](https://dev.mysql.com/downloads/installer/).


## Iniciando
``` bash
  # Clonar o projeto:
  $ git clone https://github.com/diasRibeirao/avaliacao-infuse.git

  # Entrar no diretório do projeto:
  $ cd avaliacao-infuse
```

## Banco de Dados
``` bash
  # O banco de dados MySQL deve estar instalado e executando
  # na máquina, ou via Docker Image, com as configurações:
  spring.datasource.url=jdbc:mysql://localhost:3306/infuse_db
  spring.datasource.username=root
  spring.datasource.password=admin
  spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

  # Se preferir, pode alterar as configurações acima para
  # sua preferência para ser executado o prgrama
```

## Executando o Projeto
```bash
  # Instalar as dependências:
  $ mvn clean install 

  # Rodar a aplicação:
  $ mvn spring-boot:run

  # Rodar a aplicação com o CMD:
  $ Executar o seguinte comando: java -jar target/avaliacao-infuse-0.0.1-SNAPSHOT.jar

```


## Documentação dos Endpoints

#### [**http://localhost:8181/swagger-ui/index.html**](http://localhost:8081/api/swagger-ui/index.html)

<br /><br />
Emerson Dias de Oliveira<br />
https://github.com/diasRibeirao
