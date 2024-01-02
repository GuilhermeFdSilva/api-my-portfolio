# Api-my-portfolio 🤖

API desenvolvida em Java e Spring, utilizada para comunicação da minha aplicação Front-End ([My portfolio](https://github.com/GuilhermeFdSilva/MyPortfolio)), com meu banco de dados instanciado no AWS.

As dependencias utilizadas são:

[Spring JPA](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa) <br>
[Spring Starter Web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web) <br>
[Spring Security](https://mvnrepository.com/artifact/org.springframework.security/spring-security-crypto) <br>
[MySQL Connector](https://mvnrepository.com/artifact/com.mysql/mysql-connector-j/8.2.0)

## Tecnologias aplicadas 🧑🏽‍💻

<a href="https://docs.oracle.com/en/java/">
  <img src="https://guilhermefdsilva.github.io/read-db-myPortfolio/sticks/stick-java.svg" alt="Java" height="28px">
</a>
<br>
<a href="https://dev.mysql.com/doc/">
  <img src="https://guilhermefdsilva.github.io/read-db-myPortfolio/sticks/stick-mysql.svg" alt="MySQL" height="28px">
</a>

## Só acredito vendo 👀

Para conferir se está tudo certo, você pode acessar um dos seguintes Endpoints:

http://api.francaguilherme.com.br/languages <br>
http://api.francaguilherme.com.br/projects

**Ou**

Rodar o projeto em sua máquina, você vai precisar de:

 - [Intellij](https://www.jetbrains.com/pt-br/idea/download/?section=windows)
     - ou outra IDE de sua preferencia.
 - [MySql Workbench](https://www.mysql.com/products/workbench/)
 - [Postman](https://www.postman.com/downloads/)
     - Ou qualquer ferramenta para fazer requisições para a API.

**1°** - Configure no Workbench um servidor local para seu banco de dados:

- Caso precise de auxílio para configurar seu Workbench, recomendo [este video](https://www.youtube.com/watch?v=jvG4whJuNe0).

**2°** - Clone este repositório.

```
git clone https://github.com/GuilhermeFdSilva/api-my-portfolio.git
```

**3°** - Após abrir o projeto em sua IDE, faça a instalação das dependências.

**4°** - Configure o arquivo ``application.properties``, para os valores de seu servidor local.
- Em: _spring.datasource.url_ coloque jdbc:mysql://localhost:{Porta utilizada}/{Nome do schema};
- Em: _spring.datasource.username_ coloque o nome do usuário administrador do banco de dados;
- Em: _spring.datasource.password_ coloque sua senha.

**5°** - Utilizando o Postman ou outra ferramenta, você pode gerenciar e atualize o seu banco de dados! Lembre-se que a senha do administrador é definida inicialmente como: admin123.

 ### Contribuições são bem-vindas! 🫱🏽‍🫲🏾
