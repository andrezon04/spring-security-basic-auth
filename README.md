<div align="center">

# Autenticação com Spring Security

Um laboratório de HTTP Basic, BCrypt e controle de acesso.

  <img alt="Java 25" src="https://img.shields.io/badge/Java%2025-1a1b27?style=flat-square" />
  <img alt="Spring Security" src="https://img.shields.io/badge/Spring%20Security-1a1b27?style=flat-square" />
  <img alt="MySQL" src="https://img.shields.io/badge/MySQL-1a1b27?style=flat-square" />

**Projeto de estudo**

</div>

---

Projeto de estudo de autenticação HTTP Basic, persistência de usuários e codificação de senhas com BCrypt.

## Foco técnico

- Configuração inicial de HTTP Basic com Spring Security.
- Codificação de senhas com BCrypt e carregamento de usuários pelo e-mail.
- Estudo de perfis de acesso; a restrição administrativa ainda requer o ajuste descrito ao final.

## Tecnologias

Java 25, Spring Boot 4.0.5, Spring Security, Spring Data JPA, MySQL, Lombok e Maven Wrapper.

## O que está implementado

- Cadastro de usuário com nome, e-mail e senha.
- Persistência em MySQL.
- Hash de senha com BCrypt.
- Carregamento de usuários pelo e-mail.
- Autenticação HTTP Basic.
- Perfis `ROLE_USER` e `ROLE_ADMIN`; o cadastro atribui `ROLE_USER`.

## Como executar

1. Instale o JDK 25 e disponibilize o MySQL.
2. Clone o repositório:

```bash
git clone https://github.com/andrezon04/spring-security-basic-auth.git
cd spring-security-basic-auth
```

3. Crie o banco:

```sql
CREATE DATABASE db_security;
```

4. Configure a conexão. Exemplo em Bash:

```bash
export SPRING_DATASOURCE_URL='jdbc:mysql://localhost:3306/db_security'
export SPRING_DATASOURCE_USERNAME='seu_usuario'
export SPRING_DATASOURCE_PASSWORD='sua_senha'
bash mvnw spring-boot:run
```

No Windows, configure as mesmas variáveis no seu terminal e use `mvnw.cmd spring-boot:run`.

## Endpoints

| Método | Rota | Comportamento atual |
| --- | --- | --- |
| POST | `/users` | Cadastro liberado pela configuração atual. |
| GET | `/users/admin` | Rota do controller; requer autenticação pela regra geral atual. |

Exemplo de cadastro:

```bash
curl -X POST http://localhost:8080/users \
  -H 'Content-Type: application/json' \
  -d '{"name":"Pessoa de exemplo","email":"pessoa@example.com","pass":"senha-local-de-exemplo"}'
```

Para enviar credenciais HTTP Basic sem escrevê-las na URL, use `curl -u pessoa@example.com http://localhost:8080/users/admin`; o curl solicitará a senha.

## Estado do estudo

A configuração declara uma restrição de administrador para `/admin`, enquanto o controller expõe `/users/admin`. Portanto, a rota do controller ainda não aplica a restrição por perfil pretendida.

O cadastro atualmente retorna a entidade persistida, incluindo o hash da senha. O CSRF está desabilitado. Esses pontos precisam ser revistos antes de utilizar a aplicação fora de um ambiente de estudo.

Próximos passos: alinhar as rotas de autorização, retornar um DTO sem a senha, tratar usuário inexistente, validar os dados e adicionar testes de autenticação e autorização.

## Verificação

```bash
bash mvnw test
```

Os testes Spring dependem da configuração de banco de dados. A existência de um teste de contexto não comprova, por si só, o funcionamento das regras de autorização.
